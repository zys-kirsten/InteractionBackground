package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;

public interface EvaluationElementService {

	List<Evaluationelement> listByFatherId(Integer eeid);

	int updateEvaluationElement(List<Evaluationelement> elements);

	List<Evaluationelement> listCourseBasicElement(Integer cid);

	List<List<Evaluationelement>> listCourseElements(Integer cid);

	List<Evaluationelement> listCourseFiveEvaluationElements(Integer cid);

	int addFiveEvaluationElements(Course course);
	
	Evaluationelement findById(Integer eeid);

	int addEvaluationElement(Evaluationelement evaluationelement);

	void deleteEvaluationElement(Integer eeid);


}
