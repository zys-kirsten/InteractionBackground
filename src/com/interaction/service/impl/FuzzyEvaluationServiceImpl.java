package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.algorithm.fuzzy.FuzzyEvaluationCompute;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationElementDAO;
import com.interaction.dao.QuantizationFuzzyEvaluationDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.SeminarscoreDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.dao.UnquantizationFuzzyEvaluationDAO;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Quantizationfuzzyevaluation;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Seminarscore;
import com.interaction.pojo.Student;
import com.interaction.pojo.Unquantizationfuzzyevaluation;
import com.interaction.service.FuzzyEvaluationService;

@Service
public class FuzzyEvaluationServiceImpl implements FuzzyEvaluationService{
	
	//成绩评判等级
	private final Integer A = 95;
	private final Integer B = 85;
	private final Integer C = 75;
	private final Integer D = 65;
	private final Integer E = 35;
	
	@Resource
	private UnquantizationFuzzyEvaluationDAO unquantizationFuzzyEvaluationDAOImpl;
	@Resource
	private QuantizationFuzzyEvaluationDAO quantizationFuzzyEvaluationDAOImpl;
	@Resource
	private EvaluationElementDAO evaluationElementDAOImpl;
	@Resource
	private SeminarscoreDAO seminarscoreDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
	

	//进行模糊综合，返回模糊综合之后的结果
	@Override
	public double fuzzyEvaluation(Integer seid,Integer sid,Integer cid){
		double result = 0;
		Course course = courseDAOImpl.findById(cid);
		if (course == null) {
			return 0.0;
		}
		Seminar seminar = seminarDAOImpl.findById(seid);
		if (seminar == null) {
			return 0.0;
		}
		Student student = studentDAOImpl.findById(sid);
		if (student == null) {
			return 0.0;
		}
		
		List<Evaluationelement> basic = evaluationElementDAOImpl.listCourseBasicElement(cid);//非量化指标与量化指标
		double[] basicWeight = new double[basic.size()];
		for(int i = 0; i < basic.size(); i++){
			if (basic.get(i).getWeight() != null) {
				basicWeight[i] = basic.get(i).getWeight();
			}
		}
		
		List<Evaluationelement> secondLevel = evaluationElementDAOImpl.listByFatherId(basic.get(0).getEeid()); //得到自评，组内评，组间评价，师评
		double[] seconeLevalWeight = new double[secondLevel.size()];
		for(int i = 0; i < secondLevel.size(); i++){
			if (secondLevel.get(i).getWeight() != null) {
				seconeLevalWeight[i] = secondLevel.get(i).getWeight();
			}
		}
		
		//分别计算自评，组内评价，组间评评，师评的隶属度,得到非量化指标的评价矩阵
		double[][] secondLevalMatrix = new double[secondLevel.size()][5];
		for (int i = 0; i < secondLevel.size(); i++) {
			List<Evaluationelement> temp = evaluationElementDAOImpl.listByFatherId(secondLevel.get(i).getEeid());
			if (temp != null && temp.size() != 0) {
				double[] tempWeight = new double[temp.size()];
				for (int j = 0; j < tempWeight.length; j++) {
					if (temp.get(j).getWeight() != null) {
						tempWeight[j] = temp.get(j).getWeight();
					}
				}
				double[][] tempMatrix = constructMatrix(temp, seid, sid);
				
				secondLevalMatrix[i] = FuzzyEvaluationCompute.fuzzyCompute(tempWeight, tempMatrix);
				
//***为了学生成绩查询，每一个高等级评价因素的评价结果需要插入到Seminarscore中。
				double[] eval = secondLevalMatrix[i];
				double score = eval[0]*A+eval[1]*B+eval[2]*C+eval[3]*D+eval[4]*E;
				addSeminarscore(seminar,student,secondLevel.get(i),FuzzyEvaluationCompute.round(score, 2));
			}
		}
		//计算非量化指标的隶属度
		double[] unquantization = FuzzyEvaluationCompute.fuzzyCompute(seconeLevalWeight, secondLevalMatrix);
		
		//计算量化指标下的权重与评价矩阵
		List<Evaluationelement> quantizationSub = evaluationElementDAOImpl.listByFatherId(basic.get(1).getEeid());
		double[] quanWeight = new double[quantizationSub.size()];
		for (int i = 0; i < quanWeight.length; i++) {
			if (quantizationSub.get(i).getWeight() != null) {
				quanWeight[i] = quantizationSub.get(i).getWeight();
			}
		}
		double[][] quanMartix = constructMatrix(quantizationSub, seid, sid);
		//计算量化指标的隶属度
		double[] quantization = FuzzyEvaluationCompute.fuzzyCompute(quanWeight, quanMartix);
		
//***为了学生成绩查询，每一个高等级评价因素的评价结果需要插入到Seminarscore中。
		double score = quantization[0]*A+quantization[1]*B+quantization[2]*C+quantization[3]*D+quantization[4]*E;
		addSeminarscore(seminar,student,basic.get(1),FuzzyEvaluationCompute.round(score, 2));
		
		double[][] basicMatrix = {unquantization,quantization};
		
		//计算得到目标隶属度
		double[] resultWeight = FuzzyEvaluationCompute.fuzzyCompute(basicWeight, basicMatrix);
		
		result = resultWeight[0]*A+resultWeight[1]*B+resultWeight[2]*C+resultWeight[3]*D+resultWeight[4]*E;

//***将结果插入到学生总成绩当中
		Seminarclass seminarclass = seminarclassDAOImpl.findByCEE(cid, seid, sid);
		seminarclass.setSeScore(FuzzyEvaluationCompute.round(result, 2));
		seminarclassDAOImpl.updateSeminarclass(seminarclass);
		
		return FuzzyEvaluationCompute.round(result, 2);
	}
	
