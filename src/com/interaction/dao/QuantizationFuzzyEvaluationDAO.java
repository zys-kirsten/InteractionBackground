package com.interaction.dao;

import com.interaction.pojo.Quantizationfuzzyevaluation;

public interface QuantizationFuzzyEvaluationDAO {

	Quantizationfuzzyevaluation listQuantizationFuzzyEvaluationBySSE(Integer seid, Integer sid, Integer eeid);

}
