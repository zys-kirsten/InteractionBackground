package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SeminarDAO;
import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Teacher;

@Repository
public class SeminarDAOImpl extends HibernateDaoSupport implements SeminarDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public int addSeminar(Seminar seminar) {
		Serializable id=getHibernateTemplate().save(seminar);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public int updateSeminar(Seminar seminar) {
		try {
			getHibernateTemplate().update(seminar);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	} 
	
	@Override
	public Seminar findById(Integer seid) {
		String hql="from Seminar s where s.seId = ?";
		List<Seminar> seminar = getHibernateTemplate().find(hql,seid);
		if(seminar==null)
			return null;
		return seminar.get(0);
	}

	@Override
	public List<Seminar> listByCourse(Integer cid) {
		String hql="from Seminar s where s.course.cid = ?";
		List<Seminar> seminar = getHibernateTemplate().find(hql,cid);
		if(seminar==null)
			return null;
		return seminar;
	}
	@Override
	public int deleteSeminar(Seminar seminar) {
		try {
			getHibernateTemplate().delete(seminar);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
		
	}

	@Override
	public List<Seminar> findBySeName(Integer cid, String seName) {
		String hql = "from Seminar se where se.course.cid = ? and se.seName = ?";
		List<Seminar> seminars = getHibernateTemplate().find(hql, cid,seName);
		if(seminars == null || seminars.size() == 0)
			return null;
		return seminars;
	}
}