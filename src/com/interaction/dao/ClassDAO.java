package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Class;

public interface ClassDAO{

	List<Class> listClassBySid(int sid);

	int addClass(Class class1);

	Class listByCidAndSid(Integer cid, int sid);
}