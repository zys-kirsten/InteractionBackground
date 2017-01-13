package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.ClassModuleDAO;
import com.interaction.dao.ClassModuleSeminarDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.pojo.Classmodule;
import com.interaction.pojo.Classmoduleseminar;
import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.service.ClassModuleService;
import com.interaction.vo.ClassModuleVo;

@Service
public class ClassModuleServiceImpl implements ClassModuleService{

	@Resource
	private ClassModuleDAO classModuleDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private ClassModuleSeminarDAO classModuleSeminarDAOImpl;
	
	@Resource
	private SeminarDAO seminarDAOImpl;

	//添加课堂模式
	@Override
	public int addClassModule(ClassModuleVo classModuleVo) {
		Classmodule classmodule = v2cmp(classModuleVo);
		int classmoduleResult = -1,classmoduleSeminarResult = -1;
		boolean flag = true;
		
		if(classmodule != null ){
			classmoduleResult = classModuleDAOImpl.addClassModule(classmodule);
			if(classmoduleResult != -1){
				
				List<Seminar> seminars = classModuleVo.getSeminars();
				for(Seminar s:seminars){
					Classmoduleseminar cmse = new Classmoduleseminar();
					cmse.setClassmodule(classmodule);
					cmse.setSeminar(s);
					classmoduleSeminarResult = classModuleSeminarDAOImpl.addClassModuleSeminar(cmse);;
					if (classmoduleSeminarResult == -1) {
						flag = false;
					}
				}
			}
		}
		
		
		if(classmoduleResult == -1){
			return -1;
		}else if(classmoduleResult != -1 && flag == false){
			return 0;
		}else {
			return 1;
		}
	}

	private Classmodule v2cmp(ClassModuleVo classModuleVo) {
		if(classModuleVo == null)
			return null;
		
		Classmodule classmodule = new Classmodule();
		Course course = courseDAOImpl.findById(classModuleVo.getCid());
		classmodule.setCmid(classModuleVo.getCmid());
		classmodule.setCourse(course);
		classmodule.setGroupNum(classModuleVo.getGroupNum());
		classmodule.setGroupTime(classModuleVo.getGroupTime());
		classmodule.setModuleName(classModuleVo.getModuleName());
		classmodule.setProNum(classModuleVo.getProNum());
		classmodule.setProTime(classModuleVo.getProTime());
		return classmodule;
	}

	//修改课堂模式
	@Override
	public int updateClassModule(ClassModuleVo classModuleVo) {
		Classmodule classmodule = v2cmp(classModuleVo);
		int cmResult = -1,cmsResult= -1;
		boolean flag = true;
		cmResult = classModuleDAOImpl.updateClassModule(classmodule);
		
		List<Seminar> seminars = classModuleVo.getSeminars();
		if(seminars != null && seminars.size() != 0){
			List<Classmoduleseminar> classmoduleseminars = classModuleSeminarDAOImpl.listByCmid(classModuleVo.getCmid());
			cmsResult = classModuleSeminarDAOImpl.deleteCms(classmoduleseminars);
			if(cmsResult != -1){
				for(Iterator<Seminar> iterator = seminars.iterator();iterator.hasNext();){
					Classmoduleseminar classmoduleseminar = new Classmoduleseminar();
					classmoduleseminar.setClassmodule(classmodule);
					classmoduleseminar.setSeminar(iterator.next());
					cmsResult = classModuleSeminarDAOImpl.addClassModuleSeminar(classmoduleseminar);
					if (cmsResult == -1) {
						flag = false;
					}
				}
			}
		}
		if(cmResult == -1){
			return -1;
		}else if(cmResult != -1 && flag == false){
			return 0;
		}else {
			return 1;
		}
	}

	@Override
	public List<ClassModuleVo> listByCourse(Integer cid) {
		List<ClassModuleVo> ltcmvo = new ArrayList<ClassModuleVo>();
		List<Classmodule> ltcmpo = classModuleDAOImpl.listByCourse(cid);
		if (ltcmpo == null ||ltcmpo.size()==0) {
			return null;
		}
		ltcmvo = p2v(ltcmpo);
		return ltcmvo;
	}

	private List<ClassModuleVo> p2v(List<Classmodule> ltcmpo) {
		List<ClassModuleVo> ltcmvo = new ArrayList<ClassModuleVo>();
		for(Iterator<Classmodule> iterator = ltcmpo.iterator();iterator.hasNext();){
			Classmodule cm = iterator.next();
			ClassModuleVo cmvo = p2v(cm);
			ltcmvo.add(cmvo);
		}
		return ltcmvo;
	}

	private ClassModuleVo p2v(Classmodule cm) {
		ClassModuleVo cmvo = new ClassModuleVo();
		cmvo.setCmid(cm.getCmid());
		cmvo.setGroupNum(cm.getGroupNum());
		cmvo.setGroupTime(cm.getGroupTime());
		cmvo.setModuleName(cm.getModuleName());
		cmvo.setProNum(cm.getProNum());
		cmvo.setProTime(cm.getProTime());
		
		Course course = courseDAOImpl.findById(cm.getCourse().getCid());
		cmvo.setCid(course.getCid());
		cmvo.setCname(course.getCname());
		
		List<Classmoduleseminar> classmoduleseminars = classModuleSeminarDAOImpl.listByCmid(cm.getCmid());
		List<Seminar> seminars = new ArrayList<Seminar>();
		for(Iterator<Classmoduleseminar> iterator = classmoduleseminars.iterator();iterator.hasNext();){
			Classmoduleseminar cms = iterator.next();
			Seminar seminar = seminarDAOImpl.findById(cms.getSeminar().getSeId());
			seminars.add(seminar);
		}
		cmvo.setSeminars(seminars);
		
		return cmvo;
	}

	@Override
	public ClassModuleVo findById(Integer cmid) {
		return p2v(classModuleDAOImpl.findById(cmid));
	}

	@Override
	public int deleteClassModuleById(Integer cmid) {
		Classmodule classmodule = classModuleDAOImpl.findById(cmid);
		List<Classmoduleseminar> classmoduleseminars = classModuleSeminarDAOImpl.listByCmid(cmid);
		int cm=-1,cms=-1;
		
		cms = classModuleSeminarDAOImpl.deleteCms(classmoduleseminars);
		if (cms != -1) {
			cm = classModuleDAOImpl.delete(classmodule);
		}
		if (cm== -1) {
			return -1;
		}
		return 1;
	}
}
