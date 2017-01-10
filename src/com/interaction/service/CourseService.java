package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Course;
import com.interaction.vo.CourseVo;

public interface CourseService {

	public int addCourse(Course course);
	public List<CourseVo> listCourse(Integer tid);
	public Course listCourseById(Integer cid);
	public int updateCourse(Course course);
	public int deleteCourseById(Integer cid);
	public List<Course> findByCondition(Integer tid, String condition, String inputValue);
	public Course listCourseByName(Integer integer, String cname);
	public List<CourseVo> listCourseByStudent(int sid);
}
