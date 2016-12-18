package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.CourseDAO;
import com.interaction.pojo.Course;
import com.interaction.pojo.Teacher;

@Repository
public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public int addCourse(Course course) {
		Serializable id=getHibernateTemplate().save(course);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public List<Course> listCourse(Integer tid) {
		String hql="from Course c where c.teacher.tid = ?";
		List<Course> courses = getHibernateTemplate().find(hql,tid);
		if(courses==null)
			return null;
		return courses;
	}

	@Override
	public Course findById(Integer cid) {
		String hql="from Course c where c.cid = ?";
		List<Course> courses = getHibernateTemplate().find(hql,cid);
		if(courses==null)
			return null;
		return courses.get(0);
	}
	@Override
	public int updateCourse(Course course) {
		try {
			getHibernateTemplate().update(course);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
	@Override
	public int deleteCourse(Course course) {
  
		try {
			getHibernateTemplate().delete(course);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
	}

	@Override
	public List<Course> listCourseByCname(Integer tid, String inputValue) {
		Session session = getSession();
		String hql="from Course c where c.teacher.tid = ? and c.cname like ?";
		Query courses = session.createQuery(hql);
		courses.setInteger(0, tid);
		courses.setString(1, "%"+inputValue+"%");
		session.clear();
		return courses.list();
	}

	@Override
	public List<Course> listCourseByCnumber(Integer tid, String inputValue) {
		Session session = getSession();
		String hql="from Course c where c.teacher.tid = ? and c.cnumber like ?";
		Query courses = session.createQuery(hql);
		courses.setInteger(0, tid);
		courses.setString(1, "%"+inputValue+"%");
		session.clear();
		return courses.list();
	}

	@Override
	public List<Course> listCourseByCterm(Integer tid, String inputValue) {
		Session session = getSession();
		String hql="from Course c where c.teacher.tid = ? and c.cterm like ?";
		Query courses = session.createQuery(hql);
		courses.setInteger(0, tid);
		courses.setString(1, "%"+inputValue+"%");
		session.clear();
		return courses.list();
	}
}