package com.interaction.pojo;

/**
 * Classmoduleseminar entity. @author MyEclipse Persistence Tools
 */

public class Classmoduleseminar implements java.io.Serializable {

	// Fields

	private Integer cmseid;
	private Classmodule classmodule;
	private Seminar seminar;

	// Constructors

	/** default constructor */
	public Classmoduleseminar() {
	}

	/** minimal constructor */
	public Classmoduleseminar(Integer cmseid) {
		this.cmseid = cmseid;
	}

	/** full constructor */
	public Classmoduleseminar(Integer cmseid, Classmodule classmodule,
			Seminar seminar) {
		this.cmseid = cmseid;
		this.classmodule = classmodule;
		this.seminar = seminar;
	}

	// Property accessors

	public Integer getCmseid() {
		return this.cmseid;
	}

	public void setCmseid(Integer cmseid) {
		this.cmseid = cmseid;
	}

	public Classmodule getClassmodule() {
		return this.classmodule;
	}

	public void setClassmodule(Classmodule classmodule) {
		this.classmodule = classmodule;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

}