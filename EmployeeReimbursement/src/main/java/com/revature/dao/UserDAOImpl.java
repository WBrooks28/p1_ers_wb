package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class UserDAOImpl implements UserDAO {

	Connection conn;
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	//here we are establishing that connection so JDBC can start preparing statements
	public UserDAOImpl() {
		conn = JDBCConnectionUtil.getConnection();
	}
	
	@Override
	public int create(User user) {
		// insert new users into db
		try {
			String sql = "INSERT INTO ers_users (username, password, first_name, last_name, role) VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getFirstName());
		pstmt.setString(4, user.getLastName());
		pstmt.setString(5, "employee");
		
		pstmt.executeUpdate();
		
		ResultSet rs = pstmt.getGeneratedKeys();
		
		rs.next();
		
		logger.info("UserDAOImpl - create() - new user id is " + rs.getInt(1));
		return rs.getInt("user_id");
		
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public User getById(int id) {
		try {
			String sql = "SELECT * FROM ers_users WHERE user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			User target = new User();
			
			while(rs.next()) {
				target.setId(rs.getInt("user_id"));
				target.setUsername(rs.getString("username"));
				target.setPassword(rs.getString("password"));
				target.setFirstName(rs.getString("first_name"));
				target.setLastName(rs.getString("last_name"));
				target.setRole(rs.getString("role"));
			}
			
			return target;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public User getByUsername(String username) {
		try {
			String sql = "SELECT * FROM ers_users WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			User user = new User();
			
			while(rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setRole(rs.getString("role"));
			}
			
			//now that our object's fields have been set, it can be returned that found user 
			return user;
			
		}catch(SQLException sqlEx) {
			logger.error("UserDAOImpl::getByUsername() exception - Message: " + sqlEx.getMessage());
		}
		
		//else if no user is found, return null
		return null;
	}

	@Override
	public String getRole(String username) {
		try {
			String sql = "SELECT role FROM ers_users WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			return rs.getString(1);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Override
	public int getId(String username) {
		try {
			String sql = "SELECT user_id FROM ers_users WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

}
