package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Scoreshow;

public interface ScoreshowDAO {

	public int addScoreShow(Scoreshow scoreshow);

	public List<Scoreshow> listByEvaluation(Integer eid);

	public int updateScoreShow(Scoreshow scoreshow);

	public int deleteScoreshow(Scoreshow scoreshow);

}
