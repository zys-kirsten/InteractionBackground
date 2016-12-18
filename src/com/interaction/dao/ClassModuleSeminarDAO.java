package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Classmoduleseminar;

public interface ClassModuleSeminarDAO {

	int addClassModuleSeminar(Classmoduleseminar cmse);

	List<Classmoduleseminar> listByCmid(Integer cmid);

	int deleteCms(List<Classmoduleseminar> classmoduleseminars);

}
