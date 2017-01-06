package com.interaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interaction.pojo.Answer;
import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Teacher;
import com.interaction.service.QuestionService;
import com.interaction.service.SeminarService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.QuestionVo;
import com.interaction.vo.SeminarVo;

@Controller
public class QuestionController {
	
	@Resource
	private QuestionService questionServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	
	//添加测试题前先查看本门课有哪些研讨课
	@RequestMapping("/listSeminarBeforeAddQuestion")
	public String listSeminarBeforeAddQuestion(@RequestParam(value="cid") Integer cid){
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "question/addQuestion";
	}
	
	//删除测试题
	@RequestMapping("/deleteQuestion")
	public String deleteQuestion(@RequestParam(value="qid") Integer qid) throws IOException{
		System.out.println("deleteSeminar qid = "+qid);
		int result = questionServiceImpl.deleteQuestionById(qid);
		return listCourseQuestion(getCourse().getCid());
	}
	
	//回显修改研讨课信息
	@RequestMapping("/editQuestion")
	public String editQuestion(@RequestParam(value="qid") Integer qid){
		QuestionVo questionVo = questionServiceImpl.findById(qid);
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(getCourse().getCid());
		System.out.println(seminarVos.size());
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		SessionUtil.getMySession().setAttribute("questionVo", questionVo);
		return "question/editQuestion";
	}
	
	//添加(修改)测试题
	@RequestMapping("/addQuestion")
	public String addQuestion(QuestionVo questionVo){
		String[] checkCorrect = SessionUtil.getRequest().getParameterValues("correct");
		for(int i=0;i<checkCorrect.length;i++){
			for(int j=0;j<4;j++){
				if(questionVo.getAnswers().get(j).getCorrect() == null || questionVo.getAnswers().get(j).getCorrect() == 0){
					if(checkCorrect[i].equals("a"+j)){
						questionVo.getAnswers().get(j).setCorrect(1);
					}else{
						questionVo.getAnswers().get(j).setCorrect(0);
					}
				}
			}
		}
		
		questionVo.setCid(getCourse().getCid());

		int result = -1;
		if (questionVo.getQid() == null) {
			result = questionServiceImpl.addQuestion(questionVo);//添加测试题
		}else{
			result = questionServiceImpl.updateQuestion(questionVo);//修改测试题
		}
		
		if(result != -1){
			listCourseQuestion();
			return "question/listQuestion";
		}else{
			return "error";
		}
	}
	
	
	//列出符合某一查询条件的所有课程
		@RequestMapping("/searchQuestion")
		public void searchQuestion(String condition,String inputValue,HttpServletResponse response) throws IOException{
	    	String msg="fail";
	    	List<QuestionVo> questionVos = questionServiceImpl.findByCondition(getCourse().getCid(),condition,inputValue);
	    	SessionUtil.getMySession().setAttribute("questionVos",questionVos);
	    	
	    	if(questionVos!=null){
	    		msg="success";
	    	}
	    	response.getWriter().write(msg);
		}

	//列出某一门课的所有测试题
	private void listCourseQuestion() {
		List<QuestionVo> questionVos = questionServiceImpl.listQuestionByCourse(getCourse().getCid());
		SessionUtil.getMySession().setAttribute("questionVos",questionVos);		
				
	}

	//列出某一门课的所有章节的测试题
	@RequestMapping("/listCourseQuestion")
	public String listCourseQuestion(@RequestParam(value = "cid")Integer cid){
		listCourseQuestion();
		return "question/listQuestion";
	}
	
	//获得session中的课程信息
	private Course getCourse(){
		return (Course) SessionUtil.getMySession().getAttribute("course");
	}
	
}
