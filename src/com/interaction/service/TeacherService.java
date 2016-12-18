package com.interaction.service;

import com.interaction.pojo.Teacher;

public interface TeacherService {

	int register(Teacher teacher);

	Teacher findById(int tid);

	Teacher login(String taccount);

	Teacher listByAccount(String taccount);

}
