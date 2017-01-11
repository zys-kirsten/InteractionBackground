package com.interaction.dao.impl;

import java.io.Serializable;
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
		if(seminar==null || seminar.size() == 0)
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

	@Override
	public Seminarclass findByCEE(int cid, int seid, int sid) {
		String hql="from Seminarclass sc where sc.course.cid=? and sc.seminar.seId = ? and sc.student.sid=?";
		List<Seminarclass> seminar = getHibernateTemplate().find(hql,cid,seid,sid);
		if(seminar==null || seminar.size() == 0)
			return null;
		return seminar.get(0);
	}

	@Override
	public int addSeminarclass(Seminarclass seminarclass) {
		Serializable id = getHibernateTemplate().save(seminarclass);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
	@Override
	public List<Seminarclass> listByCidAndSid(int cid, int sid) {
		String hql="from Seminarclass sc where sc.course.cid = ? and sc.student.sid = ?";
		List<Seminarclass> seminarclasses = getHibernateTemplate().find(hql,cid,sid);
		if(seminarclasses==null || seminarclasses.size() == 0)
			return null;
		return seminarclasses;
	}
	
	@Override
	public List<Seminarclass> listGroup(int seid) {
		String hql="from Seminarclass sc where sc.confirmGroup = 1 and sc.seminar.seId = ? order by groupNum";
		List<Seminarclass> seminarclasses = getHibernateTemplate().find(hql,seid);
		if(seminarclasses==null || seminarclasses.size() == 0)
			return null;
		return seminarclasses;
	}
	
	@Override
	public Seminarclass  findMyGroupNum(Integer seid, int sid) {
		String hql="from Seminarclass sc where  sc.seminar.seId = ? and  sc.student.sid = ?";
		List<Seminarclass> seminarclasses = getHibernateTemplate().find(hql,seid,sid);
		if(seminarclasses==null || seminarclasses.size() == 0)
			return null;
		return seminarclasses.get(0);
	}
	
	@Override
	public List<Seminarclass> listOtherGroupNums(int seid, int groupNum) {
		String hql="from Seminarclass sc where  sc.seminar.seId = ? and groupNum is not ?";
		List<Seminarclass> seminarclasses = getHibernateTemplate().find(hql,seid,groupNum);
		if(seminarclasses==null || seminarclasses.size() == 0)
			return null;
		return seminarclasses;
	}
	
	@Override
	public List<Seminarclass> listByGroupNum(int seid, int groupNum) {
		String hql="from Seminarclass sc where  sc.seminar.seId = ? and groupNum = ?";
		List<Seminarclass> seminarclasses = getHibernateTemplate().find(hql,seid,groupNum);
		if(seminarclasses==null || seminarclasses.size() == 0)
			return null;
		return seminarclasses;
	}
}