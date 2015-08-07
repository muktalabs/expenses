package com.muktalabs.em.service;

import java.util.List;

import com.muktalabs.em.model.User;
import com.muktalabs.em.model.User;

public interface UserService {

    public String save(User user);
    public User getByUserName(String name);
    
    public String update(User user);

    public List<User> list(int startIndex, int pageSize, User user);

    public List<User> list(User criteria);
    
    public List<User> listByCompanyId(String userId, User user);

    public User getById(String leaveId, User user);

    public String delete(String leaveId, User user);

}