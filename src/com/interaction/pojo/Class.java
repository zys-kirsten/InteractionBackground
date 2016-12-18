package com.interaction.pojo;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class Class implements java.io.Serializable {

	// Fields

	private Integer claId;
	private Student student;
	private Course course;
	private Double score;

	// Constructors

	/** default constructor */
	public Class() {
	}

	/** minimal constructor */
	public Class(Integer claId, Student student, Course course) {
		this.claId = claId;
		this.student = student;
		this.course = course;
	}

	/** full constructor */
	public Class(Integer claId, Student student, Course course, Double score) {
		this.claId = claId;
		this.student = student;
		this.course = course;
		this.score = score;
	}

	// Property accessors

	public Integer getClaId() {
		return this.claId;
	}

	public void setClaId(Integer claId) {
		this.claId = claId;
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

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}