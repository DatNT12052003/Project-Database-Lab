package model.subject;

public class Subject {
	private String subjectid;
	private String name;
	private int mass;
	private int tuition;
	
	public Subject() {
		super();
	}

	public Subject(String subjectid, String name, int mass, int tuition) {
		super();
		this.subjectid = subjectid;
		this.name = name;
		this.mass = mass;
		this.tuition = tuition;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getTuition() {
		return tuition;
	}

	public void setTuition(int tuition) {
		this.tuition = tuition;
	}
	
    @Override
    public String toString() {
        return getSubjectid() + " - " + getName();
    }
	
}
