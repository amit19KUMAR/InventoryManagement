package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class NonUniqueEmailexception extends InventoryParentException{

	public NonUniqueEmailexception(String message, Throwable cause) {
		super(message, cause);
	}

	public NonUniqueEmailexception(String message) {
		super(message);
	}
	
	
	
}
