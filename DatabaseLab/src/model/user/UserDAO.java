package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection_database.*;

public class UserDAO {
	
	public User getUserByAccount(String account) {
	    String sql = "SELECT * FROM users WHERE account = ?";
	    User user = null;

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, account);
	        ResultSet resultSet = pstmt.executeQuery();

	        if (resultSet.next()) {
	            user = new User();
	            user.setAccount(resultSet.getString("account"));
	            user.setPassword(resultSet.getString("password"));
	            user.setRole(resultSet.getString("role"));
	            user.setStatus(resultSet.getString("status"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	
	public void insertUser(String userid, String account, String password, String role, String createddate) {
		String sql = "INSERT INTO users (userid, account, password, role, status, createddate) VALUES (?, ?, ?, ?, 'active', ?)";
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, userid);
			pstmt.setString(2, account);
			pstmt.setString(3, password);
			pstmt.setString(4, role);
			pstmt.setString(5, createddate);
			
			int affectedRows = pstmt.executeUpdate();
			
            if (affectedRows > 0) {
                System.out.println("Insert completed!");
            } else {
                System.out.println("Error insert!");
            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
