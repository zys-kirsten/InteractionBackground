package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ScoreshowDAO;
import com.interaction.pojo.Scoreshow;

@Repository
public class ScoreshowDAOImpl extends HibernateDaoSupport implements ScoreshowDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addScoreShow(Scoreshow scoreshow) {

		Serializable id = getHibernateTemplate().save(scoreshow);
		if(id == null || id.toString().length() == 0)
			return -1;
		
		return Integer.parseInt(id.toString());
	}

	@Override
	public List<Scoreshow> listByEvaluation(Integer eid) {
		String hql = "from Scoreshow ss where ss.evaluation.eid=?";
		List<Scoreshow> scoreshows = getHibernateTemplate().find(hql,eid);
		
		if(scoreshows == null || scoreshows.size() == 0)
			return null;
		return scoreshows;
	}

	@Override
	public int updateScoreShow(Scoreshow scoreshow) {
		try {
			getHibernateTemplate().update(scoreshow);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	@Override
	public int deleteScoreshow(Scoreshow scoreshow) {
		try {
			getHibernateTemplate().delete(scoreshow);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
	}
	
}
