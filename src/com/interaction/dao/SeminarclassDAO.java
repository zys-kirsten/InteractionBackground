package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Seminarclass;

public interface SeminarclassDAO {

	public List<Seminarclass> listBySeminar(Integer seid);

	public List<Seminarclass> listLoginStudents(Integer seId);
	
}