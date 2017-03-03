package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.AdminDAO;
import com.interaction.pojo.Admin;
import com.interaction.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminDAO adminDAOImpl;

	@Override
	public Admin login(String aaccount) {
		return adminDAOImpl.findAdminByAccount(aaccount);
	}

	@Override
	public List<Admin> listAllDeveloper() {
		return adminDAOImpl.listAllDeveloper();
	}
}
