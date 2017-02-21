package com.interaction.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SeminarpointfeedbackDAO;

@Repository
public class SeminarpointfeedbackDAOImpl extends HibernateDaoSupport implements SeminarpointfeedbackDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
}
