package com.interaction.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SemclatestDAO;
import com.interaction.pojo.Semclatest;

@Repository
public class SemclatestDAOImpl extends HibernateDaoSupport implements SemclatestDAO {
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addSemclatest(Semclatest semclatest) {
		Serializable id = getHibernateTemplate().save(semclatest);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
}