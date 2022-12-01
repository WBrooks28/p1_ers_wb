package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Login;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.servlet.http.Cookie;

public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private static UserService uServ = new UserServiceImpl();
	
	// handler for registration requests
	public static Handler register = ctx -> {
		
		logger.info("User is making a registration request...");
		
		String body = ctx.body();
		System.out.println(body);
		
		ObjectMapper om = new ObjectMapper();

		User target = om.readValue(body, User.class);
		logger.info("New User: " + target);
		
		boolean isCreated = uServ.register(target);
		
		if(isCreated == true) {
			ctx.html("The new user has been created successfully.");
			ctx.status(HttpStatus.CREATED);
		}else {
			ctx.html("Error during creation. Try again.");
			ctx.status(HttpStatus.NO_CONTENT);
		}
	};
	
	// handler for get user by id
	public static Handler getUserById = ctx -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		User target = uServ.getById(id);
		
		if(target != null && target.getUsername() != null) {
			ctx.json(target);
		}else {
			ctx.html("Error during user search by that id. Try again.");
			ctx.status(HttpStatus.NOT_FOUND);
		}
	};
	
	// handler for user login
	public static Handler login = ctx -> {
		String body = ctx.body();
		
		ObjectMapper om = new ObjectMapper();
		Login target = om.readValue(body, Login.class);
		
		boolean isAuthenicated = uServ.login(target.getUsername(), target.getPassword());
		
		if(isAuthenicated == true) {
			ctx.html("Successful login. Welcome " + target.getUsername());
			
			ctx.cookieStore().set("Auth-Cookie", target.getUsername());
			Cookie auth = new Cookie("Auth-Cookie", target.getUsername());
			ctx.res().addCookie(auth);
			ctx.status(HttpStatus.OK);
		}else {
			ctx.html("Invalid username and/or password. Please try again.");
			ctx.status(HttpStatus.UNAUTHORIZED);
		}
	};
}
