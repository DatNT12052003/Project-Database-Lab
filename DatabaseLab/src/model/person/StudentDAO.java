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
import model.user.User;

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
	
//    public String generateStudentID() {
//        int count = getCountStudents() + 1; // Đếm số sinh viên hiện có rồi +1
//        return String.format("S%09d", count); // Định dạng thành S000000001
//    }
    
	public ObservableList<Student> getAllStudents() {
        String sql = "SELECT * FROM students WHERE status = 'Studying'";
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentid(resultSet.getString("studentid"));  
                student.setFullName(resultSet.getString("fullname")); 
                student.setDateOfBirth(resultSet.getString("dateofbirth")); 
                student.setGender(resultSet.getString("gender"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));  
                student.setEmail(resultSet.getString("email"));  
                student.setStatus(resultSet.getString("status"));
                
                studentList.add(student); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
	
	public void insertStudent(String fullName, String dateOfBirth, String gender, String address, String phone, String email) {
		String sql = "INSERT INTO students (studentid, fullname, dateofbirth, gender, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, generateStudentID());
		        pstmt.setString(2, fullName);
		        pstmt.setString(3, dateOfBirth);
		        pstmt.setString(4, gender);
		        pstmt.setString(5, address);
		        pstmt.setString(6, phone);
		        pstmt.setString(7, email);
		        
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
	
	public void updateStudent(String studentid, String fullName, String dateOfBirth, String gender, String address, String phone, String email) {
	    String sql = "UPDATE students SET fullName = ?, dateofbirth = ?, gender = ?, address = ?, phone = ?, email = ? WHERE studentid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, fullName);
	        pstmt.setString(2, dateOfBirth);
	        pstmt.setString(3, gender);
	        pstmt.setString(4, address);
	        pstmt.setString(5, phone);
	        pstmt.setString(6, email);
	        pstmt.setString(7, studentid);

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
	
	public void deleteStudent(String studentid) {
	    String sql = "UPDATE students SET status = 'Deleted' WHERE studentid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, studentid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Delete successful!");
	        } else {
	            System.out.println("Error deleting Student! Student not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
    public User studentsJoinUsers(String studentid) {
        String sql = "SELECT u.account FROM students INNER JOIN users AS u ON u.userid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentid);
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
	
	public void updateStatus(String studentid, String status) {
	    String sql = "UPDATE students SET status = ? WHERE studentid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, status);
	        pstmt.setString(2, studentid);

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
	
    public List<String> getAllStudentid() {
        String sql = "SELECT studentid FROM students";
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
    
    public String generateStudentID() {
        int count = 1;

        List<String> idList = getAllStudentid();

        while(count<=idList.size()) {
    		String studentid = String.format("S%09d", count);
    		if(!idList.contains(studentid)) {
    			return studentid;
    		}
    		count++;
    	}

      count = getCountStudents() + 1; // Đếm số sinh viên hiện có rồi +1
      return String.format("S%09d", count); // Định dạng thành S000000001
    }
    
	public ObservableList<Student> getAllStudentByCourseid(String courseid) {
        String sql = "select s.* from students as s where studentid in (select studentid from studies where courseid = ?)";
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

        	pstmt.setString(1, courseid);
        	
        	ResultSet resultSet = pstmt.executeQuery();
        	
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentid(resultSet.getString("studentid"));  
                student.setFullName(resultSet.getString("fullname")); 
                student.setDateOfBirth(resultSet.getString("dateofbirth")); 
                student.setGender(resultSet.getString("gender"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));  
                student.setEmail(resultSet.getString("email"));  
                student.setStatus(resultSet.getString("status"));
                
                studentList.add(student); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
	
	public Student getStudentById(String studentid) {
        String sql = "select * from students where studentid = ?";
        
        Student student = new Student();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

        	pstmt.setString(1, studentid);
        	
        	ResultSet resultSet = pstmt.executeQuery();
        	
            while (resultSet.next()) {
                student.setStudentid(resultSet.getString("studentid"));  
                student.setFullName(resultSet.getString("fullname")); 
                student.setDateOfBirth(resultSet.getString("dateofbirth")); 
                student.setGender(resultSet.getString("gender"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));  
                student.setEmail(resultSet.getString("email"));  
                student.setStatus(resultSet.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
	}
}
