package com.app.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

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
	 */

	
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Transactional 
	public void createUser(User user) {
		log.info("UserService :: createUser");
		userRepository.save(user);
		log.info("UserService :: createUser :: User saved Successfully in Database {}", user.toString());
	}

	public User fetchUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
		log.info("UserService :: fetchUserById :: User fetched Successfully", user.toString());
		return user;
	}

	public List<User> fetchAllUsers() {
		List<User> userList = userRepository.findAll();
		log.info("UserService :: fetchAllUsers {} ", userList);
		List<User> result = userList.stream().sorted(Comparator.comparing(User::getUsername)).toList();
		log.info("UserService :: fetchAllUsers:: Sorted User List according Username {} ", result);
		return result;
	}
	
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Transactional
	public void updateUser(Long id, User inputUser) {
		log.info("UserService ::updateUser {} {} ", id, inputUser.getEmail());
		// User dbUser = userRepository.findById(id).get();
		User dbUser = fetchUserById(id);
		dbUser.setUsername(inputUser.getUsername());
		dbUser.setPassword(inputUser.getPassword());
		dbUser.setEmail(inputUser.getEmail());
		log.info("UserService ::updateUser:: User update successfully ");
		userRepository.save(dbUser);
	}

	
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Transactional
	public void updatePassword(Long id, User userInput) {
		log.info("UserService ::updateUser {} {} ",id, userInput.getEmail());
		User user = userRepository.findById(id).get();
		user.setPassword(userInput.getPassword());
		log.info("UserService ::updateUser:: User password update successfully ");
	}

	// Hard Delete: Permanently Delete the User from DB
	// Soft Delete: We can also do soft delete, just we have to change the Status as
	// Active or Inactive
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Transactional
	public void deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			log.info("UserService :: DeleteUser {}", id);
		} else {
			log.error("User with given ID is not Found");
			throw new RuntimeException("User Not Found");
		}
	}

	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Transactional
	public void deleteUser_another(Long id) {
		if (userRepository.existsById(id)) {
			User user = fetchUserById(id);
			userRepository.delete(user);
			log.info("UserService :: DeleteUser {}", id);
		} else {
			log.error("User with given ID is not Found");
			throw new RuntimeException("User Not Found");
		}
	}

}
