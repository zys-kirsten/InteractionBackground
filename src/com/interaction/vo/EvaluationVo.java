package com.interaction.vo;

import java.util.List;

import com.interaction.pojo.Scoreshow;

public class EvaluationVo {

	private Integer eid;
	private String edescription;
	private String ename;
	private String ecalcul;
	
	private Integer cid;
	private String cname;
	
	private List<Scoreshow> scoreshows;//页面显示评价标准集合（A,B,C,D）

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEdescription() {
		return edescription;
	}

	public void setEdescription(String edescription) {
		this.edescription = edescription;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEcalcul() {
		return ecalcul;
	}

	public void setEcalcul(String ecalcul) {
		this.ecalcul = ecalcul;
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

	public List<Scoreshow> getScoreshows() {
		return scoreshows;
	}

	public void setScoreshows(List<Scoreshow> scoreshows) {
		this.scoreshows = scoreshows;
	}
	
	
}
