package com.mindtree.inventory.dao;

import java.util.List;

import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.exceptions.InventoryRecordDaoException;
import com.mindtree.inventory.exceptions.NoRecordsException;

public interface InventoryRecordDao {

	String addToInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException;
	
	String updateInventory(InventoryRecord inventoryRecord) throws InventoryRecordDaoException;
	
	List<InventoryRecord> getAllInventoryRecord() throws InventoryRecordDaoException;

	String deleteInventoryRecord(int productId) throws InventoryRecordDaoException;
	
	InventoryRecord getRecordById(int productId) throws NoRecordsException, InventoryRecordDaoException;
}
