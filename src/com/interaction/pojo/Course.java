package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer cid;
	private Teacher teacher;
	private String cnumber;
	private String cname;
	private String cterm;
	private Set outgroupevals = new HashSet(0);
	private Set ingroupevals = new HashSet(0);
	private Set classes = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set seminars = new HashSet(0);
	private Set seminarclasses = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Teacher teacher, String cnumber, String cname,
			String cterm) {
		this.teacher = teacher;
		this.cnumber = cnumber;
		this.cname = cname;
		this.cterm = cterm;
	}

	/** full constructor */
	public Course(Integer cid, Teacher teacher, String cnumber, String cname,
			String cterm, Set outgroupevals, Set ingroupevals, Set classes,
			Set questions, Set seminars, Set seminarclasses, Set semclatests) {
		this.cid = cid;
		this.teacher = teacher;
		this.cnumber = cnumber;
		this.cname = cname;
		this.cterm = cterm;
		this.outgroupevals = outgroupevals;
		this.ingroupevals = ingroupevals;
		this.classes = classes;
		this.questions = questions;
		this.seminars = seminars;
		this.seminarclasses = seminarclasses;
		this.semclatests = semclatests;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCterm() {
		return this.cterm;
	}

	public void setCterm(String cterm) {
		this.cterm = cterm;
	}

	public Set getOutgroupevals() {
		return this.outgroupevals;
	}

	public void setOutgroupevals(Set outgroupevals) {
		this.outgroupevals = outgroupevals;
	}

	public Set getIngroupevals() {
		return this.ingroupevals;
	}

	public void setIngroupevals(Set ingroupevals) {
		this.ingroupevals = ingroupevals;
	}

	public Set getClasses() {
		return this.classes;
	}

	public void setClasses(Set classes) {
		this.classes = classes;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

	public Set getSeminars() {
		return this.seminars;
	}

	public void setSeminars(Set seminars) {
		this.seminars = seminars;
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

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", teacher=" + teacher + ", cnumber=" + cnumber + ", cname=" + cname + ", cterm="
				+ cterm + "]";
	}
	
	

}