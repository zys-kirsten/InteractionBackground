package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Functioncomponent;

public interface FunctioncomponentDAO {

	List<Functioncomponent> listAllFunctioncomponent();

	List<Functioncomponent> listFunctioncomponentByAid(Integer aid);

	int addFunctioncomponent(Functioncomponent functioncomponent);

	Functioncomponent findById(Integer fcid);
	
	

}
