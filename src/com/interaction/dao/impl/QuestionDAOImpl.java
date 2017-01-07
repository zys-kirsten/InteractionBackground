package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.QuestionDAO;
import com.interaction.pojo.Question;

@Repository
public class QuestionDAOImpl extends HibernateDaoSupport implements QuestionDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public int addQuestion(Question question) {
    
		Serializable id = getHibernateTemplate().save(question);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public Question findById(Integer qid) {

		String hql = "from Question q where q.qid = ?";
		List<Question> questions = getHibernateTemplate().find(hql, qid);
		if(questions == null || questions.size() == 0)
			return null;
		
		return questions.get(0);
	}


	@Override
	public List<Question> listByCourse(Integer cid) {

		String hql = "from Question q where q.course.cid=?";
		List<Question> questions = getHibernateTemplate().find(hql,cid);
		if(questions == null || questions.size() == 0)
			return null;
		
		return questions;
	}

	@Override
	public int deleteQuestion(Question question) {
		try {
			getHibernateTemplate().delete(question);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public int updateQuestion(Question question) {
		try {
			getHibernateTemplate().update(question);
			return question.getQid();
		} catch (Exception e) {
		}
		return -1;
	}

	@Override
	public List<Question> listByContent(Integer cid, String inputValue) {
		Session session = getSession();
		String hql="from Question q where q.course.cid = ? and q.content like ?";
		Query question = session.createQuery(hql);
		question.setInteger(0, cid);
		question.setString(1, "%"+inputValue+"%");
		session.clear();
		return question.list();
	}
	
	@Override
	public List<Question> listByseName(Integer cid, String seName) {
		String hql = "from Question q where q.course.cid=? and q.seminar.seName like ?";
		Session session = getSession();
		Query question = session.createQuery(hql);
		question.setInteger(0, cid);
		question.setString(1, "%"+seName+"%");
		session.clear();
		return question.list();
	}
	@Override
	public List<Question> listBySeId(Integer seId) {
		String hql = "from Question q where q.seminar.seId=?";
		List<Question> questions = getHibernateTemplate().find(hql,seId);
		if(questions == null || questions.size() == 0)
			return null;
		
		return questions;
	}
	@Override
	public void updateQuestions(List<Question> questions) {
		try {
			getHibernateTemplate().saveOrUpdateAll(questions);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}