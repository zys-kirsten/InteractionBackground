package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ResponderdataDAO;
import com.interaction.pojo.Answer;
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
	public int updateResponderdata(Responderdata responderdata) {
		try {
			getHibernateTemplate().update(responderdata);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
	@Override
	public Responderdata findByIdBeVisited(int rdid) {
		String hql = "from Responderdata r where r.rdid=? and beVisited = 1";
		List<Responderdata> responderdatas = getHibernateTemplate().find(hql, rdid);
		if(responderdatas == null || responderdatas.size() == 0)
			return null;
		return responderdatas.get(0);
	}
}
