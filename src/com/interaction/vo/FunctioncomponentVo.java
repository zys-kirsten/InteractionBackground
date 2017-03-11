package com.interaction.vo;

public class FunctioncomponentVo {

	private Integer fcid;
	private String fcname;
	private String description;
	private String url;
	private Integer type;
	private Integer state;
	
	private Integer aid;
	private String aname;
	
	private Integer mid;
	private String mname;
	
	private boolean flag = false; //为了功能菜单显示
	
	
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getFcid() {
		return fcid;
	}
	
	public String getFcname() {
		return fcname;
	}

	public void setFcname(String fcname) {
		this.fcname = fcname;
	}

	public void setFcid(Integer fcid) {
		this.fcid = fcid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
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
	@Override
	public String toString() {
		return "FunctioncomponentVo [fcid=" + fcid + ", description=" + description + ", url=" + url + ", type=" + type
				+ ", state=" + state + ", aid=" + aid + ", aname=" + aname + ", mid=" + mid + ", mname=" + mname + "]";
	}
	
	
}
