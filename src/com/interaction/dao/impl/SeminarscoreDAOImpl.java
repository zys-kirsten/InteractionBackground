package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SeminarscoreDAO;
import com.interaction.pojo.Seminarscore;

@Repository
public class SeminarscoreDAOImpl extends HibernateDaoSupport implements SeminarscoreDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public List<Seminarscore> listBySeidAndSid(int seid, int sid) {
		String hql = "from Seminarscore ss where ss.seminar.seId=? and ss.student.sid=?";
		List<Seminarscore> seminarscores = getHibernateTemplate().find(hql, seid,sid);
		if(seminarscores == null || seminarscores.size() == 0)
			return null;
		return seminarscores;
	}
	
	@Override
	public int addSeminarscore(Seminarscore seminarscore) {

		Serializable id = getHibernateTemplate().save(seminarscore);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
}
