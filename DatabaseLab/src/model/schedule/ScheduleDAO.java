package model.schedule;

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
import model.person.Teacher;
import model.room.Room;

public class ScheduleDAO {
	public ObservableList<Schedule> getAllSchedules() {
        String sql = "SELECT * FROM schedules where status = 'Using'";
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleid(resultSet.getString("scheduleid"));  
                schedule.setDay(resultSet.getString("day")); 
                schedule.setTimeStart(resultSet.getString("timestart"));
                schedule.setTimeEnd(resultSet.getString("timeend"));  
                
                scheduleList.add(schedule); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
	
	public String generateScheduleId(String day) {
	    int count = 0;
	    String d = "";

	    switch (day) {
	        case "Monday":
	            d = "MON";
	            break;
	        case "Tuesday":
	            d = "TUE";
	            break;
	        case "Wednesday":
	            d = "WED";
	            break;
	        case "Thursday":
	            d = "THU";
	            break;
	        case "Friday":
	            d = "FRI";
	            break;
	        case "Saturday":
	            d = "SAT";
	            break;
	        case "Sunday":
	            d = "SUN";
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid day: " + day);
	    }

	    String sql = "SELECT COUNT(*) FROM schedules WHERE scheduleid LIKE ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, d + "%"); // Fix lỗi LIKE

	        ResultSet result = pstmt.executeQuery();
	        if (result.next()) {
	            count = result.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Nếu muốn bắt đầu từ 1 thay vì 0, tăng count lên 1
	    String scheduleId = d + String.format("%02d", count + 1);

	    return scheduleId;
	}
	
	public void insertSchedule(String day, String timeStart, String timeEnd) {
		String sql = "INSERT INTO schedules (scheduleid, day, timestart, timeend) VALUES (?, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

			    pstmt.setString(1, generateScheduleid(day));
			    pstmt.setString(2, day);
		        pstmt.setString(3, timeStart);
		        pstmt.setString(4, timeEnd);
		        
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
	
	public void updateSchedule(String scheduleid, String day, String timeStart, String timeEnd) {
	    String sql = "UPDATE schedules SET scheduleid = ?, day = ?, timestart = ?, timeend = ? WHERE scheduleid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	    	pstmt.setString(1, generateScheduleid(day));
	        pstmt.setString(2, day);
	        pstmt.setString(3, timeStart);
	        pstmt.setString(4, timeEnd);
	        pstmt.setString(5, scheduleid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Update successful!");
	        } else {
	            System.out.println("Error updating room!");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deleteSchedule(String scheduleid) {
	    String sql = "DELETE FROM schedules WHERE scheduleid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, scheduleid);

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Delete successful!");
	        } else {
	            System.out.println("Error deleting Room! Room not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
    public List<String> getStringSchedules(String stringLike) {
        String sql = "SELECT scheduleid FROM schedules WHERE scheduleid LIKE ?";
        List<String> idList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stringLike + "%");  // Thêm wildcard vào chuỗi tìm kiếm

            try (ResultSet resultSet = pstmt.executeQuery()) {  // Đảm bảo đóng ResultSet
                while (resultSet.next()) {
                    idList.add(resultSet.getString("scheduleid"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idList;
    }
    
    public String generateScheduleid(String day) {
        int count = 1;
        String prefix;

	    switch (day) {
        case "Monday":
        	prefix = "MON";
            break;
        case "Tuesday":
        	prefix = "TUE";
            break;
        case "Wednesday":
        	prefix = "WED";
            break;
        case "Thursday":
        	prefix = "THU";
            break;
        case "Friday":
        	prefix = "FRI";
            break;
        case "Saturday":
        	prefix = "SAT";
            break;
        case "Sunday":
        	prefix = "SUN";
            break;
        default:
            throw new IllegalArgumentException("Invalid day: " + day);
    }

        List<String> idList = getStringSchedules(prefix);
        

        while(count<=idList.size()) {
    		String scheduleid = prefix + String.format("%02d", count);
    		if(!idList.contains(scheduleid)) {
    			return scheduleid;
    		}
    		count++;
    	}

        String sql = "SELECT COUNT(*) FROM schedules WHERE scheduleid LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                count = Math.max(count, result.getInt(1) + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prefix + String.format("%02d", count);
    }
    
	public void updateStatus(String scheduleid, String status) {
	    String sql = "UPDATE schedules SET status = ? WHERE scheduleid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, status);
	        pstmt.setString(2, scheduleid);

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
	
    public ObservableList<Schedule> getSchedulesByRoomid(String roomid) {
    	String sql = """
    		    SELECT s.* FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN rooms AS r ON c.roomid = r.roomid
    		    WHERE r.roomid = ?""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, roomid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    public ObservableList<Schedule> getSchedulesNotInRoomid(String roomid) {
    	String sql = """
    			SELECT * FROM schedules
    			WHERE scheduleid NOT IN
    		    (SELECT s.scheduleid FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN rooms AS r ON c.roomid = r.roomid
    		    WHERE r.roomid = ? AND (c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL))""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, roomid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    public ObservableList<Schedule> getSchedulesNotInRoomidAndCourseid(String roomid, String courseid) {
    	String sql = """
    			SELECT * FROM schedules
    			WHERE scheduleid NOT IN
    		    (SELECT s.scheduleid FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN rooms AS r ON c.roomid = r.roomid
    		    WHERE r.roomid = ? AND (c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL) AND c.courseid = ?)""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, roomid);
	        pstmt.setString(2, courseid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    
    public ObservableList<Schedule> getSchedulesByTeacherid(String teacherid) {
    	String sql = """
    		    SELECT s.* FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN teachers AS t ON c.teacherid = t.teacherid
    		    WHERE t.teacherid = ?""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, teacherid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    public ObservableList<Schedule> getSchedulesNotInTeacherid(String teacherid) {
    	String sql = """
    			SELECT * FROM schedules
    			WHERE scheduleid NOT IN 
    		    (SELECT s.scheduleid FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN teachers AS t ON c.teacherid = t.teacherid
    		    WHERE t.teacherid = ? AND (c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL))""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, teacherid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    public ObservableList<Schedule> getSchedulesNotInTeacheridAndCourseid(String teacherid, String courseid) {
    	String sql = """
    			SELECT * FROM schedules
    			WHERE scheduleid NOT IN 
    		    (SELECT s.scheduleid FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN teachers AS t ON c.teacherid = t.teacherid
    		    WHERE t.teacherid = ? AND (c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL)  AND c.courseid = ?)""";
	    
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, teacherid);
	        pstmt.setString(2, courseid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            while (resultSet.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setScheduleid(resultSet.getString("scheduleid"));  
	                schedule.setDay(resultSet.getString("day")); 
	                schedule.setTimeStart(resultSet.getString("timestart"));
	                schedule.setTimeEnd(resultSet.getString("timeend"));  
	                
	                scheduleList.add(schedule); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return scheduleList;  
	}
    
    
    public ObservableList<Schedule> getSchedulesNotInRoomOrTeacher(String roomid, String teacherid) {
        if (roomid == null || teacherid == null) {
            return getAllSchedules();
        }

        String sql = """
            SELECT * FROM schedules s  
            WHERE NOT EXISTS (
                SELECT 1 FROM course_schedule cs
                INNER JOIN courses c ON cs.courseid = c.courseid
                LEFT JOIN rooms r ON c.roomid = r.roomid
                LEFT JOIN teachers t ON c.teacherid = t.teacherid
                WHERE cs.scheduleid = s.scheduleid
                AND (r.roomid = ? OR t.teacherid = ?)
            )
        """;

        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomid);
            pstmt.setString(2, teacherid);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleid(resultSet.getString("scheduleid"));
                    schedule.setDay(resultSet.getString("day"));
                    schedule.setTimeStart(resultSet.getString("timestart"));
                    schedule.setTimeEnd(resultSet.getString("timeend"));

                    scheduleList.add(schedule);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scheduleList;
    }

	
	public ObservableList<Schedule> getScheduleByCourseid(String courseid) {
        String sql = "select s.* from schedules as s\r\n"
        		+ "inner join course_schedule as cs on s.scheduleid = cs.scheduleid\r\n"
        		+ "inner join courses as c on cs.courseid = c.courseid\r\n"
        		+ "where c.courseid = ?";
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, courseid);
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Schedule schedule = new Schedule();
		                schedule.setScheduleid(resultSet.getString("scheduleid"));  
		                schedule.setDay(resultSet.getString("day")); 
		                schedule.setTimeStart(resultSet.getString("timestart"));
		                schedule.setTimeEnd(resultSet.getString("timeend"));  
		                
		                scheduleList.add(schedule); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return scheduleList;
    }
	
	public ObservableList<Schedule> getScheduleNotInCourse(String courseid) {
        String sql = "SELECT * FROM schedules\r\n"
        		+ "WHERE scheduleid NOT IN\r\n"
        		+ "(select s.scheduleid from schedules as s\r\n"
        		+ "INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid\r\n"
        		+ "INNER JOIN courses AS c ON cs.courseid = c.courseid\r\n"
        		+ "where c.courseid = ? AND (c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL));";
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, courseid);
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {  
		            while (resultSet.next()) {
		                Schedule schedule = new Schedule();
		                schedule.setScheduleid(resultSet.getString("scheduleid"));  
		                schedule.setDay(resultSet.getString("day")); 
		                schedule.setTimeStart(resultSet.getString("timestart"));
		                schedule.setTimeEnd(resultSet.getString("timeend"));  
		                
		                scheduleList.add(schedule); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
        return scheduleList;
    }
}
