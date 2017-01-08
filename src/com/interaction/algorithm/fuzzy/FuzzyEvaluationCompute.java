package com.interaction.algorithm.fuzzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.vo.FuzzyEvaluation;

public class FuzzyEvaluationCompute {
	
	//权重与对应评价矩阵进行模糊综合（向量与矩阵相乘）,并进行归一化处理
	public static double[] fuzzyCompute(double[] weight, double[][] fuzzyMatrix){
		double[] result = new double[5];
		double sum = 0;  //为了归一化
		//模糊综合
		for(int i = 0; i < 5; i++){
			for (int j = 0; j < weight.length; j++) {
				result[i] += weight[j]*fuzzyMatrix[j][i];
			}
			sum += result[i];
		}
		//归一化
		if (sum != 0) {
			for (int i = 0; i < result.length; i++) {
				result[i] = round(result[i]/sum, 3);
			}	
		}
		return result;
	}

	//四舍五入
	public static double round(double v, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(v));  
        BigDecimal one = new BigDecimal("1");  
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }  
	
//================================================================================================================
	//构造某一上层评价因素的评价矩阵
	public static double[][] constructMatrix(List<Evaluationelement> evaluationelements,Integer seid, Integer sid){
		
		double[][] result = new double[evaluationelements.size()][5];
		
		for (int i = 0; i < evaluationelements.size(); i++) {
			double[] temp = FuzzyEvaluationCompute.membershipCompute(seid, sid, evaluationelements.get(i).getEeid());
			result[i] = temp;
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(evaluationelements.get(i).getEename()+"的隶属度向量为：{");
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j]+",");
			}
			System.out.println("}");
		}
		
		System.out.println("评价矩阵为：");
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j]+",");
			}
			System.out.println();
		}
		return result;
	}
	
	//计算某一评价因素的隶属度向量
	public static double[]  membershipCompute(Integer seid,Integer sid,Integer eeid){
		double[] result = new double[5];
		int rank1 = 0, rank2 = 0, rank3 = 0, rank4 = 0, rank5 = 0; //记录五个等级数目
		int totalNum = 0;
		
		//根据研讨课ID，被评价者ID，评价因素ID查找出某一评价因素的所有记录
		List<FuzzyEvaluation> fuzzyEvaluations = new ArrayList<>();//listFuzzyEvaluationBySSE(seid,sid,eeid);
		
		//实验数据
		FuzzyEvaluation f1 = new FuzzyEvaluation(1, 1, 1, "张三", 12, "认真程度", "优秀");
		FuzzyEvaluation f2 = new FuzzyEvaluation(2, 1, 1, "张三", 12, "认真程度", "优秀");
		FuzzyEvaluation f3 = new FuzzyEvaluation(3, 1, 1, "张三", 12, "认真程度", "良好");
		FuzzyEvaluation f4 = new FuzzyEvaluation(4, 1, 1, "张三", 12, "认真程度", "优秀");
		FuzzyEvaluation f5 = new FuzzyEvaluation(5, 1, 1, "张三", 12, "认真程度", "合格");
		FuzzyEvaluation f6 = new FuzzyEvaluation(6, 1, 1, "张三", 12, "认真程度", "优秀");
		FuzzyEvaluation f7 = new FuzzyEvaluation(7, 1, 1, "张三", 12, "认真程度", "中等");
		FuzzyEvaluation f8 = new FuzzyEvaluation(8, 1, 1, "张三", 12, "认真程度", "不合格");
		FuzzyEvaluation f9 = new FuzzyEvaluation(9, 1, 1, "张三", 12, "认真程度", "良好");
		fuzzyEvaluations.add(f1);
		fuzzyEvaluations.add(f2);
		fuzzyEvaluations.add(f3);
		fuzzyEvaluations.add(f4);
		fuzzyEvaluations.add(f5);
		fuzzyEvaluations.add(f6);
		fuzzyEvaluations.add(f7);
		fuzzyEvaluations.add(f8);
		fuzzyEvaluations.add(f9);
		//实验数据
		
		totalNum = fuzzyEvaluations.size();
		for (int i = 0; i < totalNum; i++) {
			if (fuzzyEvaluations.get(i).getEvalrank().equals("优秀")) {
				rank1++;
			}else if (fuzzyEvaluations.get(i).getEvalrank().equals("良好")) {
				rank2++;
			}else if (fuzzyEvaluations.get(i).getEvalrank().equals("中等")) {
				rank3++;
			}else if (fuzzyEvaluations.get(i).getEvalrank().equals("合格")) {
				rank4++;
			}else{
				rank5++;
			}
		}
		
		result[0] = round((double)rank1/totalNum, 3);
		result[1] = round((double)rank2/totalNum,3);
		result[2] = round((double)rank3/totalNum, 3);
		result[3] = round((double)rank4/totalNum, 3);
		result[4] = round((double)rank5/totalNum, 3);
		
//			for (int i = 0; i < result.length; i++) {
//				System.out.println("rank["+i+"] = "+result[i]);
//			}
		return result;
	}
	public static void main(String[] args) {
		
		List<Evaluationelement> evaluationelements = new ArrayList<>();
        Course course = new Course();
		Evaluationelement father = new Evaluationelement(1, course);
		Evaluationelement e1 = new Evaluationelement(2, course);
		e1.setEvaluationelement(father);
		evaluationelements.add(e1);
		Evaluationelement e2 = new Evaluationelement(2, course);
		e1.setEvaluationelement(father);
		evaluationelements.add(e2);
		Evaluationelement e3 = new Evaluationelement(2, course);
		e1.setEvaluationelement(father);
		evaluationelements.add(e3);
		Evaluationelement e4 = new Evaluationelement(2, course);
		e1.setEvaluationelement(father);
		evaluationelements.add(e4);
		
		double[][] matrix = constructMatrix(evaluationelements, 1, 1);
		double[] weight = {0.2,0.1,0.5,0.2};
		
		weight = fuzzyCompute(weight, matrix);
		for (int i = 0; i < weight.length; i++) {
			System.out.println(weight[i]);
		}
//		membershipCompute(1, 1, 1);
//		double[] weight = {1,2,3};
//		double[][] fuzzyMatrix = {{1,2,3,4,5},{2,3,4,5,6},{3,4,5,6,7}};
//		
//		double[] result = fuzzyCompute(weight, fuzzyMatrix);
//		for (int i = 0; i < result.length; i++) {
//			System.out.println("result["+i+"] = "+result[i]);
//		}
	}
}
