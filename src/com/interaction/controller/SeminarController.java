package com.interaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Teacher;
import com.interaction.service.SeminarService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.CourseVo;
import com.interaction.vo.SeminarVo;


@Controller
public class SeminarController {

	@Resource
	private SeminarService seminarServiceImpl;
	
	//删除该教师选择的研讨课
		@RequestMapping("/deleteSeminar")
		public String deleteCourse(@RequestParam(value="seId") Integer seId,@RequestParam(value="cid") Integer cid) throws IOException{
			System.out.println("deleteSeminar seId = "+seId);
			int result = seminarServiceImpl.deleteSeminarById(seId);
		
			return listSeminarByCourse(cid);
		}
	
	//回显修改研讨课信息
		@RequestMapping("/editSeminar")
		public String editSeminar(@RequestParam(value="seId") Integer seId){
			System.out.println("listSeminar seId = "+seId);
			Seminar seminar = seminarServiceImpl.findById(seId);
			HttpSession session = SessionUtil.getMySession();
			
			session.setAttribute("seminar", seminar);
			return "seminar/editSeminar";
		}
	
	//添加(修改)研讨课信息
	@RequestMapping("/addSeminar")
	public void addSeminar(SeminarVo seminarVo,HttpServletResponse response) throws IOException{
		String msg = "fail";
			
		Course course = getCourse();
		Teacher teacher = getTeacher();
		seminarVo.setCid(course.getCid());
		seminarVo.setTid(teacher.getTid());
		System.out.println(seminarVo+"********");
		int result = -1;

		if (seminarVo.getSeId() == null) {
			result = seminarServiceImpl.addSeminar(seminarVo);//添加研讨课
		}else{
			result = seminarServiceImpl.updateSeminar(seminarVo);//修改研讨课
		}
		
		if(result != -1){
			listCourseSeminar();
			msg = "success";
		}
		response.getWriter().print(msg);
	}
	
	
	private void listCourseSeminar() {
		List<SeminarVo> sVos = seminarServiceImpl.listByCourse(getCourse().getCid());
		 HttpSession session = SessionUtil.getMySession();
		 System.out.println("private listSeminar");
		 session.setAttribute("seminarVos", sVos);
		
	}

	//列出某一课程的所有研讨课
	@RequestMapping("/listSeminarByCourse")
	public String listSeminarByCourse(@RequestParam(value="cid") Integer cid){
//		if(cid == null){
//			return "error";
//		}
//		List<SeminarVo> sVos = seminarServiceImpl.listByCourse(cid);
//	    SessionUtil.getMySession().setAttribute("seminarVos", sVos);
		listCourseSeminar();
		return "listSeminar";
	}
	
//	//列出某一教师的所有研讨课
//	@RequestMapping("/listSeminar")
//	public String listSeminarByTeacher(@RequestParam(value="tid") Integer tid){
//		ModelAndView modelAndView = new ModelAndView();
//		
//		if(tid == null){
//			modelAndView.setViewName("error");
//			return modelAndView;
//		}
//		List<SeminarVo> sVos = seminarServiceImpl.listByTeacher(tid);
//	   
//		if(sVos == null || sVos.size() == 0){
//			modelAndView.setViewName("error");
//		}else {
//			modelAndView.setViewName("seminarDisplay");
//		    modelAndView.addObject("seminarVos", sVos);
//		}		
//		return modelAndView;
//	}
	
	//列出某一位教师有研讨课的所有课程
	@RequestMapping("/listHaveSeminarCourse")
	public String listHaveSeminarCourse(){
		List<Course> courses = seminarServiceImpl.listHaveSeminarCourseByTeacher(getTeacher().getTid());
		SessionUtil.getMySession().setAttribute("courseSeminar", courses);
		return "listSeminarCourse";
	}
	
	//列出某一教师的所有研讨课
	@RequestMapping("/listAllSeminar")
	public String listSeminarByTeacher(){
		listSeminar();
		return "listAllSeminar";
	}
	
	//列出教师所有课程的所有研讨课
    private void listSeminar(){
     List<List<SeminarVo>> seminars = seminarServiceImpl.listAllByTeacher(getTeacher().getTid());
    // List<SeminarVo> seminars = seminarServiceImpl.listByTeacher(getTeacher().getTid());
	 HttpSession session = SessionUtil.getMySession();
	 System.out.println("private listSeminar");
	 session.setAttribute("allSeminars", seminars);
    }
    
    //获得session中的教师
    private Teacher getTeacher(){
   	return (Teacher) SessionUtil.getMySession().getAttribute("teacher");
    }
    
    //获得session中的课程
    private Course getCourse(){
   	return (Course) SessionUtil.getMySession().getAttribute("course");
    }
	
}
