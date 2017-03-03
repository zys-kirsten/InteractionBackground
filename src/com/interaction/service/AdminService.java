package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Admin;

public interface AdminService {

	Admin login(String aaccount);

	List<Admin> listAllDeveloper();

}
