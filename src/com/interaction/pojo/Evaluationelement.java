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
	private Integer isleaf;
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
			Set evaluationelements,Integer isleaf) {
		this.eeid = eeid;
		this.course = course;
		this.evaluationelement = evaluationelement;
		this.eename = eename;
		this.weight = weight;
		this.evaluationelements = evaluationelements;
		this.isleaf = isleaf;
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

	public Set getEvaluationelements() {
		return this.evaluationelements;
	}

	public void setEvaluationelements(Set evaluationelements) {
		this.evaluationelements = evaluationelements;
	}

	
	public Integer getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	@Override
	public String toString() {
		return "Evaluationelement [eeid=" + eeid + ",  eename=" + eename + ", weight=" + weight +  ", isLeaf=" + isleaf +  "]";
	}

	
}