package com.muktalabs.em.service;

import java.util.List;

import com.muktalabs.em.model.Company;
import com.muktalabs.em.model.User;

public interface CompanyService {

    public String save(Company company);
    
    public String update(Company company);

    public List<Company> list(int startIndex, int pageSize, User user);

    public List<Company> list(Company criteria);
    
    public List<Company> listByCompanyId(String companyId, User user);

    public Company getById(String leaveId, User user);

    public String delete(String leaveId, User user);

}