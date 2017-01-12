package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Seminarscore;

public interface SeminarscoreDAO {

	List<Seminarscore> listBySeidAndSid(int seid, int sid);

	int addSeminarscore(Seminarscore seminarscore);

}
