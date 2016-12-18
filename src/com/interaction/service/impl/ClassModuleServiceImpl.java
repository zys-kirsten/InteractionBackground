package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.cache.infinispan.util.FlagAdapter;
import org.springframework.stereotype.Service;

import com.interaction.dao.ClassModuleDAO;
import com.interaction.dao.ClassModuleEvaluationDAO;
import com.interaction.dao.ClassModuleSeminarDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.pojo.Classmodule;
import com.interaction.pojo.Classmoduleevaluation;
import com.interaction.pojo.Classmoduleseminar;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Seminar;
import com.interaction.service.ClassModuleService;
import com.interaction.vo.ClassModuleVo;

@Service
public class ClassModuleServiceImpl implements ClassModuleService{

	@Resource
	private ClassModuleDAO classModuleDAOImpl;
	@Resource
	private ClassModuleEvaluationDAO classModuleEvaluationDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private ClassModuleSeminarDAO classModuleSeminarDAOImpl;
	@Resource
	private EvaluationDAO evaluationDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;

	//添加课堂模式
	@Override
	public int addClassModule(ClassModuleVo classModuleVo) {
		Classmodule classmodule = v2cmp(classModuleVo);
		int classmoduleResult = -1,evaluationResult = -1;
		boolean flag = true;
		
		if(classmodule != null ){
			classmoduleResult = classModuleDAOImpl.addClassModule(classmodule);
			if(classmoduleResult != -1){
				List<Evaluation> evaluations = classModuleVo.getEvaluations();
				for(Evaluation e:evaluations){
					Classmoduleevaluation cme = new Classmoduleevaluation();
					cme.setClassmodule(classmodule);
					cme.setEvaluation(e);
					evaluationResult = classModuleEvaluationDAOImpl.addClassModuleEvaluation(cme);
					if (evaluationResult == -1) {
						flag = false;
					}
				}
				
				List<Seminar> seminars = classModuleVo.getSeminars();
				for(Seminar s:seminars){
					Classmoduleseminar cmse = new Classmoduleseminar();
					cmse.setClassmodule(classmodule);
					cmse.setSeminar(s);
					evaluationResult = classModuleSeminarDAOImpl.addClassModuleSeminar(cmse);;
					if (evaluationResult == -1) {
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
		classmodule.setAbility(classModuleVo.getAbility());
		classmodule.setActivity(classModuleVo.getActivity());
		classmodule.setCensus(classModuleVo.getCensus());
		classmodule.setCourse(course);
		classmodule.setGroupNum(classModuleVo.getGroupNum());
		classmodule.setGrpMaxNum(classModuleVo.getGrpMaxNum());
		classmodule.setGrpMinNum(classModuleVo.getGrpMinNum());
		classmodule.setModuleName(classModuleVo.getModuleName());
		classmodule.setProNum(classModuleVo.getProNum());
		classmodule.setProPerson(classModuleVo.getProPerson());
		classmodule.setProScore(classModuleVo.getProScore());
		classmodule.setProTime(classModuleVo.getProTime());
		classmodule.setQuality(classModuleVo.getQuality());
		classmodule.setResNum(classModuleVo.getResNum());
		classmodule.setResScore(classModuleVo.getResScore());
		classmodule.setScore(classModuleVo.getScore());
		classmodule.setSex(classModuleVo.getSex());
		classmodule.setTeamLeader(classModuleVo.getTeamLeader());
		return classmodule;
	}

	//修改课堂模式
	@Override
	public int updateClassModule(ClassModuleVo classModuleVo) {
		Classmodule classmodule = v2cmp(classModuleVo);
		int cmResult = -1,cmeResult = -1,cmsResult= -1;
		boolean flag = true;
		cmResult = classModuleDAOImpl.updateClassModule(classmodule);
		
		List<Evaluation> evaluations = classModuleVo.getEvaluations();
		if(evaluations != null && evaluations.size() != 0){
			List<Classmoduleevaluation> classmoduleevaluations = classModuleEvaluationDAOImpl.listByCmid(classModuleVo.getCmid());
			cmeResult = classModuleEvaluationDAOImpl.deleteCme(classmoduleevaluations);
			if(cmeResult != -1){
				for(Iterator<Evaluation> iterator = evaluations.iterator();iterator.hasNext();){
					Classmoduleevaluation classmoduleevaluation = new Classmoduleevaluation();
					classmoduleevaluation.setClassmodule(classmodule);
					classmoduleevaluation.setEvaluation(iterator.next());
					cmeResult = classModuleEvaluationDAOImpl.addClassModuleEvaluation(classmoduleevaluation);
					if (cmeResult == -1) {
						flag = false;
					}
				}
			}
		}
		
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
		cmvo.setAbility(cm.getAbility());
		cmvo.setActivity(cm.getActivity());
		cmvo.setCensus(cm.getCensus());
		cmvo.setCmid(cm.getCmid());
		cmvo.setGroupNum(cm.getGroupNum());
		cmvo.setGrpMaxNum(cm.getGrpMaxNum());
		cmvo.setGrpMinNum(cm.getGrpMinNum());
		cmvo.setModuleName(cm.getModuleName());
		cmvo.setProNum(cm.getProNum());
		cmvo.setProPerson(cm.getProPerson());
		cmvo.setProScore(cm.getProScore());
		cmvo.setProTime(cm.getProTime());
		cmvo.setQuality(cm.getQuality());
		cmvo.setTeamLeader(cm.getTeamLeader());
		cmvo.setSex(cm.getSex());
		cmvo.setScore(cm.getScore());
		cmvo.setResScore(cm.getResScore());
		cmvo.setResNum(cm.getResNum());
		
		Course course = courseDAOImpl.findById(cm.getCourse().getCid());
		cmvo.setCid(course.getCid());
		cmvo.setCname(course.getCname());
		
		List<Classmoduleevaluation> classmoduleevaluations = classModuleEvaluationDAOImpl.listByCmid(cm.getCmid());
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		for(Iterator<Classmoduleevaluation> iterator = classmoduleevaluations.iterator();iterator.hasNext();){
			Classmoduleevaluation cme = iterator.next();
			Evaluation evaluation = evaluationDAOImpl.findById(cme.getEvaluation().getEid());
			evaluations.add(evaluation);
		}
		cmvo.setEvaluations(evaluations);
		
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
		List<Classmoduleevaluation> classmoduleevaluations = classModuleEvaluationDAOImpl.listByCmid(cmid);
		int cm=-1,cms=-1,cme=-1;
		
		cms = classModuleSeminarDAOImpl.deleteCms(classmoduleseminars);
		cme = classModuleEvaluationDAOImpl.deleteCme(classmoduleevaluations);
		if (cms != -1 && cme != -1) {
			cm = classModuleDAOImpl.delete(classmodule);
		}
		if (cm== -1) {
			return -1;
		}
		return 1;
	}
}
