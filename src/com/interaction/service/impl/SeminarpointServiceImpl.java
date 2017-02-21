package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.SeminarpointDAO;
import com.interaction.pojo.Seminarpoint;
import com.interaction.service.SeminarpointService;

@Service
public class SeminarpointServiceImpl implements SeminarpointService{
	@Resource
	private SeminarpointDAO seminarpointDAOImpl;

	@Override
	public List<Seminarpoint> listBySeId(Integer seId) {
		return seminarpointDAOImpl.listBySeId(seId);
	}

	@Override
	public int addSeminarpoint(Seminarpoint seminarpoint) {
		return seminarpointDAOImpl.addSeminarpoint(seminarpoint);
	}

	@Override
	public int updateSeminarpoint(Seminarpoint seminarpoint) {
		return seminarpointDAOImpl.updateSeminarpoint(seminarpoint);
	}

	@Override
	public Seminarpoint findById(Integer spid) {
		return seminarpointDAOImpl.findById(spid);
	}
	
	@Override
	public void deleteSeminarpoint(Integer spid) {
		Seminarpoint seminarpoint = seminarpointDAOImpl.findById(spid);
		seminarpointDAOImpl.deleteSeminarpoint(seminarpoint);
	}
}
