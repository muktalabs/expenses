package com.muktalabs.em.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.muktalabs.em.controller.json.ResponseCode;
import com.muktalabs.em.model.ExpenseType;
import com.muktalabs.em.model.User;
import com.muktalabs.em.service.ExpenseTypeService;
import com.muktalabs.em.util.LoginCheckUtil;
import com.muktalabs.em.util.StringUtils;


@RestController
@RequestMapping("/expensetype/")
public class ExpenseTypeController {

    @Autowired
    ExpenseTypeService expenseTypeService;

    private static final Logger logger = Logger.getLogger(ExpenseTypeController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getView(@ModelAttribute ExpenseType expensetype) {
        return new ModelAndView("list_expensetype_master");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<ExpenseType> getAllExpenseType(@RequestParam int jtStartIndex, @RequestParam int jtPageSize
            ,HttpSession session) {

        logger.info("Start getExpenseType. jtStartIndex=" + jtStartIndex + ", jtPageSize=" + jtPageSize);
        List<ExpenseType> response = new ArrayList<ExpenseType>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
            response = expenseTypeService.list(jtStartIndex, jtPageSize, user);       
            return response;
        }
        else{
            ExpenseType el = new ExpenseType();
            el.error  = "nouser";
            response.add(el);
        }
        return response;
    }
    
    @RequestMapping(value = "/listByExpenseTypeId/{id}", method = RequestMethod.GET)
    public @ResponseBody List<ExpenseType> getExpenseTypesByExpenseTypeId(@PathVariable("id")  String expenseTypeId
            ,HttpSession session) {

        logger.info("Start getExpenseTypes. by expenseTypeId=" + expenseTypeId);
        List<ExpenseType> response = new ArrayList<ExpenseType>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        try {
            response = expenseTypeService.listByExpenseTypeId(expenseTypeId, user);
            } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while retriving employer Heads: ", ex);         
            }        
        }
        else{
            ExpenseType el = new ExpenseType();
            el.error  = "nouser";
            response.add(el);
        }
       return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody ExpenseType getExpenseType(@PathVariable("id") String headID, HttpSession session) {

        logger.info("Start getExpenseType. ID=" + headID);
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        return expenseTypeService.getById(headID, user);
        }
        else{
            ExpenseType el =new ExpenseType();
            el.error = "nouser";
            return el;
        }        
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createExpenseType(@ModelAttribute ExpenseType expensetype ,BindingResult result,
            HttpSession session) {

        String response = ResponseCode.SUCCESS;
        logger.info("Start createExpenseType.");
        boolean check = LoginCheckUtil.loginCheck();
       if(check){
        if (result.hasErrors()) {
            logger.info("Data binding errors: " + result);
            response = ResponseCode.ERROR + "Data binding errors";
        } else {
            try {
           	if(expensetype.getExpenseTypeId()!= null && expensetype.getExpenseTypeId()!= ""){
           		expenseTypeService.update(expensetype);
           //		expenseTypeService.update(description);
            	    
            } else {
            		expenseTypeService.save(expensetype);
            		//expenseTypeService.save(description);
            	    
            	}
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while saving expensetype: ", ex);
                response = ResponseCode.ERROR + ex.getMessage();
            }
        }
        return response;
        }
       else {
        return "nouser";
    }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateExpenseType(@ModelAttribute ExpenseType expensetype, BindingResult result,
            HttpSession session){

        logger.info("Start updateExpenseType.");
        String response = ResponseCode.SUCCESS;
        boolean check = LoginCheckUtil.loginCheck();
        if(check){
        if (result.hasErrors()) {
            response = ResponseCode.ERROR + "Binding Errors";
        } else {
            try {
                if(! StringUtils.isUUID(expensetype.getExpenseTypeId())){
                    throw new RuntimeException("Incorrect primary key : " + expensetype.getExpenseTypeId());
                }
                User user = (User) session.getAttribute("logged_in_user");
                ExpenseType existing = expenseTypeService.getById(expensetype.getExpenseTypeId(), user);
               // existing.update(expensetype);
               
                if (user != null) {
                   // existing.setModifiedBy(user.getUserId());
                    
                }
                //existing.setModifiedOn(new Date());

                expenseTypeService.update(existing);
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
    public ModelAndView editOrDeleteExpenseType(
    		@RequestParam String expenseTypeId, 
    		@RequestParam String operation, 
    		HttpSession session) {

        logger.info("Start editOrDeleteExpenseType. expenseTypeId="+expenseTypeId+", operation="+operation);
        ModelAndView response = new ModelAndView("expensetype_list");;
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        try {
        	if("edit".equals(operation)){
        		ExpenseType data = expenseTypeService.getById(expenseTypeId, user);
        		response = new ModelAndView("expense_type");
        		response.addObject("expenseTypeData", data);
        	} else if ("delete".equals(operation)){
        		expenseTypeService.delete(expenseTypeId, user);
        		response.addObject("message", ResponseCode.SUCCESS+" 1 ExpenseType deleted.");
        	}
            
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while deleting expensetype: ", ex);
            response.addObject("message", ResponseCode.ERROR + ex.getMessage());
        }
        return response;
       
    }
}
