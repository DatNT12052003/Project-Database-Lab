package model.course_schedule;

public class CourseSchedule {
	private String courseid;
	private String scheduleid;
	
	public CourseSchedule() {
		super();
	}

	public CourseSchedule(String courseid, String scheduleid) {
		super();
		this.courseid = courseid;
		this.scheduleid = scheduleid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	
}
