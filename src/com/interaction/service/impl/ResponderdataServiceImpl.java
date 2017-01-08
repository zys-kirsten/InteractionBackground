package com.interaction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.ResponderdataDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Responderdata;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Student;
import com.interaction.service.ResponderdataService;

@Service
public class ResponderdataServiceImpl implements ResponderdataService{

	@Resource
	private ResponderdataDAO responderdataDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	
	//启动抢答器
	@Override
	public Integer startResponder(int seId) {
		Seminar seminar = seminarDAOImpl.findById(seId);
		if (seminar != null) {
			Responderdata responderdata = new Responderdata();
			responderdata.setSeminar(seminar);
			responderdata.setBeVisited(1);
			return responderdataDAOImpl.addResponderdata(responderdata);
		}
		return -1;
	}
	
	//查找哪一个学生抢答到题目
	@Override
	public Student listDoneStudent(int rdid) {
		Responderdata responderdata = responderdataDAOImpl.findById(rdid);
		if (responderdata != null) {
			if (responderdata.getStudent() != null) {
				return studentDAOImpl.findById(responderdata.getStudent().getSid());
			}
		}
		return null;
	}

	//确定由哪位同学回答，抢答结束
	@Override
	public void endResponder(Integer rdid) {
		Responderdata responderdata = responderdataDAOImpl.findById(rdid);
		if (responderdata != null) {
			responderdata.setBeVisited(0);
			responderdataDAOImpl.updateResponderdata(responderdata);
		}
	}

	//教师重新开启抢答题，选择其他同学回答该问题
	@Override
	public void resetResponder(int rdid) {
		Responderdata responderdata = responderdataDAOImpl.findById(rdid);
		if (responderdata != null) {
			responderdata.setStudent(null);
			responderdataDAOImpl.updateResponderdata(responderdata);
		}
		
	}
}
