package com.interaction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluationelement;
import com.interaction.pojo.Seminar;
import com.interaction.pojo.Teacher;
import com.interaction.service.CourseService;
import com.interaction.service.EvaluationElementService;
import com.interaction.service.SeminarClassService;
import com.interaction.service.SeminarService;
import com.interaction.service.TeacherService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.CourseVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarVo;

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
	
	
//=====================================教师Android端===================================================================	
	@RequestMapping("teacherlogin")
	public void Login(@RequestParam("tAccount")String tAccount,@RequestParam("tPwd")String tPwd,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.print(tAccount);
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
		
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层，查找CId的课程下的所有研讨课。
		 */
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
	 * 分组方法(finished! test success!)
	 * 返回数据List<GroupVo> groups
	 */
	@RequestMapping("grouping")
	public void grouping(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层，对seId研讨课中的学生进行分组，并返回分组结果。
		 */
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
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层的分组结果确认的方法，传入参数seId。
		 */
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
	 * 组内评价开始，教师点击开始组内评价按钮时调用该方法
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startingroupevaluate")
	public void startingroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startingroupevaluate.do  "+seId);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 组内评价结束，组内评价倒计时结束时调用该方法 教师重置评价时间时调用该方法
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("endingroupevaluate")
	public void endingroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
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
	 * 组间评价开始，教师点击开始组间评价按钮时调用该方法
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startoutgroupevaluate")
	public void startoutgroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startoutgroupevaluate.do  "+seId);   
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 组间评价结束，组间评价倒计时结束时调用该方法 教师重置评价时间时调用该方法
	 */
	@RequestMapping("endoutgroupevaluate")
	public void endoutgroupevaluate(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endoutgroupevaluate.do  "+seId);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 没有传入参数，有可能需要改变
	 * 
	 * 查询当前投票题投票数据
	 * 
	 */
	@RequestMapping("getvotedata")
	public void getvotedata(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层，查询当前投票题投票数据。
		 */
		
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		//需要实现
	    //该处将查找出的投票数据添加到jsonObject中
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put("A", 10); 
		jsonObject.put("B", 15); 
		jsonObject.put("C", 1); 
		jsonObject.put("D", 15); 



		out.print(jsonObject.toString());
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("getvotedata.do  "+jsonObject.toString());
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 投票开始，教师点击开始投票按钮时调用该方法
	 * 
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startvote")
	public void startvote(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startvote.do  ");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 选课开始
	 * 有可能需要添加标志位
	 * 无返回值
	 */
	@RequestMapping("startcourseselect")
	public void startcourseselect(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startcourseselect.do  ");
	}
	/**
	 * 
	 * @param cId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 查询研讨课选中的人数
	 * 
	 * 返回人数数据
	 */
	@RequestMapping("findseminarstudentsnumberbycid")
	public void findseminarstudentsnumberbycid(@RequestParam("cId")String cId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层查找出选课的学生数
		 */
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//需要实现
	    //该处将查找出的学生数添加到List<SeminarStudentNo>中
		List<SeminarStudentNo> nos = new ArrayList<SeminarStudentNo>();
		SeminarStudentNo number = new SeminarStudentNo("数据仓库研讨课1",10);
		nos.add(number);
		number = new SeminarStudentNo("数据仓库研讨课2",20);
		nos.add(number);
		number = new SeminarStudentNo("数据仓库研讨课3",30);
		nos.add(number);
		
		//转化成JsonString
		String opts = createJsonString("numbers",nos);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findseminarstudentsnumberbycid.do  "+opts);
	}
	/**
	 * 
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
	 * 开始限时答题
	 * 有可能需要标志位
	 */
	@RequestMapping("starttimelimitexercise")
	public void starttimelimitexercise(@RequestParam("seId")String seId,@RequestParam("time")String time,
			@RequestParam("number")String number,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("starttimelimitexercise.do  "+"seId:"+seId+"time:"+time+"number:"+number);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 结束限时答题
	 * 有可能需要标志位
	 */
	@RequestMapping("endtimelimitexercise")
	public void endtimelimitexercise(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("endtimelimitexercise.do  "+"seId:"+seId);
	}
	/**
	 * 
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
	public void exerciserushsubmit(@RequestParam("seId")String seId,@RequestParam("sId")String sId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("exerciserushsubmit.do  "+seId +sId);
	}
	/**
	 * 
	 * @param seId
	 * @param request
	 * @param response
	 * @throws IOException
	 * 开始抢答
	 * 有可能需要标志位
	 */
	@RequestMapping("startexerciserush")//
	public void startexerciserush(@RequestParam("seId")String seId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层开始抢答，返回学生信息
		 * 开始抢答功能开启之后，才能进行抢答，当出现同学抢答成功时，应该关闭抢答功能，并将学生信息返回，以上功能应该在service中实现
		 * 
		 */
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//需要实现
	    //该处将查找出的学生信息添加到jsonObject
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put("name","张三");  //jsonObject中的key不能改变只能修改value
		jsonObject.put("sId","16161616"); 
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("startexerciserush.do  "+jsonObject.toString());
	}
	/**
	 * 
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
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层查找教师对学生的评价信息
		 * 
		 */
		//定义response的各种参数
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//将查询到的信息添加到List<StudentEvaluateVo> students
		List<StudentEvaluateVo> students = new ArrayList<StudentEvaluateVo>();
		StudentEvaluateVo stu = new StudentEvaluateVo(1,"张三","No");
		students.add(stu);
		stu = new StudentEvaluateVo(2,"李四","No");
		students.add(stu);
		stu = new StudentEvaluateVo(3,"王五","No");
		students.add(stu);
		
		
		//转化成JsonString
		String opts = createJsonString("students",students);
		out.print(opts);
		out.flush();
		out.close();
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("findstudentsbyseid.do  "+opts);
	}
	/**
	 * 
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
			@RequestParam("seId")String seId,@RequestParam("sId")String sId,
			@RequestParam("positivity")String positivity,
			@RequestParam("communicate")String communicate,
			@RequestParam("keypoint")String keypoint,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 需要实现
		 * 在这里实现自己的代码，调用service层submit教师评价信息
		 * 
		 */
		
		//忽略该行，system.out用于测试，实际编码中不需要实现
		System.out.println("teacherevaluatesubmit.do  "+seId +sId+positivity+communicate+keypoint);
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
		List<Evaluationelement> evaluationelements = evaluationElementServiceImpl.listCourseFiveEvaluationElements(course.getCid());
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
