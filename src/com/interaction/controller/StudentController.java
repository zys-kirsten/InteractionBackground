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
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.StudentService;
import com.interaction.service.UnquantizationFuzzyEvaluationService;
import com.interaction.utils.JsonUtils;
import com.interaction.vo.AndroidEvaluationVo;
import com.interaction.vo.CourseVo;
import com.interaction.vo.EvaluateKeys;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
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
		if (!sid.equals("-1")) {
			flag = seminarClassServiceImpl.stuSelectSeminar(Integer.parseInt(cid),Integer.parseInt(seid),Integer.parseInt(sid));
			JsonUtils.toJson(response, "flag", flag);
		}
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
	
	//学生在组间评价前查询需要评价的组
	@RequestMapping("/stuListOutGroupEvaluation")
	public void stuListOutGroupEvaluation(@RequestParam("seid")String seid,@RequestParam("groupNum")String groupNum,HttpServletResponse response) throws IOException{
		if (!groupNum.equals("-1")) {
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
}
