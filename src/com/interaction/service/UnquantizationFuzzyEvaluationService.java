package com.interaction.service;

import java.util.List;

import com.interaction.vo.AndroidEvaluationVo;

public interface UnquantizationFuzzyEvaluationService {

	int submitEvaluations(List<AndroidEvaluationVo> list);

}
