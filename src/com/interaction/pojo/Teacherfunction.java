package com.interaction.pojo;

/**
 * Teacherfunction entity. @author MyEclipse Persistence Tools
 */

public class Teacherfunction implements java.io.Serializable {

	// Fields

	private Integer tfid;
	private Teacher teacher;
	private Functioncomponent functioncomponent;

	// Constructors

	/** default constructor */
	public Teacherfunction() {
	}

	/** full constructor */
	public Teacherfunction(Teacher teacher, Functioncomponent functioncomponent) {
		this.teacher = teacher;
		this.functioncomponent = functioncomponent;
	}

	// Property accessors

	public Integer getTfid() {
		return this.tfid;
	}

	public void setTfid(Integer tfid) {
		this.tfid = tfid;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Functioncomponent getFunctioncomponent() {
		return this.functioncomponent;
	}

	public void setFunctioncomponent(Functioncomponent functioncomponent) {
		this.functioncomponent = functioncomponent;
	}

}