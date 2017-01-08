package com.interaction.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.UnquantizationFuzzyEvaluationDAO;
import com.interaction.pojo.Unquantizationfuzzyevaluation;

@Repository
public class UnquantizationFuzzyEvaluationDAOImpl extends HibernateDaoSupport implements UnquantizationFuzzyEvaluationDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public List<Unquantizationfuzzyevaluation> listUnquantizationFuzzyEvaluationBySSE(Integer seid, Integer sid,
			Integer eeid) {
		String hql="from Unquantizationfuzzyevaluation ufe where ufe.seminar.seId = ? and ufe.student.sid = ? and ufe.evaluationelement.eeid = ?";
		List<Unquantizationfuzzyevaluation> unquantizationfuzzyevaluations = getHibernateTemplate().find(hql,seid,sid,eeid);
		if(unquantizationfuzzyevaluations==null || unquantizationfuzzyevaluations.size() == 0)
			return null;
		return unquantizationfuzzyevaluations;
	}
}
