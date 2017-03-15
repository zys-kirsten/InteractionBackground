package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Admin;

public interface AdminService {

	Admin login(String aaccount);

	List<Admin> listAllDeveloper();

	void deleteByAid(Integer aid);

	int addDeveloper(Admin admin);

}
