package com.interaction.vo;

import java.util.Arrays;

public class EvaluationWeightVo {
	private double quantization;  //量化指标
	private double unquantization; //非量化指标
	
	private String[][] quantizationIndex = new String[4][4]; //量化指标下的各个评价因素对比值
	private String[][] unquantizationIndex =  new String[3][3]; //非量化指标下的各个评价因素对比值
	
	private String[][] selfIndex = new String[4][4]; //学生自评指标下的各个评价因素对比值
	private String[][] stuIndex = new String[5][5]; //学生评价指标下的各个评价因素对比值
	private String[][] teacherIndex= new String[5][5]; //教师评价指标下的各个评价因素对比值
	public double getQuantization() {
		return quantization;
	}
	public void setQuantization(double quantization) {
		this.quantization = quantization;
	}
	public double getUnquantization() {
		return unquantization;
	}
	public void setUnquantization(double unquantization) {
		this.unquantization = unquantization;
	}
	public String[][] getQuantizationIndex() {
		return quantizationIndex;
	}
	public void setQuantizationIndex(String[][] quantizationIndex) {
		this.quantizationIndex = quantizationIndex;
	}
	public String[][] getUnquantizationIndex() {
		return unquantizationIndex;
	}
	public void setUnquantizationIndex(String[][] unquantizationIndex) {
		this.unquantizationIndex = unquantizationIndex;
	}
	public String[][] getSelfIndex() {
		return selfIndex;
	}
	public void setSelfIndex(String[][] selfIndex) {
		this.selfIndex = selfIndex;
	}
	public String[][] getStuIndex() {
		return stuIndex;
	}
	public void setStuIndex(String[][] stuIndex) {
		this.stuIndex = stuIndex;
	}
	public String[][] getTeacherIndex() {
		return teacherIndex;
	}
	public void setTeacherIndex(String[][] teacherIndex) {
		this.teacherIndex = teacherIndex;
	}
	@Override
	public String toString() {
		return "EvaluationWeightVo [quantization=" + quantization + ", unquantization=" + unquantization
				+ ", quantizationIndex=" +quantizationIndex.length + ", unquantizationIndex="
				+ unquantizationIndex.length + ", selfIndex=" +selfIndex.length + ", stuIndex="
				+ stuIndex.length + ", teacherIndex=" + teacherIndex.length + "]";
	}
	
	
	
	
	/*//量化指标下的评价因素
	private double q_exercise; //课堂练习题 
	private double q_answer;   //抢答次数
	private double q_vote;     //投票答题
	private double q_extrascore; //教师额外加分
	
	//非量化指标下的评价因素
	private double uq_self;  //学生自评
	private double uq_stu;   //学生评价
	private double uq_teacher; //教师评价
	
	//学生自评下的评价因素
	private double self_homework; //作业完成情况
	private double self_konwledge;//知识点掌握
	private double self_earnest;  //认真程度
	private double self_cooperation;//合作素质
	
	//学生评价下的评价因素
	private double stu_achievement; //成果准备认真程度
	private double stu_content; //内容覆盖程度
	private double stu_active;  //课堂活跃
	private double stu_cooperation;//合作素质
	private double stu_knowledge;//知识点掌握
	
	//教师评价下的评价因素
	private double teacher_earnest;//认真程度
	private double teacher_scope;  //知识点广度
	private double teacher_depth;  //知识点深度
	private double teacher_active; //课堂活跃
	private double teacher_cooperation; //合作素质
*/	

}
