package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InvalidDataException extends InventoryParentException{

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(String message) {
		super(message);
	}

}
