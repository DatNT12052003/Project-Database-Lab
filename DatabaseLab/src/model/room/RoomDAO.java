package model.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.person.Teacher;

public class RoomDAO {
	public ObservableList<Room> getAllRooms() {
        String sql = "SELECT * FROM rooms WHERE status IN ('Empty', 'Using')";
        ObservableList<Room> roomList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomid(resultSet.getString("roomid"));  
                room.setAddress(resultSet.getString("address")); 
                room.setType(resultSet.getString("type"));
                room.setMaxStudents(resultSet.getInt("maxstudents"));
                room.setStatus(resultSet.getString("status"));  
                
                roomList.add(room); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
	
	public void insertRoom(String address, String type, int maxStudents) {
		String sql = "INSERT INTO rooms (roomid, address, type, maxstudents) VALUES (?, ?, ?, ?)";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

			    pstmt.setString(1, generateRoomid(type));
		        pstmt.setString(2, address);
		        pstmt.setString(3, type);
		        pstmt.setInt(4, maxStudents);
		        
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
	
	public void updateRoom(String roomid, String address, String type, int maxStudents) {
	    String sql = "UPDATE rooms SET roomid = ?, address = ?, type = ?, maxStudents = ? WHERE roomid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	        pstmt.setString(1, generateRoomid(type));
	        pstmt.setString(2, address);
	        pstmt.setString(3, type);
	        pstmt.setInt(4, maxStudents);
	        pstmt.setString(5, roomid);

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
	
	public void deleteRoom(String roomid) {
	    String sql = "UPDATE rooms SET status = 'deleted' WHERE roomid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, roomid);

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
    
    public List<String> getStringRooms(String stringLike) {
        String sql = "SELECT roomid FROM rooms WHERE roomid LIKE ?";
        List<String> idList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stringLike + "%");  // Thêm wildcard vào chuỗi tìm kiếm

            try (ResultSet resultSet = pstmt.executeQuery()) {  // Đảm bảo đóng ResultSet
                while (resultSet.next()) {
                    idList.add(resultSet.getString("roomid"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idList;
    }
    
    public String generateRoomid(String type) {
        int count = 1;
        String prefix;

        switch (type) {
            case "Offline":
                prefix = "OFF";
                break;
            case "Online":
                prefix = "ONL";
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }

        List<String> idList = getStringRooms(prefix);
        

        while(count<=idList.size()) {
    		String roomid = prefix + String.format("%05d", count);
    		if(!idList.contains(roomid)) {
    			return roomid;
    		}
    		count++;
    	}

        String sql = "SELECT COUNT(*) FROM rooms WHERE roomid LIKE ?";
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

        return prefix + String.format("%05d", count);
    }

	public void updateStatus(String roomid, String status) {
	    String sql = "UPDATE rooms SET status = ? WHERE roomid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, status);
	        pstmt.setString(2, roomid);

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
	
    public Room getRoomById(String roomid) {
	    String sql = "SELECT * FROM rooms WHERE roomid = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, roomid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	            if (resultSet.next()) {
	                Room room = new Room();
	                room.setRoomid(resultSet.getString("roomid"));  
	                room.setAddress(resultSet.getString("address")); 
	                room.setType(resultSet.getString("type"));
	                room.setMaxStudents(resultSet.getInt("maxstudents"));
	                room.setStatus(resultSet.getString("status"));  
	                
	                return room;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;  
	}
	
    ////////////////////////////////
	
    public ObservableList<Room> getRoomsByScheduleid(String scheduleid) {
	    String sql = "select r.* from rooms as r\r\n"
	    		+ "where r.roomid in (\r\n"
	    		+ "select c.roomid from courses as c \r\n"
	    		+ "inner join course_schedule as cs on c.courseid = cs.courseid\r\n"
	    		+ "where scheduleid = ?\r\n"
	    		+ ")";
	    
	    ObservableList<Room> roomList = FXCollections.observableArrayList();
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, scheduleid);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {  
	        	while (resultSet.next()) {
	                Room room = new Room();
	                room.setRoomid(resultSet.getString("roomid"));  
	                room.setAddress(resultSet.getString("address")); 
	                room.setType(resultSet.getString("type"));
	                room.setMaxStudents(resultSet.getInt("maxstudents"));
	                room.setStatus(resultSet.getString("status"));  
	                
	                roomList.add(room); 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return roomList;  
	}
}
