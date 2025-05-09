package model.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.subject.Subject;
import model.user.User;

public class TeacherDAO {

	private int getCountTeachers() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM teachers";
		
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
    
	public ObservableList<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM teachers WHERE status = 'Teaching'";
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherid(resultSet.getString("teacherid"));  
                teacher.setFullName(resultSet.getString("fullname")); 
                teacher.setDateOfBirth(resultSet.getString("dateofbirth")); 
                teacher.setGender(resultSet.getString("gender"));  
                teacher.setAddress(resultSet.getString("address"));
                teacher.setPhone(resultSet.getString("phone"));  
                teacher.setEmail(resultSet.getString("email")); 
                teacher.setExpertise(resultSet.getString("expertise")); 
                teacher.setLevel(resultSet.getString("level")); 
                teacher.setSalary(resultSet.getInt("salary")); 
                teacher.setStatus(resultSet.getString("status"));
                
                teacherList.add(teacher); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }
	
	public void insertTeacher(String fullName, String dateOfBirth, String gender, String address, String phone, String email, String expertise, String level, int salary) {
		String sql = "INSERT INTO teachers (teacherid, fullname, dateofbirth, gender, address, phone, email, expertise, level, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, generateTeacherID());
		        pstmt.setString(2, fullName);
		        pstmt.setString(3, dateOfBirth);
		        pstmt.setString(4, gender);
		        pstmt.setString(5, address);
		        pstmt.setString(6, phone);
		        pstmt.setString(7, email);
		        pstmt.setString(8, expertise);
		        pstmt.setString(9, level);
		        pstmt.setInt(10, salary);
		        
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
	
	public void updateTeacher(String teacherid, String fullName, String dateOfBirth, String gender, String address, String phone, String email, String expertise, String level, int salary) {
	    String sql = "UPDATE teachers SET fullName = ?, dateofbirth = ?, gender = ?, address = ?, phone = ?, email = ?, expertise = ?, level = ?, salary = ? WHERE teacherid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, fullName);
	        pstmt.setString(2, dateOfBirth);
	        pstmt.setString(3, gender);
	        pstmt.setString(4, address);
	        pstmt.setString(5, phone);
	        pstmt.setString(6, email);
	        pstmt.setString(7, expertise);
	        pstmt.setString(8, level);
	        pstmt.setInt(9, salary);
	        pstmt.setString(10, teacherid);

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
	
	public void deleteTeacher(String teacherid) {
	    String sql = "UPDATE teachers SET status = 'Deleted' WHERE teacherid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, teacherid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Delete successful!");
	        } else {
	            System.out.println("Error deleting Teacher! Teacher not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
    public User teachersJoinUsers(String teacherid) {
        String sql = "SELECT u.account FROM teachers INNER JOIN users AS u ON u.userid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, teacherid);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setAccount(resultSet.getString("account"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
	public void updateStatus(String teacherid, String status) {
	    String sql = "UPDATE teachers SET status = ? WHERE teacherid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, status);
	        pstmt.setString(2, teacherid);

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
    
   ///////////////////////////////////////////////////////////// 
    public List<String> getTeacherid() {
        String sql = "SELECT teacherid FROM teachers";
        List<String> idList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        		Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(sql)) {

        	while(resultSet.next()) {
        		idList.add(resultSet.getString(1));
        	}

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idList;
    }
    
    public String generateTeacherID() {
        int count = 1;

        List<String> idList = getTeacherid();

        while(count<=idList.size()) {
    		String teacherid = String.format("T%09d", count);
    		if(!idList.contains(teacherid)) {
    			return teacherid;
    		}
    		count++;
    	}
        
        count = getCountTeachers() + 1;

        return String.format("T%09d", count);
    }
    
    public Teacher getTeacherById(String teacherid) {
	    String sql = "SELECT * FROM teachers WHERE teacherid = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, teacherid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            if (resultSet.next()) {
	                Teacher teacher = new Teacher();
	                teacher.setTeacherid(resultSet.getString("teacherid"));  
	                teacher.setFullName(resultSet.getString("fullname")); 
	                teacher.setDateOfBirth(resultSet.getString("dateofbirth")); 
	                teacher.setGender(resultSet.getString("gender"));  
	                teacher.setAddress(resultSet.getString("address"));
	                teacher.setPhone(resultSet.getString("phone"));  
	                teacher.setEmail(resultSet.getString("email")); 
	                teacher.setExpertise(resultSet.getString("expertise")); 
	                teacher.setLevel(resultSet.getString("level")); 
	                teacher.setSalary(resultSet.getInt("salary")); 
	                teacher.setStatus(resultSet.getString("status"));
	                
	                return teacher;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;  
	}
    
    public ObservableList<Teacher> getTeachersBySubject(String subjectName) {
	    String sql = "SELECT * FROM teachers WHERE expertise = ?";
	    
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, subjectName);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Teacher teacher = new Teacher();
	                teacher.setTeacherid(resultSet.getString("teacherid"));  
	                teacher.setFullName(resultSet.getString("fullname")); 
	                teacher.setDateOfBirth(resultSet.getString("dateofbirth")); 
	                teacher.setGender(resultSet.getString("gender"));  
	                teacher.setAddress(resultSet.getString("address"));
	                teacher.setPhone(resultSet.getString("phone"));  
	                teacher.setEmail(resultSet.getString("email")); 
	                teacher.setExpertise(resultSet.getString("expertise")); 
	                teacher.setLevel(resultSet.getString("level")); 
	                teacher.setSalary(resultSet.getInt("salary")); 
	                teacher.setStatus(resultSet.getString("status"));
	                
	                teacherList.add(teacher); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return teacherList;  
	}
    
    
    ///////////////////
    
    public ObservableList<Teacher> getTeachersByScheduleid(String scheduleid) {
	    String sql = "select t.* from teachers as t\r\n"
	    		+ "where t.teacherid in (\r\n"
	    		+ "select c.teacherid from courses as c \r\n"
	    		+ "inner join course_schedule as cs on c.courseid = cs.courseid\r\n"
	    		+ "where scheduleid = ?\r\n"
	    		+ ")";
	    
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, scheduleid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Teacher teacher = new Teacher();
	                teacher.setTeacherid(resultSet.getString("teacherid"));  
	                teacher.setFullName(resultSet.getString("fullname")); 
	                teacher.setDateOfBirth(resultSet.getString("dateofbirth")); 
	                teacher.setGender(resultSet.getString("gender"));  
	                teacher.setAddress(resultSet.getString("address"));
	                teacher.setPhone(resultSet.getString("phone"));  
	                teacher.setEmail(resultSet.getString("email")); 
	                teacher.setExpertise(resultSet.getString("expertise")); 
	                teacher.setLevel(resultSet.getString("level")); 
	                teacher.setSalary(resultSet.getInt("salary")); 
	                teacher.setStatus(resultSet.getString("status"));
	                
	                teacherList.add(teacher); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return teacherList;  
	}
	
}
