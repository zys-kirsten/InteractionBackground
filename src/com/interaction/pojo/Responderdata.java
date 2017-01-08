package com.interaction.pojo;

/**
 * Responderdata entity. @author MyEclipse Persistence Tools
 */

public class Responderdata implements java.io.Serializable {

	// Fields

	private Integer rdid;
	private Student student;
	private Seminar seminar;
	private Integer rscore;
	private Integer beVisited;

	// Constructors

	/** default constructor */
	public Responderdata() {
	}

	/** minimal constructor */
	public Responderdata(Integer rdid) {
		this.rdid = rdid;
	}

	/** full constructor */
	public Responderdata(Integer rdid, Student student, Seminar seminar,
			Integer rscore, Integer beVisited) {
		this.rdid = rdid;
		this.student = student;
		this.seminar = seminar;
		this.rscore = rscore;
		this.beVisited = beVisited;
	}

	// Property accessors

	public Integer getRdid() {
		return this.rdid;
	}

	public void setRdid(Integer rdid) {
		this.rdid = rdid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public Integer getRscore() {
		return this.rscore;
	}

	public void setRscore(Integer rscore) {
		this.rscore = rscore;
	}

	public Integer getBeVisited() {
		return this.beVisited;
	}

	public void setBeVisited(Integer beVisited) {
		this.beVisited = beVisited;
	}

}