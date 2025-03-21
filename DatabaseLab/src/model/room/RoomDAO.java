package model.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDAO {
	public ObservableList<Room> getAllRooms() {
        String sql = "SELECT * FROM rooms";
        ObservableList<Room> roomList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomid(resultSet.getString("roomid"));  
                room.setAddress(resultSet.getString("address")); 
                room.setType(resultSet.getString("type"));
                room.setStatus(resultSet.getString("status"));  
                
                roomList.add(room); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
	
	public void insertRoom(String address, String type) {
		String sql = "INSERT INTO rooms (roomid, address, type, status) VALUES (?, ?, ?, 'empty')";
		
	    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	    		if(type.contains("off")) {
			        pstmt.setString(1, generateRoomidOff());
	    		}else {
	    			pstmt.setString(1, generateRoomidOnl());
	    		}
		        pstmt.setString(2, address);
		        pstmt.setString(3, type);
		        
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
	
	public void updateRoom(String roomid, String address, String type, String status) {
	    String sql = "UPDATE rooms SET address = ?, type = ?, status = ? WHERE roomid = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, address);
	        pstmt.setString(2, type);
	        pstmt.setString(3, status);
	        pstmt.setString(4, roomid);

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
	    String sql = "DELETE FROM rooms WHERE roomid = ?";

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
	
	private int getCountRoomOff() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM rooms WHERE roomid LIKE 'OFF%'";
		
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
	
	private int getCountRoomOnl() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM rooms WHERE roomid LIKE 'ONL%'";
		
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
	
    public String generateRoomidOff() {
        int count = getCountRoomOff() + 1; 
        return String.format("OFF%05d", count);
    }
    
    public String generateRoomidOnl() {
        int count = getCountRoomOnl() + 1; 
        return String.format("ONL%05d", count);
    }
	
}
