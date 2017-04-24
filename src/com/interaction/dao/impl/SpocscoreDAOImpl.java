package com.interaction.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SpocscoreDAO;
import com.interaction.pojo.Spocscore;

@Repository
public class SpocscoreDAOImpl extends HibernateDaoSupport implements SpocscoreDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	
	@Override
	public int addSpocscore(Spocscore spocscore) {
		Serializable id = getHibernateTemplate().save(spocscore);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

}
