package com.interaction.service;

import java.io.InputStream;
import java.util.List;

import com.interaction.pojo.Student;

public interface StudentService {
	public int addStudent(Student student);
	public Student findById(Integer SId);
	public List<Student> findAll();
	public Student findBySaccount(String saccount);
	public int stuSignIn(int cid, int seid, int sid);
	public List<Student> readReport(InputStream inputStream);
	public int insertStudents(List<Student> list, Integer cid);
}
