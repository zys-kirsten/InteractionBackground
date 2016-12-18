package com.interaction.vo;

import java.util.List;

import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Seminar;

public class ClassModuleVo {

	private Integer cmid;  //课程模式ID
	private String moduleName;//课程模式名称
	
	//分组配置
	private Integer groupNum;//分组个数
	private Integer teamLeader;//随机组长  0:否   1:是
	private Integer grpMaxNum;//每组最多人数
	private Integer grpMinNum;//每组最少人数
	//学生分组考虑的权值
	private Integer score;//学生学习成绩
	private Integer ability;//学生学习能力
	private Integer activity;//活跃程度
	private Integer quality;//综合素质
	private Integer sex;//性别   0:女   1:男
	
	//评价配置
	private List<Evaluation> evaluations;
	
	//测试题配置
	private Integer proNum;//测试题个数
	private Integer proScore;//题目分值
	private Integer proTime;//测试题做题时间
	private String proPerson;//参加测试同学
	
	//抢答配置
	private Integer resNum;//抢答题目个数
	private Integer resScore;//抢答分值
	private String census;//统计方式
	
	//所属的课程
	private Integer cid;
	private String cname;
	
	//所属的研讨课
	private List<Seminar> seminars;
	public Integer getCmid() {
		return cmid;
	}
	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Integer getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}
	public Integer getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(Integer teamLeader) {
		this.teamLeader = teamLeader;
	}
	public Integer getGrpMaxNum() {
		return grpMaxNum;
	}
	public void setGrpMaxNum(Integer grpMaxNum) {
		this.grpMaxNum = grpMaxNum;
	}
	public Integer getGrpMinNum() {
		return grpMinNum;
	}
	public void setGrpMinNum(Integer grpMinNum) {
		this.grpMinNum = grpMinNum;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getAbility() {
		return ability;
	}
	public void setAbility(Integer ability) {
		this.ability = ability;
	}
	public Integer getActivity() {
		return activity;
	}
	public void setActivity(Integer activity) {
		this.activity = activity;
	}
	public Integer getQuality() {
		return quality;
	}
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getProNum() {
		return proNum;
	}
	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}
	public Integer getProScore() {
		return proScore;
	}
	public void setProScore(Integer proScore) {
		this.proScore = proScore;
	}
	public Integer getProTime() {
		return proTime;
	}
	public void setProTime(Integer proTime) {
		this.proTime = proTime;
	}
	public String getProPerson() {
		return proPerson;
	}
	public void setProPerson(String proPerson) {
		this.proPerson = proPerson;
	}
	public Integer getResNum() {
		return resNum;
	}
	public void setResNum(Integer resNum) {
		this.resNum = resNum;
	}
	public Integer getResScore() {
		return resScore;
	}
	public void setResScore(Integer resScore) {
		this.resScore = resScore;
	}
	public String getCensus() {
		return census;
	}
	public void setCensus(String census) {
		this.census = census;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public List<Seminar> getSeminars() {
		return seminars;
	}
	public void setSeminars(List<Seminar> seminars) {
		this.seminars = seminars;
	}
	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
//	@Override
//	public String toString() {
//		return "ClassModuleVo [evaluations=" + evaluations + ", seminars=" + seminars + "]";
//	}
	
	
	
}
