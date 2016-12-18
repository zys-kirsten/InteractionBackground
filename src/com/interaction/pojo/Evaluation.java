package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Evaluation entity. @author MyEclipse Persistence Tools
 */

public class Evaluation implements java.io.Serializable {

	// Fields

	private Integer eid;
	private Course course;
	private String edescription;
	private String ename;
	private String ecalcul;
	private Set outgroupevalitems = new HashSet(0);
	private Set ingroupevalitems = new HashSet(0);
	private Set scoreshows = new HashSet(0);

	// Constructors

	/** default constructor */
	public Evaluation() {
	}

	/** minimal constructor */
	public Evaluation(Course course, String ename, String ecalcul) {
		this.course = course;
		this.ename = ename;
		this.ecalcul = ecalcul;
	}

	/** full constructor */
	public Evaluation(Integer eid, Course course, String edescription,
			String ename, String ecalcul, Set outgroupevalitems,
			Set ingroupevalitems, Set scoreshows) {
		this.eid = eid;
		this.course = course;
		this.edescription = edescription;
		this.ename = ename;
		this.ecalcul = ecalcul;
		this.outgroupevalitems = outgroupevalitems;
		this.ingroupevalitems = ingroupevalitems;
		this.scoreshows = scoreshows;
	}

	// Property accessors

	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getEdescription() {
		return this.edescription;
	}

	public void setEdescription(String edescription) {
		this.edescription = edescription;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEcalcul() {
		return this.ecalcul;
	}

	public void setEcalcul(String ecalcul) {
		this.ecalcul = ecalcul;
	}

	public Set getOutgroupevalitems() {
		return this.outgroupevalitems;
	}

	public void setOutgroupevalitems(Set outgroupevalitems) {
		this.outgroupevalitems = outgroupevalitems;
	}

	public Set getIngroupevalitems() {
		return this.ingroupevalitems;
	}

	public void setIngroupevalitems(Set ingroupevalitems) {
		this.ingroupevalitems = ingroupevalitems;
	}

	public Set getScoreshows() {
		return this.scoreshows;
	}

	public void setScoreshows(Set scoreshows) {
		this.scoreshows = scoreshows;
	}

	@Override
	public String toString() {
		return "Evaluation [eid=" + eid + ", course=" + course + ", edescription=" + edescription + ", ename=" + ename
				+ ", ecalcul=" + ecalcul + ", outgroupevalitems=" + outgroupevalitems + ", ingroupevalitems="
				+ ingroupevalitems + ", scoreshows=" + scoreshows + "]";
	}

	
}