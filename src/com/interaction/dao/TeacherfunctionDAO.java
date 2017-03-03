package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Teacherfunction;

public interface TeacherfunctionDAO {

	List<Teacherfunction> listByTid(Integer tid);

	Teacherfunction listByTidAndFcid(Integer tid, Integer fcid);

	void deleteTeacherfunction(Teacherfunction teacherfunction);

	int addTeacherFunction(Teacherfunction teacherfunction);

}
