package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Evaluationelement;

public interface EvaluationElementDAO {

	public int addEvaluationElement(Evaluationelement evaluationelement);

	public Evaluationelement findById(int eeid);

	public int updateEvaluationElement(Evaluationelement evaluationelement);
	
	public int updateEvaluationElements(List<Evaluationelement> evaluationelements);

	public int deleteEvaluationElement(Evaluationelement evaluationelement);
	
	public List<Evaluationelement> listByCourse(Integer cid);
	
	public List<Evaluationelement> listByFatherId(Integer eeid);

	public List<Evaluationelement> listCourseBasicElement(Integer cid);
}
