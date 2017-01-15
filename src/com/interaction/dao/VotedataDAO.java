package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Votedata;

public interface VotedataDAO {

	List<Votedata> listCurrentVotedataBySeidAndVqid(int seid, int vqid);

	int addVotedata(Votedata votedata);

	Votedata listBySeidAndSidBeVisited(int seid, int sid);

}
