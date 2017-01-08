package com.interaction.pojo;

/**
 * Votedata entity. @author MyEclipse Persistence Tools
 */

public class Votedata implements java.io.Serializable {

	// Fields

	private Integer vdid;
	private Student student;
	private Votequestion votequestion;
	private Seminar seminar;
	private String stuAnswer;

	// Constructors

	/** default constructor */
	public Votedata() {
	}

	/** minimal constructor */
	public Votedata(Integer vdid) {
		this.vdid = vdid;
	}

	/** full constructor */
	public Votedata(Integer vdid, Student student, Votequestion votequestion,
			Seminar seminar, String stuAnswer) {
		this.vdid = vdid;
		this.student = student;
		this.votequestion = votequestion;
		this.seminar = seminar;
		this.stuAnswer = stuAnswer;
	}

	// Property accessors

	public Integer getVdid() {
		return this.vdid;
	}

	public void setVdid(Integer vdid) {
		this.vdid = vdid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Votequestion getVotequestion() {
		return this.votequestion;
	}

	public void setVotequestion(Votequestion votequestion) {
		this.votequestion = votequestion;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public String getStuAnswer() {
		return this.stuAnswer;
	}

	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}

}