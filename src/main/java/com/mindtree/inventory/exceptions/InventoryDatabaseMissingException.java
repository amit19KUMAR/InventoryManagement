package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryDatabaseMissingException extends InventoryParentException{

	public InventoryDatabaseMissingException(String message) {
		super(message);
	}

	public InventoryDatabaseMissingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
