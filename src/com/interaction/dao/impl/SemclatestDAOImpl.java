package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.SemclatestDAO;
import com.interaction.pojo.Semclatest;
import com.interaction.pojo.Votequestion;

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
	
	@Override
	public Semclatest listBySeidAndSidAndQidBeVisited(int seid, int sid, int qid) {
		String hql = "from Semclatest s where s.seminar.seId=? and s.student.sid=? and s.question.qid=? and s.question.beVisited=1";
		List<Semclatest> semclatests = getHibernateTemplate().find(hql,seid,sid,qid);
		if (semclatests == null || semclatests.size() == 0) {
			return null;
		}
		return semclatests.get(0);
	}
}