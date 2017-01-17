package com.interaction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.service.ClassModuleService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.ClassModuleVo;
import com.interaction.vo.SeminarVo; 
    
@Controller   
public class ClassModuleController {   
	
	@Resource
	private ClassModuleService classModuleServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	@Resource
	private SeminarClassService seminarClassServiceImpl;
	
	//修改配置前回显配置信息
	@RequestMapping("/editClassModule")
	public String editClassModule(@RequestParam(value="cmid")Integer cmid){
		ClassModuleVo classModuleVo = classModuleServiceImpl.findById(cmid);
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(getCourse().getCid());
		SessionUtil.getMySession().setAttribute("classModuleVo", classModuleVo);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "configDetail/editClassModule";
	}
	
	//进入配置界面前显示该门课已有的研讨课信息
	@RequestMapping("/configClassModuleEval")
	public String listEvaluationAndSeminar(@RequestParam(value="cid")Integer cid){
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "configDetail/addClassModule";
	}
    
	//添加(修改)配置信息  
	@RequestMapping("/addClassModule")
	public String addClassModule(ClassModuleVo classModuleVo){
		Integer cid = getCourse().getCid();
		String[] checkSeName = SessionUtil.getRequest().getParameterValues("seminar");
		List<Seminar> seminars = new ArrayList<Seminar>();
		
		if (checkSeName !=null && checkSeName.length !=0) {
			for(int i=0;i<checkSeName.length;i++){
				Seminar seminar = seminarServiceImpl.findBySeName(cid,checkSeName[i]);
				seminars.add(seminar);
			}
		}
		classModuleVo.setSeminars(seminars);
        classModuleVo.setCid(cid);
		int result = -1;
		if (classModuleVo.getCmid() == null) {
			result = classModuleServiceImpl.addClassModule(classModuleVo);//添加测试题
		}else{
			result = classModuleServiceImpl.updateClassModule(classModuleVo);//修改测试题
		}
		
		if(result != -1){
			listClassModule();
			return "configDetail/listClassModule";
		}else{
			return "error";
		}
	}
	
	//删除某一课堂模式
	@RequestMapping("/deleteClassModule")
	public String deleteClassModule(@RequestParam(value="cmid")Integer cmid){
		int result = classModuleServiceImpl.deleteClassModuleById(cmid);
		if (result == -1) {
			return "error";
		}
		listClassModule();
		return "configDetail/listClassModule";
	}
	//列出所有课堂模式
	@RequestMapping("/listClassModule")
	public String listClassModule(@RequestParam(value="cid")Integer cid){
		listClassModule();
		return "configDetail/listClassModule";
	}
	private void listClassModule() {
		Integer cid = getCourse().getCid();
		List<ClassModuleVo> classModuleVos = classModuleServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("classModuleVos", classModuleVos);
	}
    
  //获得session中的课程信息
  	private Course getCourse(){
  		return (Course) SessionUtil.getMySession().getAttribute("course");
  	}
  	
}  