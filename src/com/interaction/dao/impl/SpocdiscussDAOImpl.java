package com.interaction.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SpocdiscussDAO;
import com.interaction.pojo.Spocdiscuss;

@Repository
public class SpocdiscussDAOImpl extends HibernateDaoSupport implements SpocdiscussDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addSpocdiscuss(Spocdiscuss spocdiscuss) {
		Serializable id = getHibernateTemplate().save(spocdiscuss);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
}
