package com.interaction.pojo;

/**
 * Ingroupevalitem entity. @author MyEclipse Persistence Tools
 */

public class Ingroupevalitem implements java.io.Serializable {

	// Fields

	private Integer inItemId;
	private Student student;
	private Ingroupeval ingroupeval;
	private Evaluation evaluation;
	private String ename;
	private String escoreShow;
	private Double escore;

	// Constructors

	/** default constructor */
	public Ingroupevalitem() {
	}

	/** minimal constructor */
	public Ingroupevalitem(Integer inItemId, Ingroupeval ingroupeval,
			Evaluation evaluation, String ename, String escoreShow) {
		this.inItemId = inItemId;
		this.ingroupeval = ingroupeval;
		this.evaluation = evaluation;
		this.ename = ename;
		this.escoreShow = escoreShow;
	}

	/** full constructor */
	public Ingroupevalitem(Integer inItemId, Student student,
			Ingroupeval ingroupeval, Evaluation evaluation, String ename,
			String escoreShow, Double escore) {
		this.inItemId = inItemId;
		this.student = student;
		this.ingroupeval = ingroupeval;
		this.evaluation = evaluation;
		this.ename = ename;
		this.escoreShow = escoreShow;
		this.escore = escore;
	}

	// Property accessors

	public Integer getInItemId() {
		return this.inItemId;
	}

	public void setInItemId(Integer inItemId) {
		this.inItemId = inItemId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Ingroupeval getIngroupeval() {
		return this.ingroupeval;
	}

	public void setIngroupeval(Ingroupeval ingroupeval) {
		this.ingroupeval = ingroupeval;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEscoreShow() {
		return this.escoreShow;
	}

	public void setEscoreShow(String escoreShow) {
		this.escoreShow = escoreShow;
	}

	public Double getEscore() {
		return this.escore;
	}

	public void setEscore(Double escore) {
		this.escore = escore;
	}

}