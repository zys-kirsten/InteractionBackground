package com.interaction.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ResponderdataDAO;
import com.interaction.pojo.Responderdata;

@Repository
public class ResponderdataDAOImpl extends HibernateDaoSupport implements ResponderdataDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addResponderdata(Responderdata responderdata) {
		Serializable id = getHibernateTemplate().save(responderdata);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
	@Override
	public Responderdata findById(int rdid) {
		return getHibernateTemplate().get(Responderdata.class, rdid);
	}
	
	@Override
	public void updateResponderdata(Responderdata responderdata) {
		try {
			getHibernateTemplate().update(responderdata);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
