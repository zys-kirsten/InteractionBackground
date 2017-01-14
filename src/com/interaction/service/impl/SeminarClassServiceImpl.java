package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.algorithm.group.GroupDivide;
import com.interaction.dao.AnswerDAO;
import com.interaction.dao.ClassModuleDAO;
import com.interaction.dao.ClassModuleSeminarDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationElementDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.SeminarscoreDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Classmodule;
import com.interaction.pojo.Classmoduleseminar;
import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Seminarscore;
import com.interaction.pojo.Student;
import com.interaction.service.SeminarClassService;
import com.interaction.utils.DateUtil;
import com.interaction.vo.EvaluationElementScore;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarStudentNo;
import com.interaction.vo.SeminarscoreVo;

@Service
public class SeminarClassServiceImpl implements SeminarClassService{

	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
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
	private SeminarscoreDAO seminarscoreDAOImpl;
	@Resource
	private EvaluationElementDAO evaluationElementDAOImpl;
	@Resource
	private ClassModuleSeminarDAO classModuleSeminarDAOImpl;
	@Resource
	private ClassModuleDAO classModuleDAOImpl;

	
//====================================PC======================================================
	//列出某一研讨课的研讨班选课信息
	@Override
	public List<SeminarClassVo> listOneSeminarClass(Integer seid) {
		List<SeminarClassVo> ltscvo = new ArrayList<SeminarClassVo>();
		List<Seminarclass> ltscpo = seminarclassDAOImpl.listBySeminar(seid);

		ltscvo = p2v(ltscpo);
		
		return ltscvo;
	}
	
	//列出某一门课的所有研讨班选课信息
	@Override
	public List<List<SeminarClassVo>> listAllSeminarClass(Integer cid) {
		List<List<SeminarClassVo>> lltscvo = new ArrayList<List<SeminarClassVo>>();
		List<Seminar> seminars = seminarDAOImpl.listByCourse(cid);
		
		for(Seminar seminar:seminars){
			List<Seminarclass> ltscpo= seminarclassDAOImpl.listBySeminar(seminar.getSeId());
			List<SeminarClassVo> ltsevo = p2v(ltscpo);
			if(ltsevo != null && ltsevo.size() != 0)
			   lltscvo.add(ltsevo);
		}
		
 		return lltscvo;
	}
	
	private List<SeminarClassVo> p2v(List<Seminarclass> ltscpo){
		List<SeminarClassVo> ltscvo = new ArrayList<SeminarClassVo>();
		if(ltscpo == null || ltscpo.size() == 0){
			return null;
		}
		
		for(Iterator<Seminarclass> iterator = ltscpo.iterator();iterator.hasNext();){
			Seminarclass seminarclass = iterator.next();
			SeminarClassVo seminarClassVo = p2v(seminarclass);
			
			ltscvo.add(seminarClassVo);
		}
		return ltscvo;
		
	}
	
	private SeminarClassVo p2v(Seminarclass scpo){
		SeminarClassVo scvo = new SeminarClassVo();
		
		if(scpo == null)
			return null;
		Seminar seminar = seminarDAOImpl.findById(scpo.getSeminar().getSeId());

		if(scpo.getStudent().getSid() != null){
			Student student = studentDAOImpl.findById(scpo.getStudent().getSid());
			
			scvo.setSid(student.getSid());
			scvo.setSname(student.getSname());
		}
		
		scvo.setSeId(seminar.getSeId());
		scvo.setSeName(seminar.getSeName());
		scvo.setSeTheme(seminar.getSeTheme());
		scvo.setSeTime(DateUtil.d2s(seminar.getSeTime()));
		
		return scvo;
	}

	
//====================================教师Android========================================================
	//列车该门研讨课已经登录的学生
	@Override
	public List<SeminarClassVo> listLoginStudents(Integer seId) {
		List<Seminarclass> ltpo = seminarclassDAOImpl.listLoginStudents(seId); 
		return p2v(ltpo);
	}

