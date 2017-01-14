package com.interaction.service;

import java.util.List;

import com.interaction.vo.VotedataVo;

public interface VotedataService {

	List<VotedataVo> listCurrentVotedataBySeidAndVqid(int seid, int vqid);

	//学生进行投票数据修改
	int stuBeginVote(int seid, int sid, String stuAnswer);

}
