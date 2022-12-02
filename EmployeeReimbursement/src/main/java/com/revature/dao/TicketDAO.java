package com.revature.dao;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {

	public int createTicket (Ticket ticket);
	
	public List<Ticket> getAllTickets();
	
	public boolean updateTicket(Ticket ticket);
	
	//employee
	public List<Ticket> getTicketsByAuthorId(int authorId);
	
	public Ticket getTicketById(int id);
}
