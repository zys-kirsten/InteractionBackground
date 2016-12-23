package com.interaction.vo;

public class EvaluationWeightVo2 {
	private double quantization;  //量化指标
	private double unquantization; //非量化指标
	
	//量化指标下的评价因素
	private double q_exercise; //课堂练习题 
	private double q_answer;   //抢答次数
	private double q_vote;     //投票答题
	private double q_extrascore; //教师额外加分
	
	//非量化指标下的评价因素
	private double[] uq_self;  //学生自评
	private double[] uq_stu;   //学生评价
	private double[] uq_teacher; //教师评价
	
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
	public double getQ_exercise() {
		return q_exercise;
	}
	public void setQ_exercise(double q_exercise) {
		this.q_exercise = q_exercise;
	}
	public double getQ_answer() {
		return q_answer;
	}
	public void setQ_answer(double q_answer) {
		this.q_answer = q_answer;
	}
	public double getQ_vote() {
		return q_vote;
	}
	public void setQ_vote(double q_vote) {
		this.q_vote = q_vote;
	}
	public double getQ_extrascore() {
		return q_extrascore;
	}
	public void setQ_extrascore(double q_extrascore) {
		this.q_extrascore = q_extrascore;
	}
	
	public double[] getUq_self() {
		return uq_self;
	}
	public void setUq_self(double[] uq_self) {
		this.uq_self = uq_self;
	}
	public double[] getUq_stu() {
		return uq_stu;
	}
	public void setUq_stu(double[] uq_stu) {
		this.uq_stu = uq_stu;
	}
	public double[] getUq_teacher() {
		return uq_teacher;
	}
	public void setUq_teacher(double[] uq_teacher) {
		this.uq_teacher = uq_teacher;
	}
	public double getSelf_homework() {
		return self_homework;
	}
	public void setSelf_homework(double self_homework) {
		this.self_homework = self_homework;
	}
	public double getSelf_konwledge() {
		return self_konwledge;
	}
	public void setSelf_konwledge(double self_konwledge) {
		this.self_konwledge = self_konwledge;
	}
	public double getSelf_earnest() {
		return self_earnest;
	}
	public void setSelf_earnest(double self_earnest) {
		this.self_earnest = self_earnest;
	}
	public double getSelf_cooperation() {
		return self_cooperation;
	}
	public void setSelf_cooperation(double self_cooperation) {
		this.self_cooperation = self_cooperation;
	}
	public double getStu_achievement() {
		return stu_achievement;
	}
	public void setStu_achievement(double stu_achievement) {
		this.stu_achievement = stu_achievement;
	}
	public double getStu_content() {
		return stu_content;
	}
	public void setStu_content(double stu_content) {
		this.stu_content = stu_content;
	}
	public double getStu_active() {
		return stu_active;
	}
	public void setStu_active(double stu_active) {
		this.stu_active = stu_active;
	}
	public double getStu_cooperation() {
		return stu_cooperation;
	}
	public void setStu_cooperation(double stu_cooperation) {
		this.stu_cooperation = stu_cooperation;
	}
	public double getStu_knowledge() {
		return stu_knowledge;
	}
	public void setStu_knowledge(double stu_knowledge) {
		this.stu_knowledge = stu_knowledge;
	}
	public double getTeacher_earnest() {
		return teacher_earnest;
	}
	public void setTeacher_earnest(double teacher_earnest) {
		this.teacher_earnest = teacher_earnest;
	}
	public double getTeacher_scope() {
		return teacher_scope;
	}
	public void setTeacher_scope(double teacher_scope) {
		this.teacher_scope = teacher_scope;
	}
	public double getTeacher_depth() {
		return teacher_depth;
	}
	public void setTeacher_depth(double teacher_depth) {
		this.teacher_depth = teacher_depth;
	}
	public double getTeacher_active() {
		return teacher_active;
	}
	public void setTeacher_active(double teacher_active) {
		this.teacher_active = teacher_active;
	}
	public double getTeacher_cooperation() {
		return teacher_cooperation;
	}
	public void setTeacher_cooperation(double teacher_cooperation) {
		this.teacher_cooperation = teacher_cooperation;
	}
	
}
