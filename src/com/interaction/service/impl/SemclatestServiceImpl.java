package com.interaction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.AnswerDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.dao.SemclatestDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Answer;
import com.interaction.pojo.Course;
import com.interaction.pojo.Question;
import com.interaction.pojo.Semclatest;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Student;
import com.interaction.service.SemclatestService;

@Service
public class SemclatestServiceImpl implements SemclatestService{

	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private QuestionDAO questionDAOImpl;
	@Resource
	private AnswerDAO answerDAOImpl;
	@Resource
	private SemclatestDAO semclatestDAOImpl;
	
	
	@Override
	public int submitSemclatest(int cid, int seid, int sid, int qid, int aid) {
		Question question = questionDAOImpl.findByIdBeVisited(qid);
		if (question == null) {
			return -1;
		}
		Course course = courseDAOImpl.findById(cid);
		if (course == null) {
			return -1;
		}
		Seminar seminar = seminarDAOImpl.findById(seid);
		if (seminar == null) {
			return -1;			
		}
		Student student = studentDAOImpl.findById(sid);
		if (student == null) {
			return -1;
		}
		Answer answer = answerDAOImpl.findById(aid);
		if (answer == null) {
			return -1;
		}
		
		Semclatest judge = semclatestDAOImpl.listBySeidAndSidAndQidBeVisited(seid,sid,qid);
		if (judge != null) {
			return -1;
		}
		
		Semclatest semclatest = new Semclatest();
		semclatest.setAnswer(answer);
		semclatest.setCourse(course);
		semclatest.setQuestion(question);
		semclatest.setSeminar(seminar);
		semclatest.setStudent(student);
		return semclatestDAOImpl.addSemclatest(semclatest);
	}
}
