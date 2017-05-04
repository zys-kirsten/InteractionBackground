package com.interaction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.service.QuestionService;
import com.interaction.service.ResponderdataService;
import com.interaction.service.SemclatestService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.StudentService;
import com.interaction.service.UnquantizationFuzzyEvaluationService;
import com.interaction.service.VotedataService;
import com.interaction.utils.JsonUtils;
import com.interaction.vo.AndroidEvaluationVo;
import com.interaction.vo.CourseVo;
import com.interaction.vo.EvaluateKeys;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.QuestionVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarVo;
import com.interaction.vo.SeminarscoreVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller   
public class StudentController {
	@Resource
	private StudentService studentServiceImpl;
	@Resource
	private CourseService courseServiceImpl;
	@Resource
	private SeminarClassService seminarClassServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	@Resource
	private EvaluationElementService evaluationElementServiceImpl;
	@Resource
	private UnquantizationFuzzyEvaluationService unquantizationFuzzyEvaluationServiceImpl;
	@Resource
	private SemclatestService semclatestServiceImpl;
	@Resource
	private VotedataService votedataServiceImpl;
	@Resource
	private QuestionService questionServiceImpl;
	@Resource
	private ResponderdataService responderdataServiceImpl;
	
	//学生登录
	@RequestMapping("/stuLogin")
	public void stuLogin(@RequestParam("saccount")String saccount,@RequestParam("spwd")String spwd,HttpServletResponse response) throws IOException{
		
		Integer sid = -1;
		Student student = studentServiceImpl.findBySaccount(saccount);
		if (student != null) {
			if (student.getSpwd().equals(spwd)) {
				sid = student.getSid();
			}
		}
		JsonUtils.toJson(response, "sid", sid);
		System.out.println("stuLogin");
	}
	
