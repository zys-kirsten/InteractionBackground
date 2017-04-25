package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

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


	@Override
	public Spocscore findBySidAndSeid(Integer sid, Integer seId) {
		String hql="from Spocscore ss where ss.student.sid=? and ss.seminar.seId = ?";
		List<Spocscore> spocscores = getHibernateTemplate().find(hql,sid,seId);
		if(spocscores == null || spocscores.size() == 0)
			return null;
		return spocscores.get(0);
	}


	@Override
	public int updateSpocscore(Spocscore spocscore) {
		try {
			getHibernateTemplate().update(spocscore);
			return 1;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Spocscore> listBySeid(Integer seId) {
		String hql="from Spocscore ss where ss.seminar.seId = ?";
		List<Spocscore> spocscores = getHibernateTemplate().find(hql,seId);
		if(spocscores == null || spocscores.size() == 0)
			return null;
		return spocscores;
	}
}
