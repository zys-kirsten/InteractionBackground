package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.FunctioncomponentDAO;
import com.interaction.pojo.Functioncomponent;

@Repository
public class FunctioncomponentDAOImpl extends HibernateDaoSupport implements FunctioncomponentDAO{
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public List<Functioncomponent> listAllFunctioncomponent() {
		String hql = "from Functioncomponent";
		List<Functioncomponent> functioncomponents = getHibernateTemplate().find(hql);
		if(functioncomponents == null || functioncomponents.size() == 0){
			return null;
		}
		return functioncomponents;
	}

	@Override
	public List<Functioncomponent> listFunctioncomponentByAid(Integer aid) {
		String hql = "from Functioncomponent fc where fc.admin.aid = ?";
		List<Functioncomponent> functioncomponents = getHibernateTemplate().find(hql,aid);
		if(functioncomponents == null || functioncomponents.size() == 0){
			return null;
		}
		return functioncomponents;
	}
	
	@Override
	public int addFunctioncomponent(Functioncomponent functioncomponent) {

		Serializable id = getHibernateTemplate().save(functioncomponent);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public Functioncomponent findById(Integer fcid) {
		return getHibernateTemplate().get(Functioncomponent.class, fcid);
	}
}
