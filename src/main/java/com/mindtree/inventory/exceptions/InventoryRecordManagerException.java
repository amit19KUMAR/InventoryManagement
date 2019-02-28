package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryRecordManagerException extends InventoryParentException{

	public InventoryRecordManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryRecordManagerException(String message) {
		super(message);
	}

}
