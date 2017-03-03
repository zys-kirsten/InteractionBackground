package com.interaction.vo;

public class MenuVo {

	private Integer mid;
	private String mname;
	
	private Integer fatherId;
	private String fatherName;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	@Override
	public String toString() {
		return "MenuVo [mid=" + mid + ", mname=" + mname + ", fatherId=" + fatherId + ", fatherName=" + fatherName + "]";
	}
	
	
}
