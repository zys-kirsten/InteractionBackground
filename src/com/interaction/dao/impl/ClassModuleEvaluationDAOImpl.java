package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ClassModuleEvaluationDAO;
import com.interaction.pojo.Classmoduleevaluation;

@Repository
public class ClassModuleEvaluationDAOImpl extends HibernateDaoSupport implements ClassModuleEvaluationDAO{
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addClassModuleEvaluation(Classmoduleevaluation cme) {
		Serializable id = getHibernateTemplate().save(cme);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public List<Classmoduleevaluation> listByCmid(Integer cmid) {
		String hql="from Classmoduleevaluation cme where cme.classmodule.cmid = ?";
		List<Classmoduleevaluation> classmoduleevaluations = getHibernateTemplate().find(hql,cmid);
		if(classmoduleevaluations==null)
			return null;
		return classmoduleevaluations;
	}

	@Override
	public int deleteCme(List<Classmoduleevaluation> classmoduleevaluations) {
		try {
			getHibernateTemplate().deleteAll(classmoduleevaluations);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
}
