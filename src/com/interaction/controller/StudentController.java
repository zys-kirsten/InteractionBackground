package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Student;
import com.interaction.service.CourseService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.StudentService;
import com.interaction.utils.JsonUtils;
import com.interaction.vo.CourseVo;
import com.interaction.vo.SeminarVo;

@Controller   
public class StudentController {
	@Resource
	private StudentService studentServiceImpl;
	@Resource
	private CourseService courseServiceImpl;
	@Resource
	private SeminarClassService seminarClassServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	
	//学生登录
	@RequestMapping("/stuLogin")
	public void stuLogin(@RequestParam("saccount")String saccount,@RequestParam("spwd")String spwd,HttpServletResponse response) throws IOException{
		
		Integer sid = -1;
		Student student = studentServiceImpl.findBySaccount(saccount);
		if (student != null) {
			if (student.getSpwd().equals(spwd)) {
				sid = student.getSid();
			}
		}
		JsonUtils.toJson(response, "sid", sid);
	}
	
	//学生签到
	@RequestMapping("/stuSignIn")
	public void stuSignIn(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		flag = studentServiceImpl.stuSignIn(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
	}
	
	//学生查看我的课程
	@RequestMapping("/stuListCourse")
	public void stuListCourse(@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<CourseVo> courses = courseServiceImpl.listCourseByStudent(Integer.parseInt(sid));
		JsonUtils.toJson(response, "courses", courses);
	}

	//学生选课
	@RequestMapping("/stuSelectSeminar")
	public void stuSelectSeminar(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		flag = seminarClassServiceImpl.stuSelectSeminar(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
	}
	
	//学生查看某一门课程下的“我的研讨课”
	@RequestMapping("/stuListMySeminar")
	public void stuListMySeminar(@RequestParam("cid")String cid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<SeminarVo> seminars = seminarServiceImpl.listByCidAndSid(Integer.parseInt(cid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "seminars", seminars);
	}
	
	//学生查看自己的课堂分组
	@RequestMapping("/stuGrouping")
	public void stuGrouping(){
		
	}
	
}
