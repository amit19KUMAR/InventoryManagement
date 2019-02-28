package com.mindtree.inventory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.inventory.entity.InventoryRecord;
import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.exceptions.InventoryRecordDaoException;
import com.mindtree.inventory.exceptions.InventoryRecordManagerException;
import com.mindtree.inventory.exceptions.InventoryUserDaoException;
import com.mindtree.inventory.exceptions.InventoryUserManagerException;
import com.mindtree.inventory.exceptions.NoExistingUserException;
import com.mindtree.inventory.exceptions.NoRecordsException;
import com.mindtree.inventory.exceptions.NonUniqueEmailexception;
import com.mindtree.inventory.servce.InventoryRecordService;
import com.mindtree.inventory.servce.InventoryServiceUser;
import com.mindtree.inventory.servce.serviceimpl.InventoryRecordServiceImpl;
import com.mindtree.inventory.servce.serviceimpl.InventoryServiceUserImpl;

import ch.qos.logback.core.status.Status;


@RestController
@CrossOrigin
public class InventoryAppController {
	
	@Autowired
	private InventoryServiceUser userService;
	
	@Autowired
	private InventoryRecordService recordService;

	@PostMapping(value="/addUser")
	public String addUserToDatabase(@RequestBody User user) throws InventoryUserDaoException, NonUniqueEmailexception {
		return userService.addUserToDatabase(user);
	}
	
	@GetMapping(value="/getAllUser")
	public List<User> getAllUsers() throws InventoryUserDaoException{
		return userService.getAllUsers();
	}
	
	@PostMapping(value="/getUserByName")
	public List<User> getUserByName(@RequestBody User user) throws InventoryUserDaoException, NoExistingUserException{
		return userService.getUserByName(user.getUserName());
	}
	
	@PostMapping(value="/getUserByEmail/{email}")
	public List<User> getUserByEmail(@PathVariable String email) throws InventoryUserDaoException, NoExistingUserException{
		return userService.getUserByEmail(email);
	}
	
	@PutMapping(value="/updateUserRole/{userId}/{role}")
	public String upadteUserRole(@PathVariable int userId,@PathVariable String role ) throws NoExistingUserException, InventoryUserManagerException {
		return userService.updateUserRole(userId, role);
	}
	
	@PostMapping(value="/addRecord/{role}")
	public ResponseEntity<Map<String, Object>> addRecords(@PathVariable String role,@RequestBody InventoryRecord inventoryRecord) throws InventoryRecordDaoException {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("error","false");
			response.put("body",recordService.addToInventory(role,inventoryRecord));
			response.put("message", "Added to inventory");
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put("error","true");
			response.put("body",null);
			response.put("message", "Failed to add to Inventory : "+e.getMessage());
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/getAllRecords")
	public List<InventoryRecord> getAllRecords() throws InventoryRecordDaoException{
		return recordService.getAllInventoryRecord();
	}
	
	@PostMapping(value="/getRecordById/{productId}")
	public InventoryRecord getRecordById(@PathVariable int productId) throws NoRecordsException, InventoryRecordManagerException{
		return recordService.getRecordById(productId);
	}
	
	@PutMapping(value="/updateRecordStatus/{userName}")
	public String updateRecordStatus(@PathVariable String userName, @RequestBody InventoryRecord record) throws InventoryRecordManagerException {
		return recordService.updateRecordStatus(userName, record);
	}
	
	@PutMapping(value="/updateRecordStatusById/{userName}/{productId}")
	public String updateRecordStatusById(@PathVariable String userName,@PathVariable int productId) throws InventoryRecordManagerException, NoRecordsException {
		return recordService.updateRecordStatusById(userName, productId);
	}
	
	@PutMapping(value="/updateRecord/{role}")
	public ResponseEntity<Map<String, Object>> updateRecord(@PathVariable String role,@RequestBody InventoryRecord record) throws InventoryRecordManagerException, NoRecordsException {

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("error","false");
			response.put("body",recordService.updateRecord(role, record));
			response.put("message", "Record Sent");
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put("error","true");
			response.put("body",null);
			response.put("message", "Failed To Send Record : "+e.getMessage());
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}

	}
	
	@PostMapping(value="/login/{email}/{userPassword}")
	public ResponseEntity<Map<String, Object>> login(@PathVariable String email,@PathVariable String userPassword) throws InventoryUserManagerException {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("error","false");
			response.put("body",userService.login(email,userPassword));
			response.put("message", "Login Successful");
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put("error","true");
			response.put("body",null);
			response.put("message", "Login Unsuccessful : "+e.getMessage());
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}

	}
	
	@GetMapping(value="/getInventory")
	public ResponseEntity<Map<String, Object>> getInventory() throws InventoryRecordManagerException, NoRecordsException{
//		return recordService.getInventory();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("error","false");
			response.put("body",recordService.getInventory());
			response.put("message", "Invetory Fetched");
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put("error","true");
			response.put("body",null);
			response.put("message", "Failed To Fetch from Inventory : "+e.getMessage());
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value="/deleteRecord/{productId}")
	public ResponseEntity<Map<String, Object>> deleteInventory(@PathVariable int productId) throws InventoryRecordManagerException, NoRecordsException{
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response.put("error","false");
			response.put("body",recordService.deleteInventory(productId));
			response.put("message", "Successful Deleted Record");
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put("error","true");
			response.put("body",null);
			response.put("message",e.getMessage());
			response.put("status",String.valueOf(HttpStatus.OK));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
	}

}
