package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationElementDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Seminar;
import com.interaction.service.EvaluationElementService;

@Service
public class EvaluationElementServiceImpl implements EvaluationElementService{

	@Resource
	private EvaluationElementDAO evaluationElementDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl; 

	
//================================================ Android端 ======================================================================
	//执行各项评价（包括启动与结束）
	@Override
	public void executeEvaluation(int seid,String fatherName,String condition) {
		Seminar seminar = seminarDAOImpl.findById(seid);
		List<Evaluationelement> evaluationelements = evaluationElementDAOImpl.listByCourseAndFatherName(seminar.getCourse().getCid(),fatherName);
		if (evaluationelements != null) {
			if (condition.equals("start")) {
				for(Evaluationelement e:evaluationelements){
					e.setBevisited(1);
				}
			}else {
				for(Evaluationelement e:evaluationelements){
					e.setBevisited(0);
				}
			}
			evaluationElementDAOImpl.updateEvaluationElements(evaluationelements);
		}
	}
	
	
//================================================PC端============================================================================
	
	//根据父级ID查找评价元素列表
	@Override
	public List<Evaluationelement> listByFatherId(Integer eeid) {
		return evaluationElementDAOImpl.listByFatherId(eeid);
	}

	//更新评价元素
	@Override
	public int updateEvaluationElement(List<Evaluationelement> elements) {
		return evaluationElementDAOImpl.updateEvaluationElements(elements);
	}

	//显示某门课顶层评价因素(非量化因素，量化因素)
	@Override
	public List<Evaluationelement> listCourseBasicElement(Integer cid) {
		return evaluationElementDAOImpl.listCourseBasicElement(cid) ;
	}

	//按等级显示某门课所有的评价因素
	@Override
	public List<List<Evaluationelement>> listCourseElements(Integer cid) {
		List<List<Evaluationelement>> lists = new ArrayList<>();
		List<Evaluationelement> list = evaluationElementDAOImpl.listCourseBasicElement(cid);
		lists.add(list);//添加非量化指标与量化指标
		
		List<Evaluationelement> list1 = evaluationElementDAOImpl.listByFatherId(list.get(0).getEeid());
		lists.add(list1);//添加学生自评、组内评价、组间评价、教师评价
		
		list = evaluationElementDAOImpl.listByFatherId(list.get(1).getEeid());
		lists.add(list);//添加量化指标的面的各个因素
		
		for (int i = 0; i < list1.size(); i++) {
			List<Evaluationelement> temp = evaluationElementDAOImpl.listByFatherId(list1.get(i).getEeid());
			lists.add(temp);//添加学生自评、组内评价、组间评价、教师评价下面的个各评价因素
		}
		return lists;
	}

	//显示某门课的六个评价指标（非量化因素、量化因素、自评、组内评价、组间评价、师评）
	@Override
	public List<Evaluationelement> listCourseSixEvaluationElements(Integer cid) {
		List<Evaluationelement> list = new ArrayList<Evaluationelement>();
		List<Evaluationelement> list2 = evaluationElementDAOImpl.listCourseBasicElement(cid);
		list.addAll(list2);
		list2 = evaluationElementDAOImpl.listByFatherId(list2.get(0).getEeid());
		list.addAll(list2);
		return list;
	}

	//添加某门课的六个评价指标（非量化因素、量化因素、自评、组内评价、组间评价、师评）
	@Override
	public int addSixEvaluationElements(Course course) {
		int result = 0;
		int sum = 0;
		int unquantizationId = 0;
		int quantizationId = 0;
		for (int i = 0; i < 9; i++) {
			Evaluationelement evaluationelement = new Evaluationelement();
			evaluationelement.setCourse(course);
			
			if (i == 0) {
				evaluationelement.setEename("非量化指标");
				evaluationelement.setIsleaf(0);
			}else if (i == 1) {
				evaluationelement.setEename("量化指标");
				evaluationelement.setIsleaf(0);
			}else{
				if (i <= 5) {
					evaluationelement.setIsleaf(0);
					Evaluationelement unquantization = evaluationElementDAOImpl.findById(unquantizationId);
					evaluationelement.setEvaluationelement(unquantization);
					if (i == 2) {
					    evaluationelement.setEename("学生自评");
					}else if (i == 3) {
						evaluationelement.setEename("组内评价");
					}else if (i == 4) {
						evaluationelement.setEename("组间评价");
					}else{
						evaluationelement.setEename("教师评价");
					}
				}else{
					evaluationelement.setIsleaf(1);
					Evaluationelement quantization = evaluationElementDAOImpl.findById(quantizationId);
					evaluationelement.setEvaluationelement(quantization);
					if (i == 6) {
					    evaluationelement.setEename("课堂练习题");
					}else if (i == 7) {
						evaluationelement.setEename("课堂抢答题");
					}else{
						evaluationelement.setEename("课堂投票");
					}
				}
			}
			
			result = evaluationElementDAOImpl.addEvaluationElement(evaluationelement);
			sum += result;
			if (i == 0) {
				unquantizationId = result;
			}
			if (i == 1) {
				quantizationId = result;
			}
		}
		return sum;
	}

	//添加评价元素
	@Override
	public int addEvaluationElement(Evaluationelement evaluationelement) {
		evaluationelement.setIsleaf(1);
		return evaluationElementDAOImpl.addEvaluationElement(evaluationelement);
	}
	
	//根据eeid查找evaluationelement
	@Override
	public Evaluationelement findById(Integer eeid) {
		return evaluationElementDAOImpl.findById(eeid);
	}
	
	//删除某一元素
	@Override
	public void deleteEvaluationElement(Integer eeid) {
		evaluationElementDAOImpl.deleteEvaluationElement(evaluationElementDAOImpl.findById(eeid));
	}
}
