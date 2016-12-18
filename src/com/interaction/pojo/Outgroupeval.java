package com.interaction.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Outgroupeval entity. @author MyEclipse Persistence Tools
 */

public class Outgroupeval implements java.io.Serializable {

	// Fields

	private Integer ogeid;
	private Course course;
	private Seminar seminar;
	private Integer groupNum;
	private Date outTime;
	private Set outgroupevalitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Outgroupeval() {
	}

	/** minimal constructor */
	public Outgroupeval(Course course) {
		this.course = course;
	}

	/** full constructor */
	public Outgroupeval(Integer ogeid, Course course, Seminar seminar,
			Integer groupNum, Date outTime, Set outgroupevalitems) {
		this.ogeid = ogeid;
		this.course = course;
		this.seminar = seminar;
		this.groupNum = groupNum;
		this.outTime = outTime;
		this.outgroupevalitems = outgroupevalitems;
	}

	// Property accessors

	public Integer getOgeid() {
		return this.ogeid;
	}

	public void setOgeid(Integer ogeid) {
		this.ogeid = ogeid;
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

	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Set getOutgroupevalitems() {
		return this.outgroupevalitems;
	}

	public void setOutgroupevalitems(Set outgroupevalitems) {
		this.outgroupevalitems = outgroupevalitems;
	}

}