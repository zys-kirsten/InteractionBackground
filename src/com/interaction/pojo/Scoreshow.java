package com.interaction.pojo;

/**
 * Scoreshow entity. @author MyEclipse Persistence Tools
 */

public class Scoreshow implements java.io.Serializable {

	// Fields

	private Integer ssid;
	private Evaluation evaluation;
	private String escoreShow;
	private Double escore;

	// Constructors

	/** default constructor */
	public Scoreshow() {
	}

	/** full constructor */
	public Scoreshow(Evaluation evaluation, String escoreShow, Double escore) {
		this.evaluation = evaluation;
		this.escoreShow = escoreShow;
		this.escore = escore;
	}

	// Property accessors

	public Integer getSsid() {
		return this.ssid;
	}

	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getEscoreShow() {
		return this.escoreShow;
	}

	public void setEscoreShow(String escoreShow) {
		this.escoreShow = escoreShow;
	}

	public Double getEscore() {
		return this.escore;
	}

	public void setEscore(Double escore) {
		this.escore = escore;
	}

	@Override
	public String toString() {
		return "Scoreshow [ssid=" + ssid + ", evaluation=" + evaluation + ", escoreShow=" + escoreShow + ", escore="
				+ escore + "]";
	}

	
}