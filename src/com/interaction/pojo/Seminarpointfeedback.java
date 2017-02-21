package com.interaction.pojo;

/**
 * Seminarpointfeedback entity. @author MyEclipse Persistence Tools
 */

public class Seminarpointfeedback implements java.io.Serializable {

	// Fields

	private Integer spfid;
	private Student student;
	private Seminarpoint seminarpoint;
	private Integer time;
	private String result;

	// Constructors

	/** default constructor */
	public Seminarpointfeedback() {
	}

	/** minimal constructor */
	public Seminarpointfeedback(Integer spfid) {
		this.spfid = spfid;
	}

	/** full constructor */
	public Seminarpointfeedback(Integer spfid, Student student,
			Seminarpoint seminarpoint, Integer time, String result) {
		this.spfid = spfid;
		this.student = student;
		this.seminarpoint = seminarpoint;
		this.time = time;
		this.result = result;
	}

	// Property accessors

	public Integer getSpfid() {
		return this.spfid;
	}

	public void setSpfid(Integer spfid) {
		this.spfid = spfid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Seminarpoint getSeminarpoint() {
		return this.seminarpoint;
	}

	public void setSeminarpoint(Seminarpoint seminarpoint) {
		this.seminarpoint = seminarpoint;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}