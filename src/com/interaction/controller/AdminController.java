package com.interaction.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interaction.pojo.Admin;
import com.interaction.pojo.Teacher;
import com.interaction.service.AdminService;
import com.interaction.service.FunctioncomponentService;
import com.interaction.service.MenuService;
import com.interaction.service.TeacherService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.FunctioncomponentVo;
import com.interaction.vo.MenuVo;

@Controller
public class AdminController {
	
	@Resource
	private AdminService adminServiceImpl;
	@Resource
	private TeacherService teacherServiceImpl;
	@Resource
	private FunctioncomponentService functioncomponentServiceImpl; 
	@Resource
	private MenuService menuServiceImpl;
	
	//添加菜单
	@RequestMapping("/addMenu")
	public String addMenu(MenuVo menuVo){
		menuServiceImpl.addMenu(menuVo);
		return adminListMenu();
	}
	
	//添加菜单前查询已有的菜单作为父级菜单备选
	@RequestMapping("/adminBeforeAddMenu")
	public String adminBeforeAddMenu(){
		List<MenuVo> menuVos = menuServiceImpl.listAllMenu();
		SessionUtil.getMySession().setAttribute("menuVos", menuVos);
		return "../adminBackground/menu/addMenu";
	}
	
	//管理员查看菜单
	@RequestMapping("/adminListMenu")
	public String adminListMenu(){
		List<MenuVo> menuVos = menuServiceImpl.listAllMenu();
		SessionUtil.getMySession().setAttribute("menuVos", menuVos);
		return "../adminBackground/menu/listMenu";
	}
	
	//管理员查看组件列表
	@RequestMapping("/adminListComponent")
	public String adminListComponent(){
		List<FunctioncomponentVo> functioncomponents = functioncomponentServiceImpl.listAllFunctioncomponent();
		SessionUtil.getMySession().setAttribute("functioncomponents", functioncomponents);
		return "../adminBackground/component/listComponent";
	}
	
	//管理员查看教师列表
	@RequestMapping("/adminListDeveloper")
	public String adminListDeveloper(){
		List<Admin> develpoers = adminServiceImpl.listAllDeveloper();
		SessionUtil.getMySession().setAttribute("develpoers", develpoers);
		return "../adminBackground/developer/listDeveloper";
	}
	
	//管理员查看教师列表
	@RequestMapping("/adminListTeacher")
	public String adminListTeacher(){
		List<Teacher> teachers = teacherServiceImpl.listAllTeachers();
		SessionUtil.getMySession().setAttribute("teachers", teachers);
		return "../adminBackground/teacher/listTeacher";
	}
	
	//系统管理员或构件开发人员登录
	@RequestMapping("/systemLoginCheck")
	public void systemLoginCheck(String aaccount, String apwd,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
				
		Admin admin = adminServiceImpl.login(aaccount);
		String msg = "fail";
		
		if(admin != null){
			if(admin.getApwd().equals(apwd)){
				SessionUtil.getMySession().setAttribute("admin", admin);
				if (admin.getAtype() == 0) {
					msg = "success";
				}else{
					msg = "success2";
				}
			}else{
				msg = "wrong";
			}		
		}
		response.getWriter().print(msg);
	}

	//退出登录
	@RequestMapping("/adminLogout")
	public String adminLogout(HttpServletRequest request){
		Object object = request.getSession().getAttribute("admin");
		  if (object != null) {
		   try {
		    request.getSession().removeAttribute("admin");
		   } catch (Exception e) {
		    object = null;
		   }
		  }
		return "../systemLogin";
	}
}