	//对已登录的学生进行分组
	@Override
	public List<GroupVo> divideGroup(int seId) {
		
		//读取配置模式，获得分组的个数
		Classmoduleseminar classmoduleseminar = classModuleSeminarDAOImpl.listBySeId(seId);
		if (classmoduleseminar == null) {
			return null;
		}
		Classmodule classmodule = classModuleDAOImpl.findById(classmoduleseminar.getClassmodule().getCmid());
		if (classmodule == null) {
			return null;
		}
		if (classmodule.getGroupTime() == 1) {   //课堂模式显示的是课上分组
			boolean flag = true;
			List<Seminarclass> ltpo = seminarclassDAOImpl.listLoginStudents(seId);
			Integer grpNum = classmodule.getGroupNum();
			
			List<List<Seminarclass>> lists = GroupDivide.divideGroup(ltpo,grpNum);
			if (lists == null || lists.size() == 0) {
				return null;
			}
			
			List<GroupVo> groupVos = new ArrayList<>();
			Integer grNum = 1;
			for (List<Seminarclass> list:lists) {
				GroupVo groupVo = new GroupVo();
				String grNames = "";
				Integer inGroupNum = 1;
				for(Seminarclass seminarclass:list){
					seminarclass.setGroupNum(grNum);
					seminarclass.setInGroupNum(inGroupNum++);
					seminarclass.setConfirmGroup(0);
					int result = seminarclassDAOImpl.updateSeminarclass(seminarclass);  //更新研讨课学生
					if (result == -1) {
						flag = false;
					}
					Student student = studentDAOImpl.findById(seminarclass.getStudent().getSid()); //为了页面返回显示
					grNames += student.getSname()+" ";
				}
				groupVo.setGrName((grNum++).toString());
				groupVo.setStNames(grNames);
				
				groupVos.add(groupVo);
			}
			
			if (flag == false) {
				return null;
			}
			return groupVos;
		}else {  //课前教师已经分好组
			return listGroup(seId);
		}
		
	}

	//教师确认分组结果
	@Override
	public void confirmGroup(int seId) {
		List<Seminarclass> ltpo = seminarclassDAOImpl.listLoginStudents(seId);
		for (Seminarclass seminarclass:ltpo) {
			seminarclass.setConfirmGroup(1);
			seminarclassDAOImpl.updateSeminarclass(seminarclass);  //将确认分组结果置为可查看
		}
	}

	//查询当前选择研讨课的情况
	@Override
	public List<SeminarStudentNo> listCurrentSelectSeminarStuNumber(int cid) {
		List<SeminarStudentNo> studentNos = new ArrayList<SeminarStudentNo>();
		List<Seminar> seminars = seminarDAOImpl.listByCourse(cid);
		if (seminars != null && seminars.size() != 0) {
			for(Seminar seminar:seminars){
				SeminarStudentNo seminarStudentNo = new SeminarStudentNo();
				seminarStudentNo.setSeName(seminar.getSeName());
				List<Seminarclass> seminarclasses = seminarclassDAOImpl.listBySeminar(seminar.getSeId());
				if (seminarclasses != null && seminarclasses.size() != 0) {
					seminarStudentNo.setStudentNo(seminarclasses.size());
				}
				studentNos.add(seminarStudentNo);
			}
		}
		return studentNos;
	}
//=========================================学生Android端==================================================	
	//学生选课
	@Override
	public int stuSelectSeminar(int cid, int seid, int sid) {

		if (courseDAOImpl.findById(cid) == null || seminarDAOImpl.findById(seid) == null || studentDAOImpl.findById(sid) == null) {
			return -1;
		}
		
		Seminar seminar = seminarDAOImpl.findById(seid);
		if (seminar == null) {
			return -1;
		}
		Seminarclass seminarclass2 = seminarclassDAOImpl.listBySeidAndSid(seid, sid);
		if (seminarclass2 != null) {
			return -1;
		}
		
		int upNum = seminar.getSeUp();
		Object lock = new Object();
		synchronized (lock) {
			List<Seminarclass> seminarclasses = seminarclassDAOImpl.listBySeminar(seid);
			
			if (seminarclasses.size() < upNum ) {
				Seminarclass seminarclass = new Seminarclass();
				seminarclass.setCourse(courseDAOImpl.findById(cid));
				seminarclass.setSeminar(seminarDAOImpl.findById(seid));
				seminarclass.setStudent(studentDAOImpl.findById(sid));
				
				return seminarclassDAOImpl.addSeminarclass(seminarclass);
			}else {
				return -1;
			}
		}
	}

