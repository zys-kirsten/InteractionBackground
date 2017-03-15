package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.TeacherDAO;
import com.interaction.pojo.Teacher;
import com.interaction.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private TeacherDAO teacherDAOImpl;
	
	
	public int register(Teacher teacher) {
		
		return teacherDAOImpl.addTeacher(teacher);
	}

	public Teacher findById(int tid) {
		return teacherDAOImpl.findById(tid);
	}

	@Override
	public Teacher login(String taccount) {
		return teacherDAOImpl.listByAccount(taccount);
		
	}

	@Override
	public Teacher listByAccount(String taccount) {
		return teacherDAOImpl.listByAccount(taccount);
	}

	@Override
	public List<Teacher> listAllTeachers() {
		return teacherDAOImpl.listAllTeachers();
	}

	@Override
	public void deleteTeacherByTid(Integer tid) {

		Teacher teacher = teacherDAOImpl.findById(tid);
		if (teacher == null) {
			return ;
		}
		
		teacherDAOImpl.deleteTeacher(teacher);
	}

}
