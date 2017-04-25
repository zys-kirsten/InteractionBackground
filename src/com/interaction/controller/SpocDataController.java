package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.json.GsonUtil;
import com.interaction.pojo.Class;
import com.interaction.pojo.Course;
import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;
import com.interaction.pojo.Student;
import com.interaction.service.CourseService;
import com.interaction.service.SeminarService;
import com.interaction.service.SpocdataService;
import com.interaction.service.StudentService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.SeminarVo;

import net.sf.json.JSON;

@Controller
public class SpocDataController {
	
	@Resource
	private SeminarService seminarServiceImpl;
	@Resource
	private SpocdataService spocdataServiceImpl;
	@Resource
	private StudentService studentServiceImpl;;

	//导入数据前选择对应的研讨课
	@RequestMapping("/listSpocClass")
	public String listSpocClass(@RequestParam("cid")Integer cid,String go){
		System.out.println("go="+go);
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(cid);
		if(go.equals("spocData/showSpocData")){
			List<Student> students = studentServiceImpl.listStudentByCid(cid);
			SessionUtil.getMySession().setAttribute("students", students);
		}
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return go;
	}
	
	//导入spoc上的学生学习数据
	@RequestMapping("/importSpocScore")
	public String importStuScore(@RequestParam Integer seId,@RequestParam MultipartFile file) throws IOException{
		List<Spocscore> spocscores = spocdataServiceImpl.importSpocscore(file.getInputStream()); 

		if (spocscores != null && spocscores.size() != 0) {
			int result = spocdataServiceImpl.insertSpocscore(spocscores,seId);
			if (result != -1) {
				return "success";
			}
		}
		
//		for (Spocscore spocscore:spocscores) {
//			System.out.println(spocscore);
//		}
        return "error";
	}
	
	//导入spoc上的学生讨论区数据
	@RequestMapping("/importSpocDiscuss")
	public String importSpocDiscuss(@RequestParam MultipartFile file) throws IOException{
		List<Spocdiscuss> spocdiscusses = spocdataServiceImpl.importSpocdiscuss(file.getInputStream()); 

		if (spocdiscusses != null && spocdiscusses.size() != 0) {
			Course course = getCourse();
			int result = spocdataServiceImpl.insertSpocdiscuss(spocdiscusses,course.getCid());
			if (result != -1) {
				return "success";
			}
		}		
//		for (Spocdiscuss s:spocdiscusses) {
//			System.out.println(s);
//		}
        return "error";
	}
	
	
	//生成图表
	@RequestMapping("/generateGraph")
	public  void generateGraph(HttpServletResponse response,Integer seId) throws Exception { 
		String optionStr = null;
	    try {  
	        optionStr= GsonUtil.format(spocdataServiceImpl.grenateGraph(seId));
	    } catch (Exception e) {  
	    }  
	   response.getWriter().print(optionStr);  
	}  
	
	//生成讨论区图表
	@RequestMapping(value = "/generateDiscussGraph",produces="text/html;charset=UTF-8")
	public void generateDiscussGraph(HttpServletResponse response,Integer sid) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String optionStr = null;
	    try {  
	        optionStr= GsonUtil.format(spocdataServiceImpl.generateDiscussGraph(getCourse().getCid(),sid));
	        System.out.println(optionStr);
	    } catch (Exception e) {  
	    }  
	   response.getWriter().print(optionStr);  
	}
	
	private Course getCourse(){
		return (Course) SessionUtil.getMySession().getAttribute("course");
	}
}
