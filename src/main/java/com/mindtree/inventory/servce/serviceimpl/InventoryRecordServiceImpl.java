package com.mindtree.inventory.servce.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.inventory.dao.InventoryRecordDao;
import com.mindtree.inventory.dao.daoimpl.InventoryRecordDaoImpl;
import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InvalidDataException;
import com.mindtree.inventory.exceptions.InventoryRecordDaoException;
import com.mindtree.inventory.exceptions.InventoryRecordManagerException;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;
import com.mindtree.inventory.exceptions.NoExistingUserException;
import com.mindtree.inventory.exceptions.NoRecordsException;
import com.mindtree.inventory.servce.InventoryRecordService;
import com.mindtree.inventory.servce.InventoryServiceUser;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService{
	
	@Autowired
	private InventoryRecordDao recordDao;
	@Autowired
	private InventoryServiceUser userService; 
	
	@Override
	public String addToInventory(String role,InventoryRecord inventoryRecord) throws InventoryRecordDaoException, InvalidDataException {
		//InventoryRecord [productId=0, productName=null, vendor=null, mrp=0.0, batchNum=0, batchDate=null, quantity=0, status=null]
		if(inventoryRecord.getProductName()==null || inventoryRecord.getVendor()==null || inventoryRecord.getMrp()==0.0 || inventoryRecord.getBatchNum()==0 || inventoryRecord.getBatchDate()==null) {
			throw new InvalidDataException("Invalid Entry");
		}
		else {
			if(role.equalsIgnoreCase("Store Manager")) {
				inventoryRecord.setStatus("approved");
			}
			else if(role.equalsIgnoreCase("Department Manager")) {
				inventoryRecord.setStatus("pending");
			}
			return recordDao.addToInventory(inventoryRecord);
		}
		
	}

	@Override
	public String updateInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException {
		return recordDao.updateInventory(inventoryRecord);
	}

	@Override
	public List<InventoryRecord> getAllInventoryRecord() throws InventoryRecordDaoException {
		return recordDao.getAllInventoryRecord();
	}
	
	@Override
	public InventoryRecord getRecordById(int productId) throws NoRecordsException, InventoryRecordManagerException{
		/*List<InventoryRecord> records = getAllInventoryRecord();
		List<InventoryRecord> filteredRecords = records.stream()
				.filter(r -> r.getProductId()==recordId)
				.collect(Collectors.toList());
		
		if(filteredRecords.isEmpty())
		{
			throw new NoRecordsException("No Record With RecordId:"+recordId+" Exists.");
		}
		return filteredRecords;*/
		InventoryRecord record;
		try {
			record = recordDao.getRecordById(productId);
			if(record==null) {
				throw new NoRecordsException("No records with id: "+productId+" available");
			}
			return record;
		} catch (InventoryRecordDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		}
		
	}
	
	@Override
	public String updateRecordStatus(String userName, InventoryRecord record) throws InventoryRecordManagerException {
		try {
			List<User> users = userService.getUserByName(userName);
			
			for(User u: users) {
				if(u.getRole().equalsIgnoreCase("Department Manager")) {
					record.setStatus("pending");
				}
				else if(u.getRole().equalsIgnoreCase("Store Manager")) {
					record.setStatus("approved");
				}
			}
			updateInventory(record);
		} catch (NoExistingUserException |InventoryUserDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		} catch (InventoryRecordDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		}
		return "Status Updated Successfully";
	}
	
	@Override
	public String updateRecordStatusById(String userName,int productId) throws InventoryRecordManagerException, NoRecordsException {
		try {
			List<User> users = userService.getUserByName(userName);
			
			InventoryRecord record = getRecordById(productId);
			
			for(User u: users) {
				if(u.getRole().equalsIgnoreCase("Department Manager")) {
					record.setStatus("pending");
				}
				else if(u.getRole().equalsIgnoreCase("Store Manager")) {
					record.setStatus("approved");
				}
			}
			updateInventory(record);
		} catch (NoExistingUserException |InventoryUserDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		} catch (InventoryRecordDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		}
		return "Status Updated Successfully";
	}

	@Override
	public List<InventoryRecord> getInventory() throws InventoryRecordManagerException, NoRecordsException {
		try {
			List<InventoryRecord> records = getAllInventoryRecord();
			for(InventoryRecord r : records) {
				System.out.println(r.getStatus());
			}
			List<InventoryRecord> pendingRecords = records.stream()
					.filter(i -> i.getStatus().equalsIgnoreCase("pending"))
					.collect(Collectors.toList());
			
			pendingRecords.forEach(e -> {
				System.out.println(e);
			});
			
			if(pendingRecords.isEmpty()) {
				throw new NoRecordsException("No Pending Records Available.");
			}
			return pendingRecords;
		} catch (InventoryRecordDaoException e) {
			throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
		}
		
	}

	@Override
	public String updateRecord(String role, InventoryRecord record) throws InventoryRecordDaoException {

			if(role.equalsIgnoreCase("Store Manager")) {
				record.setStatus("approved");
			}
			else if(role.equalsIgnoreCase("Department Manager")) {
				record.setStatus("pending");
			}
			return updateInventory(record);
	}

	@Override
	public String deleteInventory(int productId) throws InventoryRecordManagerException,NoRecordsException {
		try {
				InventoryRecord record = getRecordById(productId);
				if(record==null) {
					throw new NoRecordsException("No Record With Id :"+productId);
				}
				else {
					return recordDao.deleteInventoryRecord(productId);
				}
			}catch(InventoryRecordDaoException e) {
				throw new InventoryRecordManagerException(e.getMessage(), e.getCause());
			}
	}
}
