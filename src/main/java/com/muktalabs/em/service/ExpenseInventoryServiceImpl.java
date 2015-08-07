package com.muktalabs.em.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muktalabs.em.dao.ExpenseInventoryDao;
import com.muktalabs.em.model.ExpenseInventory;
import com.muktalabs.em.model.User;

@Service
public class ExpenseInventoryServiceImpl implements ExpenseInventoryService {

    @Autowired
    ExpenseInventoryDao expenseInventoryDao;
    
    private static final Logger logger = Logger.getLogger(ExpenseInventoryServiceImpl.class.getName());

    @Transactional
    public String save(ExpenseInventory expenseInventory) {
        logger.info("Saving expenseInventory : " + expenseInventory);
        return expenseInventoryDao.save(expenseInventory);
    }
    
    @Transactional
    public String update(ExpenseInventory expenseInventory) {
        logger.info("Updating expenseInventory : " + expenseInventory);
        return expenseInventoryDao.update(expenseInventory);
    }

    public List<ExpenseInventory> list(ExpenseInventory criteria) {
        return expenseInventoryDao.list(criteria);
    }
    
    public List<ExpenseInventory> listByDates( Date fromDate, Date toDate, User user ){
    	logger.info("List by dates method ServiceImpl");
    	return expenseInventoryDao.listByDates(fromDate,toDate,user);
    
    }

    public ExpenseInventory getById(String inventoryId, User user) {
    	logger.info("get by id method in ServiceImpl");
    	return expenseInventoryDao.getById(inventoryId, user);
    }
    
    public List<ExpenseInventory> listByCompanyId(String companyId, User user){
        return expenseInventoryDao.listByCompanyId(companyId, user);
    }

    public String delete(String inventoryId, User user) {
        return expenseInventoryDao.delete(inventoryId, user);
    }

    public List<ExpenseInventory> list(int startIndex, int pageSize, User user) {
        return expenseInventoryDao.list(startIndex, pageSize, user);
    }

}