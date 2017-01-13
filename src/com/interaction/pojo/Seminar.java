package com.interaction.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Seminar entity. @author MyEclipse Persistence Tools
 */

public class Seminar implements java.io.Serializable {

	// Fields

	private Integer seId;
	private Course course;
	private String seName;
	private String seTheme;
	private Date seTime;
	private Integer seUp;
	private Integer seDown;
	private Integer beVisited;
	private Set votequestions = new HashSet(0);
	private Set seminarclasses = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set semclatests = new HashSet(0);
	private Set votedatas = new HashSet(0);
	private Set responderdatas = new HashSet(0);
	private Set classmoduleseminars = new HashSet(0);
	private Set unquantizationfuzzyevaluations = new HashSet(0);
	private Set quantizationfuzzyevaluations = new HashSet(0);
	private Set seminarscores = new HashSet(0);

	// Constructors

	/** default constructor */
	public Seminar() {
	}

	/** minimal constructor */
	public Seminar(Integer seId, Course course) {
		this.seId = seId;
		this.course = course;
	}

	/** full constructor */
	public Seminar(Integer seId, Course course, String seName, String seTheme,
			Date seTime, Integer seUp, Integer seDown, Integer beVisited,
			Set votequestions, Set seminarclasses, Set questions,
			Set semclatests, Set votedatas, Set responderdatas,
			Set classmoduleseminars, Set unquantizationfuzzyevaluations,
			Set quantizationfuzzyevaluations, Set seminarscores) {
		this.seId = seId;
		this.course = course;
		this.seName = seName;
		this.seTheme = seTheme;
		this.seTime = seTime;
		this.seUp = seUp;
		this.seDown = seDown;
		this.beVisited = beVisited;
		this.votequestions = votequestions;
		this.seminarclasses = seminarclasses;
		this.questions = questions;
		this.semclatests = semclatests;
		this.votedatas = votedatas;
		this.responderdatas = responderdatas;
		this.classmoduleseminars = classmoduleseminars;
		this.unquantizationfuzzyevaluations = unquantizationfuzzyevaluations;
		this.quantizationfuzzyevaluations = quantizationfuzzyevaluations;
		this.seminarscores = seminarscores;
	}

	// Property accessors

	public Integer getSeId() {
		return this.seId;
	}

	public void setSeId(Integer seId) {
		this.seId = seId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSeName() {
		return this.seName;
	}

	public void setSeName(String seName) {
		this.seName = seName;
	}

	public String getSeTheme() {
		return this.seTheme;
	}

	public void setSeTheme(String seTheme) {
		this.seTheme = seTheme;
	}

	public Date getSeTime() {
		return this.seTime;
	}

	public void setSeTime(Date seTime) {
		this.seTime = seTime;
	}

	public Integer getSeUp() {
		return this.seUp;
	}

	public void setSeUp(Integer seUp) {
		this.seUp = seUp;
	}

	public Integer getSeDown() {
		return this.seDown;
	}

	public void setSeDown(Integer seDown) {
		this.seDown = seDown;
	}

	public Integer getBeVisited() {
		return this.beVisited;
	}

	public void setBeVisited(Integer beVisited) {
		this.beVisited = beVisited;
	}

	public Set getVotequestions() {
		return this.votequestions;
	}

	public void setVotequestions(Set votequestions) {
		this.votequestions = votequestions;
	}

	public Set getSeminarclasses() {
		return this.seminarclasses;
	}

	public void setSeminarclasses(Set seminarclasses) {
		this.seminarclasses = seminarclasses;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

	public Set getSemclatests() {
		return this.semclatests;
	}

	public void setSemclatests(Set semclatests) {
		this.semclatests = semclatests;
	}

	public Set getVotedatas() {
		return this.votedatas;
	}

	public void setVotedatas(Set votedatas) {
		this.votedatas = votedatas;
	}

	public Set getResponderdatas() {
		return this.responderdatas;
	}

	public void setResponderdatas(Set responderdatas) {
		this.responderdatas = responderdatas;
	}

	public Set getClassmoduleseminars() {
		return this.classmoduleseminars;
	}

	public void setClassmoduleseminars(Set classmoduleseminars) {
		this.classmoduleseminars = classmoduleseminars;
	}

	public Set getUnquantizationfuzzyevaluations() {
		return this.unquantizationfuzzyevaluations;
	}

	public void setUnquantizationfuzzyevaluations(
			Set unquantizationfuzzyevaluations) {
		this.unquantizationfuzzyevaluations = unquantizationfuzzyevaluations;
	}

	public Set getQuantizationfuzzyevaluations() {
		return this.quantizationfuzzyevaluations;
	}

	public void setQuantizationfuzzyevaluations(Set quantizationfuzzyevaluations) {
		this.quantizationfuzzyevaluations = quantizationfuzzyevaluations;
	}

	public Set getSeminarscores() {
		return this.seminarscores;
	}

	public void setSeminarscores(Set seminarscores) {
		this.seminarscores = seminarscores;
	}

}