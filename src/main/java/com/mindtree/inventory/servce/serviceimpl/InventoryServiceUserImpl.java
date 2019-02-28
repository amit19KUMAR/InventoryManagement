package com.mindtree.inventory.servce.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.inventory.dao.InventoryUserDao;
import com.mindtree.inventory.dao.daoimpl.InventoryUserDaoImpl;
import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;
import com.mindtree.inventory.exceptions.InventoryUserManagerException;
import com.mindtree.inventory.exceptions.NoExistingUserException;
import com.mindtree.inventory.exceptions.NonUniqueEmailexception;
import com.mindtree.inventory.servce.InventoryServiceUser;

@Service
public class InventoryServiceUserImpl implements InventoryServiceUser{
	
	@Autowired
	private InventoryUserDao userDao;

	@Override
	public String addUserToDatabase(User user) throws InventoryUserDaoException, NonUniqueEmailexception {
		List<User> userlist = getAllUsers();
		for(User u : userlist) {
			if(u.getEmail().equals(user.getEmail())) {
				throw new NonUniqueEmailexception("Email already in use...try another one");
			}
		}
		return userDao.addUserToDatabase(user);
	}

	@Override
	public List<User> getAllUsers() throws InventoryUserDaoException {
		return userDao.getAllUsers();
	}
	
	@Override
	public List<User> getUserByName(String userName) throws InventoryUserDaoException, NoExistingUserException{
		List<User> users = getAllUsers();
		List<User> filteredUser = users.stream()
				.filter(u -> u.getUserName().equals(userName))
				.collect(Collectors.toList());
		
		if(filteredUser.isEmpty())
		{
			throw new NoExistingUserException("No User With Name:"+userName+" Exists.");
		}
		return filteredUser;
	}
	
	@Override
	public List<User> getUserByEmail(String email) throws InventoryUserDaoException, NoExistingUserException{
		List<User> users = getAllUsers();
		List<User> filteredUser = users.stream()
				.filter(u -> u.getEmail().equals(email))
				.collect(Collectors.toList());
		
		if(filteredUser.isEmpty())
		{
			throw new NoExistingUserException("No User With Email:"+email+" Exists.");
		}
		return filteredUser;
	}

	@Override
	public String updateUserRole(int userId, String role) throws  NoExistingUserException, InventoryUserManagerException {
		List<User> users;
		try {
			users = getUserById(userId);
			User user =users.get(0);
			user.setRole(role);
			return userDao.updateUser(user);
		} catch (InventoryUserDaoException e) {
			throw new InventoryUserManagerException(e.getMessage(),e.getCause());
		}
		
	}

	@Override
	public String updateUser(User user) throws InventoryUserDaoException {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> getUserById(int userId) throws InventoryUserDaoException, NoExistingUserException {
		List<User> users = getAllUsers();
		List<User> filteredUser = users.stream()
				.filter(u -> u.getUserId()==userId)
				.collect(Collectors.toList());
		
		if(filteredUser.isEmpty())
		{
			throw new NoExistingUserException("No User With Id:"+userId+" Exists.");
		}
		return filteredUser;
	}

	@Override
	public User login(String email, String password) throws InventoryUserManagerException {
		try {
			List<User> user = getUserByEmail(email);
			if(user.get(0).getUserPassword().equals(password)) {
				return user.get(0);
			}
//			else {
//				return "Incorrect Password";
//			}
		} catch (InventoryUserDaoException | NoExistingUserException e) {
			throw new InventoryUserManagerException(e.getMessage(), e.getCause());
		}
		return null;
		
	}
	
	

}
