package com.interaction.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer mid;
	private Menu menu;
	private String mname;
	private Set functioncomponents = new HashSet(0);
	private Set menus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(Menu menu, String mname, Set functioncomponents, Set menus) {
		this.menu = menu;
		this.mname = mname;
		this.functioncomponents = functioncomponents;
		this.menus = menus;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Set getFunctioncomponents() {
		return this.functioncomponents;
	}

	public void setFunctioncomponents(Set functioncomponents) {
		this.functioncomponents = functioncomponents;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

}