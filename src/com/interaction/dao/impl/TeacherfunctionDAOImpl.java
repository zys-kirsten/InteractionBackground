package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.TeacherfunctionDAO;
import com.interaction.pojo.Teacherfunction;

@Repository
public class TeacherfunctionDAOImpl extends HibernateDaoSupport implements TeacherfunctionDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public List<Teacherfunction> listByTid(Integer tid) {
		String hql = "from Teacherfunction tf where tf.teacher.tid=?";
		List<Teacherfunction> teacherfunctions = getHibernateTemplate().find(hql, tid);
		if (teacherfunctions == null ||teacherfunctions.size() == 0) {
			return null;
		}
		return teacherfunctions;
	}

	@Override
	public Teacherfunction listByTidAndFcid(Integer tid, Integer fcid) {
		String hql = "from Teacherfunction tf where tf.teacher.tid=? and tf.functioncomponent.fcid=?";
		List<Teacherfunction> teacherfunctions = getHibernateTemplate().find(hql, tid,fcid);
		if (teacherfunctions == null ||teacherfunctions.size() == 0) {
			return null;
		}
		return teacherfunctions.get(0);
	}

	@Override
	public void deleteTeacherfunction(Teacherfunction teacherfunction) {
		try {
			getHibernateTemplate().delete(teacherfunction);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public int addTeacherFunction(Teacherfunction teacherfunction) {
		Serializable id = getHibernateTemplate().save(teacherfunction);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}
}
