package com.interaction.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Seminar entity. @author MyEclipse Persistence Tools
 */

public class Seminar implements java.io.Serializable {

	// Fields

	private Integer seId;
	private Course course;
	private String seName;
	private String seTheme;
	private Date seTime;
	private Integer seUp;
	private Integer seDown;
	private Integer bevisited;
	private Set seminarclasses = new HashSet(0);
	private Set semclatests = new HashSet(0);
	private Set outgroupevals = new HashSet(0);
	private Set ingroupevals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Seminar() {
	}

	/** minimal constructor */
	public Seminar(Course course, String seName, String seTheme,
			Date seTime, Integer seUp, Integer seDown) {
		this.course = course;
		this.seName = seName;
		this.seTheme = seTheme;
		this.seTime = seTime;
		this.seUp = seUp;
		this.seDown = seDown;
	}

	/** full constructor */
	public Seminar(Integer seId, Course course, String seName, String seTheme,
			Date seTime, Integer seUp, Integer seDown, Set seminarclasses,
			Set semclatests, Set outgroupevals, Set ingroupevals) {
		this.seId = seId;
		this.course = course;
		this.seName = seName;
		this.seTheme = seTheme;
		this.seTime = seTime;
		this.seUp = seUp;
		this.seDown = seDown;
		this.seminarclasses = seminarclasses;
		this.semclatests = semclatests;
		this.outgroupevals = outgroupevals;
		this.ingroupevals = ingroupevals;
	}

	
	// Property accessors

	public Integer getBevisited() {
		return bevisited;
	}

	public void setBevisited(Integer bevisited) {
		this.bevisited = bevisited;
	}

	public Integer getSeId() {
		return this.seId;
	}

	public void setSeId(Integer seId) {
		this.seId = seId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSeName() {
		return this.seName;
	}

	public void setSeName(String seName) {
		this.seName = seName;
	}

	public String getSeTheme() {
		return this.seTheme;
	}

	public void setSeTheme(String seTheme) {
		this.seTheme = seTheme;
	}

	public Date getSeTime() {
		return this.seTime;
	}

	public void setSeTime(Date seTime) {
		this.seTime = seTime;
	}

	public Integer getSeUp() {
		return this.seUp;
	}

	public void setSeUp(Integer seUp) {
		this.seUp = seUp;
	}

	public Integer getSeDown() {
		return this.seDown;
	}

	public void setSeDown(Integer seDown) {
		this.seDown = seDown;
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

	@Override
	public String toString() {
		return "Seminar [seId=" + seId + ", course=" + course + ", seName=" + seName + ", seTheme=" + seTheme
				+ ", seTime=" + seTime + ", seUp=" + seUp + ", seDown=" + seDown + ", seminarclasses=" + seminarclasses
				+ ", semclatests=" + semclatests + ", outgroupevals=" + outgroupevals + ", ingroupevals=" + ingroupevals
				+ "]";
	}

	
}