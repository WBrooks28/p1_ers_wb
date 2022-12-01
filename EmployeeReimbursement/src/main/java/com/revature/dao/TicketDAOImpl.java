package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Ticket;
import com.revature.util.JDBCConnectionUtil;

public class TicketDAOImpl implements TicketDAO {

	Connection conn;
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public TicketDAOImpl() {
		conn = JDBCConnectionUtil.getConnection();
	}
	

	@Override
	public int createTicket(Ticket ticket) {
		try {
			logger.info("TicketDAOImpl - createTicket()... creating ticket...");
			
			String sql = "INSERT INTO ers_tickets (amount, description, status_id, author_id)VALUES(?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDouble(1, ticket.getAmount());
			pstmt.setString(2, ticket.getDescription());
			pstmt.setInt(3, 3);
			pstmt.setInt(4, ticket.getAuthorId());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next();
			
			logger.info("TicketDAOImpl - createTicket() - new ticket id is " + rs.getInt(1));
			return rs.getInt("ticket_id");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	@Override
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getTickets(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket getTicketById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}