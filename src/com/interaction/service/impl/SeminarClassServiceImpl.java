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
import com.interaction.dao.CourseDAO;
import com.interaction.dao.QuestionDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.service.SeminarClassService;
import com.interaction.utils.DateUtil;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarStudentNo;

@Service
public class SeminarClassServiceImpl implements SeminarClassService{

	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private ClassModuleDAO classModuleDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private QuestionDAO questionDAOImpl;
	@Resource
	private AnswerDAO answerDAOImpl;

	
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
		boolean flag = true;
		List<Seminarclass> ltpo = seminarclassDAOImpl.listLoginStudents(seId);
		
		//读取配置模式，获得分组的个数
	//	Classmodule classmodule = classModuleDAOImpl.listBySeId(seId);
		Integer grpNum = 3;
				
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

		Seminarclass seminarclass = new Seminarclass();
		if (courseDAOImpl.findById(cid) == null || seminarDAOImpl.findById(seid) == null || studentDAOImpl.findById(sid) == null) {
			return -1;
		}
		seminarclass.setCourse(courseDAOImpl.findById(cid));
		seminarclass.setSeminar(seminarDAOImpl.findById(seid));
		seminarclass.setStudent(studentDAOImpl.findById(sid));
		
		return seminarclassDAOImpl.addSeminarclass(seminarclass);
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
		Seminarclass seminarclass = seminarclassDAOImpl.findMyGroupNum(seid,sid);
		if (seminarclass == null ) {
			return -1;
		}
		return seminarclass.getGroupNum();
	}
	
	//查找除了自己组以外的其他组的组号
	@Override
	public List<GroupNumsVo> listOtherGroupNums(int seid, int groupNum) {
		List<Seminarclass> seminarclasses = seminarclassDAOImpl.listOtherGroupNums(seid, groupNum);
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
	public List<SeminarClassVo> listMyGroupOtherStu(int seid, int sid, int groupNum) {
		List<Seminarclass> seminarclasses = seminarclassDAOImpl.listByGroupNum(seid, groupNum);
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
	
}
