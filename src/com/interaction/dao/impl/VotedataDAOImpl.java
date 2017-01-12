package com.interaction.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.interaction.dao.VotedataDAO;
import com.interaction.pojo.Votedata;

@Repository
public class VotedataDAOImpl extends HibernateDaoSupport implements VotedataDAO{

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	
	@Override
	public List<Votedata> listCurrentVotedataBySeidAndVqid(int seid, int vqid) {
		String hql = "from Votedata v where v.seminar.seId=? and v.votequestion.vqid=?";
		List<Votedata> votedatas = getHibernateTemplate().find(hql,seid,vqid);
		if (votedatas == null || votedatas.size() == 0) {
			return null;
		}
		return votedatas;
	}
	
	@Override
	public int addVotedata(Votedata votedata) {

		Serializable id = getHibernateTemplate().save(votedata);
		if (id == null || id.toString().length() == 0) {
			return -1;
		}
		return Integer.parseInt(id.toString());
	}

	//执行原生的sql，但仍要求必须是数据库中存在的实体。返回vo不可以
//	@Override
//	public List<VotedataVo> listCurrentVotedataBySeidAndVqid(int seid, int vqid) {
//		String hql="SELECT StuAnswer stuAnswer,COUNT(StuAnswer) stuNum from votedata where SeId=? "
//				+ "and VQId=? GROUP BY StuAnswer";
//		SQLQuery query = getSession().createSQLQuery(hql);  
//        query.addEntity("votedata",Votedata.class);  
//        query.setInteger(0, seid);  
//        query.setInteger(1, vqid); 
//		List<Object> votedataVos =query.list();
//		for(Object o:votedataVos){
//			
//			System.out.println();
//		}
//		if(votedataVos==null || votedataVos.size() == 0)
//			return null;
//		return null;
//	}
}
