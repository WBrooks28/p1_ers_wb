package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//since this class depends on the DAO implementation class, we will need an instance of DAO here
	private static UserDAO userDAO;
	
	
	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}
	
//	public UserServiceImpl(UserDAOImpl ud) {
//		this.userDAO = ud;
//	}
	
	@Override
	public boolean register(User user) {
		//1. log event
		logger.info("UserServiceImpl::register() called. Creating new user...");
		//2. get the new id number (do dao method call here)
		int id = userDAO.create(user);
		//3. return true if id exists else return false
		logger.info("Received from DAO. New ID: " + id);
				
		return (id != 0) ? true : false;
	}

	@Override
	public boolean login(String username, String password) {
		//1. find user that matches username given
			User target = userDAO.getByUsername(username);
				
			if(target.getUsername().equalsIgnoreCase(username) && target.getPassword().equalsIgnoreCase(password)) {
					return true;
			}
			return false;
	}

	@Override
	public User getById(int id) {
		logger.info("UserService::getUserById() called. Trying to find user ID# "+ id +"...");
		return userDAO.getById(id);
	}

	@Override
	public String getRole(String username) {
		return userDAO.getRole(username);
	}

	@Override
	public int getId(String username) {
		return userDAO.getId(username);
	}

	@Override
	public User getByUsername(String username) {
		logger.info("UserService::getUserByUsername() called. Trying to find user: "+ username +"...");
		return userDAO.getByUsername(username);
	}

}
