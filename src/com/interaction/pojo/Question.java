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
	private Integer chapter;
	private String content;
	private Set answers = new HashSet(0);
	private Set semclatests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(Course course, Integer chapter, String content) {
		this.qid = qid;
		this.course = course;
		this.chapter = chapter;
		this.content = content;
	}

	/** full constructor */
	public Question(Integer qid, Course course, Integer chapter,
			String content, Set answers, Set semclatests) {
		this.qid = qid;
		this.course = course;
		this.chapter = chapter;
		this.content = content;
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

	public Integer getChapter() {
		return this.chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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