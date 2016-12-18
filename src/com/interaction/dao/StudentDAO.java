package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Student;

public interface StudentDAO {
	public int addStudent(Student student);
	public Student findById(Integer SId);
	public List<Student> findAll();
	
}