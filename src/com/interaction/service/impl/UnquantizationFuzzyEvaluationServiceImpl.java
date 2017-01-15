package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.EvaluationElementDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.dao.UnquantizationFuzzyEvaluationDAO;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Student;
import com.interaction.pojo.Unquantizationfuzzyevaluation;
import com.interaction.service.UnquantizationFuzzyEvaluationService;
import com.interaction.vo.AndroidEvaluationVo;

@Service
public class UnquantizationFuzzyEvaluationServiceImpl implements UnquantizationFuzzyEvaluationService{

	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private EvaluationElementDAO evaluationElementDAOImpl;
    @Resource
    private UnquantizationFuzzyEvaluationDAO unquantizationFuzzyEvaluationDAOImpl;
	
    
    //评价结果提交
    @Override
	public int submitEvaluations(List<AndroidEvaluationVo> list) {
    	
		int flag = -1;
		if (list != null && list.size() != 0) {
			for(AndroidEvaluationVo aev : list){
				Unquantizationfuzzyevaluation ufe = v2p(aev);
				if (ufe != null) {
					flag = unquantizationFuzzyEvaluationDAOImpl.addUnquantizationFuzzyEvaluation(ufe);
				}
			}
		}
		return flag;
	}


	private Unquantizationfuzzyevaluation v2p(AndroidEvaluationVo aev) {
		
		if (aev == null) {
			return null;
		}
		Unquantizationfuzzyevaluation ufe = new Unquantizationfuzzyevaluation();
		ufe.setEvalRank(aev.getEvalRank());
		ufe.setSeminar(seminarDAOImpl.findById(aev.getSeid()));
		ufe.setEvaluationelement(evaluationElementDAOImpl.findById(aev.getEeid()));
		ufe.setStudent(studentDAOImpl.findById(aev.getObject()));
				
		return ufe;
	}
	
	
}
