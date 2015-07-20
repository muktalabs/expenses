package com.muktalabs.em.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.muktalabs.em.model.ExpenseInventory;
import com.muktalabs.em.model.User;



@Service
public class ExpenseInventoryDaoImpl extends HibernateDaoSupport implements ExpenseInventoryDao {

    private static final Logger logger = Logger.getLogger(ExpenseInventoryDaoImpl.class.getName());
    
    @Autowired
    public void autowireSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    private static final SimpleDateFormat    	sdf= new SimpleDateFormat("yyyy-MM-dd");
    
   
    public String save(ExpenseInventory expenseInventory) {

        if (StringUtils.isEmpty(expenseInventory.getInventoryId())) {
            logger.info("Saving new expenseInventory to db : " + expenseInventory);
            String uuid = UUID.randomUUID().toString();
            expenseInventory.setInventoryId(uuid);
            
            Serializable id = getHibernateTemplate().save(expenseInventory);
            getHibernateTemplate().flush();
            
            return (String) id;
        } else {
            throw new RuntimeException("Record already exists (primary key present)");
        }
    }
    
    public String update(ExpenseInventory expenseInventory) {

        if (! StringUtils.isEmpty(expenseInventory.getInventoryId())) {
            logger.info("Updating expenseInventory record in db : " + expenseInventory);
            getHibernateTemplate().saveOrUpdate(expenseInventory);
            getHibernateTemplate().flush();
            return expenseInventory.getInventoryId();
        } else {
            throw new RuntimeException("Record does not exist (primary key not present)");
        }
    }

    public List<ExpenseInventory> list(int startIndex, int pageSize, User user ) {

        // TODO: handle startIndex, pageSize
        @SuppressWarnings("unchecked")
        List<ExpenseInventory> expenseInventoryList = (List<ExpenseInventory>) getHibernateTemplate().find("from ExpenseInventory");
        return expenseInventoryList;
    }
    
    
    public List<ExpenseInventory> listByDates( Date fromDate, Date toDate, User user ) {
        @SuppressWarnings("unchecked")
        List<ExpenseInventory> expenseInventoryList = (List<ExpenseInventory>) getHibernateTemplate().find("from ExpenseInventory where transactionDate between ? and ?",fromDate,toDate);
        logger.info("List by dates method DaoImpl");
        return expenseInventoryList;
    }
    
    

    public List<ExpenseInventory> list(ExpenseInventory criteria) {

        // TODO: add criteria
        @SuppressWarnings("unchecked")
        List<ExpenseInventory> expenseInventoryList = (List<ExpenseInventory>) getHibernateTemplate().find("from ExpenseInventory");
        return expenseInventoryList;
    }
    
    public List<ExpenseInventory> listByCompanyId(String companyId, User user){
        @SuppressWarnings("unchecked")
        List<ExpenseInventory> expenseInventoryList = (List<ExpenseInventory>) getHibernateTemplate().find("from ExpenseInventory where companyId=? ", companyId);
        return expenseInventoryList;
    }

    public ExpenseInventory getById(String id, User user) {

        ExpenseInventory expenseInventory = getHibernateTemplate().get(ExpenseInventory.class, id);
        return expenseInventory;
    }

    public String delete(String id, User user) {

        ExpenseInventory expenseInventory = this.getById(id, user);
        getHibernateTemplate().delete(expenseInventory);
        return id;
    }

}
