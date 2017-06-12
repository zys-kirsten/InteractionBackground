package com.interaction.pojo;

/**
 * Semclatest entity. @author MyEclipse Persistence Tools
 */

public class Semclatest implements java.io.Serializable {

	// Fields

	private Integer sctid;
	private Student student;
	private Course course;
	private Answer answer;
	private Question question;
	private Seminar seminar;
	private Double testScore;
	private String testTime;

	// Constructors

	/** default constructor */
	public Semclatest() {
	}

	/** minimal constructor */
	public Semclatest(Integer sctid, Student student, Course course,
			Question question, Seminar seminar) {
		this.sctid = sctid;
		this.student = student;
		this.course = course;
		this.question = question;
		this.seminar = seminar;
	}

	/** full constructor */
	public Semclatest(Integer sctid, Student student, Course course,
			Answer answer, Question question, Seminar seminar, Double testScore) {
		this.sctid = sctid;
		this.student = student;
		this.course = course;
		this.answer = answer;
		this.question = question;
		this.seminar = seminar;
		this.testScore = testScore;
	}

	// Property accessors

	
	public Integer getSctid() {
		return this.sctid;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public void setSctid(Integer sctid) {
		this.sctid = sctid;
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

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public Double getTestScore() {
		return this.testScore;
	}

	public void setTestScore(Double testScore) {
		this.testScore = testScore;
	}

}