package com.interaction.dao;

import com.interaction.pojo.Votequestion;

public interface VotequestionDAO {

	int addVotequestion(Votequestion votequestion);

	Votequestion findById(int vqid);

	void update(Votequestion votequestion);

	Votequestion listBeVisited(int seid);

}
