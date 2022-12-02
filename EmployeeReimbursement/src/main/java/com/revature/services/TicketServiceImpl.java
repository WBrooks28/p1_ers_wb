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
	public boolean updateTicketStatus(Ticket ticket) {
//		if (ticket.getStatusId() == 3) {
//			logger.info("Cannot update a ticket that has already been processed");
//			return true;
//		} else {
			return ticketDAO.updateTicket(ticket);
//		}
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketDAO.getAllTickets();
	}

	@Override
	public List<Ticket> getTicketsByAuthorId(int authorId) {
		return ticketDAO.getTicketsByAuthorId(authorId);
	}

	@Override
	public List<Ticket> getTicketByStatus(String username, int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
