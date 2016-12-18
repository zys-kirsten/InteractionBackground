package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Evaluation;
import com.interaction.vo.EvaluationVo;

public interface EvaluationService {

	public int addEvaluation(EvaluationVo evaluationVo);

	public List<EvaluationVo> listByCourse(Integer cid);

	public int updateQuestion(EvaluationVo evaluationVo);

	public int deleteEvaluationById(Integer eid);

	public EvaluationVo findById(Integer eid);

	public List<EvaluationVo> findByCondition(Integer cid, String condition, String inputValue);

	public Evaluation findByEname(Integer cid, String string);

}
