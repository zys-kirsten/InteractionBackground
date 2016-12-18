package com.interaction.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.CourseDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.TeacherDAO;
import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Teacher;
import com.interaction.service.SeminarService;
import com.interaction.utils.DateUtil;
import com.interaction.vo.CourseVo;
import com.interaction.vo.SeminarVo;

@Service
public class SeminarServiceImpl implements SeminarService{

	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private TeacherDAO teacherDAOImpl;
	
	//添加研讨课
	@Override
	public int addSeminar(SeminarVo seminarVo) {
		if(seminarVo == null)
			return -1;
		
		Seminar seminar = v2p(seminarVo);	
		return seminarDAOImpl.addSeminar(seminar);
	}

    //修改研讨课
	@Override
	public int updateSeminar(SeminarVo seminarVo) {
		if(seminarVo == null)
			return -1;
		
		Seminar seminar = v2p(seminarVo);	
		return seminarDAOImpl.updateSeminar(seminar);
	}
	
	//根据研讨课Id查找研讨课
	@Override
	public Seminar findById(Integer seid) {
		return seminarDAOImpl.findById(seid);
	}

	//列出课程id为cid的所有研讨课
	@Override
	public List<SeminarVo> listByCourse(Integer cid) {
		List<SeminarVo> lsvos = new ArrayList<SeminarVo>();
		List<Seminar> lspos= seminarDAOImpl.listByCourse(cid);
		
		lsvos = p2v(lspos);
		return lsvos;
	}
	
	//列出任课教师id为tid的所有研讨课
	@Override
	public List<SeminarVo> listByTeacher(Integer tid) {
		List<SeminarVo> sv = new ArrayList<SeminarVo>();
		List<Course> courses = courseDAOImpl.listCourse(tid);
		
		if(courses == null || courses.size() == 0)
			return null;
		
		for(Iterator<Course> iterator = courses.iterator();iterator.hasNext();){
			Course course = iterator.next();
			List<Seminar> seminar = seminarDAOImpl.listByCourse(course.getCid());
			
			if(seminar != null && seminar.size() != 0){
				List<SeminarVo> seminarVos = p2v(seminar);
				sv.addAll(seminarVos);
			}
		}
		return sv;
	}

	//列出任课教师id为tid的所有研讨课
		@Override
		public List<List<SeminarVo>> listAllByTeacher(Integer tid) {
			List<List<SeminarVo>> ltsv = new ArrayList<>();
			List<SeminarVo> sv = new ArrayList<>();
			List<Course> courses = courseDAOImpl.listCourse(tid);
			
			if(courses == null || courses.size() == 0)
				return null;
			
			for(Iterator<Course> iterator = courses.iterator();iterator.hasNext();){
				Course course = iterator.next();
				List<Seminar> seminar = seminarDAOImpl.listByCourse(course.getCid());
				
				if(seminar != null && seminar.size() != 0){
					List<SeminarVo> seminarVos = p2v(seminar);
					sv.addAll(seminarVos);
				}
				ltsv.add(sv);
			}
			return ltsv;
		}
	
	
	//列出某一位教师所有有研讨课的课程
	@Override
	public List<Course> listHaveSeminarCourseByTeacher(Integer tid) {
		List<Course> courses1 = courseDAOImpl.listCourse(tid);
		List<Course> courses = new ArrayList<>();
		
		for (Course course:courses1) {
			List<Seminar> seminars = seminarDAOImpl.listByCourse(course.getCid());
			if( seminars != null && seminars.size() != 0){
				courses.add(course);
			}
		}
		return courses;
	}
	
	
	private List<SeminarVo> p2v(List<Seminar> seminars){
		List<SeminarVo> listSeminarVos = new ArrayList<SeminarVo>();
		
		if(seminars==null || seminars.size()==0){
			return null;
		}
		for(Iterator<Seminar> iterator = seminars.iterator();iterator.hasNext();){
			Seminar seminar = iterator.next();
			SeminarVo seminarVo = p2v(seminar); 
			listSeminarVos.add(seminarVo);
		}
		return listSeminarVos;
	}

	private SeminarVo p2v(Seminar seminar) {
		if(seminar == null)
			return null;
		
		SeminarVo  sv = new SeminarVo();
		
		Course course = courseDAOImpl.findById(seminar.getCourse().getCid());
		Teacher teacher = teacherDAOImpl.findById(course.getTeacher().getTid());
		
		sv.setCid(course.getCid());
		sv.setCname(course.getCname());
		sv.setSeDown(seminar.getSeDown());
		sv.setSeId(seminar.getSeId());
		sv.setSeName(seminar.getSeName());
		sv.setSeTheme(seminar.getSeTheme());
		sv.setSeTime(DateUtil.d2s(seminar.getSeTime()));
		sv.setSeUp(seminar.getSeUp());
		sv.setTid(teacher.getTid());
		sv.setTname(teacher.getTname());
		
		return sv;
	}
	
	private Seminar v2p(SeminarVo seminarVo){
		Seminar seminar = new Seminar();
		
		if(seminarVo == null)
			return null;
		Course course = courseDAOImpl.findById(seminarVo.getCid());
		seminar.setCourse(course);
		seminar.setSeDown(seminarVo.getSeDown());
		seminar.setSeId(seminarVo.getSeId());
		seminar.setSeName(seminarVo.getSeName());
		seminar.setSeTheme(seminarVo.getSeTheme());
		seminar.setSeUp(seminarVo.getSeUp());
		try {
			seminar.setSeTime(DateUtil.s2d(seminarVo.getSeTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return seminar;
	}
	@Override
	public int deleteSeminarById(Integer seId) {

		Seminar seminar = seminarDAOImpl.findById(seId);
		return seminarDAOImpl.deleteSeminar(seminar);
	}

	@Override
	public Seminar findBySeName(Integer cid, String seName) {
		return seminarDAOImpl.findBySeName(cid, seName).get(0);
	}
}
