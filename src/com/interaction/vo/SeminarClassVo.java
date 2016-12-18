package com.interaction.vo;



public class SeminarClassVo {

	
	//研讨班中某一学生
	private Integer sid;
	private String sname;

	//研讨班的主题、名称及时间
	private Integer seId;
	private String seName;
	private String seTheme;
	private String seTime;
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
	public Integer getSeId() {
		return seId;
	}
	public void setSeId(Integer seId) {
		this.seId = seId;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getSeTheme() {
		return seTheme;
	}
	public void setSeTheme(String seTheme) {
		this.seTheme = seTheme;
	}
	public String getSeTime() {
		return seTime;
	}
	public void setSeTime(String seTime) {
		this.seTime = seTime;
	}
	
	
}
