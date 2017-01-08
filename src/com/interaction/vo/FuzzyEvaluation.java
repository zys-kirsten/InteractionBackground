package com.interaction.vo;

public class FuzzyEvaluation {
	private Integer feid;
	
	private Integer seid;// 所在研讨课ID
	
	private Integer sid;//被评价学生的ID
	private String sname;//被评价学生的姓名
	
	private Integer eeid;//评价因素的ID
	private String  eename;//评价因素的名称（内容）
	
	private String evalrank;//评价等级。（优秀，良好，中等，合格，不合格）

	public Integer getFeid() {
		return feid;
	}

	public void setFeid(Integer feid) {
		this.feid = feid;
	}

	public Integer getSeid() {
		return seid;
	}

	public void setSeid(Integer seid) {
		this.seid = seid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

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

	public String getEvalrank() {
		return evalrank;
	}

	public void setEvalrank(String evalrank) {
		this.evalrank = evalrank;
	}

	public FuzzyEvaluation(Integer feid, Integer seid, Integer sid, String sname, Integer eeid, String eename,
			String evalrank) {
		super();
		this.feid = feid;
		this.seid = seid;
		this.sid = sid;
		this.sname = sname;
		this.eeid = eeid;
		this.eename = eename;
		this.evalrank = evalrank;
	}

	public FuzzyEvaluation() {
	}

	@Override
	public String toString() {
		return "FuzzyEvaluation [feid=" + feid + ", seid=" + seid + ", sid=" + sid + ", sname=" + sname + ", eeid="
				+ eeid + ", eename=" + eename + ", evalrank=" + evalrank + "]";
	}
	
	

}
