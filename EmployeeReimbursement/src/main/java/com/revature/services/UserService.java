package com.revature.services;

import com.revature.models.User;

public interface UserService {

	public boolean register(User user);
	
	public boolean login(String username, String password);
	
	public User getById(int id);
	
	public User getByUsername(String username);
	
	public String getRole(String username);

    public int getId(String username);
}
