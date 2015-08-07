package com.muktalabs.em.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muktalabs.em.dao.UserDao;
import com.muktalabs.em.model.User;
import com.muktalabs.em.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Transactional
    public String save(User user) {
        logger.info("Saving user : " + user);
        return userDao.save(user);
    }
    
    @Override
	public User getByUserName(String name) {
		logger.info("user in service impl :" + name);
		return userDao.getUserByUsername(name);
	}
    
    @Transactional
    public String update(User user) {
        logger.info("Updating user : " + user);
        return userDao.update(user);
    }

    public List<User> list(User criteria) {
        return userDao.list(criteria);
    }

    public User getById(String id, User user) {
        return userDao.getById(id, user);
    }
    
    public List<User> listByCompanyId(String userId, User user){
        return userDao.listByCompanyId(userId, user);
    }

    public String delete(String id, User user) {
        return userDao.delete(id, user);
    }

    public List<User> list(int startIndex, int pageSize, User user) {
        return userDao.list(startIndex, pageSize, user);
    }

}