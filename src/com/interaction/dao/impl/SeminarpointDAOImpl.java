package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SeminarpointDAO;
import com.interaction.pojo.Seminarpoint;

@Repository
public class SeminarpointDAOImpl extends HibernateDaoSupport implements SeminarpointDAO{
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public List<Seminarpoint> listBySeId(Integer seId) {
		String hql="from Seminarpoint sp where sp.seminar.seId = ?";
		List<Seminarpoint> seminarpoints = getHibernateTemplate().find(hql, seId);
		if (seminarpoints != null && seminarpoints.size() != 0) {
			return seminarpoints;
		}
		return null;
	}

	@Override
	public int addSeminarpoint(Seminarpoint seminarpoint) {
		Serializable id = getHibernateTemplate().save(seminarpoint);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public int updateSeminarpoint(Seminarpoint seminarpoint) {
		try {
			getHibernateTemplate().update(seminarpoint);
			return seminarpoint.getSpid();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
	@Override
	public Seminarpoint findById(Integer spid) {
		try {
			return getHibernateTemplate().get(Seminarpoint.class, spid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public void deleteSeminarpoint(Seminarpoint seminarpoint) {

		try {
			getHibernateTemplate().delete(seminarpoint);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
