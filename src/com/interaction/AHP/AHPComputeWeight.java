package com.interaction.AHP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.utils.SessionUtil;
import com.interaction.vo.EvaluationElement;
import com.interaction.vo.EvaluationWeightVo;

@Controller
public class AHPComputeWeight { 
	
	//页面点击“非量化指标评价设置”
	@RequestMapping("/ShowUnquantizationWeight")
	public String ShowUnquantizationWeight(Integer eeid){
		System.out.println("ShowUnquantizationWeight  eeid==>"+eeid);
		List<EvaluationElement> elementsuws = new ArrayList<EvaluationElement>();//1.根据eeid找到父因素ID为eeid的因素
		
		//实验数据
		EvaluationElement elementssaw = new EvaluationElement(3,"学生自评");
		elementssaw.setWeight(1.0);
		EvaluationElement element = new EvaluationElement(4, "学生评价");
		element.setWeight(0.234);
		EvaluationElement element2 = new EvaluationElement(4, "教师评价");
		element.setWeight(0.111);
		elementsuws.add(elementssaw);
		elementsuws.add(element);
		elementsuws.add(element2);
		//实验数据
		
		SessionUtil.getMySession().setAttribute("elementsuws", elementsuws);
		return "evaluation/configUnquantization";
		
	}
	
	//“非量化指标评价设置”提交配置结果
	@RequestMapping("/CommitShowUnquantizationWeight")
	public String CommitShowUnquantizationWeight(EvaluationWeightVo evaluationWeightVo){
		//非量化指标设置是否合理
		
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
	
	//页面点击“学生学习效果评价设置”
	@RequestMapping("/ShowStudentAchieveWeight")
	public String ShowStudentAchieveWeight(Integer cid){
		System.out.println("ShowStudentAchieveWeight  cid==>"+cid);
		List<EvaluationElement> elementssaws = new ArrayList<EvaluationElement>();//1.根据cid找到父因素ID为0的因素，即量化因素与非量化因素
		
		//实验数据
		EvaluationElement elementssaw = new EvaluationElement(1,"量化因素");
		elementssaw.setWeight(1.0);
		EvaluationElement element = new EvaluationElement(2, "非量化因素");
		element.setWeight(0.234);
		elementssaws.add(elementssaw);
		elementssaws.add(element);
		//实验数据
		
		SessionUtil.getMySession().setAttribute("elementssaws", elementssaws);
		return "evaluation/configStudentAchieve";
		
	}
	//“学生学习效果评价设置”提交配置结果（就分为量化指标与非量化指标，不改变）
	@RequestMapping("/CommitStudentAchieveWeight")
	public String CommitStudentAchieveWeight(EvaluationWeightVo evaluationWeightVo){
	    //学生学习效果评价设置
	    //1.直接调用service层更新量化指标权值与非量化指标权值
		//2.将二者的值放到session中，页面显示权值配置后的结果
         List<EvaluationElement> elementssaws = new ArrayList<EvaluationElement>();//1.根据cid找到父因素ID为0的因素，即量化因素与非量化因素
		
		//实验数据
		EvaluationElement elementssaw = new EvaluationElement(1,"量化因素");
		elementssaw.setWeight(0.5);
		EvaluationElement element = new EvaluationElement(2, "非量化因素");
		element.setWeight(0.55);
		elementssaws.add(elementssaw);
		elementssaws.add(element);
		//实验数据
		
		SessionUtil.getMySession().setAttribute("elementssaws", elementssaws);
		return "evaluation/configStudentAchieve";
	}
		
		
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
			List<EvaluationElement> elementssaws = new ArrayList<EvaluationElement>();
			EvaluationElement elementssaw = new EvaluationElement(1,"量化因素");
			EvaluationElement element = new EvaluationElement(2, "非量化因素");
			elementssaws.add(elementssaw);
			elementssaws.add(element);
			SessionUtil.getMySession().setAttribute("elementssaws", elementssaws);
			return "evaluation/configStudentAchieve";
			
		}
}  