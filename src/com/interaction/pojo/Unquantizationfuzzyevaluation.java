package com.interaction.pojo;

/**
 * Unquantizationfuzzyevaluation entity. @author MyEclipse Persistence Tools
 */

public class Unquantizationfuzzyevaluation implements java.io.Serializable {

	// Fields

	private Integer ufeid;
	private Student student;
	private Evaluationelement evaluationelement;
	private Seminar seminar;
	private String evalRank;

	// Constructors

	/** default constructor */
	public Unquantizationfuzzyevaluation() {
	}

	/** minimal constructor */
	public Unquantizationfuzzyevaluation(Integer ufeid) {
		this.ufeid = ufeid;
	}

	/** full constructor */
	public Unquantizationfuzzyevaluation(Integer ufeid, Student student,
			Evaluationelement evaluationelement, Seminar seminar,
			String evalRank) {
		this.ufeid = ufeid;
		this.student = student;
		this.evaluationelement = evaluationelement;
		this.seminar = seminar;
		this.evalRank = evalRank;
	}

	// Property accessors

	public Integer getUfeid() {
		return this.ufeid;
	}

	public void setUfeid(Integer ufeid) {
		this.ufeid = ufeid;
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

	public String getEvalRank() {
		return this.evalRank;
	}

	public void setEvalRank(String evalRank) {
		this.evalRank = evalRank;
	}

}