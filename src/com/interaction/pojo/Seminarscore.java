package com.interaction.pojo;

/**
 * Seminarscore entity. @author MyEclipse Persistence Tools
 */

public class Seminarscore implements java.io.Serializable {

	// Fields

	private Integer ssid;
	private Student student;
	private Evaluationelement evaluationelement;
	private Seminar seminar;
	private Double sscore;

	// Constructors

	/** default constructor */
	public Seminarscore() {
	}

	/** minimal constructor */
	public Seminarscore(Integer ssid) {
		this.ssid = ssid;
	}

	/** full constructor */
	public Seminarscore(Integer ssid, Student student,
			Evaluationelement evaluationelement, Seminar seminar, Double sscore) {
		this.ssid = ssid;
		this.student = student;
		this.evaluationelement = evaluationelement;
		this.seminar = seminar;
		this.sscore = sscore;
	}

	// Property accessors

	public Integer getSsid() {
		return this.ssid;
	}

	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Evaluationelement getEvaluationelement() {
		return this.evaluationelement;
	}

	public void setEvaluationelement(Evaluationelement evaluationelement) {
		this.evaluationelement = evaluationelement;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public Double getSscore() {
		return this.sscore;
	}

	public void setSscore(Double sscore) {
		this.sscore = sscore;
	}

}