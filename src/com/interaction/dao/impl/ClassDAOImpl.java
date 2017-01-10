package com.interaction.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ClassDAO;
import com.interaction.pojo.Class;

@Repository
public class ClassDAOImpl extends HibernateDaoSupport implements ClassDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public List<Class> listClassBySid(int sid) {
		String hql = "from Class c where c.student.sid=?";
		List<Class> classes = getHibernateTemplate().find(hql, sid);
		if(classes == null || classes.size() == 0)
			return null;
		return classes;
	}
}