package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.ClassDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.TeacherDAO;
import com.interaction.pojo.Class;
import com.interaction.pojo.Course;
import com.interaction.pojo.Teacher;
import com.interaction.service.CourseService;
import com.interaction.vo.CourseVo;


@Service
public class CourseServiceImpl implements CourseService{

	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private TeacherDAO teacherDAOImpl;
	@Resource
	private ClassDAO classDAOImpl;
	
	@Override
	public int addCourse(Course course) {
		return courseDAOImpl.addCourse(course);
	}

	@Override
	public List<CourseVo> listCourse(Integer tid) {
		List<CourseVo> courseVos = new ArrayList<CourseVo>();
		List<Course> courses = courseDAOImpl.listCourse(tid);
		courseVos = p2v(courses);
		
		return courseVos;
	}

	
	private List<CourseVo> p2v(List<Course> lscspo) {
		List<CourseVo> lscsvo=new ArrayList<CourseVo>();
		if (lscspo==null||lscspo.size()==0) {
			return null;
		}
		for (Iterator<Course> iterator = lscspo.iterator(); iterator.hasNext();) {
			Course course = (Course) iterator.next();
			CourseVo courseVo=new CourseVo();
			courseVo=p2v(course);
			lscsvo.add(courseVo);
		}
		
		return lscsvo;
	}

	public CourseVo p2v(Course course) {
		
		if(course==null)
			return null;
		CourseVo courseVo = new CourseVo();
		courseVo.setCid(course.getCid());
		
		courseVo.setCname(course.getCname());
		courseVo.setCnumber(course.getCnumber());
		courseVo.setCterm(course.getCterm());
		
		Teacher teacher = teacherDAOImpl.findById(course.getTeacher().getTid());
		
		courseVo.setTid(teacher.getTid());
		courseVo.setTname(teacher.getTname());
		return courseVo;
	}
	private Course v2p(CourseVo courseVo) {
		if(courseVo==null){
			return null;
		}
		Course course = new Course();
		
		course.setCid(courseVo.getCid());
		course.setCname(courseVo.getCname());
		course.setCnumber(courseVo.getCnumber());
		course.setCterm(courseVo.getCterm());
		course.setTeacher(teacherDAOImpl.findById(courseVo.getTid()));	
		return course;
	}
	
	@Override
	public Course listCourseById(Integer cid) {
		return courseDAOImpl.findById(cid);
	}
	
	@Override
	public int updateCourse(Course course) {
		return courseDAOImpl.updateCourse(course);
	}
	@Override
	public int deleteCourseById(Integer cid) {
		Course course = courseDAOImpl.findById(cid);
		return courseDAOImpl.deleteCourse(course);
	}
	
	@Override
	public List<Course> findByCondition(Integer tid, String condition, String inputValue) {
		List<Course> courses = null;
		if(condition.equals("cname")){
			courses = courseDAOImpl.listCourseByCname(tid,inputValue);
		}
		if(condition.equals("cnumber")){
			courses = courseDAOImpl.listCourseByCnumber(tid,inputValue);
		}
		if(condition.equals("cterm")){
			courses = courseDAOImpl.listCourseByCterm(tid,inputValue);
		}
		return courses;
	}
	
	@Override
	public Course listCourseByName(Integer tid,String cname) {
		return courseDAOImpl.listCourseByCname(tid, cname).get(0);
	}

	@Override
	public List<CourseVo> listCourseByStudent(int sid) {
     
		List<Class> classes = classDAOImpl.listClassBySid(sid);
		if (classes == null || classes.size() == 0) {
			return null;
		}
		
		List<CourseVo> courseVos = new ArrayList<CourseVo>();
		for(Class c : classes){
			CourseVo courseVo = p2v(courseDAOImpl.findById(c.getCourse().getCid()));
			courseVos.add(courseVo);
		}
		return courseVos;
	}
}
