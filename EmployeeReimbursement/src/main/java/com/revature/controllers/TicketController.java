package com.revature.controllers;

import java.util.List;

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
		
		
		ObjectMapper om = new ObjectMapper();
		Ticket target = om.readValue(body, Ticket.class);
		
		String un = ctx.cookieStore().get("Auth-Cookie");
		int authId = uServ.getId(un);
		System.out.println(authId);
		target.setAuthorId(authId);
		
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
	
	public static Handler getAllTickets = ctx -> {
		
		logger.info("TicketController: getAllTicketTickets()... creating ticket...");
		
		String body = ctx.body();
		System.out.println(body);
		String un = ctx.cookieStore().get("User-Cookie");
		String authRole = uServ.getRole(un);
		System.out.println(authRole);
		
		List<Ticket> tickets = tServ.getAllTickets();
		
		if(!tickets.isEmpty()) {
			ctx.json(tickets);
			ctx.status(HttpStatus.OK);
		} else {
			ctx.html("No tickets found.");
			ctx.status(HttpStatus.NOT_FOUND);
		}
		
	};

}
