package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String taccount;
	private String tpwd;
	private String tname;
	private String tphone;
	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String tacount, String tpwd, String tname) {
		this.tid = tid;
		this.taccount = tacount;
		this.tpwd = tpwd;
		this.tname = tname;
	}

	/** full constructor */
	public Teacher(Integer tid, String tacount, String tpwd, String tname,
			String tphone, Set courses) {
		this.tid = tid;
		this.taccount = tacount;
		this.tpwd = tpwd;
		this.tname = tname;
		this.tphone = tphone;
		this.courses = courses;
	}

	// Property accessors

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTaccount() {
		return this.taccount;
	}

	public void setTaccount(String tacount) {
		this.taccount = tacount;
	}

	public String getTpwd() {
		return this.tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTphone() {
		return this.tphone;
	}

	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

}