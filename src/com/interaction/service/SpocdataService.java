package com.interaction.service;

import java.io.InputStream;
import java.util.List;

import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;

public interface SpocdataService {

	public List<Spocscore> importSpocscore(InputStream inputStream);

	public int insertSpocscore(List<Spocscore> spocscores, Integer seId);
	
	public List<Spocdiscuss> importSpocdiscuss(InputStream inputStream);

	public int insertSpocdiscuss(List<Spocdiscuss> spocdiscusses, Integer cid);
}
