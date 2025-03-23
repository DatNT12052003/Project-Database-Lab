package model.course;

import model.person.Teacher;
import model.room.Room;
import model.subject.Subject;

public class Course {
	private String courseid;
	private int currentStudents;
	private String regisStartDate;
	private String courseStartDate;
	private String status;
	private Teacher teacher;
	private Subject subject;
	private Room room;
	
	public Course() {
		super();
	}

	public Course(String courseid, int currentStudents, String regisStartDate, String courseStartDate, String status,
			Teacher teacher, Subject subject, Room room) {
		super();
		this.courseid = courseid;
		this.currentStudents = currentStudents;
		this.regisStartDate = regisStartDate;
		this.courseStartDate = courseStartDate;
		this.status = status;
		this.teacher = teacher;
		this.subject = subject;
		this.room = room;
	}
	
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public String getRegisStartDate() {
		return regisStartDate;
	}

	public void setRegisStartDate(String regisStartDate) {
		this.regisStartDate = regisStartDate;
	}

	public String getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
}
