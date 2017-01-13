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
	private Set classes = new HashSet(0);
	private Set classmodules = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set seminars = new HashSet(0);
	private Set evaluationelements = new HashSet(0);
	private Set seminarclasses = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Integer cid, Teacher teacher, String cnumber, String cname,
			String cterm) {
		this.cid = cid;
		this.teacher = teacher;
		this.cnumber = cnumber;
		this.cname = cname;
		this.cterm = cterm;
	}

	/** full constructor */
	public Course(Integer cid, Teacher teacher, String cnumber, String cname,
			String cterm, Set classes, Set classmodules, Set questions,
			Set seminars, Set evaluationelements, Set seminarclasses,
			Set semclatests) {
		this.cid = cid;
		this.teacher = teacher;
		this.cnumber = cnumber;
		this.cname = cname;
		this.cterm = cterm;
		this.classes = classes;
		this.classmodules = classmodules;
		this.questions = questions;
		this.seminars = seminars;
		this.evaluationelements = evaluationelements;
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

	public Set getClasses() {
		return this.classes;
	}

	public void setClasses(Set classes) {
		this.classes = classes;
	}

	public Set getClassmodules() {
		return this.classmodules;
	}

	public void setClassmodules(Set classmodules) {
		this.classmodules = classmodules;
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

	public Set getEvaluationelements() {
		return this.evaluationelements;
	}

	public void setEvaluationelements(Set evaluationelements) {
		this.evaluationelements = evaluationelements;
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