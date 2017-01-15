package com.interaction.dao;

import com.interaction.pojo.Semclatest;

public interface SemclatestDAO {

	int addSemclatest(Semclatest semclatest);

	Semclatest listBySeidAndSidAndQidBeVisited(int seid, int sid, int qid);
	
}