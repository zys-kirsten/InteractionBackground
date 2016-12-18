package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.CourseDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.service.SeminarClassService;
import com.interaction.utils.DateUtil;
import com.interaction.vo.SeminarClassVo;

@Service
public class SeminarClassServiceImpl implements SeminarClassService{

	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	
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

	
	
	

}
