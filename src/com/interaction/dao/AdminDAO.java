package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Admin;

public interface AdminDAO {

	Admin findAdminByAccount(String aaccount);

	List<Admin> listAllDeveloper();

	Admin findById(Integer aid);

	void deleteAdmin(Admin admin);

	int addDeveloper(Admin admin);

}
