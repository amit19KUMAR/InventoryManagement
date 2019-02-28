package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryParentException extends Exception{

	public InventoryParentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryParentException(String message) {
		super(message);
	}

}
