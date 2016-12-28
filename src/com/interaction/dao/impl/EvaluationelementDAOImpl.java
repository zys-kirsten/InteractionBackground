package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.EvaluationElementDAO;
import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Evaluationelement;

@Repository
public class EvaluationelementDAOImpl extends HibernateDaoSupport implements EvaluationElementDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addEvaluationElement(Evaluationelement evaluationelement) {

		Serializable id = getHibernateTemplate().save(evaluationelement);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public Evaluationelement findById(int eeid) {
		return getHibernateTemplate().get(Evaluationelement.class, eeid);
	}

	@Override
	public int updateEvaluationElement(Evaluationelement evaluationelement) {
		try {
			getHibernateTemplate().update(evaluationelement);
			return evaluationelement.getEeid();
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public int deleteEvaluationElement(Evaluationelement evaluationelement) {
		try {
			getHibernateTemplate().delete(evaluationelement);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public List<Evaluationelement> listByCourse(Integer cid) {
		String hql = "from Evaluationelement e where e.course.cid=?";
		List<Evaluationelement> evaluationelements = getHibernateTemplate().find(hql, cid);
		if(evaluationelements == null || evaluationelements.size() == 0)
			return null;
		return evaluationelements;
	}

	@Override
	public List<Evaluationelement> listByFatherId(Integer eeid) {
		String hql = "from Evaluationelement e where e.evaluationelement.eeid=?";
		List<Evaluationelement> evaluationelements = getHibernateTemplate().find(hql, eeid);
		if(evaluationelements == null || evaluationelements.size() == 0)
			return null;
		return evaluationelements;
	}

	@Override
	public int updateEvaluationElements(List<Evaluationelement> evaluationelements) {
		try {
			getHibernateTemplate().saveOrUpdateAll(evaluationelements);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	@Override
	public List<Evaluationelement> listCourseBasicElement(Integer cid) {
		String hql = "from Evaluationelement e where e.course.cid=? and evaluationelement.eeid is null";
		List<Evaluationelement> evaluationelements = getHibernateTemplate().find(hql, cid);
		if(evaluationelements == null || evaluationelements.size() == 0)
			return null;
		return evaluationelements;
	}
}
