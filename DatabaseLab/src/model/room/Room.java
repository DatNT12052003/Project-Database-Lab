package model.room;

public class Room {
	private String roomid;
	private String address;
	private String type;
	private String status;
	
	public Room() {
		super();
	}

	public Room(String roomid, String address, String type, String status) {
		super();
		this.roomid = roomid;
		this.address = address;
		this.type = type;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
