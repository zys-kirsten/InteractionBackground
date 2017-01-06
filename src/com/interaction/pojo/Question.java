package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	private Integer qid;
	private Course course;
	private Seminar seminar;
	private String content;
	private Integer beVisited;
	private Set answers = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(Integer qid) {
		this.qid = qid;
	}

	/** full constructor */
	public Question(Integer qid, Course course, Seminar seminar,
			String content, Integer beVisited, Set answers, Set semclatests) {
		this.qid = qid;
		this.course = course;
		this.seminar = seminar;
		this.content = content;
		this.beVisited = beVisited;
		this.answers = answers;
		this.semclatests = semclatests;
	}

	// Property accessors

	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBeVisited() {
		return this.beVisited;
	}

	public void setBeVisited(Integer beVisited) {
		this.beVisited = beVisited;
	}

	public Set getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set answers) {
		this.answers = answers;
	}

	public Set getSemclatests() {
		return this.semclatests;
	}

	public void setSemclatests(Set semclatests) {
		this.semclatests = semclatests;
	}

}