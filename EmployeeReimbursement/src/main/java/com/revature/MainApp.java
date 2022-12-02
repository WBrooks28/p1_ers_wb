package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controllers.TicketController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class MainApp {

	public static Logger logger = LoggerFactory.getLogger(MainApp.class);
	
	public static void main(String[] args) {

		Javalin app = Javalin.create().start(9000);
		
		
		//BEFORE & AFTER HANDLERS FOR EACH ENDPOINT CALL
		app.before(ctx -> {
			logger.info("Request at URL " + ctx.url() + " has started.");
		});
		
		app.after(ctx -> {
			logger.info("Request at URL " + ctx.url() + " is now complete.");
		});
		
		//GET methods
		app.get("/test", ctx -> {
			logger.info("Testing app...");
			ctx.html("Test endpoint");
		});
		
		app.get("/users/{id}", UserController.getUserById);

		app.get("/tickets", TicketController.getAllTickets);
		app.get("/tickets/{authorId}", TicketController.getTicketsByAuthorId);
		
		//POST methods
		app.post("/users/register", UserController.register);
		app.post("/users/login", UserController.login);
		app.post("/logout", UserController.logout);
		
		app.post("/ticket/submit", TicketController.createTicket);
		
		//PUT methods
		app.put("/ticket/{id}", TicketController.updateTicketStatus);
		
		//DELETE methods
		
		
	}

}
