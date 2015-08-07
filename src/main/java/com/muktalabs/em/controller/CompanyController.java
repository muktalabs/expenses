package com.muktalabs.em.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.muktalabs.em.model.Company;
import com.muktalabs.em.model.User;
import com.muktalabs.em.service.CompanyService;
import com.muktalabs.em.util.LoginCheckUtil;
import com.muktalabs.em.util.StringUtils;


@RestController
@RequestMapping("/company/")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    private static final Logger logger = Logger.getLogger(CompanyController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getView(@ModelAttribute Company company) {
        return new ModelAndView("list_company_master");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Company> getAllCompanys(@RequestParam int jtStartIndex, @RequestParam int jtPageSize
            ,HttpSession session) {

        logger.info("Start getCompany. jtStartIndex=" + jtStartIndex + ", jtPageSize=" + jtPageSize);
        List<Company> response = new ArrayList<Company>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
            response = companyService.list(jtStartIndex, jtPageSize, user);       
            return response;
        }
        else{
            Company el = new Company();
            el.error  = "nouser";
            response.add(el);
        }
        return response;
    }
    
    @RequestMapping(value = "/listByCompanyId/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Company> getCompanysByCompanyId(@PathVariable("id")  String companyId
            ,HttpSession session) {

        logger.info("Start getCompanys. by companyId=" + companyId);
        List<Company> response = new ArrayList<Company>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        try {
            response = companyService.listByCompanyId(companyId, user);
            } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while retriving employer Heads: ", ex);         
            }        
        }
        else{
            Company el = new Company();
            el.error  = "nouser";
            response.add(el);
        }
       return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody Company getCompany(@PathVariable("id") String headID, HttpSession session) {

        logger.info("Start getCompany. ID=" + headID);
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        return companyService.getById(headID, user);
        }
        else{
            Company el =new Company();
            el.error = "nouser";
            return el;
        }        
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createCompany(@ModelAttribute Company company, BindingResult result,
            HttpSession session) {

    	ModelAndView response = new ModelAndView("company_create");
        logger.info("Start createCompany.");
        boolean check = LoginCheckUtil.loginCheck();
       if(check){
        if (result.hasErrors()) {
            logger.info("Data binding errors: " + result);
            response = new ModelAndView("company_create");
            response.addObject("message", "Data binding errors: " + result);
            response.addObject("companyData", company);
        } else {
            try {
            	if(company.getCompanyId()!= null && company.getCompanyId()!= ""){
            		companyService.update(company);
            		response = new ModelAndView("company_list");
            	} else {
            		companyService.save(company);
            		response = new ModelAndView("company_list");
            	}
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while saving company: ", ex);
                response = new ModelAndView("company_create");
                response.addObject("message","Exception while saving company: ");
                response.addObject("companyData", company);
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
    public String updateCompany(@ModelAttribute Company company, BindingResult result,
            HttpSession session){

        logger.info("Start updateCompany.");
        String response = ResponseCode.SUCCESS;
        boolean check = LoginCheckUtil.loginCheck();
        if(check){
        if (result.hasErrors()) {
            response = ResponseCode.ERROR + "Binding Errors";
        } else {
            try {
                if(! StringUtils.isUUID(company.getCompanyId())){
                    throw new RuntimeException("Incorrect primary key : " + company.getCompanyId());
                }
                User user = (User) session.getAttribute("logged_in_user");
                Company existing = companyService.getById(company.getCompanyId(), user);
               // existing.update(company);
               
                if (user != null) {
                   // existing.setModifiedBy(user.getUserId());
                    
                }
                //existing.setModifiedOn(new Date());

                companyService.update(existing);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while updating company: ", ex);
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
    public ModelAndView editOrDeleteCompany(
    		@RequestParam String companyId, 
    		@RequestParam String operation, 
    		HttpSession session) {

        logger.info("Start editOrDeleteCompany. companyId="+companyId+", operation="+operation);
        ModelAndView response = new ModelAndView("company_list");;
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        try {
        	if("edit".equals(operation)){
        		Company data = companyService.getById(companyId, user);
        		response = new ModelAndView("company_create");
        		response.addObject("companyData", data);
        	} else if ("delete".equals(operation)){
        		companyService.delete(companyId, user);
        		response.addObject("message", ResponseCode.SUCCESS+" 1 Company deleted.");
        	}
            
        } catch (DataIntegrityViolationException ex) {
            logger.log(Level.WARNING, "Exception while deleting company: ", ex);
            response.addObject("message", ResponseCode.ERROR + "Data exists for company cannot delete it.");
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while deleting company: ", ex);
            response.addObject("message", ResponseCode.ERROR + ex.getMessage());
        }
        return response;
       
    }
}
