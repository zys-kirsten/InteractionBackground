package com.interaction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Student;
import com.interaction.service.StudentService;

@Controller   
public class StudentController {
	@Resource
	private StudentService studentServiceImpl;
	
	@RequestMapping("/stuLogin")
	public void stuLogin(@RequestParam("saccount")String saccount,@RequestParam("spwd")String spwd,HttpServletResponse response) throws IOException{
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Integer sid = -1;
		Student student = studentServiceImpl.findBySaccount(saccount);
		if (student != null) {
			if (student.getSpwd().equals(spwd)) {
				sid = student.getSid();
			}
		}
		
		String opts = createJsonString("sid",sid);
		out.print(opts);
		out.flush();
		out.close();
		System.out.println("teacherlogin.do  SAccount : "+tAccount+"   SPwd : "+tPwd);
	}

}
