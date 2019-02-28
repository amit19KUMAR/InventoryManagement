package com.mindtree.inventory.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.inventory.dao.InventoryUserDao;
import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;
import com.mindtree.inventory.utility.InventorySessionFactory;


@Repository
public class InventoryUserDaoImpl implements InventoryUserDao{

	@Autowired
	private InventorySessionFactory isf;
	
	
	@Override
	public String addUserToDatabase(User user) throws InventoryUserDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			throw new InventoryUserDaoException(e.getMessage(), e.getCause());
		}finally {
			isf.closeSession();
		}
		return "User Added Successfully";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> getAllUsers() throws InventoryUserDaoException {
		List<User> users = new ArrayList<>();
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from User");
			users = query.list();
		} catch (HibernateException e) {
			throw new InventoryUserDaoException(e.getMessage(),e.getCause());
		}finally {
			isf.closeSession();
		}
		return users;
	}
	
	public String updateUserRole(int userId,String role) throws InventoryUserDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			User user = session.get(User.class, userId);
			user.setRole(role);
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new InventoryUserDaoException(e.getMessage(),e.getCause());
		}finally {
			isf.closeSession();
		}
		return "User role updated successfully";
		
	}
	
	public String updateUser(User user) throws InventoryUserDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new InventoryUserDaoException(e.getMessage(),e.getCause());
		}finally {
			isf.closeSession();
		}
		return "User details updated successfully";
		
	}

}
