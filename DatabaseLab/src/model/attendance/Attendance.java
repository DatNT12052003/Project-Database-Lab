package model.attendance;

public class Attendance {
	private int  attendanceid;
	private String studyid;
	private String attendanceDate;
	private boolean status;
	
	public Attendance() {
		super();
	}

	public Attendance(int attendanceid, String studyid, String attendanceDate, boolean status) {
		super();
		this.attendanceid = attendanceid;
		this.studyid = studyid;
		this.attendanceDate = attendanceDate;
		this.status = status;
	}

	public int getAttendanceid() {
		return attendanceid;
	}

	public void setAttendanceid(int attendanceid) {
		this.attendanceid = attendanceid;
	}

	public String getStudyid() {
		return studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
