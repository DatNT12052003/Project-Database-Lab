package model.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubjectDAO {
	public ObservableList<Subject> getAllSubjects() {
        String sql = "SELECT * FROM subjects";
        ObservableList<Subject> subjectList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectid(resultSet.getString("subjectid"));  
                subject.setName(resultSet.getString("name"));   
                subject.setMass(resultSet.getInt("mass"));  
                subject.setTuition(resultSet.getInt("tuition"));  
                
                subjectList.add(subject); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }
	
	public void insertSubject(String subjectid, String name, int mass, int tuition) {
		String sql = "INSERT INTO subjects (subjectid, name, mass, tuition) VALUES ( ?, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, subjectid);
		        pstmt.setString(2, name);
		        pstmt.setInt(3, mass);
		        pstmt.setInt(4, tuition);
		        
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
	
	public void updateSubject(String subjectid, String newSubjectid, String name, int mass, int tuition) {
	    String sql = "UPDATE subjects SET subjectid = ?, name = ?, mass = ?, tuition = ? WHERE subjectid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	    	pstmt.setString(1, newSubjectid);
	        pstmt.setString(2, name);
	        pstmt.setInt(3, mass);
	        pstmt.setInt(4, tuition);
	        pstmt.setString(5, subjectid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Update successful!");
	        } else {
	            System.out.println("Error updating user!");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deleteSubject(String subjectid) {
	    String sql = "DELETE FROM subjects WHERE subjectid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, subjectid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Delete successful!");
	        } else {
	            System.out.println("Error deleting Subject! Subject not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

