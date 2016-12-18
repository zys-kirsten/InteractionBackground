package com.interaction.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ingroupeval entity. @author MyEclipse Persistence Tools
 */

public class Ingroupeval implements java.io.Serializable {

	// Fields

	private Integer igeid;
	private Student student;
	private Course course;
	private Seminar seminar;
	private Date inTime;
	private Set ingroupevalitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ingroupeval() {
	}

	/** minimal constructor */
	public Ingroupeval(Course course) {
		this.course = course;
	}

	/** full constructor */
	public Ingroupeval(Integer igeid, Student student, Course course,
			Seminar seminar, Date inTime, Set ingroupevalitems) {
		this.igeid = igeid;
		this.student = student;
		this.course = course;
		this.seminar = seminar;
		this.inTime = inTime;
		this.ingroupevalitems = ingroupevalitems;
	}

	// Property accessors

	public Integer getIgeid() {
		return this.igeid;
	}

	public void setIgeid(Integer igeid) {
		this.igeid = igeid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Set getIngroupevalitems() {
		return this.ingroupevalitems;
	}

	public void setIngroupevalitems(Set ingroupevalitems) {
		this.ingroupevalitems = ingroupevalitems;
	}

}