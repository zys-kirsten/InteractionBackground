package com.interaction.vo;

import java.util.List;

public class ComponentVo {
	private Integer mid;
	private String mname;
	
	private List<FunctioncomponentVo> functioncomponentVos;

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

	public List<FunctioncomponentVo> getFunctioncomponentVos() {
		return functioncomponentVos;
	}

	public void setFunctioncomponentVos(List<FunctioncomponentVo> functioncomponentVos) {
		this.functioncomponentVos = functioncomponentVos;
	}

	

	

}
