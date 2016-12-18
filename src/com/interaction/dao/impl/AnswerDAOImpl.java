package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.AnswerDAO;
import com.interaction.pojo.Answer;


@Repository
public class AnswerDAOImpl extends HibernateDaoSupport implements AnswerDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public int addAnswer(Answer answer) {

		Serializable id = getHibernateTemplate().save(answer);
		
		if(id == null || id.toString().length() == 0)
			return -1;
		
		return Integer.parseInt(id.toString());
	}

	@Override
	public List<Answer> listByQid(Integer qid) {
		String hql = "from Answer a where a.question.qid=?";
		List<Answer> answers = getHibernateTemplate().find(hql, qid);
		if(answers == null || answers.size() == 0)
			return null;
		return answers;
	}

	@Override
	public int deleteAnswer(Answer answer) {
		try {
			getHibernateTemplate().delete(answer);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
	}

	@Override
	public int updateAnswer(Answer answer) {
		try {
			getHibernateTemplate().update(answer);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
}