	//学生查看课堂分组
	@Override
	public List<GroupVo> listGroup(int seid) {
		List<Seminarclass> seminarclasses = seminarclassDAOImpl.listGroup(seid);
		if (seminarclasses == null || seminarclasses.size() == 0) {
			return null;
		}
		
		List<GroupVo> groupVos = new ArrayList<>();
		GroupVo groupVo = new GroupVo();
		Integer grNum = 1;
		String  grNames = "";
		for(Seminarclass sc:seminarclasses){
			Student student = studentDAOImpl.findById(sc.getStudent().getSid());
			if (student != null) {
				if (sc.getGroupNum() == grNum) {
					grNames += student.getSname()+" ";
				}else{
					groupVo.setGrName(grNum.toString());
					groupVo.setStNames(grNames);
					groupVos.add(groupVo);
					
					groupVo = new GroupVo();
					grNum++;
					grNames = student.getSname()+" ";
				}
			}
		}
		groupVo.setGrName(grNum.toString());
		groupVo.setStNames(grNames);
		groupVos.add(groupVo);
		return groupVos;
	}

	//找到某个学生自己的分组
	@Override
	public Integer findMyGroupNum(Integer seid,int sid) {
		Seminarclass seminarclass = seminarclassDAOImpl.listBySeidAndSid(seid,sid);
		if (seminarclass == null || seminarclass.getGroupNum() == null) {
			return -1;
		}
		return seminarclass.getGroupNum();
	}
	
	//查找除了自己组以外的其他组的组号
	@Override
	public List<GroupNumsVo> listOtherGroupNums(int seid, int sid) {
		Seminarclass seminarclass = seminarclassDAOImpl.listBySeidAndSid(seid, sid);
		if (seminarclass == null || seminarclass.getGroupNum() == null) {
			return null;
		}
		List<Seminarclass> seminarclasses = seminarclassDAOImpl.listOtherGroupNums(seid, seminarclass.getGroupNum());
		if (seminarclasses == null || seminarclasses.size() == 0) {
			return null;
		}
		
		Set<Integer> temp = new HashSet<Integer>();
		for(Seminarclass sc:seminarclasses){
			temp.add(sc.getGroupNum());
		}
		
		List<GroupNumsVo> groupNumsVos = new ArrayList<GroupNumsVo>();
		for(Integer i:temp){
			GroupNumsVo groupNumsVo = new GroupNumsVo();
			groupNumsVo.setGrNumber(i);
			groupNumsVos.add(groupNumsVo);
		}
		return groupNumsVos;
	}
	
	//根据组号查找属于该组的同学
	@Override
	public List<Seminarclass> listByGroupNum(int seid, int groupNum) {
		return seminarclassDAOImpl.listByGroupNum(seid, groupNum);
	}
	
	//列出学生id为sid的那一组的其他同学
	@Override
	public List<SeminarClassVo> listMyGroupOtherStu(int seid, int sid) {
		Seminarclass seminarclass = seminarclassDAOImpl.listBySeidAndSid(seid, sid);
		if (seminarclass == null || seminarclass.getGroupNum() == null) {
			return null;
		}
		List<Seminarclass> seminarclasses = seminarclassDAOImpl.listByGroupNum(seid, seminarclass.getGroupNum());
		if (seminarclasses == null || seminarclasses.size() == 0) {
			return null;
		}
		
		int originalSize = seminarclasses.size();
		for(int i = 0; i < seminarclasses.size();i++){
			if (seminarclasses.get(i).getStudent().getSid() == sid) {
				seminarclasses.remove(seminarclasses.get(i));
			}
		}
		int newSize = seminarclasses.size();
		if (originalSize == newSize) {
			return null;
		}
		return p2v(seminarclasses);
	}
	
	//学生查询自己的研讨课成绩
	@Override
	public SeminarscoreVo stuFindMySeminarScore(int seid, int sid) {

		Seminarclass seminarclass = seminarclassDAOImpl.listBySeidAndSid(seid, sid);
		if (seminarclass == null || seminarclass.getSeScore() == null) {
			return null;
		}
		SeminarscoreVo ssvo = new SeminarscoreVo();
		ssvo.setTotalScore(seminarclass.getSeScore());
		
		List<Seminarscore> seminarscores = seminarscoreDAOImpl.listBySeidAndSid(seid,sid);
		List<EvaluationElementScore> ltees = new ArrayList<EvaluationElementScore>();
		if (seminarscores != null && seminarscores.size() != 0) {
			for(Seminarscore sc:seminarscores){
				Evaluationelement  ee = evaluationElementDAOImpl.findById(sc.getEvaluationelement().getEeid());
				EvaluationElementScore ees = new EvaluationElementScore();
				if (ee != null) {
					ees.setEename(ee.getEename());
					ees.setEescore(sc.getSscore());
				}
				
				ltees.add(ees);
			}
		}
		
		ssvo.setElementScores(ltees);
		return ssvo;
	}
}
