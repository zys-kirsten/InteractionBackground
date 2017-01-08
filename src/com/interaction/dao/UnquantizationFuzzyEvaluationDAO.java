package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Unquantizationfuzzyevaluation;

public interface UnquantizationFuzzyEvaluationDAO {

	List<Unquantizationfuzzyevaluation> listUnquantizationFuzzyEvaluationBySSE(Integer seid, Integer sid, Integer eeid);

}
