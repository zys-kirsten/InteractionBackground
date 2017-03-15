package com.interaction.dao;

import java.util.List;
import com.interaction.pojo.Teacher;

public interface TeacherDAO {
	public int addTeacher(Teacher teacher);
	public int modifyTeacher(Teacher teacher);
	public List<Teacher> listByStudent(String sid);
	public Teacher findById(int tid);
	public Teacher listByAccount(String taccount);
	public List<Teacher> listAllTeachers();
	public void deleteTeacher(Teacher teacher);
	
}