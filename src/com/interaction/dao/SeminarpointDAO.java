package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Seminarpoint;

public interface SeminarpointDAO {

	List<Seminarpoint> listBySeId(Integer seId);

	int addSeminarpoint(Seminarpoint seminarpoint);

	int updateSeminarpoint(Seminarpoint seminarpoint);

	Seminarpoint findById(Integer spid);

	void deleteSeminarpoint(Seminarpoint seminarpoint);

}
