package com.interaction.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.AdminDAO;
import com.interaction.pojo.Admin;

@Repository
public class AdminDAOImpl extends HibernateDaoSupport implements AdminDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public Admin findAdminByAccount(String aaccount) {
		String hql = "from Admin a where a.aaccount = ?";
		List<Admin> admins = getHibernateTemplate().find(hql,aaccount);
		if (admins == null || admins.size() == 0) {
			return null;
		}
		return admins.get(0);
	}

	@Override
	public List<Admin> listAllDeveloper() {
		String hql = "from Admin a where a.atype = 1";
		List<Admin> admins = getHibernateTemplate().find(hql);
		if (admins == null || admins.size() == 0) {
			return null;
		}
		return admins;
	}

	@Override
	public Admin findById(Integer aid) {
		try {
			return getHibernateTemplate().get(Admin.class, aid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
