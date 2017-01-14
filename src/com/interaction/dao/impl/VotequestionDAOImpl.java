package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.VotequestionDAO;
import com.interaction.pojo.Votedata;
import com.interaction.pojo.Votequestion;

@Repository
public class VotequestionDAOImpl extends HibernateDaoSupport implements VotequestionDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public int addVotequestion(Votequestion votequestion) {
		Serializable id = getHibernateTemplate().save(votequestion);
		if(id==null||id.toString().length()==0){
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	@Override
	public Votequestion findById(int vqid) {
		return getHibernateTemplate().get(Votequestion.class,vqid);
	}

	@Override
	public void update(Votequestion votequestion) {

		try {
			getHibernateTemplate().update(votequestion);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public Votequestion listBeVisited(int seid) {
		String hql = "from Votequestion v where v.seminar.seId=? and v.beVisited=1";
		List<Votequestion> votequestion = getHibernateTemplate().find(hql,seid);
		if (votequestion == null || votequestion.size() == 0) {
			return null;
		}
		return votequestion.get(0);
	}
}
