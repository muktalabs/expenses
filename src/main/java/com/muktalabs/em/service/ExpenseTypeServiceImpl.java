package com.muktalabs.em.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muktalabs.em.dao.ExpenseTypeDao;
import com.muktalabs.em.model.ExpenseType;
import com.muktalabs.em.model.User;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    @Autowired
    ExpenseTypeDao expenseTypeDao;
    
    private static final Logger logger = Logger.getLogger(ExpenseTypeServiceImpl.class.getName());

    @Transactional
    public String save(ExpenseType expenseType) {
        logger.info("Saving expenseType : " + expenseType);
        return expenseTypeDao.save(expenseType);
    }
    
    @Transactional
    public String update(ExpenseType expenseType) {
        logger.info("Updating expenseType : " + expenseType);
        return expenseTypeDao.update(expenseType);
    }

    public List<ExpenseType> list(ExpenseType criteria) {
        return expenseTypeDao.list(criteria);
    }

    public ExpenseType getById(String id, User user) {
        return expenseTypeDao.getById(id, user);
    }
    
    public List<ExpenseType> listByExpenseTypeId(String expenseTypeId, User user){
        return expenseTypeDao.listByExpenseTypeId(expenseTypeId, user);
    }

    public String delete(String id, User user) {
        return expenseTypeDao.delete(id, user);
    }

    public List<ExpenseType> list(int startIndex, int pageSize, User user) {
        return expenseTypeDao.list(startIndex, pageSize, user);
    }

}