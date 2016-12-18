package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.ClassModuleDAO;
import com.interaction.pojo.Classmodule;
import com.interaction.pojo.Question;

@Repository
public class ClassModuleDAOImpl extends HibernateDaoSupport implements ClassModuleDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}

	@Override
	public int addClassModule(Classmodule classmodule) {
		Serializable id = getHibernateTemplate().save(classmodule);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public Classmodule findById(Integer cmid) {
		return getHibernateTemplate().get(Classmodule.class, cmid);
	}

	@Override
	public List<Classmodule> listByCourse(Integer cid) {
		String hql = "from Classmodule cm where cm.course.cid=?";
		List<Classmodule> classmodules = getHibernateTemplate().find(hql,cid);
		if(classmodules == null || classmodules.size() == 0)
			return null;
		
		return classmodules;
	}

	@Override
	public int updateClassModule(Classmodule classmodule) {
		try {
			getHibernateTemplate().update(classmodule);
			return classmodule.getCmid();
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	@Override
	public int delete(Classmodule classmodule) {

		try {
			getHibernateTemplate().delete(classmodule);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
