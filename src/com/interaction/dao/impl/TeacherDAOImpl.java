package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.TeacherDAO;
import com.interaction.pojo.Teacher;

@Repository
public class TeacherDAOImpl extends HibernateDaoSupport implements TeacherDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	

	public int addTeacher(Teacher teacher) {
		
		Serializable id=getHibernateTemplate().save(teacher);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	
	public List<Teacher> listByStudent(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int modifyTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Teacher findById(int tid) {
		String hql="from Teacher t where t.tid = ?";
		List<Teacher> teacher = getHibernateTemplate().find(hql,tid);
		if(teacher==null)
			return null;
		return teacher.get(0);
	}


	@Override
	public Teacher listByAccount(String taccount) {
		String hql="from Teacher t where t.taccount = ?";
		List<Teacher> teacher = getHibernateTemplate().find(hql,taccount);
		if(teacher==null || teacher.size() == 0)
			return null;
		return teacher.get(0);
	}


	@Override
	public List<Teacher> listAllTeachers() {
		String hql="from Teacher";
		List<Teacher> teachers = getHibernateTemplate().find(hql);
		if(teachers==null || teachers.size() == 0)
			return null;
		return teachers;
	}


	@Override
	public void deleteTeacher(Teacher teacher) {
		getHibernateTemplate().delete(teacher);
	}
	
}