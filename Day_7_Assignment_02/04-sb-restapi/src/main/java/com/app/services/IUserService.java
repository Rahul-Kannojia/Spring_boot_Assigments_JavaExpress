package com.app.services;

import java.util.List;

import com.app.entities.User;

public interface IUserService {

	public void createUser(User user);
	public User fetchUserById(Long id);
	public List<User> fetchAllUsers();
	public void updateUser(Long id, User userInput);
	public void updatePassword(Long id, User userInput);
	public void deleteUser(Long id);
	public void deleteUser_another(Long id);
}
