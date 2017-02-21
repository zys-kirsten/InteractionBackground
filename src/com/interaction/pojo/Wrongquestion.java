package com.interaction.pojo;

/**
 * Wrongquestion entity. @author MyEclipse Persistence Tools
 */

public class Wrongquestion implements java.io.Serializable {

	// Fields

	private Integer wqid;
	private Student student;
	private Question question;

	// Constructors

	/** default constructor */
	public Wrongquestion() {
	}

	/** minimal constructor */
	public Wrongquestion(Integer wqid) {
		this.wqid = wqid;
	}

	/** full constructor */
	public Wrongquestion(Integer wqid, Student student, Question question) {
		this.wqid = wqid;
		this.student = student;
		this.question = question;
	}

	// Property accessors

	public Integer getWqid() {
		return this.wqid;
	}

	public void setWqid(Integer wqid) {
		this.wqid = wqid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}