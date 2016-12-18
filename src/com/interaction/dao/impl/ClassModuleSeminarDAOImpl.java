package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ClassModuleSeminarDAO;
import com.interaction.pojo.Classmoduleseminar;

@Repository
public class ClassModuleSeminarDAOImpl extends HibernateDaoSupport implements ClassModuleSeminarDAO{
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addClassModuleSeminar(Classmoduleseminar cmse) {
		Serializable id = getHibernateTemplate().save(cmse);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public List<Classmoduleseminar> listByCmid(Integer cmid) {
		String hql="from Classmoduleseminar cms where cms.classmodule.cmid = ?";
		List<Classmoduleseminar> classmoduleseminars = getHibernateTemplate().find(hql,cmid);
		if(classmoduleseminars==null)
			return null;
		return classmoduleseminars;
	}

	@Override
	public int deleteCms(List<Classmoduleseminar> classmoduleseminars) {
		try {
			getHibernateTemplate().deleteAll(classmoduleseminars);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
}
