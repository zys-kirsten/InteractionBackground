package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Seminarpoint;

public interface SeminarpointService {

	List<Seminarpoint> listBySeId(Integer seId);

	int addSeminarpoint(Seminarpoint seminarpoint);

	int updateSeminarpoint(Seminarpoint seminarpoint);

	Seminarpoint findById(Integer spid);

	void deleteSeminarpoint(Integer spid);

}
