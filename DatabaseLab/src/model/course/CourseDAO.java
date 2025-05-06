package model.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.room.Room;
import model.room.RoomDAO;
import model.schedule.Schedule;
import model.subject.SubjectDAO;
import model.user.User;

public class CourseDAO {
	
	
	public ObservableList<Course> getAllCourses() {
        String sql = "SELECT * FROM courses WHERE status NOT IN ('Canceled', 'Completed') OR status IS NULL";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
        	
            TeacherDAO teacherDAO = new TeacherDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            RoomDAO roomDAO = new RoomDAO();

            while (resultSet.next()) {
                Course course = new Course();

                course.setCourseid(resultSet.getString("courseid"));
                course.setCurrentStudents(resultSet.getInt("currentstudents"));
                course.setRegisStartDate(resultSet.getString("regisstartdate"));
                course.setCourseStartDate(resultSet.getString("coursestartdate"));
                course.setStatus(resultSet.getString("status"));
                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
                
                courseList.add(course); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
	
	private int getCountCourses() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM courses";
		
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
    
    public List<String> getCourseid() {
        String sql = "SELECT courseid FROM courses";
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
    
    public String generateCourseid() {
        int count = 1;

        List<String> idList = getCourseid();

        while(count<=idList.size()) {
    		String courseid = String.format("C%05d", count);
    		if(!idList.contains(courseid)) {
    			return courseid;
    		}
    		count++;
    	}
        
        count = getCountCourses() + 1;

        return String.format("C%05d", count);
    }
    
	public ObservableList<Course> getAllCoursesRegistration() {
        String sql = "SELECT * FROM courses WHERE status = 'Registration'";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
        	
            TeacherDAO teacherDAO = new TeacherDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            RoomDAO roomDAO = new RoomDAO();

            while (resultSet.next()) {
                Course course = new Course();

                course.setCourseid(resultSet.getString("courseid"));
                course.setCurrentStudents(resultSet.getInt("currentstudents"));
                course.setRegisStartDate(resultSet.getString("regisstartdate"));
                course.setCourseStartDate(resultSet.getString("coursestartdate"));
                course.setStatus(resultSet.getString("status"));
                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
                
                courseList.add(course); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
	
	public ObservableList<Course> getAllStudyingCoursesByStudentid(String studentid) {
        String sql = "select c.* from courses as c where courseid in (select courseid from studies where studentid = ? and status = 'Studying');";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, studentid);
		        
                TeacherDAO teacherDAO = new TeacherDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                RoomDAO roomDAO = new RoomDAO();
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Course course = new Course();

		                course.setCourseid(resultSet.getString("courseid"));
		                course.setCurrentStudents(resultSet.getInt("currentstudents"));
		                course.setRegisStartDate(resultSet.getString("regisstartdate"));
		                course.setCourseStartDate(resultSet.getString("coursestartdate"));
		                course.setStatus(resultSet.getString("status"));
		                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
		                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
		                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
		                
		                courseList.add(course); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return courseList;
    }
	
	public ObservableList<Course> getAllTeachingCoursesByTeacherid(String teacherid) {
        String sql = "select * from courses where teacherid = ? and status = 'Ongoing';";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, teacherid);
		        
                TeacherDAO teacherDAO = new TeacherDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                RoomDAO roomDAO = new RoomDAO();
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Course course = new Course();

		                course.setCourseid(resultSet.getString("courseid"));
		                course.setCurrentStudents(resultSet.getInt("currentstudents"));
		                course.setRegisStartDate(resultSet.getString("regisstartdate"));
		                course.setCourseStartDate(resultSet.getString("coursestartdate"));
		                course.setStatus(resultSet.getString("status"));
		                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
		                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
		                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
		                
		                courseList.add(course); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return courseList;
    }
	
	public void insertCourse(String roomid, String teacherid, String subjectid, String regisstartdate, String coursestartdate, String status) {
		String sql = "INSERT INTO courses (courseid, roomid, teacherid, subjectid, currentstudents, regisstartdate, coursestartdate, status) VALUES (?, ?, ?, ?, 0, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, generateCourseid());
		        pstmt.setString(2, roomid);
		        pstmt.setString(3, teacherid);
		        pstmt.setString(4, subjectid);
		        pstmt.setString(5, regisstartdate);
		        pstmt.setString(6, coursestartdate);
		        pstmt.setString(7, status);
		        
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
	
	public Course getCourseById(String courseid) {
    	String sql = "SELECT * FROM courses WHERE courseid = ?";
    	Course course = new Course();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, courseid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	        	if(resultSet.next()) {
	                TeacherDAO teacherDAO = new TeacherDAO();
	                SubjectDAO subjectDAO = new SubjectDAO();
	                RoomDAO roomDAO = new RoomDAO();

	                course.setCourseid(resultSet.getString("courseid"));
	                course.setCurrentStudents(resultSet.getInt("currentstudents"));
	                course.setRegisStartDate(resultSet.getString("regisstartdate"));
	                course.setCourseStartDate(resultSet.getString("coursestartdate"));
	                course.setStatus(resultSet.getString("status"));
	                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
	                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
	                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
	        	}
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return course;  
	}
	
	public ObservableList<Course> getAllCoursesByStudentid(String studentid) {
        String sql = "select c.* from courses as c where courseid in (select courseid from studies where studentid = ?);";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, studentid);
		        
                TeacherDAO teacherDAO = new TeacherDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                RoomDAO roomDAO = new RoomDAO();
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Course course = new Course();


		                course.setCourseid(resultSet.getString("courseid"));
		                course.setCurrentStudents(resultSet.getInt("currentstudents"));
		                course.setRegisStartDate(resultSet.getString("regisstartdate"));
		                course.setCourseStartDate(resultSet.getString("coursestartdate"));
		                course.setStatus(resultSet.getString("status"));
		                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
		                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
		                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
		                
		                courseList.add(course); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return courseList;
    }
	
	public ObservableList<Course> getAllRegisteredCoursesByStudentid(String studentid) {
        String sql = "select c.* from courses as c where courseid in (select courseid from studies where studentid = ? and status = 'Registered');";
        ObservableList<Course> courseList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, studentid);
		        
                TeacherDAO teacherDAO = new TeacherDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                RoomDAO roomDAO = new RoomDAO();
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Course course = new Course();

		                course.setCourseid(resultSet.getString("courseid"));
		                course.setCurrentStudents(resultSet.getInt("currentstudents"));
		                course.setRegisStartDate(resultSet.getString("regisstartdate"));
		                course.setCourseStartDate(resultSet.getString("coursestartdate"));
		                course.setStatus(resultSet.getString("status"));
		                course.setTeacher(teacherDAO.getTeacherById(resultSet.getString("teacherid")));
		                course.setSubject(subjectDAO.getSubjectById(resultSet.getString("subjectid")));
		                course.setRoom(roomDAO.getRoomById(resultSet.getString("roomid")));
		                
		                courseList.add(course); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return courseList;
    }
	
	public void updateCourse(String courseid, String teacherid, String roomid, String regisStartDate, String courseStartDate, String status) {
		String sql = "update courses set teacherid = ?, roomid = ?, regisstartdate =?, coursestartdate = ?, status = ? where courseid = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, teacherid);
		        pstmt.setString(2, roomid);
		        pstmt.setString(3, regisStartDate);
		        pstmt.setString(4, courseStartDate);
		        pstmt.setString(5, status);
		        pstmt.setString(6, courseid);

		        int affectedRows = pstmt.executeUpdate();

		        if (affectedRows > 0) {
		            System.out.println("Update successful!");
		        } else {
		            System.out.println("Error updating course!");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	
	
}
