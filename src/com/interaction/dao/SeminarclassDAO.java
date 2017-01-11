package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Seminarclass;

public interface SeminarclassDAO {

	public List<Seminarclass> listBySeminar(Integer seid);

	public List<Seminarclass> listLoginStudents(Integer seId);

	public int updateSeminarclass(Seminarclass seminarclass);

	public Seminarclass findByCEE(int cid, int seid, int sid);

	public int addSeminarclass(Seminarclass seminarclass);

	public List<Seminarclass> listByCidAndSid(int cid, int sid);
	
}