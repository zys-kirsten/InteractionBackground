package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.EvaluationDAO;
import com.interaction.pojo.Evaluation;

@Repository
public class EvaluationDAOImpl extends HibernateDaoSupport implements EvaluationDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	@Override
	public int addEvaluation(Evaluation epo) {

		Serializable id = getHibernateTemplate().save(epo);
		if(id == null || id.toString().length() == 0)
			return -1;
		return Integer.parseInt(id.toString());
	}

	@Override
	public Evaluation findById(int eid) {
		String hql = "from Evaluation e where e.eid=?";
		List<Evaluation> evaluations = getHibernateTemplate().find(hql,eid);
		if(evaluations == null || evaluations.size() == 0)
			return null;
		return evaluations.get(0);
	}

	@Override
	public List<Evaluation> listByCourse(Integer cid) {
		String hql = "from Evaluation e where e.course.cid=?";
		List<Evaluation> evaluations = getHibernateTemplate().find(hql, cid);
		if(evaluations == null || evaluations.size() == 0)
			return null;
		return evaluations;
	}
	@Override
	public int updateEvaluation(Evaluation evaluation) {
		try {
			getHibernateTemplate().update(evaluation);
			return evaluation.getEid();
		} catch (Exception e) {
		}
		return -1;
	}
	@Override
	public int deleteEvaluation(Evaluation evaluation) {
		try {
			getHibernateTemplate().delete(evaluation);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}
	@Override
	public List<Evaluation> listByEname(Integer cid, String inputValue) {
		Session session = getSession();
		String hql="from Evaluation e where e.course.cid = ? and e.ename like ?";
		Query evaluation = session.createQuery(hql);
		evaluation.setInteger(0, cid);
		evaluation.setString(1, "%"+inputValue+"%");
		session.clear();
		return evaluation.list();
	}
	@Override
	public List<Evaluation> listByEdescription(Integer cid, String inputValue) {
		Session session = getSession();
		String hql="from Evaluation e where e.course.cid = ? and e.edescription like ?";
		Query evaluation = session.createQuery(hql);
		evaluation.setInteger(0, cid);
		evaluation.setString(1, "%"+inputValue+"%");
		session.clear();
		return evaluation.list();
	}
	@Override
	public List<Evaluation> listByEcalcul(Integer cid, String inputValue) {
		Session session = getSession();
		String hql="from Evaluation e where e.course.cid = ? and e.ecalcul like ?";
		Query evaluation = session.createQuery(hql);
		evaluation.setInteger(0, cid);
		evaluation.setString(1, "%"+inputValue+"%");
		session.clear();
		return evaluation.list();
	}
	@Override
	public List<Evaluation> findByEname(Integer cid, String ename) {
		String hql = "from Evaluation e where e.course.cid = ? and e.ename = ?";
		List<Evaluation> evaluations = getHibernateTemplate().find(hql, cid,ename);
		if(evaluations == null || evaluations.size() == 0)
			return null;
		return evaluations;
	}
	
}