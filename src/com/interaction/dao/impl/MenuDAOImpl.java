package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.MenuDAO;
import com.interaction.pojo.Menu;

@Repository
public class MenuDAOImpl extends HibernateDaoSupport implements MenuDAO{
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public Menu findById(Integer mid) {
		try {
			return getHibernateTemplate().get(Menu.class, mid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Menu> listAll() {
		String hql = "from Menu";
		List<Menu> menus = getHibernateTemplate().find(hql);
		if (menus == null || menus.size() == 0) {
			return null;
		}
		return menus;
	}
	

	@Override
	public int addMenu(Menu menu) {

		Serializable id = getHibernateTemplate().save(menu);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
}
