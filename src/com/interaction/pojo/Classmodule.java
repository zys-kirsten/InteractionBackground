package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Classmodule entity. @author MyEclipse Persistence Tools
 */

public class Classmodule implements java.io.Serializable {

	// Fields

	private Integer cmid;
	private Course course;
	private String moduleName;
	private Integer groupNum;
	private Integer teamLeader;
	private Integer grpMaxNum;
	private Integer grpMinNum;
	private Integer score;
	private Integer ability;
	private Integer activity;
	private Integer quality;
	private Integer sex;
	private Integer proNum;
	private Integer proScore;
	private Integer proTime;
	private String proPerson;
	private Integer resNum;
	private Integer resScore;
	private String census;
	private Set classmoduleseminars = new HashSet(0);
	private Set classmoduleevaluations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Classmodule() {
	}

	/** minimal constructor */
	public Classmodule(Integer cmid) {
		this.cmid = cmid;
	}

	/** full constructor */
	public Classmodule(Integer cmid, Course course, String moduleName,
			Integer groupNum, Integer teamLeader, Integer grpMaxNum,
			Integer grpMinNum, Integer score, Integer ability,
			Integer activity, Integer quality, Integer sex, Integer proNum,
			Integer proScore, Integer proTime, String proPerson,
			Integer resNum, Integer resScore, String census,
			Set classmoduleseminars, Set classmoduleevaluations) {
		this.cmid = cmid;
		this.course = course;
		this.moduleName = moduleName;
		this.groupNum = groupNum;
		this.teamLeader = teamLeader;
		this.grpMaxNum = grpMaxNum;
		this.grpMinNum = grpMinNum;
		this.score = score;
		this.ability = ability;
		this.activity = activity;
		this.quality = quality;
		this.sex = sex;
		this.proNum = proNum;
		this.proScore = proScore;
		this.proTime = proTime;
		this.proPerson = proPerson;
		this.resNum = resNum;
		this.resScore = resScore;
		this.census = census;
		this.classmoduleseminars = classmoduleseminars;
		this.classmoduleevaluations = classmoduleevaluations;
	}

	// Property accessors

	public Integer getCmid() {
		return this.cmid;
	}

	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Integer getTeamLeader() {
		return this.teamLeader;
	}

	public void setTeamLeader(Integer teamLeader) {
		this.teamLeader = teamLeader;
	}

	public Integer getGrpMaxNum() {
		return this.grpMaxNum;
	}

	public void setGrpMaxNum(Integer grpMaxNum) {
		this.grpMaxNum = grpMaxNum;
	}

	public Integer getGrpMinNum() {
		return this.grpMinNum;
	}

	public void setGrpMinNum(Integer grpMinNum) {
		this.grpMinNum = grpMinNum;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getAbility() {
		return this.ability;
	}

	public void setAbility(Integer ability) {
		this.ability = ability;
	}

	public Integer getActivity() {
		return this.activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getProNum() {
		return this.proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}

	public Integer getProScore() {
		return this.proScore;
	}

	public void setProScore(Integer proScore) {
		this.proScore = proScore;
	}

	public Integer getProTime() {
		return this.proTime;
	}

	public void setProTime(Integer proTime) {
		this.proTime = proTime;
	}

	public String getProPerson() {
		return this.proPerson;
	}

	public void setProPerson(String proPerson) {
		this.proPerson = proPerson;
	}

	public Integer getResNum() {
		return this.resNum;
	}

	public void setResNum(Integer resNum) {
		this.resNum = resNum;
	}

	public Integer getResScore() {
		return this.resScore;
	}

	public void setResScore(Integer resScore) {
		this.resScore = resScore;
	}

	public String getCensus() {
		return this.census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public Set getClassmoduleseminars() {
		return this.classmoduleseminars;
	}

	public void setClassmoduleseminars(Set classmoduleseminars) {
		this.classmoduleseminars = classmoduleseminars;
	}

	public Set getClassmoduleevaluations() {
		return this.classmoduleevaluations;
	}

	public void setClassmoduleevaluations(Set classmoduleevaluations) {
		this.classmoduleevaluations = classmoduleevaluations;
	}

}