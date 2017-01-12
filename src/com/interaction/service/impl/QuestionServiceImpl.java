package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.algorithm.group.GroupDivide;
import com.interaction.dao.AnswerDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.pojo.Answer;
import com.interaction.pojo.Course;
import com.interaction.pojo.Question;
import com.interaction.pojo.Seminar;
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
	@Resource
	private SeminarDAO seminarDAOImpl;
	
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
		Seminar seminar = seminarDAOImpl.findById(qp.getSeminar().getSeId());
		List<Answer> answers = answerDAOImpl.listByQid(qp.getQid());
		qv.setSeId(seminar.getSeId());
		qv.setSeName(seminar.getSeName());
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
		Seminar seminar = seminarDAOImpl.findById(questionVo.getSeId());
		question.setQid(questionVo.getQid());
		question.setSeminar(seminar);
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
		if(condition.equals("seName")){
			questionVos = p2v(questionDAOImpl.listByseName(cid,inputValue));
		}
		if(condition.equals("content")){
			questionVos = p2v(questionDAOImpl.listByContent(cid,inputValue));
		}
		return questionVos;
	}
//========================================Android端=======================================================================
	//开展限时测试题
	@Override
	public void startTimeLimitExercise(Integer seId, Integer number) {
		List<Question> questions = questionDAOImpl.listBySeId(seId);
		if (questions != null && questions.size() != 0 && number > 0) {
			if (questions.size() <= number) {
				for(Question question:questions){
					question.setBeVisited(1);
				}
				questionDAOImpl.updateQuestions(questions);
			} else {
                int[] index = GroupDivide.getRandomIndex(questions.size());
                for (int i = 0; i < number; i++) {
					Question question = questions.get(--index[i]);
					question.setBeVisited(1);
					questionDAOImpl.updateQuestion(question);
				}
			}
		}
		
	}
	//结束限时测试题
	@Override
	public void endTimeLimitExercise(int seId) {
		List<Question> questions = questionDAOImpl.listBySeId(seId);
		if (questions != null && questions.size() != 0) {
			for(Question question:questions){
				question.setBeVisited(0);
			}
			questionDAOImpl.updateQuestions(questions);
		}
	}
//==============================学生android端===================================================================

	@Override
	public List<QuestionVo> listByCidAndSeidBeVisted(int cid, int seid) {
		List<Question> questions = questionDAOImpl.listByCidAndSeidBeVisted(cid,seid);
		if (questions == null  || questions.size() == 0) {
			return null;
		}
		return p2v(questions);
	}
}