	//学生签到
	@RequestMapping("/stuSignIn")
	public void stuSignIn(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		flag = studentServiceImpl.stuSignIn(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuSignIn");
	}
	
	//学生查看我的课程
	@RequestMapping("/stuListCourse")
	public void stuListCourse(@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<CourseVo> courses = courseServiceImpl.listCourseByStudent(Integer.parseInt(sid));
		JsonUtils.toJson(response, "courses", courses);
		System.out.println("stuListCourse");
	}

	//学生查看选课列表（需要判断标志位）
	@RequestMapping("/stuListSelectSeminar")
	public void stuListSelectSeminar(@RequestParam("cid")String cid,HttpServletResponse response) throws IOException{
		List<List<SeminarVo>> selectseminars = seminarServiceImpl.listSelectSeminar(Integer.parseInt(cid));
		JsonUtils.toJson(response, "selectseminars", selectseminars);
		System.out.println("stuListSelectSeminar");
	}
	
	
	//学生选课(线程加锁)
	@RequestMapping("/stuSelectSeminar")
	public void stuSelectSeminar(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		
		flag = seminarClassServiceImpl.stuSelectSeminar(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuSelectSeminar");
		
	}
	
	//学生查看某一门课程下的“我的研讨课”
	@RequestMapping("/stuListMySeminar")
	public void stuListMySeminar(@RequestParam("cid")String cid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<SeminarVo> seminars = seminarServiceImpl.listByCidAndSid(Integer.parseInt(cid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "seminars", seminars);
		System.out.println("stuListMySeminar");
	}
	
	//学生查看自己的课堂分组(需要判断标志位)
	@RequestMapping("/stuGrouping")
	public void stuGrouping(@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{

		List<GroupVo> groups = seminarClassServiceImpl.listGroup(Integer.parseInt(seid));
		if (groups != null && groups.size() != 0) {
			Integer groupNum = seminarClassServiceImpl.findMyGroupNum(Integer.parseInt(seid),Integer.parseInt(sid));
			
			if (groupNum != -1) {                   //这个研讨课的分组里面有我
				response.setContentType("application/json");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				JSONObject jsonObject=new JSONObject();  
				jsonObject.put("groups", groups); 
				jsonObject.put("groupNum", groupNum); 

				out.print(jsonObject.toString());
				out.flush();
				out.close();
			}
		}
		
		System.out.println("stuGrouping");
	}
	
	//学生在组间评价前查询需要评价的组(需要查看标志位)
	@RequestMapping("/stuListOutGroupEvaluation")
	public void stuListOutGroupEvaluation(@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<GroupNumsVo>  groupnumsVo = seminarClassServiceImpl.listOtherGroupNums(Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "groupnumsVo", groupnumsVo);
		System.out.println("stuListOutGroupEvaluation");
	}
	
	//学生点击某一个具体的组号，查询组间评价信息(需要查看标志位)
	@RequestMapping("/stufindOutGroupEvaluationKeys")
	public void stufindOutGroupEvaluationKeys(@RequestParam("cid")String cid,HttpServletResponse response) throws IOException{
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listByFatherNameNeedVisited(Integer.parseInt(cid),"组间评价");

		if (evaluationelements != null && evaluationelements.size() != 0) {
			List<EvaluateKeys> keys = new ArrayList<EvaluateKeys>();
			for(Evaluationelement ee : evaluationelements){
				EvaluateKeys key = new EvaluateKeys(ee.getEeid(), ee.getEename(), "");
				keys.add(key);
			}

			JsonUtils.toJson(response, "keys",keys);
		}
		System.out.println("stufindOutGroupEvaluationKeys");
	}
	
	//学生提交组间评价信息
	@RequestMapping("/stuOutGroupEvaluationSubmit")
	public void stuOutGroupEvaluationSubmit(@RequestParam("outgroupevaluations")String outgroupevaluations,HttpServletResponse response) throws IOException{
		
		List<AndroidEvaluationVo> list = new ArrayList<AndroidEvaluationVo>();
		AndroidEvaluationVo evaluation;
		JSONObject jsonObject= JSONObject.fromObject(outgroupevaluations);
		JSONArray jsonArray = jsonObject.getJSONArray("outgroupevaluations");
		for (int i = 0; i < jsonArray.size(); i++) {  
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			int seid = jsonObject2.getInt("seid");
			int groupNum = jsonObject2.getInt("object");
			List<Seminarclass> seminarclasses = seminarClassServiceImpl.listByGroupNum(seid,groupNum);
			
			if (seminarclasses != null && seminarclasses.size() != 0) {
				for(Seminarclass sc:seminarclasses){
					evaluation = new AndroidEvaluationVo(seid,sc.getStudent().getSid(),jsonObject2.getInt("eeid"),jsonObject2.getString("evalRank"));
					list.add(evaluation);
				}
			}
		}

		int flag = unquantizationFuzzyEvaluationServiceImpl.submitEvaluations(list);
		
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuOutGroupEvaluationSubmit");
	}
	
	//学生在组内评价前查询需要评价的组内成员(需要查看标志位)
	@RequestMapping("/stuListInGroupEvaluation")
	public void stuListInGroupEvaluation(@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<SeminarClassVo> students = seminarClassServiceImpl.listMyGroupOtherStu(Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "students", students);
		System.out.println("stuListInGroupEvaluation");
	}
	
	//学生点击组内某一具体同学，查询组内评价信息(需要查看标志位)
	@RequestMapping("/stufindInGroupEvaluationKeys")
	public void stufindInGroupEvaluationKeys(@RequestParam("cid")String cid,HttpServletResponse response) throws IOException{
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listByFatherNameNeedVisited(Integer.parseInt(cid),"组内评价");

		if (evaluationelements != null && evaluationelements.size() != 0) {
			List<EvaluateKeys> keys = new ArrayList<EvaluateKeys>();
			for(Evaluationelement ee : evaluationelements){
				EvaluateKeys key = new EvaluateKeys(ee.getEeid(), ee.getEename(), "");
				keys.add(key);
			}

			JsonUtils.toJson(response, "keys",keys);
		}
		System.out.println("stufindInGroupEvaluationKeys");
	}
	
	//学生提交组内评价信息
	@RequestMapping("/stuInGroupEvaluationSubmit")
	public void stuInGroupEvaluationSubmit(@RequestParam("ingroupevaluations")String ingroupevaluations,HttpServletResponse response) throws IOException{
		
		List<AndroidEvaluationVo> list = new ArrayList<AndroidEvaluationVo>();
		AndroidEvaluationVo evaluation;
		JSONObject jsonObject= JSONObject.fromObject(ingroupevaluations);
		JSONArray jsonArray = jsonObject.getJSONArray("ingroupevaluations");
		for (int i = 0; i < jsonArray.size(); i++) {  
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			evaluation = new AndroidEvaluationVo(jsonObject2.getInt("seid"),jsonObject2.getInt("object"),jsonObject2.getInt("eeid"),jsonObject2.getString("evalRank"));
			list.add(evaluation);
		}

		int flag = unquantizationFuzzyEvaluationServiceImpl.submitEvaluations(list);
		
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuInGroupEvaluationSubmit");
	}
	
	//学生点击自评，查询自评信息
	@RequestMapping("/stufindSelfEvaluationKeys")
	public void stufindSelfEvaluationKeys(@RequestParam("cid")String cid,HttpServletResponse response) throws IOException{
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listByFatherName(Integer.parseInt(cid),"学生自评");

		if (evaluationelements != null && evaluationelements.size() != 0) {
			List<EvaluateKeys> keys = new ArrayList<EvaluateKeys>();
			for(Evaluationelement ee : evaluationelements){
				EvaluateKeys key = new EvaluateKeys(ee.getEeid(), ee.getEename(), "");
				keys.add(key);
			}

			JsonUtils.toJson(response, "keys",keys);
		}
		System.out.println("stufindSelfEvaluationKeys");
	}
	
	//学生提交自评信息
	@RequestMapping("/stuSelfEvaluationSubmit")
	public void stuSelfEvaluationSubmit(@RequestParam("selfevaluations")String selfevaluations,HttpServletResponse response) throws IOException{
		
		List<AndroidEvaluationVo> list = new ArrayList<AndroidEvaluationVo>();
		AndroidEvaluationVo evaluation;
		JSONObject jsonObject= JSONObject.fromObject(selfevaluations);
		JSONArray jsonArray = jsonObject.getJSONArray("selfevaluations");
		for (int i = 0; i < jsonArray.size(); i++) {  
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			evaluation = new AndroidEvaluationVo(jsonObject2.getInt("seid"),jsonObject2.getInt("object"),jsonObject2.getInt("eeid"),jsonObject2.getString("evalRank"));
			list.add(evaluation);
		}

		int flag = unquantizationFuzzyEvaluationServiceImpl.submitEvaluations(list);
		
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuSelfEvaluationSubmit");
	}
	
	//学生点击“限时练习题”，查找课堂限时练习题(需要查看标志位)
	@RequestMapping("/stuListLimiteTimeExercise")
	public void stuListLimiteTimeExercise(@RequestParam("cid")String cid,@RequestParam("seid")String seid,HttpServletResponse response) throws IOException{
		List<QuestionVo> questions = questionServiceImpl.listByCidAndSeidBeVisted(Integer.parseInt(cid),Integer.parseInt(seid));
		JsonUtils.toJson(response, "questions", questions);
		System.out.println("stuListLimiteTimeExercise");
	}
	
	//学生提交限时练习题
	@RequestMapping("/stuLimiteTimeExerciseSubmit")
	public void stuLimiteTimeExerciseSubmit(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,
			@RequestParam("qid")String qid,@RequestParam("aid")String aid,HttpServletResponse response) throws IOException{
		int flag = semclatestServiceImpl.submitSemclatest(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid),Integer.parseInt(qid),Integer.parseInt(aid));
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuLimiteTimeExerciseSubmit");
	}
	
	//学生点击抢答按钮，开始抢答(线程互斥控制)(需要查看标志位)
	@RequestMapping("/stuBeginResponder")
	public void stuBeginResponder(@RequestParam("sid")String sid,@RequestParam("seid")String seid,HttpServletResponse response) throws IOException, NumberFormatException, InterruptedException{
		int flag = responderdataServiceImpl.stuBeginResponder(Integer.parseInt(sid),Integer.parseInt(seid));
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuBeginResponder");
	}
	
	//学生开始投票(需要查看标志位)
	@RequestMapping("/stuBeginVote")
	public void stuBeginVote(@RequestParam("seid")String seid,@RequestParam("sid")String sid,
			@RequestParam("stuAnswer")String stuAnswer,HttpServletResponse response) throws IOException{
		int flag = votedataServiceImpl.stuBeginVote(Integer.parseInt(seid),Integer.parseInt(sid),stuAnswer);
		JsonUtils.toJson(response, "flag", flag);
		System.out.println("stuBeginVote");
	}
	
	//学生查看研讨课成绩
	@RequestMapping("/stuFindMySeminarScore")
	public void stuFindMySeminarScore(@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
	    SeminarscoreVo seminarscore = seminarClassServiceImpl.stuFindMySeminarScore(Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "seminarscore", seminarscore);
		System.out.println("stuFindMySeminarScore");
	}
	
}
