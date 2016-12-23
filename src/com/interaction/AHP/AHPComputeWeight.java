package com.interaction.AHP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.vo.EvaluationWeightVo;

@Controller
public class AHPComputeWeight {  
  
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
}  