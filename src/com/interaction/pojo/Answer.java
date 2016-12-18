package com.interaction.pojo;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */

public class Answer implements java.io.Serializable {

	// Fields

	private Integer aid;
	private Question question;
	private String acontent;
	private String aexplain;
	private Integer correct;

	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** minimal constructor */
	public Answer(Question question, String acontent,
			Integer correct) {
		this.aid = aid;
		this.question = question;
		this.acontent = acontent;
		this.correct = correct;
	}

	/** full constructor */
	public Answer(Integer aid, Question question, String acontent,
			String aexplain, Integer correct) {
		this.aid = aid;
		this.question = question;
		this.acontent = acontent;
		this.aexplain = aexplain;
		this.correct = correct;
	}

	// Property accessors

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAcontent() {
		return this.acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public String getAexplain() {
		return this.aexplain;
	}

	public void setAexplain(String aexplain) {
		this.aexplain = aexplain;
	}

	public Integer getCorrect() {
		return this.correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Answer [aid=" + aid + ", question=" + question + ", acontent=" + acontent + ", aexplain=" + aexplain
				+ ", correct=" + correct + "]";
	}

	
}