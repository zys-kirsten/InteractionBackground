package com.interaction.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SeminarclassDAO;
import com.interaction.pojo.Seminarclass;

@Repository
public class SeminarclassDAOImpl extends HibernateDaoSupport implements SeminarclassDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public List<Seminarclass> listBySeminar(Integer seid) {
		String hql="from Seminarclass sc where sc.seminar.seId = ?";
		List<Seminarclass> seminar = getHibernateTemplate().find(hql,seid);
		if(seminar==null)
			return null;
		return seminar;
	}

	@Override
	public List<Seminarclass> listLoginStudents(Integer seId) {
		String hql="from Seminarclass sc where sc.seminar.seId = ? and sc.isLogin = 1";
		List<Seminarclass> seminar = getHibernateTemplate().find(hql,seId);
		if(seminar==null)
			return null;
		return seminar;
	}

	@Override
	public int updateSeminarclass(Seminarclass seminarclass) {
		try {
			getHibernateTemplate().update(seminarclass);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
}