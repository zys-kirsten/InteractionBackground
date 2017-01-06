package testDao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.interaction.dao.AnswerDAO;
import com.interaction.dao.ClassModuleDAO;
import com.interaction.dao.ClassModuleEvaluationDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.dao.ScoreshowDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.TeacherDAO;
import com.interaction.dao.VotedataDAO;
import com.interaction.dao.impl.StudentDAOImpl;
import com.interaction.pojo.Votedata;


public class TestDao {


	private BeanFactory beanFactory;
	private StudentDAOImpl studentDAOImpl;
	private TeacherDAO teacherDAOImpl;
	private CourseDAO courseDAOImpl;
	private SeminarDAO seminarDAOImpl;
	private SeminarclassDAO seminarclassDAOImpl;
	private QuestionDAO questionDAOImpl;
	private AnswerDAO answerDAOImpl;
	private EvaluationDAO evaluationDAOImpl;
	private ScoreshowDAO scoreshowDAOImpl;
	private ClassModuleDAO classModuleDAOImpl;
	private ClassModuleEvaluationDAO classModuleEvaluationDAOImpl;
	private VotedataDAO votedataDAOImpl;
	
	public void getDao(){
		
		beanFactory =new ClassPathXmlApplicationContext("applicationContext.xml");
		//studentDAOImpl =(StudentDAOImpl)beanFactory.getBean("studentDAOImpl");
		//teacherDAOImpl = (TeacherDAO) beanFactory.getBean("teacherDAOImpl");
		//courseDAOImpl = (CourseDAO) beanFactory.getBean("courseDAOImpl");
		//seminarDAOImpl = (SeminarDAO) beanFactory.getBean("seminarDAOImpl");
		//seminarclassDAOImpl = (SeminarclassDAO) beanFactory.getBean("seminarclassDAOImpl");
	   // questionDAOImpl = (QuestionDAO) beanFactory.getBean("questionDAOImpl");
	   // answerDAOImpl = (AnswerDAO) beanFactory.getBean("answerDAOImpl");
		//evaluationDAOImpl = (EvaluationDAO) beanFactory.getBean("evaluationDAOImpl");
	   // scoreshowDAOImpl = (ScoreshowDAO) beanFactory.getBean("scoreshowDAOImpl");
		//classModuleDAOImpl = (ClassModuleDAO) beanFactory.getBean("classModuleDAOImpl");
		//classModuleEvaluationDAOImpl = (ClassModuleEvaluationDAO) beanFactory.getBean("classModuleEvaluationDAOImpl");
		votedataDAOImpl = (VotedataDAO) beanFactory.getBean("votedataDAOImpl");
		
	}

	
	@Test
	public void test(){
		getDao();
		
		List<Votedata> votedataVos = votedataDAOImpl.listCurrentVotedataBySeidAndVqid(1, 1);
		for (int i = 0; i < votedataVos.size(); i++) {
			System.out.println(votedataVos.get(i).getStuAnswer());
		}
//		Student student = new Student("12", "tom", "10850866", "123");
//		studentDAOImpl.addStudent(student);
//		Student student = studentDAOImpl.findById("12");
//		System.out.println(student.getSname());
		//Teacher teacher = new Teacher(1, "1234", "1234");
		//Teacher teacher = teacherDAOImpl.findById(2);
		//teacherDAOImpl.addTeacher(teacher);
//		
//		Course course = courseDAOImpl.findById(1);
//		Classmodule classmodule = new Classmodule();
//		classmodule.setAbility(1);
//		classmodule.setCourse(course);
//		classModuleDAOImpl.addClassModule(classmodule);
	//	courseDAOImpl.addCourse(course);
		
//		Classmodule classmodule = classModuleDAOImpl.findById(1);
//		List<Evaluation> evaluations = new ArrayList<>();
//		Evaluation evaluation = evaluationDAOImpl.findById(2);
//		Classmoduleevaluation classmoduleevaluation = new Classmoduleevaluation();
//		classmoduleevaluation.setClassmodule(classmodule);
//		classmoduleevaluation.setEvaluation(evaluation);
//		classModuleEvaluationDAOImpl.addClassModuleEvaluation(classmoduleevaluation);
//		List<Course> courses = courseDAOImpl.listCourse(2);
//		for(Course c:courses){
//			System.out.println(c.getCname());
//			System.out.println(c.getCnumber());
//			System.out.println(c.getCterm());
//
//		}
		
//		Seminar seminar = new Seminar(course, "第二次", "程序", new Date(2012, 3, 4), 30, 10);
//		seminarDAOImpl.addSeminar(seminar);
		
//		List<Seminar> seminar = seminarDAOImpl.listByCourse(1);
//		for(Seminar c:seminar){
//			System.out.println(c.getSeName());
//			System.out.println(c.getSeTheme());
//			System.out.println(c.getSeUp());
//			System.out.println(c.getSeDown());
//
//
//		}
		
//		List<Seminarclass> seminar = seminarclassDAOImpl.listBySeminar(1);
//		for(Seminarclass c:seminar){
//			System.out.println(c.getStudent().getSid());
//			System.out.println(c.getSeminar().getSeId());
//		}
//		
		
//		Question question = new Question(course, 1, "个发货时是否");
//		questionDAOImpl.addQuestion(question);
//		
//		List<Question> questions = questionDAOImpl.listByChapter(1, 1);
//		for(Question c:questions){
//			System.out.println(c.getContent());
//			List<Answer> answers = answerDAOImpl.listByQid(c.getQid());
//			if(answers != null && answers.size() != 0){
//				for(Answer answer:answers)
//				System.out.println("答案："+answer.getAcontent());
//			}						
//		}
		
//		Question question = questionDAOImpl.findById(1);
//		
//		Answer answer = new Answer(question, "sdfs", 1);
//		answerDAOImpl.addAnswer(answer);
		
//		Evaluation evaluation = new Evaluation(course, "发布", "所有评价者的均值");
//		evaluationDAOImpl.addEvaluation(evaluation);
//		List<Evaluation> evaluations = evaluationDAOImpl.listByCourse(1);
//		for(Evaluation e:evaluations){
//			System.out.println(e.getEname());
//		}
		
//		Evaluation evaluation = evaluationDAOImpl.findById(1);
//		Scoreshow scoreshow = new Scoreshow(evaluation, "A",4.0);
//		scoreshowDAOImpl.addScoreShow(scoreshow);
//		List<Evaluation> evaluations =evaluationDAOImpl.listByCourse(1);
//		for(Evaluation c:evaluations){
//			System.out.println(c.getEname());
//			List<Scoreshow> answers = scoreshowDAOImpl.listByEvaluation(c.getEid());
//			if(answers != null && answers.size() != 0){
//				for(Scoreshow ss:answers)
//				System.out.println("页面显示："+ss.getEscoreShow()+" 对应分值"+ss.getEscore());
//			}
//			
//		}
	}
}
