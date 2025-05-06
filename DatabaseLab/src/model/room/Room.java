package model.room;

import java.util.Objects;

import model.person.Teacher;

public class Room {
	private String roomid;
	private String address;
	private String type;
	private int maxStudents;
	private String status;
	
	public Room() {
		super();
	}

	public Room(String roomid, String address, String type, int maxStudents, String status) {
		super();
		this.roomid = roomid;
		this.address = address;
		this.type = type;
		this.maxStudents = maxStudents;
		this.status = status;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
    @Override
    public String toString() {
        return getRoomid() + " - " + getAddress();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return this.roomid.equals(room.roomid); // So sánh theo ID giáo viên
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomid); // Đảm bảo hashCode phù hợp với equals
    }
	
}
