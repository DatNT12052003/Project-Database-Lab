package model.schedule;

public class Schedule {
	private String scheduleid;
	private String day;
	private String timeStart;
	private String timeEnd;
	
	public Schedule() {
		super();
	}

	public Schedule(String scheduleid, String day, String timeStart, String timeEnd) {
		super();
		this.scheduleid = scheduleid;
		this.day = day;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public String getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
    @Override
    public String toString() {
        return getScheduleid() + " - " + getDay() + " - " + getTimeStart() + " - " + getTimeEnd();
    }
	
}
