package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Ticket;
import com.revature.services.TicketService;
import com.revature.services.TicketServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

public class TicketController {
	
	private static UserService uServ = new UserServiceImpl();
	private static TicketService tServ = new TicketServiceImpl();
	
	private static Logger logger = LoggerFactory.getLogger(TicketController.class);

	public static Handler createTicket = ctx -> {
		
		logger.info("TicketController: createTicket()... creating ticket...");
		
		String body = ctx.body();
		
		System.out.println(body);
		
		ObjectMapper om = new ObjectMapper();
		Ticket target = om.readValue(body, Ticket.class);
		target.setAuthorId(Integer.parseInt(ctx.cookieStore().get("userIdCookie")));
		
		logger.info("New ticket" + target);
		
		boolean isCreated = tServ.createTicket(target);
		
		if(isCreated == true) {
			ctx.html("New ticket has been submitted");
			ctx.status(HttpStatus.CREATED);
		}else {
			ctx.html("Error during submission. Try again");
			ctx.status(HttpStatus.NO_CONTENT);
		}
		
	};

}