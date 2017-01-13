package com.interaction.vo;

import java.util.List;

import com.interaction.pojo.Seminar;

public class ClassModuleVo {

	private Integer cmid;  //课程模式ID
	private String moduleName;//课程模式名称
	
	//分组配置
	private Integer groupNum;//分组个数
	private Integer groupTime;//分组时间。1：课上分组，0：课前分组
	
	//测试题配置
	private Integer proNum;//测试题个数
	private Integer proTime;//测试题做题时间
	
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

	public Integer getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(Integer groupTime) {
		this.groupTime = groupTime;
	}

	public Integer getProNum() {
		return proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}

	public Integer getProTime() {
		return proTime;
	}

	public void setProTime(Integer proTime) {
		this.proTime = proTime;
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
	

	
//	@Override
//	public String toString() {
//		return "ClassModuleVo [evaluations=" + evaluations + ", seminars=" + seminars + "]";
//	}
	
	
	
}
