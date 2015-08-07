package com.muktalabs.em.dao;

import java.util.List;

import com.muktalabs.em.model.User;


public interface UserDao {

	public String save(User user);
	public User getUserByUsername(String username);

	public String update(User user);

	public List<User> list(int startIndex, int pageSize, User user);

	public List<User> list(User criteria);

	public List<User> listByCompanyId(String companyId, User user);

	public User getById(String id, User user);

	public String delete(String id, User user);
	
}