package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class NoRecordsException extends InventoryParentException{

	public NoRecordsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRecordsException(String message) {
		super(message);
	}

}
