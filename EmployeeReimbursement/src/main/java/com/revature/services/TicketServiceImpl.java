package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.TicketDAO;
import com.revature.dao.TicketDAOImpl;
import com.revature.models.Ticket;

public class TicketServiceImpl implements TicketService {

	private static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	private static TicketDAO ticketDAO;
	
	public TicketServiceImpl() {
		ticketDAO = new TicketDAOImpl();
	}

	@Override
	public boolean createTicket(Ticket ticket) {
		logger.info("TicketServiceImpl: createTicket() called. Creating new ticket...");
		
		int ticketId = ticketDAO.createTicket(ticket);
		return (ticketId !=0) ? true : false;
	}

	@Override
	public boolean updateTicketStatus(int ticketId, int statusId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getTicketByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getTicketByStatus(String username, int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

}