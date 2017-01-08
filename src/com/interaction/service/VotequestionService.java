package com.interaction.service;

import com.interaction.pojo.Votequestion;

public interface VotequestionService {

	int startVote(Votequestion votequestion);

	void endVote(int vqid);

}
