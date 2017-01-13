package com.interaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.algorithm.AHP.ComputeWeight;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.service.EvaluationElementService;
import com.interaction.utils.SessionUtil;

@Controller
public class EvaluationElementController { 
	
	@Resource
	private EvaluationElementService evaluationElementServiceImpl;
	
	

	//删除某一评价因素
	@RequestMapping("/deleteEvaluationElement")
	public String deleteEvaluationElement(Integer fatherId,Integer eeid,HttpServletResponse response){
		 evaluationElementServiceImpl.deleteEvaluationElement(eeid);
		 return enterNextEvaluationElement(fatherId);
	}
	
	//修改前显示某一评价信息
	@RequestMapping("/editEvaluationElement")
	public String editEvaluationElement(Integer eeid){
		Evaluationelement evaluationelement = evaluationElementServiceImpl.findById(eeid);
		SessionUtil.getMySession().setAttribute("ee", evaluationelement);
		return "evaluation/editEvaluationElement";
	}
	
	//添加(修改)某一评价等级下面的具体评价因素
	@RequestMapping("/addEvaluationElement")
	public String addEvaluationElement(Evaluationelement evaluationelement){
		int result = -1;
		evaluationelement.setCourse(getCourse());
		if (evaluationelement.getEeid() == null) {
		    result = evaluationElementServiceImpl.addEvaluationElement(evaluationelement);//添加评价因素
		}else{
			evaluationelement.setIsLeaf(1);//修改评价因素
			List<Evaluationelement> elements = new ArrayList<Evaluationelement>();
			elements.add(evaluationelement);
			result = evaluationElementServiceImpl.updateEvaluationElement(elements);
		}
		return enterNextEvaluationElement(evaluationelement.getEvaluationelement().getEeid());
	}

	//查看某一课程等级一评价因素
	@RequestMapping("/listEvaluationElement")
	public String listEvaluationElement(Integer cid){
		List<Evaluationelement>  list = evaluationElementServiceImpl.listCourseBasicElement(cid);
		SessionUtil.getMySession().setAttribute("list", list);
		return "evaluation/listEvaluationElement";
	}

	//进入某一等级的下一个等级评价因素列表
	@RequestMapping("/enterNextEvaluationElement")
	public String enterNextEvaluationElement(Integer eeid){
		List<Evaluationelement>  list = evaluationElementServiceImpl.listByFatherId(eeid);
		SessionUtil.getMySession().setAttribute("list", list);
		SessionUtil.getMySession().setAttribute("fatherId", eeid);
		SessionUtil.getMySession().setAttribute("content", evaluationElementServiceImpl.findById(eeid).getEename());
		return "evaluation/listEvaluationElement";
	}
	
	//返回某一等级的上一个等级评价因素列表
	@RequestMapping("/returnUpEvaluationElement")
	public String returnUpEvaluationElement(Integer eeid){
		Evaluationelement evaluationelement = evaluationElementServiceImpl.findById(eeid);
		if (evaluationelement.getEvaluationelement() == null) {
			List<Evaluationelement>  list = evaluationElementServiceImpl.listCourseBasicElement(getCourse().getCid());
			SessionUtil.getMySession().setAttribute("list", list);
			SessionUtil.getMySession().setAttribute("fatherId", eeid);
			SessionUtil.getMySession().setAttribute("content", evaluationElementServiceImpl.findById(eeid).getEename());
			return "evaluation/listEvaluationElement";
		}
		return enterNextEvaluationElement(evaluationelement.getEvaluationelement().getEeid());
	}
	
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

//====================================组内评价设置===========================================================
	//页面点击“组内评价设置”
	@RequestMapping("/ShowInEvalWeight")
	public String ShowInEvalWeight(Integer eeid){
		System.out.println("ShowInEvalWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);
		SessionUtil.getMySession().setAttribute("inmsg", "");
		SessionUtil.getMySession().setAttribute("elementsinews", elements);
		return "evaluation/configInEval";
	}
	
	//“组内评价设置”提交配置结果
	@RequestMapping("/CommitInEvalWeight")
	public String CommitInEvalWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//组内评价设置是否合理
		String[] inEval = request.getParameterValues("inEval");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int inEvalIndex = 0;
		
		Double[][] inEvalIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				inEvalIndexMatrix[i][j] = stringToDouble(inEval[inEvalIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(inEvalIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("inmsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementsinews", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("inmsg", "");	
			SessionUtil.getMySession().setAttribute("elementsinews", elements);	
		}
		return "evaluation/configInEval";
	}
//=============================================================================================================
//====================================组间评价设置===========================================================
	//页面点击“组间评价设置”
	@RequestMapping("/ShowOutEvalWeight")
	public String ShowOutEvalWeight(Integer eeid){
		System.out.println("ShowOutEvalWeight  eeid==>"+eeid);
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(eeid);
		SessionUtil.getMySession().setAttribute("outmsg", "");
		SessionUtil.getMySession().setAttribute("elementsoutews", elements);
		return "evaluation/configOutEval";
	}
	
	//“组间评价设置”提交配置结果
	@RequestMapping("/CommitOutEvalWeight")
	public String CommitOutEvalWeight(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//组间评价设置是否合理
		String[] outEval = request.getParameterValues("outEval");
		
		Integer fatherId = Integer.parseInt(request.getParameter("fatherId"));
		List<Evaluationelement> elements = evaluationElementServiceImpl.listByFatherId(fatherId);
		int dimension = elements.size(); 
		int outEvalIndex = 0;
		
		Double[][] outEvalIndexMatrix = new Double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for(int j = i+1; j < dimension; j++){
				outEvalIndexMatrix[i][j] = stringToDouble(outEval[outEvalIndex++]);
			}
		}
		
		Double[] result = ComputeWeight.calculWeight(outEvalIndexMatrix, dimension);
		if (result == null || result.length == 0) {
			SessionUtil.getMySession().setAttribute("outmsg", "权重设置不合理，原权重为:");
			SessionUtil.getMySession().setAttribute("elementsoutews", elements);
		}else {
			for (int i = 0; i < result.length; i++) {
				elements.get(i).setWeight(result[i]);
			}
			evaluationElementServiceImpl.updateEvaluationElement(elements);
			SessionUtil.getMySession().setAttribute("outmsg", "");	
			SessionUtil.getMySession().setAttribute("elementsoutews", elements);	
		}
		return "evaluation/configOutEval";
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
		
	//获得session中的课程信息
	private Course getCourse(){
		return (Course) SessionUtil.getMySession().getAttribute("course");
	}
//	//查看某一课程的所有等级评价因素(按等级显示)
//	@RequestMapping("/listEvaluationElement")
//	public String listEvaluationElement(Integer cid){
//		List<List<Evaluationelement>>  lists = evaluationElementServiceImpl.listCourseElements(cid);
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println("000000000000000000");
//			for (int j = 0; j < lists.get(i).size(); j++) {
//				System.out.println(lists.get(i).get(j).getEename());
//			}
//			System.out.println("000000000000000000");
//		}
//		SessionUtil.getMySession().setAttribute("lists", lists);
//		return "evaluation/listEvaluationElement";
//	}
	
}  