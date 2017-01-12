package com.interaction.vo;

import java.util.List;

public class SeminarscoreVo {
	private double totalScore;
	private List<EvaluationElementScore> elementScores;
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public List<EvaluationElementScore> getElementScores() {
		return elementScores;
	}
	public void setElementScores(List<EvaluationElementScore> elementScores) {
		this.elementScores = elementScores;
	}
	
	

}
