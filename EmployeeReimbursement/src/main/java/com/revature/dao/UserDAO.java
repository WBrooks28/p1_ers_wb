package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {

	int create(User user);
	
	User getById(int id);
	
	User getByUsername(String username);
	
	String getRole(String username);
	
	int getId(String username);
	
}
