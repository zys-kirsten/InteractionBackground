package com.interaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.AHP.ComputeWeight;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.service.EvaluationElementService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.EvaluationElementVo;
import com.interaction.vo.EvaluationWeightVo;

@Controller
public class EvaluationElementController { 
	
	@Resource
	private EvaluationElementService evaluationElementServiceImpl;

//============================================学生学习效果评价设置======================================================	
	//页面点击“学生学习效果评价设置”
	@RequestMapping("/ShowStudentAchieveWeight")
	public String ShowStudentAchieveWeight(Integer cid){
		List<Evaluationelement> elements = evaluationElementServiceImpl.listCourseBasicElement(cid);
		SessionUtil.getMySession().setAttribute("elementssaws", elements);
		return "evaluation/configStudentAchieve";
		
	}
	//“学生学习效果评价设置”提交配置结果（就分为量化指标与非量化指标，不改变）
	@RequestMapping("/CommitStudentAchieveWeight")
	public String CommitStudentAchieveWeight(HttpServletRequest request){
	    
		List<Evaluationelement> elements = evaluationElementServiceImpl.listCourseBasicElement(getCourse().getCid());
		String unquantization = request.getParameter("非量化指标");
		String quantization = request.getParameter("量化指标");
		elements.get(0).setWeight(Double.parseDouble(unquantization));
		elements.get(1).setWeight(Double.parseDouble(quantization));
		evaluationElementServiceImpl.updateEvaluationElement(elements);
		SessionUtil.getMySession().setAttribute("elementssaws", elements);
		return "evaluation/configStudentAchieve";
	}
//================================================================================================================
	
//====================================非量化指标评价设置===========================================================
	//页面点击“非量化指标评价设置”
	@RequestMapping("/ShowUnquantizationWeight")
	public String ShowUnquantizationWeight(Integer eeid){
		System.out.println("ShowUnquantizationWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);	
		SessionUtil.getMySession().setAttribute("uqmsg", "");	
		SessionUtil.getMySession().setAttribute("elementsuws", elements);
		return "evaluation/configUnquantization";
	}
	
	//“非量化指标评价设置”提交配置结果
	@RequestMapping("/CommitUnquantizationWeight")
	public String CommitUnquantizationWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//非量化指标设置是否合理
		String[] unquantization = request.getParameterValues("unquantization");
		for (int i = 0; i < unquantization.length; i++) {
			System.out.println(unquantization[i]);
		}
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		System.out.println("fatherid" + fatherId);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int unquantizaiotnIndex = 0;
		
		Double[][] unquantizationIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				unquantizationIndexMatrix[i][j] = stringToDouble(unquantization[unquantizaiotnIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(unquantizationIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("uqmsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementsuws", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("uqmsg", "");	
			SessionUtil.getMySession().setAttribute("elementsuws", elements);	
		}
		return "evaluation/configUnquantization";
	}
//=============================================================================================================
//====================================量化指标评价设置===========================================================
	//页面点击“量化指标评价设置”
	@RequestMapping("/ShowQuantizationWeight")
	public String ShowQuantizationWeight(Integer eeid){
		System.out.println("ShowQuantizationWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);
		SessionUtil.getMySession().setAttribute("qmsg", "");	
		SessionUtil.getMySession().setAttribute("elementsqws", elements);
		return "evaluation/configQuantization";
	}
	
	//“量化指标评价设置”提交配置结果
	@RequestMapping("/CommitQuantizationWeight")
	public String CommitQuantizationWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//量化指标设置是否合理
		String[] quantization = request.getParameterValues("quantization");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int quantizaiotnIndex = 0;
		
		Double[][] quantizationIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				quantizationIndexMatrix[i][j] = stringToDouble(quantization[quantizaiotnIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(quantizationIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("qmsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementsuws", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("qmsg", "");	
			SessionUtil.getMySession().setAttribute("elementsqws", elements);	
		}
		return "evaluation/configQuantization";
	}
//=============================================================================================================
//====================================学生自评设置===========================================================
	//页面点击“量学生自评设置”
	@RequestMapping("/ShowSelfEvalWeight")
	public String ShowSelfEvalWeight(Integer eeid){
		System.out.println("ShowSelfWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);	
		SessionUtil.getMySession().setAttribute("selfmsg", "");	
		SessionUtil.getMySession().setAttribute("elementssews", elements);
		return "evaluation/configSelfEval";
	}
	
	//“学生自评设置”提交配置结果
	@RequestMapping("/CommitSelfEvalWeight")
	public String CommitSelfEvalWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//学生自评设置是否合理
		String[] selfEval = request.getParameterValues("selfEval");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int selfEvalIndex = 0;
		
		Double[][] selfEvalIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				selfEvalIndexMatrix[i][j] = stringToDouble(selfEval[selfEvalIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(selfEvalIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("selfmsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementssews", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("selfmsg", "");	
			SessionUtil.getMySession().setAttribute("elementssews", elements);	
		}
		return "evaluation/configSelfEval";
	}
//=============================================================================================================

//====================================学生评价设置===========================================================
	//页面点击“学生评价设置”
	@RequestMapping("/ShowStuEvalWeight")
	public String ShowStuEvalWeight(Integer eeid){
		System.out.println("ShowStuEvalWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);
		SessionUtil.getMySession().setAttribute("stumsg", "");
		SessionUtil.getMySession().setAttribute("elementsstuews", elements);
		return "evaluation/configStuEval";
	}
	
	//“学生评价设置”提交配置结果
	@RequestMapping("/CommitStuEvalWeight")
	public String CommitStuEvalWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//学生评价设置是否合理
		String[] stuEval = request.getParameterValues("stuEval");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int stuEvalIndex = 0;
		
		Double[][] stuEvalIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				stuEvalIndexMatrix[i][j] = stringToDouble(stuEval[stuEvalIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(stuEvalIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("stumsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementsstuews", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("stumsg", "");	
			SessionUtil.getMySession().setAttribute("elementsstuews", elements);	
		}
		return "evaluation/configStuEval";
	}
//=============================================================================================================
//====================================教师评价设置===========================================================
	//页面点击“教师评价设置”
	@RequestMapping("/ShowTeacherEvalWeight")
	public String ShowTeacherEvalWeight(Integer eeid){
		System.out.println("ShowTeacherEvalWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);	
		SessionUtil.getMySession().setAttribute("teachermsg", "");
		SessionUtil.getMySession().setAttribute("elementstews", elements);
		return "evaluation/configTeacherEval";
	}
	
	//“教师评价设置”提交配置结果
	@RequestMapping("/CommitTeacherEvalWeight")
	public String CommitTeacherEvalWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//教师评价设置是否合理
		String[] teacherEval = request.getParameterValues("teacherEval");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int teacherEvalIndex = 0;
		
		Double[][] teacherEvalIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				teacherEvalIndexMatrix[i][j] = stringToDouble(teacherEval[teacherEvalIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(teacherEvalIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("teachermsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementstews", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("teachermsg", "");	
			SessionUtil.getMySession().setAttribute("elementstews", elements);	
		}
		return "evaluation/configTeacherEval";
	}
//=============================================================================================================

	
	//根据前台传递的包含配置数据的字符串数组长度计算一共有多少个评价因素（不用查询数据库，效率更高）
//	private int getDimension(int length) {
//		int number = 1;
//		int sum = 0;
//		while(sum < length){
//			sum += number++;
//		}
//		return number;
	
	//实验数据
//			EvaluationElementVo e1 = new EvaluationElementVo(1,"学生自评");
//			e1.setWeight(1.0);
//			e1.setFatherId(5);
//			EvaluationElementVo e2 = new EvaluationElementVo(2, "学生评价");
//			e2.setWeight(0.234);
//			EvaluationElementVo e3 = new EvaluationElementVo(3, "教师评价");
//			EvaluationElement e4 = new EvaluationElement(4, "教师评价");
//			EvaluationElement e5 = new EvaluationElement(5, "教师评价");
//			e3.setWeight(0.111);
//			elements.add(e1);
//			elements.add(e2);
//			elements.add(e3);
//			elements.add(e4);
//			elements.add(e5);
			//实验数据
//	}

	
		
		
	//“量化指标评价设置”提交配置结果
	@RequestMapping("/CommitShowQuantizationWeight")
	public String CommitShowQuantizationWeight(EvaluationWeightVo evaluationWeightVo){
		//量化指标设置是否合理
		Double[][] quantizationIndexMatrix = new Double[evaluationWeightVo.getQuantizationIndex().length][evaluationWeightVo.getQuantizationIndex().length];
		for (int i = 0; i < quantizationIndexMatrix.length; i++) {
			for(int j = i+1; j < quantizationIndexMatrix.length; j++){
				quantizationIndexMatrix[i][j] = stringToDouble(evaluationWeightVo.getQuantizationIndex()[i][j]);
			}
		}
		Double[] result = ComputeWeight.calculWeight(quantizationIndexMatrix, quantizationIndexMatrix.length);
		//1.如果result=null，页面返回提示信息，要求教师重新设置各个值
		//2.如果result!=null，将权值结果更新到数据库 
		//3.将权值结果放到session中，页面显示设置好的权值
		return "evaluation/configStudentAchieve";
		
	}
	
	@RequestMapping("/ComputeWeight")
	public void ComputeWeight(EvaluationWeightVo evaluationWeightVo){
		//量化指标设置是否合理
		Double[][] quantizationIndexMatrix = new Double[evaluationWeightVo.getQuantizationIndex().length][evaluationWeightVo.getQuantizationIndex().length];
		for (int i = 0; i < quantizationIndexMatrix.length; i++) {
			for(int j = i+1; j < quantizationIndexMatrix.length; j++){
				quantizationIndexMatrix[i][j] = stringToDouble(evaluationWeightVo.getQuantizationIndex()[i][j]);
			}
		}
		ComputeWeight.calculWeight(quantizationIndexMatrix, quantizationIndexMatrix.length);
//		for (int i = 0; i < quantizationIndexMatrix.length; i++) {
//			for(int j = 0; j < quantizationIndexMatrix.length; j++){
//				System.out.println("quantization["+i+"]["+j+"]="+quantizationIndexMatrix[i][j]);;
//			}
//		}
		
	}
	@RequestMapping("/ShowWeight")
	public String ShowWeight(Integer cid){
		System.out.println("showWeight  cid==>"+cid);
		EvaluationWeightVo evaluationWeightVo = new EvaluationWeightVo();
		evaluationWeightVo.setQuantization(0.9);
		String[][] quantizationIndex = {{"1.0","1.0","1.0","1.0"},{"1.0","1.0","1.0","1.0"},{"1.0","1.0","1.0","1.0"}};
		evaluationWeightVo.setQuantizationIndex(quantizationIndex);
		SessionUtil.getMySession().setAttribute("evaluationWeightVo", evaluationWeightVo);
		return "evaluation/configEvaluationWeight";
		
	}

	//将接收到的String转换成double
	private double stringToDouble(String string) {

		if (string.equals("1.0")) {
			return 1.0;
		}else if (string.equals("2.0")) {
			return 2.0;
		}else if (string.equals("3.0")) {
			return 3.0;
		}else if (string.equals("4.0")) {
			return 4.0;
		}else if (string.equals("5.0")) {
			return 5.0;
		}else if (string.equals("6.0")) {
			return 6.0;
		}else if (string.equals("7.0")) {
			return 7.0;
		}else if (string.equals("8.0")) {
			return 8.0;
		}else if (string.equals("9.0")) {
			return 9.0;
		}else if (string.equals("1.0/2.0")) {
			return 1.0/2.0;
		}else if (string.equals("1.0/3.0")) {
			return 1.0/3.0;
		}else if (string.equals("1.0/4.0")) {
			return 1.0/4.0;
		}else if (string.equals("1.0/5.0")) {
			return 1.0/5.0;
		}else if (string.equals("1.0/6.0")) {
			return 1.0/6.0;
		}else if (string.equals("1.0/7.0")) {
			return 1.0/7.0;
		}else if (string.equals("1.0/8.0")) {
			return 1.0/8.0;
		}else if (string.equals("1.0/9.0")) {
			return 1.0/9.0;
		}
		return 0;
	}
	
		//页面点击“量化指标评价设置”
		@RequestMapping("/ShowQuantization")
		public String ShowQuantization(Integer cid){
			System.out.println("ShowStudentAchieveWeight  cid==>"+cid);
			//1.根据cid
			List<EvaluationElementVo> elementssaws = new ArrayList<EvaluationElementVo>();
			EvaluationElementVo elementssaw = new EvaluationElementVo(1,"量化因素");
			EvaluationElementVo element = new EvaluationElementVo(2, "非量化因素");
			elementssaws.add(elementssaw);
			elementssaws.add(element);
			SessionUtil.getMySession().setAttribute("elementssaws", elementssaws);
			return "evaluation/configStudentAchieve";
			
		}
		
		//获得session中的课程信息
		private Course getCourse(){
			return (Course) SessionUtil.getMySession().getAttribute("course");
		}
}  