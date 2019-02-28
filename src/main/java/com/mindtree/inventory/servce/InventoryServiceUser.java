package com.mindtree.inventory.servce;

import java.util.List;

import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;
import com.mindtree.inventory.exceptions.InventoryUserManagerException;
import com.mindtree.inventory.exceptions.NoExistingUserException;
import com.mindtree.inventory.exceptions.NonUniqueEmailexception;

public interface InventoryServiceUser {
	
	String addUserToDatabase(User user) throws InventoryUserDaoException, NonUniqueEmailexception;
	
	List<User> getAllUsers() throws InventoryUserDaoException;
	
	List<User> getUserByName(String userName) throws InventoryUserDaoException, NoExistingUserException;
	
	List<User> getUserByEmail(String email) throws InventoryUserDaoException, NoExistingUserException;
	
	String updateUserRole(int userId,String role) throws NoExistingUserException, InventoryUserManagerException;
	
	String updateUser(User user) throws InventoryUserDaoException;
	
	List<User> getUserById(int userId) throws InventoryUserDaoException, NoExistingUserException;

	User login(String email, String password) throws InventoryUserManagerException;
	
}
