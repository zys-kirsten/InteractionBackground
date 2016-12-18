package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.CourseDAO;
import com.interaction.dao.EvaluationDAO;
import com.interaction.dao.ScoreshowDAO;
import com.interaction.pojo.Answer;
import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Question;
import com.interaction.pojo.Scoreshow;
import com.interaction.service.EvaluationService;
import com.interaction.vo.EvaluationVo;
import com.interaction.vo.QuestionVo;

@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Resource
	private CourseDAO courseDAOImpl;
	@Resource
	private EvaluationDAO evaluationDAOImpl;
	@Resource
	private ScoreshowDAO scoreshowDAOImpl;
	
	//添加评价单
	@Override
	public int addEvaluation(EvaluationVo evaluationVo) {
		Evaluation epo = v2ep(evaluationVo);
		int eresult = -1,sresult = -1;
		boolean flag = true;
		
		if(epo != null){
			eresult = evaluationDAOImpl.addEvaluation(epo);
			if(eresult != -1){
				List<Scoreshow> scoreshows = v2lsp(evaluationVo,eresult);
				for(Scoreshow scoreshow:scoreshows){
					sresult = scoreshowDAOImpl.addScoreShow(scoreshow);
					if(sresult == -1)
						flag = false;
				}
			}
			
		}
		
		if(eresult == -1){
			return -1;
		}else if(eresult != -1 && flag == false){
			return 0;
		}else{
			return 1;
		}	
	}

	@Override
	public List<EvaluationVo> listByCourse(Integer cid) {

		List<EvaluationVo> ltev = new ArrayList<EvaluationVo>();
		List<Evaluation> ltep = evaluationDAOImpl.listByCourse(cid);
		
		ltev = p2v(ltep);
		
		return ltev;
	}

	private List<EvaluationVo> p2v(List<Evaluation> ltep) {
		List<EvaluationVo> ltev = new ArrayList<EvaluationVo>();
		if(ltep == null || ltep.size() == 0)
			return null;
		
		for(Iterator<Evaluation> iterator = ltep.iterator();iterator.hasNext();){
			Evaluation evaluation = iterator.next();
			EvaluationVo evaluationVo = p2v(evaluation);
			
			ltev.add(evaluationVo);
		}
		return ltev;
	}

	private EvaluationVo p2v(Evaluation evaluation) {
		if(evaluation == null)
			return null;
		EvaluationVo ev = new EvaluationVo();
		
		Course course = courseDAOImpl.findById(evaluation.getCourse().getCid());
		List<Scoreshow> scoreshows = scoreshowDAOImpl.listByEvaluation(evaluation.getEid());
		
		ev.setCid(course.getCid());
		ev.setCname(course.getCname());
		ev.setEcalcul(evaluation.getEcalcul());
		ev.setEdescription(evaluation.getEdescription());
		ev.setEid(evaluation.getEid());
		ev.setEname(evaluation.getEname());
		ev.setScoreshows(scoreshows);
		
		return ev;
	}

	private Evaluation v2ep(EvaluationVo evaluationVo) {
		
		if(evaluationVo == null)
			return null;
		
		Evaluation epo = new Evaluation();
		
		Course course = courseDAOImpl.findById(evaluationVo.getCid());
		epo.setCourse(course);
		epo.setEcalcul(evaluationVo.getEcalcul());
		epo.setEdescription(evaluationVo.getEdescription());
		epo.setEid(evaluationVo.getEid());
		epo.setEname(evaluationVo.getEname());
		
		return epo;
	}
	private List<Scoreshow> v2lsp(EvaluationVo evaluationVo, int eresult) {
		if(evaluationVo == null)
			return null;
		
		List<Scoreshow> ltss = new ArrayList<Scoreshow>();
		Evaluation evaluation = evaluationDAOImpl.findById(eresult);
		
		for(Scoreshow scoreshow:evaluationVo.getScoreshows()){
			Scoreshow ss = new Scoreshow();
			ss.setEscore(scoreshow.getEscore());
			ss.setEscoreShow(scoreshow.getEscoreShow());
			ss.setEvaluation(evaluation);
			ss.setSsid(scoreshow.getSsid());
			
			ltss.add(ss);
		}
		return ltss;
	}

	//修改评价方式
	@Override
	public int updateQuestion(EvaluationVo evaluationVo) {

		Evaluation evaluation = v2ep(evaluationVo);
		int evaluationResult = -1,showScoreResult = -1;
		boolean flag = true;
		
		if(evaluation != null ){
			evaluationResult = evaluationDAOImpl.updateEvaluation(evaluation);
			if(evaluationResult != -1){
				List<Scoreshow> scoreshows = v2lsp(evaluationVo, evaluationResult);
				if(scoreshows != null && scoreshows.size() != 0){
					for(Scoreshow scoreshow:scoreshows){
						  if (scoreshow.getSsid() == null) {
							  showScoreResult = scoreshowDAOImpl.addScoreShow(scoreshow);
						}else{
							showScoreResult = scoreshowDAOImpl.updateScoreShow(scoreshow);
						}
						   if(showScoreResult == -1){
							   flag = false;
						   }
						}
				}
			}
		}
		
		
		if(evaluationResult == -1){
			return -1;
		}else if(evaluationResult != -1 && flag == false){
			return 0;
		}else {
			return 1;
		}
	}

	@Override
	public int deleteEvaluationById(Integer eid) {
		Evaluation evaluation = evaluationDAOImpl.findById(eid);
		List<Scoreshow> scoreshows = scoreshowDAOImpl.listByEvaluation(eid);
		int result = 0;
		if (scoreshows != null && scoreshows.size() !=0) {
			for (Scoreshow scoreshow:scoreshows) {
				result += scoreshowDAOImpl.deleteScoreshow(scoreshow);
			}
		}
		
		if(result == -4){
			System.out.println("if");
			return -1;
			
		}else {
			System.out.println("else");
			return evaluationDAOImpl.deleteEvaluation(evaluation);
		}
	}

	@Override
	public EvaluationVo findById(Integer eid) {
		return p2v(evaluationDAOImpl.findById(eid));
	}
	
	@Override
	public List<EvaluationVo> findByCondition(Integer cid, String condition, String inputValue) {
		List<EvaluationVo> evaluationVos = null;
		if(condition.equals("ename")){
			evaluationVos = p2v(evaluationDAOImpl.listByEname(cid,inputValue));
		}
		if(condition.equals("edescription")){
			evaluationVos = p2v(evaluationDAOImpl.listByEdescription(cid,inputValue));
		}
		if(condition.equals("ecalcul")){
			evaluationVos = p2v(evaluationDAOImpl.listByEcalcul(cid,inputValue));
		}
		return evaluationVos;
	}
	@Override
	public Evaluation findByEname(Integer cid, String ename) {
		return evaluationDAOImpl.findByEname(cid, ename).get(0);
	}

}
