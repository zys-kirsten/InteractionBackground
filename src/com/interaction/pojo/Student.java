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
	private Set ingroupevalitems = new HashSet(0);
	private Set classes = new HashSet(0);
	private Set ingroupevals = new HashSet(0);
	private Set seminarclasses = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String saccount, String spwd, String sname) {
		this.sid = sid;
		this.saccount = saccount;
		this.spwd = spwd;
		this.sname = sname;
	}

	/** full constructor */
	public Student(Integer sid, String saccount, String spwd, String sname,
			String sphone, Set ingroupevalitems, Set classes, Set ingroupevals,
			Set seminarclasses, Set semclatests) {
		this.sid = sid;
		this.saccount = saccount;
		this.spwd = spwd;
		this.sname = sname;
		this.sphone = sphone;
		this.ingroupevalitems = ingroupevalitems;
		this.classes = classes;
		this.ingroupevals = ingroupevals;
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

	public Set getIngroupevalitems() {
		return this.ingroupevalitems;
	}

	public void setIngroupevalitems(Set ingroupevalitems) {
		this.ingroupevalitems = ingroupevalitems;
	}

	public Set getClasses() {
		return this.classes;
	}

	public void setClasses(Set classes) {
		this.classes = classes;
	}

	public Set getIngroupevals() {
		return this.ingroupevals;
	}

	public void setIngroupevals(Set ingroupevals) {
		this.ingroupevals = ingroupevals;
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