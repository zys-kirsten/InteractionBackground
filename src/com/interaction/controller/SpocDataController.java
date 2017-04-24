package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.interaction.pojo.Course;
import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;
import com.interaction.service.SeminarService;
import com.interaction.service.SpocdataService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.SeminarVo;

@Controller
public class SpocDataController {
	
	@Resource
	private SeminarService seminarServiceImpl;
	@Resource
	private SpocdataService spocdataServiceImpl;

	//导入数据前选择对应的研讨课
	@RequestMapping("/listSpocClass")
	public String listSpocClass(@RequestParam("cid")Integer cid){
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "spocData/importSpocData";
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
	
	private Course getCourse(){
		return (Course) SessionUtil.getMySession().getAttribute("course");
	}
}
