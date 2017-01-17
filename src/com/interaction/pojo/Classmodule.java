package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Classmodule entity. @author MyEclipse Persistence Tools
 */

public class Classmodule implements java.io.Serializable {

	// Fields

	private Integer cmid;
	private Course course;
	private String moduleName;
	private Integer groupNum;
	private Integer groupTime;
	private Integer proNum;
	private Integer proTime;
	private Set classmoduleseminars = new HashSet(0);

	// Constructors

	/** default constructor */
	public Classmodule() {
	}

	/** minimal constructor */
	public Classmodule(Integer cmid) {
		this.cmid = cmid;
	}

	/** full constructor */
	public Classmodule(Integer cmid, Course course, String moduleName,
			Integer groupNum, Integer groupTime, Integer proNum,
			Integer proTime,Set classmoduleseminars) {
		this.cmid = cmid;
		this.course = course;
		this.moduleName = moduleName;
		this.groupNum = groupNum;
		this.groupTime = groupTime;
		this.proNum = proNum;
		this.proTime = proTime;
		this.classmoduleseminars = classmoduleseminars;
	}

	// Property accessors

	public Integer getCmid() {
		return this.cmid;
	}

	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Integer getGroupTime() {
		return this.groupTime;
	}

	public void setGroupTime(Integer groupTime) {
		this.groupTime = groupTime;
	}

	public Integer getProNum() {
		return this.proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}

	public Integer getProTime() {
		return this.proTime;
	}

	public void setProTime(Integer proTime) {
		this.proTime = proTime;
	}

	
	public Set getClassmoduleseminars() {
		return this.classmoduleseminars;
	}

	public void setClassmoduleseminars(Set classmoduleseminars) {
		this.classmoduleseminars = classmoduleseminars;
	}

}