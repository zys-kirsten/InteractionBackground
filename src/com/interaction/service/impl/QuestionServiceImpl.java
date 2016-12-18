package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.AnswerDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.pojo.Answer;
import com.interaction.pojo.Course;
import com.interaction.pojo.Question;
import com.interaction.service.QuestionService;
import com.interaction.vo.QuestionVo;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Resource
	private QuestionDAO questionDAOImpl;
	@Resource
	private AnswerDAO answerDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	
	//修改测试题
	@Override
	public int updateQuestion(QuestionVo questionVo) {

		Question question = v2qp(questionVo);
		int questionResult = -1,answerResult = -1;
		boolean flag = true;
		
		if(question != null ){
			questionResult = questionDAOImpl.updateQuestion(question);
			if(questionResult != -1){
				List<Answer> answers = v2lap(questionVo,questionResult);
				if(answers != null && answers.size() != 0){
					for(Answer answer:answers){
						  if (answer.getAid() == null) {
							answerResult = answerDAOImpl.addAnswer(answer);
						}else{
							 answerResult = answerDAOImpl.updateAnswer(answer);
						}
						   if(answerResult == -1){
							   flag = false;
						   }
						}
				}
			}
		}
		
		
		if(questionResult == -1){
			return -1;
		}else if(questionResult != -1 && flag == false){
			return 0;
		}else {
			return 1;
		}
	}
	
	//添加测试题
	@Override
	public int addQuestion(QuestionVo questionVo) {
		
		Question question = v2qp(questionVo);
		int questionResult = -1,answerResult = -1;
		boolean flag = true;
		
		if(question != null ){
			questionResult = questionDAOImpl.addQuestion(question);
			
			if(questionResult != -1){
				List<Answer> answers = v2lap(questionVo,questionResult);
//				for(Answer answer:answers){
//				   System.out.println(answer.getAcontent());
//				}
				
				if(answers != null && answers.size() != 0){
					for(Answer answer:answers){
						   answerResult = answerDAOImpl.addAnswer(answer);
						   if(answerResult == -1){
							   flag = false;
						   }
						}
				}
			}
		}
		
		
		if(questionResult == -1){
			return -1;
		}else if(questionResult != -1 && flag == false){
			return 0;
		}else {
			return 1;
		}
	}

	//列出某一章节的测试题
	@Override
	public List<QuestionVo> listQuestionByChapter(Integer cid, Integer chapter) {
		List<QuestionVo> ltqv = new ArrayList<QuestionVo>();
		List<Question> ltqp = questionDAOImpl.listByChapter(cid,chapter);
		
		ltqv = p2v(ltqp);
		return ltqv;
	}

	//列出某一门课的测试题
	@Override
	public List<QuestionVo> listQuestionByCourse(Integer cid) {
		List<QuestionVo> ltqv = new ArrayList<QuestionVo>();
		List<Question> ltqp = questionDAOImpl.listByCourse(cid);
		
		ltqv = p2v(ltqp);
		return ltqv;
	}

	private List<QuestionVo> p2v(List<Question> ltqp) {
		List<QuestionVo> ltqv = new ArrayList<QuestionVo>();
		
		if(ltqp == null || ltqp.size() == 0)
			return null;
		
		for(Iterator<Question> iterator = ltqp.iterator();iterator.hasNext();){
			Question qp = iterator.next();
			QuestionVo qv = p2v(qp);
			
			if(qv != null)
				 ltqv.add(qv);
		}
		
		return ltqv;
	}
	
	private QuestionVo p2v(Question qp) {
		if(qp == null)
			return null;
		
		QuestionVo qv = new QuestionVo();
		Course course = courseDAOImpl.findById(qp.getCourse().getCid());
		List<Answer> answers = answerDAOImpl.listByQid(qp.getQid());
		qv.setChapter(qp.getChapter());
		qv.setCid(course.getCid());
		qv.setCname(course.getCname());
		qv.setContent(qp.getContent());
		qv.setQid(qp.getQid());
		if(answers != null && answers.size() != 0)
			qv.setAnswers(answers);
		
		return qv;
	}


	private Question v2qp(QuestionVo questionVo) {

		if(questionVo == null)
			return null;
		
		Question question = new Question();
		Course course = courseDAOImpl.findById(questionVo.getCid());
		
		question.setQid(questionVo.getQid());
		question.setChapter(questionVo.getChapter());
		question.setContent(questionVo.getContent());
		question.setCourse(course);
		
		return question;
	}

	private List<Answer> v2lap(QuestionVo questionVo, Integer questionResult) {

		
		if(questionVo == null)
			return null;
		
		
		Question question = questionDAOImpl.findById(questionResult);
		List<Answer> answers = new ArrayList<Answer>();
		for(int i = 0;i<questionVo.getAnswers().size();i++){
			Answer answer = new Answer();
			answer.setAcontent(questionVo.getAnswers().get(i).getAcontent());
			answer.setAexplain(questionVo.getAnswers().get(i).getAexplain());
			answer.setAid(questionVo.getAnswers().get(i).getAid());
			answer.setQuestion(question);
			answer.setCorrect(questionVo.getAnswers().get(i).getCorrect());
			
			answers.add(answer);
		}
		
		return answers;
	}

	@Override
	public int deleteQuestionById(Integer qid) {
		Question question = questionDAOImpl.findById(qid);
		List<Answer> answers = answerDAOImpl.listByQid(qid);
		int result = 0;
		if (answers != null && answers.size() !=0) {
			for (Answer answer:answers) {
				result += answerDAOImpl.deleteAnswer(answer);
			}
		}
		
		if(result == -4){
			return -1;
			
		}else {
			return questionDAOImpl.deleteQuestion(question);
		}
		
	}

	@Override
	public QuestionVo findById(Integer qid) {
		Question question = questionDAOImpl.findById(qid);
		return p2v(question);
	}

	@Override
	public List<QuestionVo> findByCondition(Integer cid, String condition, String inputValue) {
		List<QuestionVo> questionVos = null;
		if(condition.equals("chapter")){
			questionVos = listQuestionByChapter(cid,Integer.parseInt(inputValue));
		}
		if(condition.equals("content")){
			questionVos = p2v(questionDAOImpl.listByContent(cid,inputValue));
		}
		return questionVos;
	}

	
}
