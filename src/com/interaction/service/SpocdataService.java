package com.interaction.service;

import java.io.InputStream;
import java.util.List;

import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;
import com.interaction.vo.SpocDataVo;

public interface SpocdataService {

	public List<Spocscore> importSpocscore(InputStream inputStream);

	public int insertSpocscore(List<Spocscore> spocscores, Integer seId);
	
	public List<Spocdiscuss> importSpocdiscuss(InputStream inputStream);

	public int insertSpocdiscuss(List<Spocdiscuss> spocdiscusses, Integer cid);

	public SpocDataVo grenateGraph(Integer seId);

	public List<SpocDataVo> generateDiscussGraph(Integer cid, Integer sid);

	public SpocDataVo generateStuGraph(Integer seId, Integer sid);
}
