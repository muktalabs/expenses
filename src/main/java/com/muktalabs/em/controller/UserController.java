package com.muktalabs.em.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
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
import com.muktalabs.em.dao.UserDao;
import com.muktalabs.em.model.User;
import com.muktalabs.em.model.User;
import com.muktalabs.em.service.UserService;
import com.muktalabs.em.util.LoginCheckUtil;
import com.muktalabs.em.util.StringUtils;


@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserDao userDao;
	
    @Autowired
    UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    
    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getView(@ModelAttribute User user) {
        return new ModelAndView("list_user_master");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(@RequestParam int jtStartIndex, @RequestParam int jtPageSize
            ,HttpSession session) {

        logger.info("Start getUser. jtStartIndex=" + jtStartIndex + ", jtPageSize=" + jtPageSize);
        List<User> response = new ArrayList<User>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
            response = userService.list(jtStartIndex, jtPageSize, user);       
            return response;
        }
        else{
            User el = new User();
            el.error  = "nouser";
            response.add(el);
        }
        return response;
    }
    
    @RequestMapping(value = "/listByCompanyId/{id}", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsersByCompanyId(@PathVariable("id")  String userId
            ,HttpSession session) {

        logger.info("Start getUsers. by userId=" + userId);
        List<User> response = new ArrayList<User>();
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        try {
            response = userService.listByCompanyId(userId, user);
            } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while retriving employer Heads: ", ex);         
            }        
        }
        else{
            User el = new User();
            el.error  = "nouser";
            response.add(el);
        }
       return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable("id") String headID, HttpSession session) {

        logger.info("Start getUser. ID=" + headID);
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        if(check){
        return userService.getById(headID, user);
        }
        else{
            User el =new User();
            el.error = "nouser";
            return el;
        }        
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute User user, BindingResult result,
            HttpSession session, HttpServletRequest request) {

    	ModelAndView response = new ModelAndView("user_register");
        logger.info("Start createUser: "+user);
        boolean check = LoginCheckUtil.loginCheck();
       if(check){
        if (result.hasErrors()) {
            logger.info("Data binding errors: " + result);
            response = new ModelAndView("user_register");
            response.addObject("message", "Data binding errors: " + result);
            response.addObject("userData", user);
        } else {
            try {
            	if(! user.getPassword().equals(request.getParameter("password2"))){
            		logger.log(Level.WARNING, "Passwords do not match: ");
                    response = new ModelAndView("user_register");
                    response.addObject("message","Passwords do not match: ");
                    response.addObject("userData", user);
            	}
            	
            	if(user.getUserId()!= null && user.getUserId()!= ""){
            		userService.update(user);
            		response = new ModelAndView("index");
            	} else {
            		userService.save(user);
            		response = new ModelAndView("index");
            	}
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while saving user: ", ex);
                response = new ModelAndView("user_register");
                response.addObject("message","Exception while saving user: ");
                response.addObject("userData", user);
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
    public String updateUser(@ModelAttribute User user, BindingResult result,
            HttpSession session){

        logger.info("Start updateUser.");
        String response = ResponseCode.SUCCESS;
        boolean check = LoginCheckUtil.loginCheck();
        if(check){
        if (result.hasErrors()) {
            response = ResponseCode.ERROR + "Binding Errors";
        } else {
            try {
                if(! StringUtils.isUUID(user.getUserId())){
                    throw new RuntimeException("Incorrect primary key : " + user.getUserId());
                }
               // User user = (User) session.getAttribute("logged_in_user");
               User existing = userService.getById(user.getUserId(), user);
               // existing.update(user);
               
                if (user != null) {
                   // existing.setModifiedBy(user.getUserId());
                    
                }
                //existing.setModifiedOn(new Date());

                userService.update(existing);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Exception while updating user: ", ex);
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
    public ModelAndView editOrDeleteUser(
    		@RequestParam String userId, 
    		@RequestParam String operation, 
    		HttpSession session) {

        logger.info("Start editOrDeleteUser. userId="+userId+", operation="+operation);
        ModelAndView response = new ModelAndView("user_list");;
        boolean check = LoginCheckUtil.loginCheck();
        User user = (User) session.getAttribute("logged_in_user");
        try {
        	if("edit".equals(operation)){
        		User data = userService.getById(userId, user);
        		response = new ModelAndView("user_create");
        		response.addObject("userData", data);
        	} else if ("delete".equals(operation)){
        		userService.delete(userId, user);
        		response.addObject("message", ResponseCode.SUCCESS+" 1 User deleted.");
        	}
            
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception while deleting user: ", ex);
            response.addObject("message", ResponseCode.ERROR + ex.getMessage());
        }
        return response;
       
    }
}
