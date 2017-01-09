package com.interaction.service;

public interface VotequestionService {

	int startVote(int seId,String correctAnswer);

	void endVote(int vqid);

}
