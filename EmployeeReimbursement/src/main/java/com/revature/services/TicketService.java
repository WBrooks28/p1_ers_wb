package com.revature.services;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketService {

	boolean createTicket(Ticket ticket);
	
	boolean updateTicketStatus(Ticket ticket);
	
	List<Ticket> getAllTickets();
	List<Ticket> getTicketByUser(String username);
	List<Ticket> getTicketByStatus(String username, int statusId);
	
}
