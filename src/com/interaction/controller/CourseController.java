package com.interaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Course;
import com.interaction.pojo.Teacher;
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.CourseVo;

@Controller
public class CourseController {

	@Resource
	private CourseService courseServiceImpl;
	@Resource
	private EvaluationElementService evaluationElementServiceImpl;
	
	
	//教师添加（修改）课程信息
	@RequestMapping("/addCourse")
	public void addCoures(Course course,HttpServletResponse response) throws IOException{
		String msg = "fail";
		//Teacher teacher = (Teacher) SessionUtil.getMySession().getAttribute("teacher");
		course.setTeacher(getTeacher());
		
		List<CourseVo> courseVos = new ArrayList<CourseVo>();
		int result = -1;

		if (course.getCid() == null) {
			result = courseServiceImpl.addCourse(course);//添加课程
			//添加课程的同时添加该课程的六个评价因素
			int re = evaluationElementServiceImpl.addSixEvaluationElements(course);
			if (re == -1) {
				System.out.println("添加评价因素失败！");
			}
			
		}else{
			result = courseServiceImpl.updateCourse(course);//修改课程
		}
		
		if(result != -1){
			listCourses();
			msg = "success";
		}
		response.getWriter().print(msg);
	}
	
	//教师列出本教师已经添加的课程信息
	@RequestMapping("/listCourse")
	public String listCourse(){
		listCourses();
		return "course/listCourse";
	}
		
	//回显修改课程信息
	@RequestMapping("/listEditCourse")
	public String listEditCourse(@RequestParam(value="cid") Integer cid){
		System.out.println("listCourse cid = "+cid);
		Course course = courseServiceImpl.listCourseById(cid);
		HttpSession session = SessionUtil.getMySession();
		
		session.setAttribute("course", course);
		return "course/editCourse";
	}
	
	//删除该教师选择的某门课程
	@RequestMapping("/deleteCourse")
	public String deleteCourse(@RequestParam(value="cid") Integer cid) throws IOException{
		System.out.println("deleteCourse cid = "+cid);
		int result = courseServiceImpl.deleteCourseById(cid);
		if (result != -1) {
			return listCourse();
		}
		return "course/listCourse";
	}
	
	//列出符合某一查询条件的所有课程
	@RequestMapping("/searchCourse")
	public void searchCourse(String condition,String inputValue,HttpServletResponse response) throws IOException{
		System.out.println(condition);
    	System.out.println(inputValue);
    	String msg="fail";
    	List<Course> courses = courseServiceImpl.findByCondition(getTeacher().getTid(),condition,inputValue);
    	SessionUtil.getMySession().setAttribute("courses", courses);
    	System.out.println("页面应显示数据条数："+courses.size());
    	
    	if(courses!=null){
    		msg="success";
    	}
    	response.getWriter().write(msg);
	}
	
	//列出教师课程
     private void listCourses(){
    	 List<CourseVo> courses = courseServiceImpl.listCourse(getTeacher().getTid());
		 HttpSession session = SessionUtil.getMySession();
		 System.out.println("private listCourse");
		 session.setAttribute("courses", courses);
     }
     
     //获得session中的教师
     private Teacher getTeacher(){
    	return (Teacher) SessionUtil.getMySession().getAttribute("teacher");
     }
}
