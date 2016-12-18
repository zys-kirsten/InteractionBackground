package com.interaction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.service.SeminarClassService;
import com.interaction.vo.SeminarClassVo;


@Controller
public class SeminarClassController {
	
	@Resource
	private SeminarClassService seminarClassServiceImpl;
	
	//列出选择某一门课中某一研讨主题的所有同学
	@RequestMapping("/listClassBySeminar")
	public ModelAndView listClassBySeminar(@RequestParam(value = "seid") Integer seid){
		ModelAndView modelAndView = new ModelAndView();
		
		List<SeminarClassVo> seminarClassVos = seminarClassServiceImpl.listOneSeminarClass(seid);
		
		if(seminarClassVos == null || seminarClassVos.size() == 0){
			modelAndView.setViewName("error");
		}else{
			modelAndView.setViewName("seminarClassDisplay");
			modelAndView.addObject("seminarclass", seminarClassVos);
		}
		return modelAndView;
	}
	
	//列出选择某一门课所有研讨课的所有同学
	@RequestMapping("/listClassByCourse")
	public ModelAndView listClassByCourse(@RequestParam(value = "cid") Integer cid){
		ModelAndView modelAndView = new ModelAndView();
		
		List<List<SeminarClassVo>> seminarClassVos = seminarClassServiceImpl.listAllSeminarClass(cid);
		
		if(seminarClassVos == null || seminarClassVos.size() == 0){
			modelAndView.setViewName("error");
		}else{
			
//			for (List<SeminarClassVo> list : seminarClassVos) {
//				for (SeminarClassVo seminarClassVo : list) {
//					System.out.println(seminarClassVo.getSeName());
//					System.out.println(seminarClassVo.getSeTheme());
//					System.out.println(seminarClassVo.getSeTime());
//					System.out.println(seminarClassVo.getSname());
//					System.out.println("99999999999");
//
//				}
//			}
			modelAndView.setViewName("allSeminarClassDisplay");
			modelAndView.addObject("seminarclasses", seminarClassVos);
		}
		return modelAndView;
	}

}
