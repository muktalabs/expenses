package com.muktalabs.em.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.muktalabs.em.model.ExpenseType;
import com.muktalabs.em.model.User;


@Service
public class ExpenseTypeDaoImpl extends HibernateDaoSupport implements ExpenseTypeDao {

    private static final Logger logger = Logger.getLogger(ExpenseTypeDaoImpl.class.getName());
    
    @Autowired
    public void autowireSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    public String save(ExpenseType expenseType) {

        if (StringUtils.isEmpty(expenseType.getExpenseTypeId())) {
            logger.info("Saving new expenseType to db : " + expenseType);
            String uuid = UUID.randomUUID().toString();
            expenseType.setExpenseTypeId(uuid);
            
            Serializable id = getHibernateTemplate().save(expenseType);
            getHibernateTemplate().flush();
            
            return (String) id;
        } else {
            throw new RuntimeException("Record already exists (primary key present)");
        }
    }
    
    public String update(ExpenseType expenseType) {

        if (! StringUtils.isEmpty(expenseType.getExpenseTypeId())) {
            logger.info("Updating expenseType record in db : " + expenseType);
            getHibernateTemplate().saveOrUpdate(expenseType);
            getHibernateTemplate().flush();
            return expenseType.getExpenseTypeId();
        } else {
            throw new RuntimeException("Record does not exist (primary key not present)");
        }
    }

    public List<ExpenseType> list(int startIndex, int pageSize, User user ) {

        // TODO: handle startIndex, pageSize
        @SuppressWarnings("unchecked")
        List<ExpenseType> expenseTypeList = (List<ExpenseType>) getHibernateTemplate().find("from ExpenseType");
        return expenseTypeList;
    }

    public List<ExpenseType> list(ExpenseType criteria) {

        // TODO: add criteria
        @SuppressWarnings("unchecked")
        List<ExpenseType> expenseTypeList = (List<ExpenseType>) getHibernateTemplate().find("from ExpenseType");
        return expenseTypeList;
    }
    
    public List<ExpenseType> listByExpenseTypeId(String expenseTypeId, User user){
        @SuppressWarnings("unchecked")
        List<ExpenseType> expenseTypeList = (List<ExpenseType>) getHibernateTemplate().find("from ExpenseType where expenseTypeId=? ", expenseTypeId);
        return expenseTypeList;
    }

    public ExpenseType getById(String id, User user) {

        ExpenseType expenseType = getHibernateTemplate().get(ExpenseType.class, id);
        return expenseType;
    }

    public String delete(String id, User user) {

        ExpenseType expenseType = this.getById(id, user);
        getHibernateTemplate().delete(expenseType);
        return id;
    }

}
