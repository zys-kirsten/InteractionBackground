package com.interaction.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
	
	public int addStudent(Student student) {
		return studentDAOImpl.addStudent(student);
	}

	public Student findById(Integer SId) {
		return studentDAOImpl.findById(SId);
	}
	public List<Student> findAll(){
		return studentDAOImpl.findAll();
	}

	//根据学生用户名查找学生
	@Override
	public Student findBySaccount(String saccount) {
		if (saccount == null) {
			return null;
		}
		return studentDAOImpl.findBySaccount(saccount);
	}

	//学生签到
	@Override
	public int stuSignIn(int cid, int seid, int sid) {
		Seminarclass seminarclass = seminarclassDAOImpl.findByCEE(cid,seid,sid);
		if (seminarclass != null) {
			seminarclass.setIsLogin(1);
			return seminarclassDAOImpl.updateSeminarclass(seminarclass);
		}
		return -1;
	}
	
}
