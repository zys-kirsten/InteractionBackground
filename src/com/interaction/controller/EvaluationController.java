package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Course;
import com.interaction.service.EvaluationService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.EvaluationVo;
import com.interaction.vo.QuestionVo;

@Controller
public class EvaluationController {

	@Resource
	private EvaluationService evaluationServiceImpl;
	
	//列出符合某一查询条件的所有课程
	@RequestMapping("/searchEvaluation")
	public void searchEvaluation(String condition,String inputValue,HttpServletResponse response) throws IOException{
		System.out.println(condition);
    	System.out.println(inputValue);
    	String msg="fail";
    	List<EvaluationVo> evaluationVos = evaluationServiceImpl.findByCondition(getCourse().getCid(),condition,inputValue);
    	SessionUtil.getMySession().setAttribute("evaluationVos",evaluationVos);
    	
    	if(evaluationVos!=null){
    		msg="success";
    	}
    	response.getWriter().write(msg);
	}
	
	//删除评价单
	@RequestMapping("/deleteEvaluation")
	public String deleteEvaluation(@RequestParam(value="eid") Integer eid) throws IOException{
		System.out.println("deleteEvaluationr eid = "+eid);
		int result = evaluationServiceImpl.deleteEvaluationById(eid);
		return listEvaluation(getCourse().getCid());
	}
	
	//回显修改评价单信息
	@RequestMapping("/editEvaluation")
	public String editEvaluation(@RequestParam(value="eid") Integer eid){
		System.out.println("editEvaluation eid = "+eid);
		EvaluationVo evaluationVo = evaluationServiceImpl.findById(eid);
		System.out.println(evaluationVo);
		SessionUtil.getMySession().setAttribute("evaluationVo", evaluationVo);
		return "evaluation/editEvaluation";
	}
	
	//添加(修改)评价方式
	@RequestMapping("/addEvaluation")
	public String addEvaluation(EvaluationVo evaluationVo){
		evaluationVo.setCid(getCourse().getCid());

		int result = -1; 
		if (evaluationVo.getEid() == null) {
			result = evaluationServiceImpl.addEvaluation(evaluationVo);//添加测试题
		}else{
			result = evaluationServiceImpl.updateQuestion(evaluationVo);//修改测试题
		}
		
		if(result != -1){
			listEvaluation();
			return "evaluation/listEvaluation";
		}else{
			return "error";
		}
	}
	
	//列出一门课的所有评价单
	@RequestMapping("/listEvaluation")
	public String listEvaluation(@RequestParam(value="cid")Integer cid){
		    listEvaluation();
		return "evaluation/listEvaluation";
	}
	private void listEvaluation() {
		Integer cid = getCourse().getCid();
		List<EvaluationVo> evaluationVos = evaluationServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("evaluationVos", evaluationVos);
	}

	
	//获得session中的课程信息
	private Course getCourse(){
		return (Course) SessionUtil.getMySession().getAttribute("course");
	}
		
}
