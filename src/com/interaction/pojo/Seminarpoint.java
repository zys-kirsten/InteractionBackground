package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Seminarpoint entity. @author MyEclipse Persistence Tools
 */

public class Seminarpoint implements java.io.Serializable {

	// Fields

	private Integer spid;
	private Seminar seminar;
	private String content;
	private Set seminarpointfeedbacks = new HashSet(0);

	// Constructors

	/** default constructor */
	public Seminarpoint() {
	}

	/** minimal constructor */
	public Seminarpoint(Integer spid) {
		this.spid = spid;
	}

	/** full constructor */
	public Seminarpoint(Integer spid, Seminar seminar, String content,
			Set seminarpointfeedbacks) {
		this.spid = spid;
		this.seminar = seminar;
		this.content = content;
		this.seminarpointfeedbacks = seminarpointfeedbacks;
	}

	// Property accessors

	public Integer getSpid() {
		return this.spid;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set getSeminarpointfeedbacks() {
		return this.seminarpointfeedbacks;
	}

	public void setSeminarpointfeedbacks(Set seminarpointfeedbacks) {
		this.seminarpointfeedbacks = seminarpointfeedbacks;
	}

}