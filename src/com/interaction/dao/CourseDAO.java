package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Course;


public interface CourseDAO{
	public int addCourse(Course course);
	public List<Course> listCourse(Integer tid);
	public Course findById(Integer cid);
	public int updateCourse(Course course);
	public int deleteCourse(Course course);
	public List<Course> listCourseByCname(Integer tid, String inputValue);
	public List<Course> listCourseByCnumber(Integer tid, String inputValue);
	public List<Course> listCourseByCterm(Integer tid, String inputValue);
}