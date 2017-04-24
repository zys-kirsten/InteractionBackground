package com.interaction.dao;

import com.interaction.pojo.Spocdiscuss;

public interface SpocdiscussDAO {
	public int addSpocdiscuss(Spocdiscuss spocdiscuss);

	public Spocdiscuss findBySidAndCid(Integer sid, Integer cid);

	public int updateSpocdiscuss(Spocdiscuss spocdiscuss);

}
