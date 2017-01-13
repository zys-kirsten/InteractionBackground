package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Evaluationelement entity. @author MyEclipse Persistence Tools
 */

public class Evaluationelement implements java.io.Serializable {

	// Fields

	private Integer eeid;
	private Course course;
	private Evaluationelement evaluationelement;
	private String eename;
	private Double weight;
	private Integer beVisited;
	private Integer isLeaf;
	private Set unquantizationfuzzyevaluations = new HashSet(0);
	private Set quantizationfuzzyevaluations = new HashSet(0);
	private Set seminarscores = new HashSet(0);
	private Set evaluationelements = new HashSet(0);

	// Constructors

	/** default constructor */
	public Evaluationelement() {
	}

	/** minimal constructor */
	public Evaluationelement(Integer eeid, Course course) {
		this.eeid = eeid;
		this.course = course;
	}

	/** full constructor */
	public Evaluationelement(Integer eeid, Course course,
			Evaluationelement evaluationelement, String eename, Double weight,
			Integer beVisited, Integer isLeaf,
			Set unquantizationfuzzyevaluations,
			Set quantizationfuzzyevaluations, Set seminarscores,
			Set evaluationelements) {
		this.eeid = eeid;
		this.course = course;
		this.evaluationelement = evaluationelement;
		this.eename = eename;
		this.weight = weight;
		this.beVisited = beVisited;
		this.isLeaf = isLeaf;
		this.unquantizationfuzzyevaluations = unquantizationfuzzyevaluations;
		this.quantizationfuzzyevaluations = quantizationfuzzyevaluations;
		this.seminarscores = seminarscores;
		this.evaluationelements = evaluationelements;
	}

	// Property accessors

	public Integer getEeid() {
		return this.eeid;
	}

	public void setEeid(Integer eeid) {
		this.eeid = eeid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Evaluationelement getEvaluationelement() {
		return this.evaluationelement;
	}

	public void setEvaluationelement(Evaluationelement evaluationelement) {
		this.evaluationelement = evaluationelement;
	}

	public String getEename() {
		return this.eename;
	}

	public void setEename(String eename) {
		this.eename = eename;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getBeVisited() {
		return this.beVisited;
	}

	public void setBeVisited(Integer beVisited) {
		this.beVisited = beVisited;
	}

	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
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

	public Set getEvaluationelements() {
		return this.evaluationelements;
	}

	public void setEvaluationelements(Set evaluationelements) {
		this.evaluationelements = evaluationelements;
	}

}