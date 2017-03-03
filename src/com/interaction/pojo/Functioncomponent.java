package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Functioncomponent entity. @author MyEclipse Persistence Tools
 */

public class Functioncomponent implements java.io.Serializable {

	// Fields

	private Integer fcid;
	private Admin admin;
	private Menu menu;
	private String fcname;
	private String description;
	private String url;
	private Integer type;
	private Integer state;
	private Set teacherfunctions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Functioncomponent() {
	}

	/** full constructor */
	public Functioncomponent(Admin admin, Menu menu, String fcname,
			String description, String url, Integer type, Integer state,
			Set teacherfunctions) {
		this.admin = admin;
		this.menu = menu;
		this.fcname = fcname;
		this.description = description;
		this.url = url;
		this.type = type;
		this.state = state;
		this.teacherfunctions = teacherfunctions;
	}

	// Property accessors

	public Integer getFcid() {
		return this.fcid;
	}

	public void setFcid(Integer fcid) {
		this.fcid = fcid;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getFcname() {
		return this.fcname;
	}

	public void setFcname(String fcname) {
		this.fcname = fcname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set getTeacherfunctions() {
		return this.teacherfunctions;
	}

	public void setTeacherfunctions(Set teacherfunctions) {
		this.teacherfunctions = teacherfunctions;
	}

}