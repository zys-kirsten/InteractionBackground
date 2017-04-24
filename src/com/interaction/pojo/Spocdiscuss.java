package com.interaction.pojo;

public class Spocdiscuss implements java.io.Serializable{

	private Integer sdid;
	private Course course;
	private Student student;
	private Integer note;
	private Integer subject;
	private Integer replay;
	private Integer comment;
	private Integer admire;
	
	public Spocdiscuss() {
	}

	
	
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public Integer getSdid() {
		return sdid;
	}

	public void setSdid(Integer sdid) {
		this.sdid = sdid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Integer getReplay() {
		return replay;
	}

	public void setReplay(Integer replay) {
		this.replay = replay;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getAdmire() {
		return admire;
	}

	public void setAdmire(Integer admire) {
		this.admire = admire;
	}



	@Override
	public String toString() {
		return "Spocdiscuss [sdid=" + sdid + ", course=" + course + ", student=" + student + ", note=" + note
				+ ", subject=" + subject + ", replay=" + replay + ", comment=" + comment + ", admire=" + admire + "]";
	}

	

	
}
