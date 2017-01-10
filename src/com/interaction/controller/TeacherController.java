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

import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Student;
import com.interaction.pojo.Teacher;
import com.interaction.pojo.Votequestion;
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.service.QuestionService;
import com.interaction.service.ResponderdataService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.StudentService;
import com.interaction.service.TeacherService;
import com.interaction.service.UnquantizationFuzzyEvaluationService;
import com.interaction.service.VotedataService;
import com.interaction.service.VotequestionService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.AndroidEvaluationVo;
import com.interaction.vo.CourseVo;
import com.interaction.vo.EvaluateKeys;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarStudentNo;
import com.interaction.vo.SeminarVo;
import com.interaction.vo.VotedataVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TeacherController {

	@Resource
	private TeacherService teacherServiceImpl;
	@Resource
	private CourseService courseServiceImpl;
	@Resource  
	private SeminarService seminarServiceImpl;
	@Resource
	private SeminarClassService seminarClassServiceImpl;
	@Resource
	private EvaluationElementService evaluationElementServiceImpl;
	@Resource
	private VotedataService votedataServiceImpl;
	@Resource
	private VotequestionService votequestionServiceImpl;
	@Resource
	private QuestionService questionServiceImpl;
	@Resource
	private ResponderdataService responderdataServiceImpl;
	@Resource
	private UnquantizationFuzzyEvaluationService unquantizationFuzzyEvaluationServiceImpl;
	
	
//=====================================教师Android端===================================================================	
	@RequestMapping("teacherlogin")
	public void Login(@RequestParam("tAccount")String tAccount,@RequestParam("tPwd")String tPwd,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		Integer tId = -1;
		Teacher teacher = teacherServiceImpl.listByAccount(tAccount);
		if (teacher != null) {
			if (teacher.getTpwd().equals(tPwd)) {
				tId = teacher.getTid();
			}
		}
		//返回tId
		String opts = createJsonString("tId",tId);
		out.print(opts);
		out.flush();
		out.close();
		System.out.println("teacherlogin.do  SAccount : "+tAccount+"   SPwd : "+tPwd);
	}
	
	//教师查询自己的课程列表(finished)
	@RequestMapping("findcoursebytid")
	public void findcoursebytid(@RequestParam("TId")String TId,HttpServletRequest request,HttpServletResponse response) throws IOException{

		List<CourseVo> myclass = courseServiceImpl.listCourse(Integer.parseInt(TId));
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String opts = createJsonString("classes",myclass);
		out.print(opts);

		out.flush();
		out.close();
		System.out.println("findcoursebytid.do  "+opts);
	}
	
	/**
	 * 
	 * @param CId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 查找某课程下的研讨课列表(finished)
	 * 返回数据 List<Seminar> seminars
	 * 
	 */
	@RequestMapping("findseminarbycid")
	public void findseminarbycid(@RequestParam("CId")String CId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<SeminarVo> seminars = seminarServiceImpl.listByCourse(Integer.parseInt(CId));
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//转化成JsonString
		String opts = createJsonString("seminars",seminars);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findseminarbycid.do  "+opts);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 查根据研讨课id查找已签到的学生列表(finished)
	 * 返回数据 List<Seminar> seminars
	 * 
	 */
	@RequestMapping("findsigninstudentsbyseid")
	public void findsigninstudentsbyseid(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SeminarClassVo> students = seminarClassServiceImpl.listLoginStudents(Integer.parseInt(seId));
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//转化成JsonString
		String opts = createJsonString("students",students);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findsigninstudentsbyseid.do  "+opts);
	}
	
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 分组方法(finished! test success! 配置文件读取分组个数还未实现（我自己的，跟你无关）)
	 * 返回数据List<GroupVo> groups
	 */
	@RequestMapping("grouping")
	public void grouping(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<GroupVo> groups = seminarClassServiceImpl.divideGroup(Integer.parseInt(seId));
		
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//转化成JsonString
		String gps = createJsonString("groups",groups);
		out.print(gps);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("grouping.do  "+gps);
	}
	
	
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 分组结果确认，教师进行分组之后如果对结果满意则进行确定，这时调用该方法(finished)
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("groupsubmit")
	public void groupsubmit(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		seminarClassServiceImpl.confirmGroup(Integer.parseInt(seId));
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("groupsubmit.do  "+seId);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 组内评价开始，教师点击开始组内评价按钮时调用该方法(finished!)
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startingroupevaluate")
	public void startingroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		evaluationElementServiceImpl.executeEvaluation(Integer.parseInt(seId),"组内评价","start");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startingroupevaluate.do  "+seId);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 组内评价结束，组内评价倒计时结束时调用该方法 教师重置评价时间时调用该方法（finished!）
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("endingroupevaluate")
	public void endingroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		evaluationElementServiceImpl.executeEvaluation(Integer.parseInt(seId),"组内评价","end");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endingroupevaluate.do  "+seId);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 组间评价开始，教师点击开始组间评价按钮时调用该方法（finished）
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startoutgroupevaluate")
	public void startoutgroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		evaluationElementServiceImpl.executeEvaluation(Integer.parseInt(seId),"组间评价","start");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startoutgroupevaluate.do  "+seId);   
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 组间评价结束，组间评价倒计时结束时调用该方法 教师重置评价时间时调用该方法(finished)
	 */
	@RequestMapping("endoutgroupevaluate")
	public void endoutgroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		evaluationElementServiceImpl.executeEvaluation(Integer.parseInt(seId),"组间评价","end");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endoutgroupevaluate.do  "+seId);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 没有传入参数，有可能需要改变(finished!)(需要传参数：研讨课ID，投票题目ID)
	 * 
	 * 查询当前投票题投票数据
	 * 
	 */
	@RequestMapping("getvotedata")
	public void getvotedata(@RequestParam("seId")String seId,@RequestParam("voteId")String vqid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层，查询当前投票题投票数据。
		 */
		List<VotedataVo> votedatas = votedataServiceImpl.listCurrentVotedataBySeidAndVqid(Integer.parseInt(seId),Integer.parseInt(vqid));
		
		if (votedatas != null && votedatas.size() != 0) {
			//定义response的各种参数
			response.setContentType("application/json");  
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			//需要实现
		    //该处将查找出的投票数据添加到jsonObject中
			JSONObject jsonObject=new JSONObject();  
			for (VotedataVo vv :votedatas) {
				if (vv.getStuAnswer().equals("A")) {
					jsonObject.put("A", vv.getStuNum());
				}else if (vv.getStuAnswer().equals("B")){
					jsonObject.put("B", vv.getStuNum());
				}else if (vv.getStuAnswer().equals("C")){
					jsonObject.put("C", vv.getStuNum());
				}else {
					jsonObject.put("D", vv.getStuNum());
				}
				
			}

			out.print(jsonObject.toString());
			out.flush();
			out.close();
			//忽略该行，system.out用于测试，实际编码中不需要实现
			System.out.println("getvotedata.do  "+jsonObject.toString());
		}
	}
	/**
	 * 启动投票答题后，需要将该题目的ID返回（你没写）
	 * @param request
	 * @param response
	 * @throws IOException
	 * 投票开始，教师点击开始投票按钮时调用该方法(需测试)
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startvote")
	public void startvote(@RequestParam("seId")String seId,@RequestParam("correctAnswer")String correctAnswer,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer vqid = votequestionServiceImpl.startVote(Integer.parseInt(seId),correctAnswer);
		
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//转化成JsonString
		String opts = createJsonString("vqid",vqid);
		out.print(opts);
		out.flush();
		out.close();
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startvote.do  ");
	}
	
	/**
	 * 新添加的方法！！！！！
	 * @param request
	 * @param response
	 * @throws IOException
	 * 投票结束，教师点击投票结束按钮或时间到了时调用该方法(finished)
	 * 
	 * 无返回值
	 */
	@RequestMapping("endvote")
	public void endvote(@RequestParam("vqid")String vqid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		votequestionServiceImpl.endVote(Integer.parseInt(vqid));
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endvote.do  ");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 选课开始(需要传递课程ID)(finished!)
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startcourseselect")
	public void startcourseselect(@RequestParam("cId")String cid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		seminarServiceImpl.executeCourseSelect(Integer.parseInt(cid),"start");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startcourseselect.do  ");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 选课结束(需要传递课程ID)(finished!)
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("endcourseselect")
	public void endcourseselect(@RequestParam("cId")String cid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		seminarServiceImpl.executeCourseSelect(Integer.parseInt(cid),"end");
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endcourseselect.do  ");
	}
	/**
	 * 
	 * @param cId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 查询研讨课选中的人数(finished!)
	 * 返回人数数据
	 */
	@RequestMapping("findseminarstudentsnumberbycid")
	public void findseminarstudentsnumberbycid(@RequestParam("cId")String cId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SeminarStudentNo> nos = seminarClassServiceImpl.listCurrentSelectSeminarStuNumber(Integer.parseInt(cId));
		
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		//转化成JsonString
		String opts = createJsonString("numbers",nos);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findseminarstudentsnumberbycid.do  "+opts);
	}
	/**
	 * (配置需要完成的，我还没写，你不用管)
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 查找默认的限时练习题数量和时间，单位是秒，如果没有则设置为0
	 */
	@RequestMapping("findexerciseinfo")
	public void findexerciseinfo(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层查找默认的限时练习题数量和时间
		 */
		
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//需要实现
	    //该处将查找出的数量和时间添加到jsonObject
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put("number", 10); 
		jsonObject.put("time", 7); 


		out.print(jsonObject.toString());
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findexerciseinfo.do  "+jsonObject.toString());
	}
	/**
	 * 
	 * @param seId
	 * @param time
	 * @param number
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 开始限时答题(finished!)
	 * 有可能需要标志位
	 */
	@RequestMapping("starttimelimitexercise")
	public void starttimelimitexercise(@RequestParam("seId")String seId,@RequestParam("number")String number,HttpServletRequest request,HttpServletResponse response) throws IOException{
		questionServiceImpl.startTimeLimitExercise(Integer.parseInt(seId),Integer.parseInt(number));
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("starttimelimitexercise.do  "+"seId:"+seId+"number:"+number);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 结束限时答题(finished!)
	 * 有可能需要标志位
	 */
	@RequestMapping("endtimelimitexercise")
	public void endtimelimitexercise(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		questionServiceImpl.endTimeLimitExercise(Integer.parseInt(seId));
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endtimelimitexercise.do  "+"seId:"+seId);
	}
//	/**开始抢答后需要将抢答的id返回，放入json中，你没写
//	 * (finished!)
//	 * @param seId
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * 开始抢答
//	 * 有可能需要标志位
//	 */
//	@RequestMapping("beginexerciserush")
//	public void beginexerciserush(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Integer rdid = responderdataServiceImpl.startResponder(Integer.parseInt(seId));
//		System.out.println("beginexerciserush.do  ");
//	}
	/**
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 开始抢答
	 * 有可能需要标志位
	 * @throws InterruptedException 
	 */
	@RequestMapping("startexerciserush")
	public void startexerciserush(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层开始抢答，返回学生信息
		 * 开始抢答功能开启之后，才能进行抢答，当出现同学抢答成功时，应该关闭抢答功能，并将学生信息返回，以上功能应该在service中实现
		 * 
		 */
		Integer rdid = responderdataServiceImpl.startResponder(Integer.parseInt(seId));
		Student student = null;
		
		while(student == null){
			System.out.println("还没有抢答成功的学生");
			Thread.sleep(500);
			student = responderdataServiceImpl.listDoneStudent(rdid);
		}
	
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//需要实现
		//该处将查找出的学生信息添加到jsonObject
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put("name",student.getSname());  //jsonObject中的key不能改变只能修改value
		jsonObject.put("sId",student.getSid()); 
		jsonObject.put("rushId",rdid); 
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startexerciserush.do  "+jsonObject.toString());
		
	}
	/**(finished!)
	 * 参数需要改变，需要传递抢答题目的ID
	 * @param seId
	 * @param sId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 抢答题，确定答题学生
	 * 
	 */
	
	@RequestMapping("exerciserushsubmit")
	public void exerciserushsubmit(@RequestParam("rushId")String rdid,@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		responderdataServiceImpl.endResponder(Integer.parseInt(rdid));
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("exerciserushsubmit.do  ");
	}
	
	/**
	 * @param seId
	 * @param sId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 抢答题，不满意当前抢答学生的人选，重新确定答题学生
	 * @throws InterruptedException 
	 * 
	 */
	
	@RequestMapping("reexerciserushsubmit")
	public void reexerciserushsubmit(@RequestParam("rushId")String rdid,@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		responderdataServiceImpl.resetResponder(Integer.parseInt(rdid));
        Student student = null;
		
		while(student == null){
			System.out.println("还没有抢答成功的学生");
			Thread.sleep(500);
			student = responderdataServiceImpl.listDoneStudent(Integer.parseInt(rdid));
		}
	
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//需要实现
		//该处将查找出的学生信息添加到jsonObject
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put("name",student.getSname());  //jsonObject中的key不能改变只能修改value
		jsonObject.put("sId",student.getSid()); 
		jsonObject.put("rushId",rdid); 
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("reexerciserushsubmit.do  ");
	}
	/**
	 * (finished)但需要向前台传递的两个参数需要放入json中
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 根据研讨课Id查找给研讨课中的教师对学生评价信息的列表
	 * 有可能需要标志位
	 */
	@RequestMapping("findstudentsbyseid")
	public void findstudentsbyseid(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SeminarClassVo> students = seminarClassServiceImpl.listLoginStudents(Integer.parseInt(seId));
		
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//转化成JsonString
		String opts = createJsonString("students",students);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		//System.out.println("findstudentsbyseid.do  "+opts);
	}
	/**
	 * 未实现，不知道集合怎么解析
	 * @param seId
	 * @param sId
	 * @param positivity
	 * @param communicate
	 * @param keypoint
	 * @param request
	 * @param response
	 * @throws IOException
	 * 提交教师评价信息
	 */
	@RequestMapping("teacherevaluatesubmit")
	public void teacherevaluatesubmit(
			@RequestParam("evaluations")String evaluations,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<AndroidEvaluationVo> list = new ArrayList<AndroidEvaluationVo>();
		AndroidEvaluationVo evaluation;
		JSONObject jsonObject= JSONObject.fromObject(evaluations);
		JSONArray jsonArray = jsonObject.getJSONArray("evaluations");
		for (int i = 0; i < jsonArray.size(); i++) {  
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			evaluation = new AndroidEvaluationVo(jsonObject2.getInt("seId"),jsonObject2.getInt("sId"),jsonObject2.getInt("eeId"),jsonObject2.getString("evalRank"));
			list.add(evaluation);
		}

		int flag = unquantizationFuzzyEvaluationServiceImpl.submitEvaluations(list);
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("teacherevaluatesubmit.do  "+evaluations);
	}
	/**
	 * 直接将查询到的评价因素返回不行吗？一定要转换成EvaluationKeys吗
	 * @param cId
	 * @param eeName：教师评价
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("findteacherevaluatekeys")
	public void findteacherevaluatekeys(
			@RequestParam("cId")String cId,@RequestParam("eeName")String eeName,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层查找教师评价项目
		 * 
		 */
		
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listByFatherName(Integer.parseInt(cId),"教师评价");
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		//将查询到的信息添加到List<EvaluateKeys> students
		List<EvaluateKeys> keys = new ArrayList<EvaluateKeys>();
		for(Evaluationelement ee : evaluationelements){
			EvaluateKeys key = new EvaluateKeys(ee.getEeid(), ee.getEename(), "");
			keys.add(key);
		}

		//转化成JsonString
		String opts = createJsonString("keys",keys);
		out.print(opts);
		out.flush();
		out.close();

		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("teacherevaluatesubmit.do  ");
	}

	public static String  createJsonString(String key,Object value){  

		JSONObject jsonObject=new JSONObject();  
		jsonObject.put(key, value);  
		return jsonObject.toString();  

	} 
//=====================================教师PC端========================================================================	
	//研讨课登录前选择课程
	@RequestMapping("/chooseCourse")
	public void chooseCourse(String taccount,String tpwd,HttpServletResponse response) throws IOException{

		Teacher teacher = teacherServiceImpl.login(taccount);
		String msg = "fail";
		
		if(teacher != null){
			if(teacher.getTpwd().equals(tpwd)){
				SessionUtil.getMySession().setAttribute("teacher", teacher);
				List<CourseVo> courseVos = courseServiceImpl.listCourse(teacher.getTid());
				if (courseVos !=null && courseVos.size()!=0) {
					SessionUtil.getMySession().setAttribute("courseVos", courseVos);
					msg = "success";
				}else {
					msg="success2";
				}
				
			}else{
				msg = "wrong";
			}		
		}
		response.getWriter().print(msg);
	}
	
	
	//教师注册
	@RequestMapping("/register")
	public void register(Teacher teacher,HttpServletResponse response) throws IOException{
		
		String msg = "wrong";
		int result = teacherServiceImpl.register(teacher);
		
		if (result != -1) {
			msg = "success"; 
		}
		response.getWriter().print(msg);
	}
	
	//教师注册验证
	@RequestMapping("/registerCheck")
	public void registerCheck(String taccount,HttpServletResponse response) throws IOException{
		Teacher teacher = teacherServiceImpl.listByAccount(taccount);
		if (teacher != null) {
			response.getWriter().write("fail");
		}
	}
	
	//教师研讨课登录
	@RequestMapping("/loginCheck")
	public void loginCheck(String cname,HttpServletRequest request,HttpServletResponse response) throws IOException{

		System.out.println("cname="+cname);
		Teacher teacher = (Teacher) SessionUtil.getMySession().getAttribute("teacher");
		Course course = courseServiceImpl.listCourseByName(teacher.getTid(),cname);
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listCourseSixEvaluationElements(course.getCid());
		String msg = "fail";
		if (course != null) {
			SessionUtil.getMySession().setAttribute("course", course);
			SessionUtil.getMySession().setAttribute("evaluationelements", evaluationelements);
			msg = "success";
		}
		response.getWriter().print(msg);
	}
	
	//教师普通登录
	@RequestMapping("/commonLoginCheck")
	public void commonLoginCheck(String taccount, String tpwd,
			HttpServletRequest request,HttpServletResponse response) throws IOException{

		Teacher teacher = teacherServiceImpl.login(taccount);
		String msg = "fail";
		
		if(teacher != null){
			if(teacher.getTpwd().equals(tpwd)){
				SessionUtil.getMySession().setAttribute("teacher", teacher);
				msg = "success";
			}else{
				msg = "wrong";
			}		
		}
		response.getWriter().print(msg);
	}
	
	//教师退出登录
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws IOException{
		Object object = request.getSession().getAttribute("teacher");
		  if (object != null) {
		   try {
		    request.getSession().removeAttribute("teacher");
		   } catch (Exception e) {
		    object = null;
		   }
		  }
		return "../login";
	}
}
