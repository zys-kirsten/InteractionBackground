package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer aid;
	private String aaccount;
	private String apwd;
	private String aname;
	private String aphone;
	private Integer atype;
	private Set functioncomponents = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String aaccount, String apwd, String aname, String aphone,
			Integer atype, Set functioncomponents) {
		this.aaccount = aaccount;
		this.apwd = apwd;
		this.aname = aname;
		this.aphone = aphone;
		this.atype = atype;
		this.functioncomponents = functioncomponents;
	}

	// Property accessors

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAaccount() {
		return this.aaccount;
	}

	public void setAaccount(String aaccount) {
		this.aaccount = aaccount;
	}

	public String getApwd() {
		return this.apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAphone() {
		return this.aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public Integer getAtype() {
		return this.atype;
	}

	public void setAtype(Integer atype) {
		this.atype = atype;
	}

	public Set getFunctioncomponents() {
		return this.functioncomponents;
	}

	public void setFunctioncomponents(Set functioncomponents) {
		this.functioncomponents = functioncomponents;
	}

}