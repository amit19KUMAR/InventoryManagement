package com.mindtree.inventory.exceptions;

@SuppressWarnings("serial")
public class InventoryUserDaoException extends InventoryParentException{

	public InventoryUserDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryUserDaoException(String message) {
		super(message);
	}

}
