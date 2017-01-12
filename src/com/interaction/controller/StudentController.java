package com.interaction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.pojo.Votequestion;
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.StudentService;
import com.interaction.service.UnquantizationFuzzyEvaluationService;
import com.interaction.service.VotedataService;
import com.interaction.service.VotequestionService;
import com.interaction.service.impl.SemclatestService;
import com.interaction.utils.JsonUtils;
import com.interaction.vo.AndroidEvaluationVo;
import com.interaction.vo.CourseVo;
import com.interaction.vo.EvaluateKeys;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarVo;

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
	}
	
	//学生签到
	@RequestMapping("/stuSignIn")
	public void stuSignIn(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		flag = studentServiceImpl.stuSignIn(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
	}
	
	//学生查看我的课程
	@RequestMapping("/stuListCourse")
	public void stuListCourse(@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<CourseVo> courses = courseServiceImpl.listCourseByStudent(Integer.parseInt(sid));
		JsonUtils.toJson(response, "courses", courses);
	}

	//学生查看选课列表（需要判断标志位）
	
	
	//学生选课
	@RequestMapping("/stuSelectSeminar")
	public void stuSelectSeminar(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		int flag = -1;
		
		flag = seminarClassServiceImpl.stuSelectSeminar(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "flag", flag);
		
	}
	
	//学生查看某一门课程下的“我的研讨课”
	@RequestMapping("/stuListMySeminar")
	public void stuListMySeminar(@RequestParam("cid")String cid,@RequestParam("sid")String sid,HttpServletResponse response) throws IOException{
		List<SeminarVo> seminars = seminarServiceImpl.listByCidAndSid(Integer.parseInt(cid),Integer.parseInt(sid));
		JsonUtils.toJson(response, "seminars", seminars);
	}
	
	//学生查看自己的课堂分组
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
	}
	
	//学生在组间评价前查询需要评价的组
	@RequestMapping("/stuListOutGroupEvaluation")
	public void stuListOutGroupEvaluation(@RequestParam("seid")String seid,@RequestParam("groupNum")String groupNum,HttpServletResponse response) throws IOException{
		if (groupNum != null && (!groupNum.equals("-1"))) {
			List<GroupNumsVo>  groupnumsVo = seminarClassServiceImpl.listOtherGroupNums(Integer.parseInt(seid),Integer.parseInt(groupNum));
			JsonUtils.toJson(response, "groupnumsVo", groupnumsVo);
		}
	}
	
	//学生点击某一个具体的组号，查询组间评价信息
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
	}
	
	//学生在组内评价前查询需要评价的组内成员
	@RequestMapping("/stuListInGroupEvaluation")
	public void stuListInGroupEvaluation(@RequestParam("seid")String seid,@RequestParam("sid")String sid,@RequestParam("groupNum")String groupNum,HttpServletResponse response) throws IOException{
		List<SeminarClassVo> students = seminarClassServiceImpl.listMyGroupOtherStu(Integer.parseInt(seid),Integer.parseInt(sid),Integer.parseInt(groupNum));
		JsonUtils.toJson(response, "students", students);
	}
	
	//学生点击组内某一具体同学，查询组内评价信息
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
	}
	
	//学生点击“限时练习题”，查找课堂限时练习题
	@RequestMapping("/stuListLimiteTimeExercise")
	public void stuListLimiteTimeExercise(@RequestParam("cid")String cid,@RequestParam("seid")String seid,HttpServletResponse response) throws IOException{
		
	}
	
	//学生提交限时练习题
	@RequestMapping("/stuLimiteTimeExerciseSubmit")
	public void stuLimiteTimeExerciseSubmit(@RequestParam("cid")String cid,@RequestParam("seid")String seid,@RequestParam("sid")String sid,
			@RequestParam("qid")String qid,@RequestParam("aid")String aid,HttpServletResponse response) throws IOException{
		int flag = semclatestServiceImpl.submitSemclatest(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid),Integer.parseInt(qid),Integer.parseInt(aid));
		JsonUtils.toJson(response, "flag", flag);
	}
	
	//学生点击抢答按钮，开始抢答(线程互斥控制)
	@RequestMapping("/stuBeginResponder")
	public void stuBeginResponder(@RequestParam("sid")String sid,@RequestParam("rdid")String rdid,HttpServletResponse response) throws IOException{
		
	}
	
	//学生开始投票
	@RequestMapping("/stuBeginVote")
	public void stuBeginVote(@RequestParam("seid")String seid,@RequestParam("sid")String sid,
			@RequestParam("vqid")String vqid,@RequestParam("stuAnswer")String stuAnswer,HttpServletResponse response) throws IOException{
		if (vqid != null && (!vqid.equals("-1"))) {
			int flag = votedataServiceImpl.stuBeginVote(Integer.parseInt(seid),Integer.parseInt(sid),Integer.parseInt(vqid),stuAnswer);
			JsonUtils.toJson(response, "flag", flag);
		}
	}
	
}
