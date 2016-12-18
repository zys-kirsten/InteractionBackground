package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Classmodule;

public interface ClassModuleDAO {

	int addClassModule(Classmodule classmodule);
	Classmodule findById(Integer cmid);
	List<Classmodule> listByCourse(Integer cid);
	int updateClassModule(Classmodule classmodule);
	int delete(Classmodule classmodule);

}
