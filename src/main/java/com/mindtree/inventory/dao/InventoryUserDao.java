package com.mindtree.inventory.dao;

import java.util.List;

import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;


public interface InventoryUserDao {
	
	String addUserToDatabase(User user) throws InventoryUserDaoException;
	
	List<User> getAllUsers() throws InventoryUserDaoException;
	
	String updateUserRole(int userId,String role) throws InventoryUserDaoException;
	
	String updateUser(User user) throws InventoryUserDaoException;

}
