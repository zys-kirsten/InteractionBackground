package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Course;
import com.interaction.pojo.Teacher;
import com.interaction.service.CourseService;
import com.interaction.service.TeacherService;
import com.interaction.service.impl.CourseServiceImpl;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.CourseVo;

@Controller
public class TeacherController {

	@Resource
	private TeacherService teacherServiceImpl;
	@Resource
	private CourseService courseServiceImpl;
	
	//研讨课登录前选择课程)
	@RequestMapping("/chooseCourse")
	public void chooseCourse(String taccount,String tpwd,HttpServletResponse response) throws IOException{

		System.out.println("login");
		Teacher teacher = teacherServiceImpl.login(taccount);
		String msg = "fail";
		
		if(teacher != null){
			if(teacher.getTpwd().equals(tpwd)){
				SessionUtil.getMySession().setAttribute("teacher", teacher);
				List<CourseVo> courseVos = courseServiceImpl.listCourse(teacher.getTid());
				if (courseVos !=null && courseVos.size()!=0) {
					SessionUtil.getMySession().setAttribute("courseVos", courseVos);
					msg = "success";
				}else {
					msg="success2";
				}
				
			}else{
				msg = "wrong";
			}		
		}
		response.getWriter().print(msg);
	}
	
	
	//教师注册
	@RequestMapping("/register")
	public void register(Teacher teacher,HttpServletResponse response) throws IOException{
		
		String msg = "wrong";
		int result = teacherServiceImpl.register(teacher);
		
		if (result != -1) {
			msg = "success"; 
		}
		response.getWriter().print(msg);
	}
	
	//教师注册验证
	@RequestMapping("/registerCheck")
	public void registerCheck(String taccount,HttpServletResponse response) throws IOException{
		Teacher teacher = teacherServiceImpl.listByAccount(taccount);
		if (teacher != null) {
			response.getWriter().write("fail");
		}
	}
	
	//教师研讨课登录
	@RequestMapping("/loginCheck")
	public void loginCheck(String cname,HttpServletRequest request,HttpServletResponse response) throws IOException{

		System.out.println("cname="+cname);
		Teacher teacher = (Teacher) SessionUtil.getMySession().getAttribute("teacher");
		Course course = courseServiceImpl.listCourseByName(teacher.getTid(),cname);
		String msg = "fail";
		if (course != null) {
			SessionUtil.getMySession().setAttribute("course", course);
			msg = "success";
		}
		response.getWriter().print(msg);
	}
	
//	//教师研讨课登录
//		@RequestMapping("/loginCheck")
//		public void loginCheck(String taccount, String tpwd,String cname,
//				HttpServletRequest request,HttpServletResponse response) throws IOException{
//
//			Teacher teacher = teacherServiceImpl.login(taccount);
//			Course course = courseServiceImpl.listCourseByName(teacher.getTid(),cname);
//			HttpSession session = request.getSession();
//			String msg = "fail";
//			
//			if(teacher != null){
//				if(teacher.getTpwd().equals(tpwd)){
//					session.setAttribute("teacher", teacher);
//					session.setAttribute("course", course);
//					msg = "success";
//				}else{
//					msg = "wrong";
//				}		
//			}
//			response.getWriter().print(msg);
//		}
	
	//教师普通登录
	@RequestMapping("/commonLoginCheck")
	public void commonLoginCheck(String taccount, String tpwd,
			HttpServletRequest request,HttpServletResponse response) throws IOException{

		Teacher teacher = teacherServiceImpl.login(taccount);
		String msg = "fail";
		
		if(teacher != null){
			if(teacher.getTpwd().equals(tpwd)){
				SessionUtil.getMySession().setAttribute("teacher", teacher);
				msg = "success";
			}else{
				msg = "wrong";
			}		
		}
		response.getWriter().print(msg);
	}
	
	/*@RequestMapping("/login")
	public ModelAndView login(String taccount,String tpwd) throws IOException{

		System.out.println("login");
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacher = teacherServiceImpl.login(taccount);
		
		if(teacher != null && teacher.getTpwd().equals(tpwd)){
			modelAndView.setViewName("backgroundViews/index");
			modelAndView.addObject("teacher",teacher);				
		}else {
			modelAndView.setViewName("../login");
		}
		return modelAndView;
	}*/
	
	//教师退出登录
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws IOException{
		Object object = request.getSession().getAttribute("teacher");
		  if (object != null) {
		   try {
		    request.getSession().removeAttribute("teacher");
		   } catch (Exception e) {
		    object = null;
		   }
		  }
		return "../login";
	}
}
