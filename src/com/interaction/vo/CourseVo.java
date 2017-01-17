package com.interaction.vo;


public class CourseVo {

	private Integer cid;
	private String cnumber;
	private String cname;
	private String cterm;
	private Integer semNum;
	
	private Integer tid;
	private String tname;
	
	
	public Integer getSemNum() {
		return semNum;
	}
	public void setSemNum(Integer semNum) {
		this.semNum = semNum;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCterm() {
		return cterm;
	}
	public void setCterm(String cterm) {
		this.cterm = cterm;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
}
