package com.interaction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Seminarpoint;
import com.interaction.service.SeminarService;
import com.interaction.service.SeminarpointService;
import com.interaction.utils.SessionUtil;

@Controller
public class SeminarpointController {
	@Resource
	private SeminarpointService seminarpointServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	
	//列出一个研讨课下面的所有知识点
	@RequestMapping("/listSeminarpoint")
	public String listSeminarpoint(@RequestParam("seId")Integer seId){
		List<Seminarpoint> seminarpoints = seminarpointServiceImpl.listBySeId(seId);
		SessionUtil.getMySession().setAttribute("seminarpoints", seminarpoints);
		SessionUtil.getMySession().setAttribute("seminar", seminarServiceImpl.findById(seId));
		return "seminarpoint/listSeminarpoint";
	}
	
	//添加（修改）某一知识点
	@RequestMapping("/addSeminarpoint")
	public String addSeminarpoint(Seminarpoint seminarpoint){
		int result = -1;
		if (seminarpoint.getSpid() == null) {
		    result = seminarpointServiceImpl.addSeminarpoint(seminarpoint);//添加知识点
		}else{
			result = seminarpointServiceImpl.updateSeminarpoint(seminarpoint);
		}
		return listSeminarpoint(seminarpoint.getSeminar().getSeId());
	}
	
	//修改知识点之前的回显
	@RequestMapping("/editSeminarpoint")
	public String editSeminarpoint(@RequestParam("spid")Integer spid){
		Seminarpoint seminarpoint = seminarpointServiceImpl.findById(spid);
		SessionUtil.getMySession().setAttribute("seminarpoint", seminarpoint);
		SessionUtil.getMySession().setAttribute("seminar", seminarServiceImpl.findById(seminarpoint.getSeminar().getSeId()));
		return "seminarpoint/editSeminarpoint";
	}
	
	//删除知识点
	@RequestMapping("/deleteSeminarpoint")
	public String deleteSeminarpoint(@RequestParam("spid")Integer spid,@RequestParam("seId")Integer seId){
		seminarpointServiceImpl.deleteSeminarpoint(spid);
		return listSeminarpoint(seId);
	}

}