	//构造某一上层评价因素的评价矩阵
	private double[][] constructMatrix(List<Evaluationelement> evaluationelements,Integer seid, Integer sid){
		
		double[][] result = new double[evaluationelements.size()][5];
		
		if (evaluationelements.get(0).getEvaluationelement().getEename().equals("量化指标")) {
			for (int i = 0; i < evaluationelements.size(); i++) {
				result[i] = quantizationMembershipCompute(seid, sid, evaluationelements.get(i).getEeid());
			}
		}else {
			for (int i = 0; i < evaluationelements.size(); i++) {
				result[i] = unquantizationMembershipCompute(seid, sid, evaluationelements.get(i).getEeid());
			}
			
		}
		return result;
	}
	
	//计算量化指标下的评价因素的隶属度向量
	private double[] quantizationMembershipCompute(Integer seid,Integer sid,Integer eeid){
		double[] result = new double[5];
		
		Quantizationfuzzyevaluation qfe = quantizationFuzzyEvaluationDAOImpl.listQuantizationFuzzyEvaluationBySSE(seid,sid,eeid);
		
		if (qfe != null) {
			double number = (qfe.getGetScore()/qfe.getTotalScore())*100;
			
			if (number >=95) {
				result[0] = 1;
			}else if (number < 95 && number >85) {
				result[0] = (number-85)/10;
			}else {
				result[0] = 0;
			}
			
			if (number >=95 || number<=75) {
				result[1] = 0;
			}else if (number > 75 && number <= 85) {
				result[1] = (number-75)/10;
			}else{
				result[1] = (95-number)/10;
			}
			
			if (number >=85 || number<=65) {
				result[2] = 0;
			}else if (number > 65 && number <= 75) {
				result[2] = (number-65)/10;
			}else{
				result[2] = (85-number)/10;
			}
			
			if (number >=75 || number<=35) {
				result[3] = 0;
			}else if (number > 35 && number <= 65) {
				result[3] = (number-35)/30;
			}else{
				result[3] = (75-number)/10;
			}
			
			if (number<=30) {
				result[4] = 1;
			}else if (number > 35 && number < 65) {
				result[4] = (65-number)/30;
			}else{
				result[4] = 0;
			}
		}
		
		return result;
	}
	
	//计算某一非量化评价因素的隶属度向量
	private double[] unquantizationMembershipCompute(Integer seid,Integer sid,Integer eeid){
		double[] result = new double[5];
		int rank1 = 0, rank2 = 0, rank3 = 0, rank4 = 0, rank5 = 0; //记录五个等级数目
		int totalNum = 0;
		
		//根据研讨课ID，被评价者ID，评价因素ID查找出某一评价因素的所有记录
		List<Unquantizationfuzzyevaluation> ufe = unquantizationFuzzyEvaluationDAOImpl.listUnquantizationFuzzyEvaluationBySSE(seid,sid,eeid);
		
		if (ufe != null && ufe.size() != 0) {
			totalNum = ufe.size();
			for (int i = 0; i < totalNum; i++) {
				if (ufe.get(i).getEvalRank().equals("优秀")) {
					rank1++;
				}else if (ufe.get(i).getEvalRank().equals("良好")) {
					rank2++;
				}else if (ufe.get(i).getEvalRank().equals("中等")) {
					rank3++;
				}else if (ufe.get(i).getEvalRank().equals("合格")) {
					rank4++;
				}else{
					rank5++;
				}
			}
			
			result[0] = FuzzyEvaluationCompute.round((double)rank1/totalNum, 3);
			result[1] = FuzzyEvaluationCompute.round((double)rank2/totalNum,3);
			result[2] = FuzzyEvaluationCompute.round((double)rank3/totalNum, 3);
			result[3] = FuzzyEvaluationCompute.round((double)rank4/totalNum, 3);
			result[4] = FuzzyEvaluationCompute.round((double)rank5/totalNum, 3);
		}
		return result;
	}
	
	//向seminarscore表中插入数据
	private void addSeminarscore(Seminar seminar, Student student, Evaluationelement evaluationelement, double score) {
		Seminarscore seminarscore = new Seminarscore();
		seminarscore.setEvaluationelement(evaluationelement);
		seminarscore.setSeminar(seminar);
		seminarscore.setStudent(student);
		seminarscore.setSscore(score);
		seminarscoreDAOImpl.addSeminarscore(seminarscore);
	}
	
}
