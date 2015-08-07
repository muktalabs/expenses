package com.muktalabs.em.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.muktalabs.em.controller.json.ResponseCode;
import com.muktalabs.em.model.ExpenseInventory;
import com.muktalabs.em.model.User;
import com.muktalabs.em.service.ExpenseInventoryService;
import com.muktalabs.em.util.LoginCheckUtil;
import com.muktalabs.em.util.StringUtils;


@RestController
@RequestMapping("/expenseinventory/")
public class ExpenseInventoryController {

    @Autowired
    ExpenseInventoryService expenseInventoryService;

    private static final Logger logger = Logger.getLogger(ExpenseInventoryController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getView(@ModelAttribute ExpenseInventory expensetype) {
        return new ModelAndView("list_expensetype_master");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<ExpenseInventory> getAllExpenseInventory(@RequestParam int jtStartIndex, @RequestParam int jtPageSize
            ,HttpSession session) {

        logger.info("Start getExpenseInventory. jtStartIndex=" + jtStartIndex + ", jtPageSize=" + jtPageSize);
        List<ExpenseInventory> response = new ArrayList<ExpenseInventory>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
            response = expenseInventoryService.list(jtStartIndex, jtPageSize, user);       
            return response;
        }
        else{
            ExpenseInventory el = new ExpenseInventory();
            el.error  = "nouser";
            response.add(el);
        }
        return response;
    }
    
    @RequestMapping(value = "/listByDates", method = RequestMethod.GET)
    public @ResponseBody List<ExpenseInventory> getExpenseInventoryByDates(@RequestParam Date fromDate, @RequestParam Date toDate
            ,HttpSession session) {

        logger.info("Start getExpenseInventoryByDate.fromDate=" + fromDate + ", toDate=" + toDate);
        List<ExpenseInventory> response = new ArrayList<ExpenseInventory>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
            response = expenseInventoryService.listByDates(fromDate, toDate, user);       
            return response;
        }
        else{
            ExpenseInventory el = new ExpenseInventory();
            el.error  = "nouser";
            response.add(el);
        }
        return response;
    }

    
    @RequestMapping(value = "/listByCompanyId/{id}", method = RequestMethod.GET)
    public @ResponseBody List<ExpenseInventory> getExpenseInventorysByCompanyId(@PathVariable("id")  String companyId
            ,HttpSession session) {

        logger.info("Start getExpenseInventorys. by InventoryId=" + companyId);
        List<ExpenseInventory> response = new ArrayList<ExpenseInventory>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        try {
            response = expenseInventoryService.listByCompanyId(companyId, user);
            } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while retriving employer Heads: ", ex);         
            }        
        }
        else{
            ExpenseInventory el = new ExpenseInventory();
            el.error  = "nouser";
            response.add(el);
        }
       return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody ExpenseInventory getExpenseInventory(@PathVariable("id") String headID, HttpSession session) {

        logger.info("Start getExpenseInventory. ID=" + headID);
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        return expenseInventoryService.getById(headID, user);
        }
        else{
            ExpenseInventory el =new ExpenseInventory();
            el.error = "nouser";
            return el;
        }        
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createExpenseInventory(@ModelAttribute ExpenseInventory expenseinventory ,BindingResult result,
            HttpSession session) {

        
        logger.info("Start createExpenseInventory: "+expenseinventory);
        ModelAndView response = new ModelAndView("expense_inventory_create");
        boolean check = LoginCheckUtil.loginCheck();
       if(check){
        if (result.hasErrors()) {
            logger.info("Data binding errors: " + result);
            response = new ModelAndView("expense_inventory_create");
            response.addObject("message", "Data binding errors: " + result);
            response.addObject("expenseInventoryData", expenseinventory);
            
        } else {
            try {
           	if(expenseinventory.getInventoryId()!= null && expenseinventory.getInventoryId()!= ""){
           		expenseInventoryService.update(expenseinventory);
           		response = new ModelAndView("expense_inventory_getlist");
            	    
            } else {
            		expenseInventoryService.save(expenseinventory);
            		response = new ModelAndView("expense_inventory_getlist");
            		
            		//expenseInventoryService.save(description);
            	    
            	}
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while saving expensetype: ", ex);
                response = new ModelAndView("expense_inventory_create");
                response.addObject("message","Exception while saving expensetype: ");
                response.addObject("expenseInventoryData", expenseinventory);
                
            }
        }
        return response;
        }
       else {
    	   response = new ModelAndView("signin");
    	   return response;
    }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateExpenseInventory(@ModelAttribute ExpenseInventory expenseinventory, BindingResult result,
            HttpSession session){

        logger.info("Start updateExpenseInventory.");
        String response = ResponseCode.SUCCESS;
        boolean check = LoginCheckUtil.loginCheck();
        if(check){
        if (result.hasErrors()) {
            response = ResponseCode.ERROR + "Binding Errors";
        } else {
            try {
                if(! StringUtils.isUUID(expenseinventory.getInventoryId())){
                    throw new RuntimeException("Incorrect primary key : " + expenseinventory.getInventoryId());
                }
                User user = (User) session.getAttribute("logged_in_user");
                ExpenseInventory existing = expenseInventoryService.getById(expenseinventory.getInventoryId(), user);
               
               
                if (user != null) {
                   // existing.setModifiedBy(user.getUserId());
                    
                }
                //existing.setModifiedOn(new Date());

                expenseInventoryService.update(existing);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while updating expensetype: ", ex);
                response = ResponseCode.ERROR + ex.getMessage();
            }
        }
        return response;
       }
        else{
            return "nouser";
        }
    }

    @RequestMapping(value = "/editordelete", method = RequestMethod.POST)
    public ModelAndView editOrDeleteExpenseInventory(
    		@RequestParam String inventoryId, 
    		@RequestParam String operation, 
    		HttpSession session) {

        logger.info("Start editOrDeleteExpenseInventory. inventoryId="+inventoryId+", operation="+operation);
        ModelAndView response = new ModelAndView("expense_inventory_getlist");
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        try {
        	if("edit".equals(operation)){
        		ExpenseInventory data = expenseInventoryService.getById(inventoryId, user);
        		response = new ModelAndView("expense_inventory_create");
        		response.addObject("expenseInventoryData", data);
        		
        	} else if ("delete".equals(operation)){
        		expenseInventoryService.delete(inventoryId, user);
        		response.addObject("message", ResponseCode.SUCCESS+" 1 ExpenseInventory deleted.");
        	}
            
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while deleting expensetype: ", ex);
            response.addObject("message", ResponseCode.ERROR + ex.getMessage());
        }
        return response;
       
    }
}
