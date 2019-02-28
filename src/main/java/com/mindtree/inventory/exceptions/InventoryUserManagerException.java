package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryUserManagerException extends InventoryParentException{

	public InventoryUserManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryUserManagerException(String message) {
		super(message);
	}
}
