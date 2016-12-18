package com.interaction.pojo;

/**
 * Outgroupevalitem entity. @author MyEclipse Persistence Tools
 */

public class Outgroupevalitem implements java.io.Serializable {

	// Fields

	private Integer outItemId;
	private Evaluation evaluation;
	private Outgroupeval outgroupeval;
	private Integer beEvalGroup;
	private String ename;
	private String escoreShow;
	private Double escore;

	// Constructors

	/** default constructor */
	public Outgroupevalitem() {
	}

	/** minimal constructor */
	public Outgroupevalitem(Evaluation evaluation, Outgroupeval outgroupeval,
			String ename, String escoreShow) {
		this.evaluation = evaluation;
		this.outgroupeval = outgroupeval;
		this.ename = ename;
		this.escoreShow = escoreShow;
	}

	/** full constructor */
	public Outgroupevalitem(Evaluation evaluation, Outgroupeval outgroupeval,
			Integer beEvalGroup, String ename, String escoreShow, Double escore) {
		this.evaluation = evaluation;
		this.outgroupeval = outgroupeval;
		this.beEvalGroup = beEvalGroup;
		this.ename = ename;
		this.escoreShow = escoreShow;
		this.escore = escore;
	}

	// Property accessors

	public Integer getOutItemId() {
		return this.outItemId;
	}

	public void setOutItemId(Integer outItemId) {
		this.outItemId = outItemId;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Outgroupeval getOutgroupeval() {
		return this.outgroupeval;
	}

	public void setOutgroupeval(Outgroupeval outgroupeval) {
		this.outgroupeval = outgroupeval;
	}

	public Integer getBeEvalGroup() {
		return this.beEvalGroup;
	}

	public void setBeEvalGroup(Integer beEvalGroup) {
		this.beEvalGroup = beEvalGroup;
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