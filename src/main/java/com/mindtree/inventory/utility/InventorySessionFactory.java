package com.mindtree.inventory.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.entity.User;

@Component
public class InventorySessionFactory {
	
	Session session;
	
	private SessionFactory buildSessionFactory() {
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(InventoryRecord.class)
				.buildSessionFactory();
	}
	
	public Session getSession() {
		session = buildSessionFactory().openSession();
		return session;
	}
	
	public void closeSession() {
		session.close();
	}

}
