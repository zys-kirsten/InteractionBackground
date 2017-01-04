package com.interaction.pojo;

/**
 * Seminarclass entity. @author MyEclipse Persistence Tools
 */

public class Seminarclass implements java.io.Serializable {

	// Fields

	private Integer scid;
	private Student student;
	private Course course;
	private Seminar seminar;
	private Integer groupNum;
	private Integer inGroupNum;
	private Double seScore;
	private Integer isLogin;//标识学生是否登录。1：登录，0：未登录

	// Constructors

	/** default constructor */
	public Seminarclass() {
	}

	/** minimal constructor */
	public Seminarclass(Integer scid, Student student, Course course,
			Seminar seminar) {
		this.scid = scid;
		this.student = student;
		this.course = course;
		this.seminar = seminar;
	}

	/** full constructor */
	public Seminarclass(Integer scid, Student student, Course course,
			Seminar seminar, Integer groupNum, Integer inGroupNum,
			Double seScore, Integer isLogin) {
		this.scid = scid;
		this.student = student;
		this.course = course;
		this.seminar = seminar;
		this.groupNum = groupNum;
		this.inGroupNum = inGroupNum;
		this.seScore = seScore;
		this.isLogin = isLogin;
	}

	// Property accessors

	public Integer getScid() {
		return this.scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
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

	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Integer getInGroupNum() {
		return this.inGroupNum;
	}

	public void setInGroupNum(Integer inGroupNum) {
		this.inGroupNum = inGroupNum;
	}

	public Double getSeScore() {
		return this.seScore;
	}

	public void setSeScore(Double seScore) {
		this.seScore = seScore;
	}

	public Integer getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}

}