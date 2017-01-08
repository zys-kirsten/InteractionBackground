package com.interaction.service;

import java.util.List;

import com.interaction.vo.VotedataVo;

public interface VotedataService {

	List<VotedataVo> listCurrentVotedataBySeidAndVqid(int seid, int vqid);

}
