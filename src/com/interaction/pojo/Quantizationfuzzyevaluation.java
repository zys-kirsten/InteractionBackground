package com.interaction.pojo;

/**
 * Quantizationfuzzyevaluation entity. @author MyEclipse Persistence Tools
 */

public class Quantizationfuzzyevaluation implements java.io.Serializable {

	// Fields

	private Integer qfeid;
	private Student student;
	private Evaluationelement evaluationelement;
	private Seminar seminar;
	private Double totalScore;
	private Double getScore;

	// Constructors

	/** default constructor */
	public Quantizationfuzzyevaluation() {
	}

	/** minimal constructor */
	public Quantizationfuzzyevaluation(Integer qfeid) {
		this.qfeid = qfeid;
	}

	/** full constructor */
	public Quantizationfuzzyevaluation(Integer qfeid, Student student,
			Evaluationelement evaluationelement, Seminar seminar,
			Double totalScore, Double getScore) {
		this.qfeid = qfeid;
		this.student = student;
		this.evaluationelement = evaluationelement;
		this.seminar = seminar;
		this.totalScore = totalScore;
		this.getScore = getScore;
	}

	// Property accessors

	public Integer getQfeid() {
		return this.qfeid;
	}

	public void setQfeid(Integer qfeid) {
		this.qfeid = qfeid;
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

	public Double getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Double getGetScore() {
		return this.getScore;
	}

	public void setGetScore(Double getScore) {
		this.getScore = getScore;
	}

}