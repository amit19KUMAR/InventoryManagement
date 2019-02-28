package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class NoExistingUserException extends InventoryParentException{

	public NoExistingUserException(String message) {
		super(message);
	}

	public NoExistingUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
