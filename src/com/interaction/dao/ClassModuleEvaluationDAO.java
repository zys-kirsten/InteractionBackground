package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Classmoduleevaluation;

public interface ClassModuleEvaluationDAO {

	int addClassModuleEvaluation(Classmoduleevaluation cme);

	List<Classmoduleevaluation> listByCmid(Integer cmid);

	int deleteCme(List<Classmoduleevaluation> classmoduleevaluations);

}
