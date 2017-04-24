package com.interaction.dao;

import com.interaction.pojo.Spocscore;

public interface SpocscoreDAO {

	public int addSpocscore(Spocscore spocscore);

	public Spocscore findBySidAndSeid(Integer sid, Integer seId);

	public int updateSpocscore(Spocscore spocscore);
}
