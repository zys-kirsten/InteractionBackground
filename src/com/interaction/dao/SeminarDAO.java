package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Seminar;

public interface SeminarDAO {

	int addSeminar(Seminar seminar);

	Seminar findById(Integer seid);

	List<Seminar> listByCourse(Integer cid);

	int deleteSeminar(Seminar seminar);

	int updateSeminar(Seminar seminar);

	List<Seminar> findBySeName(Integer cid, String seName);

	List<Seminar> listByCourseBeVisited(int cid);

	
	
}