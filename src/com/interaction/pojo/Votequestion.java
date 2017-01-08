package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Votequestion entity. @author MyEclipse Persistence Tools
 */

public class Votequestion implements java.io.Serializable {

	// Fields

	private Integer vqid;
	private Seminar seminar;
	private String correctAnswer;
	private Integer beVisited;
	private Set votedatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Votequestion() {
	}

	/** minimal constructor */
	public Votequestion(Integer vqid) {
		this.vqid = vqid;
	}

	/** full constructor */
	public Votequestion(Integer vqid, Seminar seminar, String correctAnswer,
			Integer beVisited, Set votedatas) {
		this.vqid = vqid;
		this.seminar = seminar;
		this.correctAnswer = correctAnswer;
		this.beVisited = beVisited;
		this.votedatas = votedatas;
	}

	// Property accessors

	public Integer getVqid() {
		return this.vqid;
	}

	public void setVqid(Integer vqid) {
		this.vqid = vqid;
	}

	public Seminar getSeminar() {
		return this.seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public String getCorrectAnswer() {
		return this.correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Integer getBeVisited() {
		return this.beVisited;
	}

	public void setBeVisited(Integer beVisited) {
		this.beVisited = beVisited;
	}

	public Set getVotedatas() {
		return this.votedatas;
	}

	public void setVotedatas(Set votedatas) {
		this.votedatas = votedatas;
	}

}