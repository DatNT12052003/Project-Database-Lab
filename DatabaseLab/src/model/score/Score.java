package model.score;

public class Score {
	private int scoreid;
	private String studyid;
	private int examNumber;
	private String examDate;
	private int coefficient;
	private float score;
	
	public Score() {
		super();
	}

	public Score(int scoreid, String studyid, int examNumber, String examDate, int coefficient, float score) {
		super();
		this.scoreid = scoreid;
		this.studyid = studyid;
		this.examNumber = examNumber;
		this.examDate = examDate;
		this.coefficient = coefficient;
		this.score = score;
	}

	public int getScoreid() {
		return scoreid;
	}

	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
	}

	public String getStudyid() {
		return studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	public int getExamNumber() {
		return examNumber;
	}

	public void setExamNumber(int examNumber) {
		this.examNumber = examNumber;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	
	
}
