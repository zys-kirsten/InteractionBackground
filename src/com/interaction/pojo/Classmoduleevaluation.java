package com.interaction.pojo;

/**
 * Classmoduleevaluation entity. @author MyEclipse Persistence Tools
 */

public class Classmoduleevaluation implements java.io.Serializable {

	// Fields

	private Integer cmeid;
	private Evaluation evaluation;
	private Classmodule classmodule;

	// Constructors

	/** default constructor */
	public Classmoduleevaluation() {
	}

	/** minimal constructor */
	public Classmoduleevaluation(Integer cmeid) {
		this.cmeid = cmeid;
	}

	/** full constructor */
	public Classmoduleevaluation(Integer cmeid, Evaluation evaluation,
			Classmodule classmodule) {
		this.cmeid = cmeid;
		this.evaluation = evaluation;
		this.classmodule = classmodule;
	}

	// Property accessors

	public Integer getCmeid() {
		return this.cmeid;
	}

	public void setCmeid(Integer cmeid) {
		this.cmeid = cmeid;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Classmodule getClassmodule() {
		return this.classmodule;
	}

	public void setClassmodule(Classmodule classmodule) {
		this.classmodule = classmodule;
	}

}