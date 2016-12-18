package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Student;
import com.interaction.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDAO studentDAOImpl;
	
	public int addStudent(Student student) {
		return studentDAOImpl.addStudent(student);
	}

	public Student findById(Integer SId) {
		return studentDAOImpl.findById(SId);
	}
	public List<Student> findAll(){
		return studentDAOImpl.findAll();
	}

}
