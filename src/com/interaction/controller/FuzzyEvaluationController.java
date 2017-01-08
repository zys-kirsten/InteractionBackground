package com.interaction.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.service.FuzzyEvaluationService;

@Controller
public class FuzzyEvaluationController {

	@Resource
	private FuzzyEvaluationService fuzzyEvaluationServiceImpl;
	
	//测试
	@RequestMapping("/testEvaluation")
	public void test(){
		double result = fuzzyEvaluationServiceImpl.fuzzyEvaluation(1,1,1);
		System.out.println("result = "+result);
	}
	
	
}
