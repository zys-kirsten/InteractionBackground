package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Spocdiscuss;

public interface SpocdiscussDAO {
	public int addSpocdiscuss(Spocdiscuss spocdiscuss);

	public List<Spocdiscuss> findBySidAndCid(Integer sid, Integer cid);

	public int updateSpocdiscuss(Spocdiscuss spocdiscuss);

	public List<Spocdiscuss> ListByCid(Integer cid);

}
