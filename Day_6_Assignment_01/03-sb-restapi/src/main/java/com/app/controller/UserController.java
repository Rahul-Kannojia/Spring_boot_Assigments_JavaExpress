package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
import com.app.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserController {

	
	/**
	 * LOGGING:
	 * 
	 * Lombok given a annotation for Logging i.e @Slf4j with the help of this we can
	 * write Log for our code. for logging how many data we want to print that much
	 * {} bracse we have to write
	 * 
	 * Ex: log.info("UserService ::updateUser {} {} ", id, userInput.toString());
	 * Here I am printing two values "id" & "User" info so 2 {} I have written
	 * 
	 * In Logging never print complete Object like Category, User, Product
	 * instead of that just print any field value as per informational related
	 */
	@Autowired
	private UserService userService;

	@PostMapping
	public void createUser(@RequestBody User user) {
		log.info("UserController :: createUser {}", user.getEmail());
		userService.createUser(user);
	}

	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("UserController :: getUserById {}", userId);
		User user = userService.fetchUserById(userId);
		return user;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		log.info("UserController :: getAllUsers");
		return userService.fetchAllUsers();
	}
	
	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser {} {}", userId ,user.getUsername());
		userService.updateUser(userId, user);
	}

	@PatchMapping("{userId}")
	public void updateUserPassword(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUserPassword {} {}", userId ,user.getUsername());
		userService.updatePassword(userId, user);
	}

	@DeleteMapping("{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		log.info("UserController :: deleteUserById {}", userId);
		userService.deleteUser(userId);
	}

}
