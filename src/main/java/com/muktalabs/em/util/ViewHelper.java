package com.muktalabs.em.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.muktalabs.em.controller.CompanyController;
import com.muktalabs.em.model.Company;

public class ViewHelper {
	
	@Autowired
	CompanyController companyController;
	
	public ViewHelper() {
	}

	public List<Company> getAllCompanies(HttpSession session){
		
		return companyController.getAllCompanys(0, 20, session);
	}
}

