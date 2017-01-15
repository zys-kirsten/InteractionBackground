package com.interaction.dao.impl;

import java.io.Serializable;
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
	
	@Override
	public int addClass(Class class1) {

		Serializable id = getHibernateTemplate().save(class1);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
	
	@Override
	public Class listByCidAndSid(Integer cid, int sid) {
		String hql = "from Class c where c.student.sid=? and c.course.cid=?";
		List<Class> classes = getHibernateTemplate().find(hql, sid,cid);
		if(classes == null || classes.size() == 0)
			return null;
		return classes.get(0);
	}
}