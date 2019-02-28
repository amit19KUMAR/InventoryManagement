package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryRecordDaoException extends InventoryParentException{

	public InventoryRecordDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryRecordDaoException(String message) {
		super(message);
	}

}
