package com.mindtree.inventory.servce;

import java.util.List;

import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.exceptions.InvalidDataException;
import com.mindtree.inventory.exceptions.InventoryRecordDaoException;
import com.mindtree.inventory.exceptions.InventoryRecordManagerException;
import com.mindtree.inventory.exceptions.NoRecordsException;

public interface InventoryRecordService {
	
	String addToInventory(String role,InventoryRecord inventoryRecord) throws InventoryRecordDaoException, InvalidDataException;
	
	String updateInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException;
	
	List<InventoryRecord> getAllInventoryRecord() throws InventoryRecordDaoException;
	
	InventoryRecord getRecordById(int recordId) throws NoRecordsException, InventoryRecordManagerException;
	
	String updateRecordStatus(String userName, InventoryRecord record) throws InventoryRecordManagerException;
	
	String updateRecordStatusById(String userName,int productId) throws InventoryRecordManagerException, NoRecordsException;

	List<InventoryRecord> getInventory() throws InventoryRecordManagerException, NoRecordsException;

	String updateRecord(String role, InventoryRecord record) throws InventoryRecordDaoException;

	String deleteInventory(int productId) throws NoRecordsException, InventoryRecordManagerException;

}
