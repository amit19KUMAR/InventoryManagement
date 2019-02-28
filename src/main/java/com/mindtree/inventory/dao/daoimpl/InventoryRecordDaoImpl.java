package com.mindtree.inventory.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.inventory.dao.InventoryRecordDao;
import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.exceptions.InventoryRecordDaoException;
import com.mindtree.inventory.exceptions.NoRecordsException;
import com.mindtree.inventory.utility.InventorySessionFactory;

@Repository
public class InventoryRecordDaoImpl implements InventoryRecordDao{

	@Autowired
	private InventorySessionFactory isf;
	
	@Override
	public String addToInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			session.save(inventoryRecord);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new InventoryRecordDaoException(e.getMessage(), e.getCause());
		}finally {
			isf.closeSession();
		}
		
		return "Record Added Successfully";
	}

	@Override
	public String updateInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			session.update(inventoryRecord);
			session.getTransaction().commit();	
		} catch (HibernateException e) {
			throw new InventoryRecordDaoException(e.getMessage(), e.getCause());
		}
		return "Record Updated Successfully";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<InventoryRecord> getAllInventoryRecord() throws InventoryRecordDaoException {
		List<InventoryRecord> records = new ArrayList<>();
		try {
			Session session = isf.getSession();
			session.beginTransaction();
			Query query = session.createQuery("from InventoryRecord");
			records = query.list();
		} catch (HibernateException e) {
			throw new InventoryRecordDaoException(e.getMessage(), e.getCause());
		}
		return records;
	}

	@Override
	public String deleteInventoryRecord(int productId) throws InventoryRecordDaoException {
		Session session = isf.getSession();
		try {
			session.beginTransaction();
			InventoryRecord record = session.get(InventoryRecord.class, productId);
			session.delete(record);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new InventoryRecordDaoException(e.getMessage(), e.getCause());
		}finally {
			isf.closeSession();
		}
		
		return "Record Deleted Successfully";
	}
	
	public InventoryRecord getRecordById(int productId) throws NoRecordsException, InventoryRecordDaoException{
		try {
			Session session = isf.getSession();
			session.beginTransaction();
			InventoryRecord record = session.get(InventoryRecord.class, productId);
			return record;
		} catch (HibernateException e) {
			throw new InventoryRecordDaoException(e.getMessage(), e.getCause());
		} finally {
			isf.closeSession();
		}
		
	}
}
