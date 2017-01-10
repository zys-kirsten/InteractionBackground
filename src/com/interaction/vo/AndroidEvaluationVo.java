package com.interaction.vo;

public class AndroidEvaluationVo {
	private Integer seid;
	private Integer object;
	private Integer eeid;
	private String evalRank;
	
	
	
	public AndroidEvaluationVo(Integer seid, Integer object, Integer eeid, String evalRank) {
		super();
		this.seid = seid;
		this.object = object;
		this.eeid = eeid;
		this.evalRank = evalRank;
	}
	public Integer getSeid() {
		return seid;
	}
	public void setSeid(Integer seid) {
		this.seid = seid;
	}
	
	public Integer getObject() {
		return object;
	}
	public void setObject(Integer object) {
		this.object = object;
	}
	public Integer getEeid() {
		return eeid;
	}
	public void setEeid(Integer eeid) {
		this.eeid = eeid;
	}
	public String getEvalRank() {
		return evalRank;
	}
	public void setEvalRank(String evalRank) {
		this.evalRank = evalRank;
	}
	
	

}
