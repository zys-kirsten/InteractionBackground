package com.interaction.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Admin;
import com.interaction.pojo.Menu;
import com.interaction.pojo.Teacher;
import com.interaction.service.FunctioncomponentService;
import com.interaction.service.MenuService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.FunctioncomponentVo;
import com.interaction.vo.MenuVo;

@Controller
public class ComponentController {
	
	@Resource
	private FunctioncomponentService functioncomponentServiceImpl;
	@Resource
	private MenuService menuServiceImpl;
	
	
	//教师配置新的功能
	@RequestMapping("/addTeacherComponent")
	public String addTeacherComponent(){
		Teacher teacher = getTeacher();
		String[] checkChoose = SessionUtil.getRequest().getParameterValues("choose");
		if (checkChoose == null) {
			return "error";
		}
		functioncomponentServiceImpl.addTeacherFunctionComponent(teacher,checkChoose);
		return teacherListComponent(teacher.getTid());
	}
	
	//教师配置新功能前先查询自己没有定制的功能构件
	@RequestMapping("/teacherListComponentBeforeConfig")
	public String teacherListComponentBeforeConfig(){
		Teacher teacher = getTeacher();
		List<FunctioncomponentVo> functioncomponentVos = functioncomponentServiceImpl.listFunctioncomponentExceptTid(teacher.getTid());
		SessionUtil.getMySession().setAttribute("functioncomponentVos", functioncomponentVos);
		return "/functionConfig/listTeacherConfigComponent";
	}
	
	//教师移除自己已经选定的某项功能
	@RequestMapping("/teacherDeleteComponent")
	public String teacherDeleteComponent(@RequestParam("fcid")Integer fcid){
		Teacher teacher = getTeacher();
		functioncomponentServiceImpl.removeComponentByTidAndFcid(teacher.getTid(),fcid);
		return teacherListComponent(teacher.getTid());
	}
	
	//列出某一教师已配置的功能构件
	@RequestMapping("/teacherListComponent")
	public String teacherListComponent(@RequestParam("tid")Integer tid){
		List<FunctioncomponentVo> functioncomponentVos = functioncomponentServiceImpl.listByTid(tid);
		SessionUtil.getMySession().setAttribute("functioncomponentVos", functioncomponentVos);
		return "/functionConfig/listTeacherComponent";
	}
	
	//列出所有某一开发者开发的构件
	@RequestMapping("/listMyComponent")
	public String listComponent(@RequestParam("aid")Integer aid){
		List<FunctioncomponentVo> functioncomponentVos = functioncomponentServiceImpl.listFunctioncomponentByAid(aid);
		SessionUtil.getMySession().setAttribute("functioncomponentVos", functioncomponentVos);
		return "../adminBackground/component/listMyComponent";
	}

	//构建添加前的显示菜单
	@RequestMapping("/beforeAddComponent")
	public String beforeAddComponent(){
		List<MenuVo> menus = menuServiceImpl.listAllMenu();
		SessionUtil.getMySession().setAttribute("menus", menus);
		return "../adminBackground/component/addComponent";
	}
	
	//添加(修改)构件
	@RequestMapping("/addComponent")
	public String addComponent(FunctioncomponentVo functioncomponentVo){
		Admin admin = getAdmin();
		functioncomponentVo.setAid(admin.getAid());
		if (functioncomponentVo.getFcid() == null) {
			int id = functioncomponentServiceImpl.addFunctioncomponent(functioncomponentVo);
		}else {
			System.out.println(111);
			int id = functioncomponentServiceImpl.updateFunctioncomponent(functioncomponentVo);
		}
		
		return listComponent(admin.getAid());
	}
	
	//修改构件前回显
	@RequestMapping("/developerEditComponent")
	public String develpoerEditComponent(@RequestParam("fcid")Integer fcid){
		FunctioncomponentVo functioncomponentVo = functioncomponentServiceImpl.findByFcid(fcid);
		List<MenuVo> menus = menuServiceImpl.listAllMenu();
		
		SessionUtil.getMySession().setAttribute("functioncomponentVo", functioncomponentVo);
		SessionUtil.getMySession().setAttribute("menus", menus);
		return "../adminBackground/component/editComponent";
	}
	
	
	//删除构件
	@RequestMapping("/developerDeleteComponent")
    public String develpoerDeleteComponent(@RequestParam("fcid") Integer fcid){
		functioncomponentServiceImpl.deleteByFcid(fcid);
		return listComponent(getAdmin().getAid());
	}
	
	
	private Admin getAdmin(){
		return (Admin)SessionUtil.getMySession().getAttribute("admin");
	}
	
	private Teacher getTeacher(){
		return (Teacher)SessionUtil.getMySession().getAttribute("teacher");
	}
	
}
