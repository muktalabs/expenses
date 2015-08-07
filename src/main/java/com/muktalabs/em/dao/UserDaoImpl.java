package com.muktalabs.em.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.muktalabs.em.model.User;


@Service
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    @Autowired
    public void autowireSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    @Override
	public User getUserByUsername(String username) {
    	
		Session session = getSessionFactory().openSession();
		 Transaction tx = null;
		 User user = null;
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 logger.info("User ID - " + username);
			 Query query = session.createQuery("from User where loginId='"+username+"'");
			 
 			 user = (User)query.uniqueResult();
 			 
 			 logger.info("User - " + user);
 			 
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 logger.warning("Error: getUserByUsername() - "+e.getMessage());
		 } finally {
			 session.close();
		 }
		 return user;
	}
	
    
    public String save(User user) {

        if (StringUtils.isEmpty(user.getUserId())) {
            logger.info("Saving new user to db : " + user);
            String uuid = UUID.randomUUID().toString();
            user.setUserId(uuid);
            
            Serializable id = getHibernateTemplate().save(user);
            getHibernateTemplate().flush();
            
            return (String) id;
        } else {
            throw new RuntimeException("Record already exists (primary key present)");
        }
    }
    
    public String update(User user) {

        if (! StringUtils.isEmpty(user.getUserId())) {
            logger.info("Updating user record in db : " + user);
            getHibernateTemplate().saveOrUpdate(user);
            getHibernateTemplate().flush();
            return user.getUserId();
        } else {
            throw new RuntimeException("Record does not exist (primary key not present)");
        }
    }

    public List<User> list(int startIndex, int pageSize, User user ) {

        // TODO: handle startIndex, pageSize
        @SuppressWarnings("unchecked")
        List<User> companyList = (List<User>) getHibernateTemplate().find("from User");
        return companyList;
    }

    public List<User> list(User criteria) {

        // TODO: add criteria
        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) getHibernateTemplate().find("from User");
        return userList;
    }
    
    public List<User> listByCompanyId(String companyId, User user){
        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) getHibernateTemplate().find("from User where companyId=? ", companyId);
        return userList;
    }

    public User getById(String id, User user) {

        User user1 = getHibernateTemplate().get(User.class, id);
        return user1;
    }

    public String delete(String id, User user) {

        User user1 = this.getById(id, user);
        getHibernateTemplate().delete(user1);
        return id;
    }

}
