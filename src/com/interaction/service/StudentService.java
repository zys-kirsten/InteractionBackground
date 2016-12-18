package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Student;

public interface StudentService {
	public int addStudent(Student student);
	public Student findById(Integer SId);
	public List<Student> findAll();
}
