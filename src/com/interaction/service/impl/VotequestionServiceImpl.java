package com.interaction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.SeminarDAO;
import com.interaction.dao.VotequestionDAO;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Votequestion;
import com.interaction.service.VotequestionService;

@Service
public class VotequestionServiceImpl implements VotequestionService{

	@Resource
	private VotequestionDAO votequestionDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;

	@Override
	public int startVote(int seId,String correctAnswer) {
		Seminar seminar = seminarDAOImpl.findById(seId);
		Votequestion votequestion = new Votequestion();
		votequestion.setCorrectAnswer(correctAnswer);
		votequestion.setSeminar(seminar);
		votequestion.setBeVisited(1);
		return votequestionDAOImpl.addVotequestion(votequestion);
	}

	@Override
	public void endVote(int vqid) {
        Votequestion votequestion = votequestionDAOImpl.findById(vqid);
        if (votequestion != null) {
        	votequestion.setBeVisited(0);
        	votequestionDAOImpl.update(votequestion);
		}
	}
	
	

}
