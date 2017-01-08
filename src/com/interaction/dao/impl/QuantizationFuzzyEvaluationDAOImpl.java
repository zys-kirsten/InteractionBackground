package com.interaction.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.QuantizationFuzzyEvaluationDAO;
import com.interaction.pojo.Quantizationfuzzyevaluation;
import com.interaction.pojo.Unquantizationfuzzyevaluation;

@Repository
public class QuantizationFuzzyEvaluationDAOImpl extends HibernateDaoSupport implements QuantizationFuzzyEvaluationDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public Quantizationfuzzyevaluation listQuantizationFuzzyEvaluationBySSE(Integer seid, Integer sid, Integer eeid) {
		String hql="from Quantizationfuzzyevaluation qfe where qfe.seminar.seId = ? and qfe.student.sid = ? and qfe.evaluationelement.eeid = ?";
		List<Quantizationfuzzyevaluation> quantizationfuzzyevaluations = getHibernateTemplate().find(hql,seid,sid,eeid);
		if(quantizationfuzzyevaluations == null || quantizationfuzzyevaluations.size() == 0)
			return null;
		return quantizationfuzzyevaluations.get(0);
	}
}
