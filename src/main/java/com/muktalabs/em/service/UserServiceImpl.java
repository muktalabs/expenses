package com.muktalabs.em.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.muktalabs.em.dao.UserDao;
import com.muktalabs.em.dao.UserDaoImpl;
import com.muktalabs.em.model.User;
import com.muktalabs.em.util.ModelUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public int save(User user) {
		return userDao.saveOrUpdate(user);
	}

	@Override
	public List<User> list(int startIndex, int pageSize, String filterColumnName, 
			String filterColumnValue, Field sortColumn, boolean asc) {
		
		List<User> userList = userDao.list(filterColumnName, filterColumnValue);
		
		if(! StringUtils.isEmpty(sortColumn)){
			userList = ModelUtils.sortOn(userList, sortColumn, asc);
		}
		
		return userList;
	}

	@Override
	public List<User> list(User criteria) {
		return userDao.list(criteria);
	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	public int delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public User getByUserName(String name) {
		logger.info("user in service impl :" + name);
		return userDao.getUserByUsername(name);
	}
}