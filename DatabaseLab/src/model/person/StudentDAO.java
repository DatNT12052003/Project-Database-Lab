package model.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection_database.DatabaseConnection;

public class StudentDAO {

	private int getCountStudents() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM students";
		
		try(Connection conn = DatabaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet result = stmt.executeQuery(sql)){
			
			if (result.next()) {
                count = result.getInt(1);
            }

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
    public String generateStudentID() {
        int count = getCountStudents() + 1; // Đếm số sinh viên hiện có rồi +1
        return String.format("S%09d", count); // Định dạng thành S000000001
    }
	
	public void insertStudent(String fullName, String dateOfBirth, String gender, String phone, String email) {
		String sql = "INSERT INTO students (studentid, fullname, dateofbirth, gender, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
		
		
	//	String studentid = generateStudentID();
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, generateStudentID());
		        pstmt.setString(2, fullName);
		        pstmt.setString(3, dateOfBirth);
		        pstmt.setString(4, gender);
		        pstmt.setString(5, phone);
		        pstmt.setString(6, email);
		        
		        int affectedRows = pstmt.executeUpdate();
		        
	            if (affectedRows > 0) {
	                System.out.println("Insert completed!");
	            } else {
	                System.out.println("Error insert!");
	            }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
}
