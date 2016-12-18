package com.interaction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Student;
import com.interaction.service.StudentService;

@Controller   
public class StudentController {
	@Resource
	private StudentService studentServiceImpl;
	
	@RequestMapping("/regist")
	public ModelAndView regist(Student student,ModelMap map){
		int sid = student.getSid();
		List<Student> students = null;
		
		int result = studentServiceImpl.addStudent(student);
		System.out.println("___=+=="+result);
		if(result != -1 ){
			System.out.println("111111111111");
			
			students = studentServiceImpl.findAll();
			System.out.println(students);
		}else{
			students = null;
		}
		
		ModelAndView modelAndView = new ModelAndView();  
        modelAndView.addObject("students", students);  
        modelAndView.setViewName("studentDisplay");  
        return modelAndView;  
	}

}
