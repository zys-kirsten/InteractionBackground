package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Student;

@Repository
public class StudentDAOImpl extends HibernateDaoSupport implements StudentDAO {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	public int addStudent(Student student) {
		Serializable id=getHibernateTemplate().save(student);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());

	}

	public Student findById(Integer SId) {
		String hql="from Student s where s.sid = ?";
		List<Student> student = getHibernateTemplate().find(hql,SId);
		if(student==null || student.size() == 0)
			return null;
		return student.get(0);

	}

	@Override
	public List<Student> findAll() {
		String hql="from Student s";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Student findBySaccount(String saccount) {
		String hql="from Student s where s.saccount = ?";
		List<Student> student = getHibernateTemplate().find(hql,saccount);
		if(student==null || student.size() == 0)
			return null;
		return student.get(0);
	}

	
}