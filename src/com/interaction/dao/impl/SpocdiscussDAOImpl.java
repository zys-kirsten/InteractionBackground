package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

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
	
	@Override
	public List<Spocdiscuss> findBySidAndCid(Integer sid, Integer cid) {
		String hql="from Spocdiscuss sd where sd.student.sid=? and sd.course.cid = ?";
		List<Spocdiscuss> spocdiscusses = getHibernateTemplate().find(hql,sid,cid);
		if(spocdiscusses == null || spocdiscusses.size() == 0)
			return null;
		return spocdiscusses;
	}


	@Override
	public int updateSpocdiscuss(Spocdiscuss spocdiscuss) {
		try {
			getHibernateTemplate().update(spocdiscuss);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	@Override
	public List<Spocdiscuss> ListByCid(Integer cid) {
		String hql="from Spocdiscuss sd where sd.course.cid = ?";
		List<Spocdiscuss> spocdiscusses = getHibernateTemplate().find(hql,cid);
		if(spocdiscusses == null || spocdiscusses.size() == 0)
			return null;
		return spocdiscusses;
	}
}
