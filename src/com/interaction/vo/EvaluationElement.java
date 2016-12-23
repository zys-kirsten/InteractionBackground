package com.interaction.vo;

public class EvaluationElement {
	private Integer cid;  //课程ID
	private Integer eeid; //评价因素ID
	private String eename; //评价因素名称
	private Double weight; //因素的权值
	private Integer fatherId; //评价因素的上一级ID。第一级评价因素的上一级ID记为0.
	public Integer getEeid() {
		return eeid;
	}
	public void setEeid(Integer eeid) {
		this.eeid = eeid;
	}
	public String getEename() {
		return eename;
	}
	public void setEename(String eename) {
		this.eename = eename;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "EvaluationElement [cid=" + cid + ", eeid=" + eeid + ", eename=" + eename + ", weight=" + weight
				+ ", fatherId=" + fatherId + "]";
	}
	public EvaluationElement(Integer eeid, String eename) {
		super();
		this.eeid = eeid;
		this.eename = eename;
	}
	public EvaluationElement() {
		// TODO Auto-generated constructor stub
	}
	

}
