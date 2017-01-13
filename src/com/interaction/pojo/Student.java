package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String saccount;
	private String spwd;
	private String sname;
	private String sphone;
	private Set unquantizationfuzzyevaluations = new HashSet(0);
	private Set votedatas = new HashSet(0);
	private Set responderdatas = new HashSet(0);
	private Set quantizationfuzzyevaluations = new HashSet(0);
	private Set classes = new HashSet(0);
	private Set seminarscores = new HashSet(0);
	private Set seminarclasses = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer sid, String saccount, String spwd, String sname) {
		this.sid = sid;
		this.saccount = saccount;
		this.spwd = spwd;
		this.sname = sname;
	}

	/** full constructor */
	public Student(Integer sid, String saccount, String spwd, String sname,
			String sphone, Set unquantizationfuzzyevaluations, Set votedatas,
			Set responderdatas, Set quantizationfuzzyevaluations, Set classes,
			Set seminarscores, Set seminarclasses, Set semclatests) {
		this.sid = sid;
		this.saccount = saccount;
		this.spwd = spwd;
		this.sname = sname;
		this.sphone = sphone;
		this.unquantizationfuzzyevaluations = unquantizationfuzzyevaluations;
		this.votedatas = votedatas;
		this.responderdatas = responderdatas;
		this.quantizationfuzzyevaluations = quantizationfuzzyevaluations;
		this.classes = classes;
		this.seminarscores = seminarscores;
		this.seminarclasses = seminarclasses;
		this.semclatests = semclatests;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSaccount() {
		return this.saccount;
	}

	public void setSaccount(String saccount) {
		this.saccount = saccount;
	}

	public String getSpwd() {
		return this.spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSphone() {
		return this.sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public Set getUnquantizationfuzzyevaluations() {
		return this.unquantizationfuzzyevaluations;
	}

	public void setUnquantizationfuzzyevaluations(
			Set unquantizationfuzzyevaluations) {
		this.unquantizationfuzzyevaluations = unquantizationfuzzyevaluations;
	}

	public Set getVotedatas() {
		return this.votedatas;
	}

	public void setVotedatas(Set votedatas) {
		this.votedatas = votedatas;
	}

	public Set getResponderdatas() {
		return this.responderdatas;
	}

	public void setResponderdatas(Set responderdatas) {
		this.responderdatas = responderdatas;
	}

	public Set getQuantizationfuzzyevaluations() {
		return this.quantizationfuzzyevaluations;
	}

	public void setQuantizationfuzzyevaluations(Set quantizationfuzzyevaluations) {
		this.quantizationfuzzyevaluations = quantizationfuzzyevaluations;
	}

	public Set getClasses() {
		return this.classes;
	}

	public void setClasses(Set classes) {
		this.classes = classes;
	}

	public Set getSeminarscores() {
		return this.seminarscores;
	}

	public void setSeminarscores(Set seminarscores) {
		this.seminarscores = seminarscores;
	}

	public Set getSeminarclasses() {
		return this.seminarclasses;
	}

	public void setSeminarclasses(Set seminarclasses) {
		this.seminarclasses = seminarclasses;
	}

	public Set getSemclatests() {
		return this.semclatests;
	}

	public void setSemclatests(Set semclatests) {
		this.semclatests = semclatests;
	}

}