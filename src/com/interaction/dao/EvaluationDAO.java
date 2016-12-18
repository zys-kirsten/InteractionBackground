package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Evaluation;

public interface EvaluationDAO {

	public int addEvaluation(Evaluation epo);

	public List<Evaluation> listByCourse(Integer cid);

	public Evaluation findById(int eresult);

	public int updateEvaluation(Evaluation evaluation);

	public int deleteEvaluation(Evaluation evaluation);

	public List<Evaluation> listByEname(Integer cid, String inputValue);

	public List<Evaluation> listByEdescription(Integer cid, String inputValue);

	public List<Evaluation> listByEcalcul(Integer cid, String inputValue);

	public List<Evaluation> findByEname(Integer cid, String ename);
}