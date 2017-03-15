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

	@Override
	public void deleteByAid(Integer aid) {
		Admin admin = adminDAOImpl.findById(aid);
		if (admin == null) {
			return;
		}
		adminDAOImpl.deleteAdmin(admin);
	}
	
	@Override
	public int addDeveloper(Admin admin) {

		admin.setAtype(1);
		return adminDAOImpl.addDeveloper(admin);
	}
}
