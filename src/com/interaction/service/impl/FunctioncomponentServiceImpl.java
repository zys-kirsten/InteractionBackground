package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.AdminDAO;
import com.interaction.dao.FunctioncomponentDAO;
import com.interaction.dao.MenuDAO;
import com.interaction.dao.TeacherfunctionDAO;
import com.interaction.pojo.Admin;
import com.interaction.pojo.Functioncomponent;
import com.interaction.pojo.Menu;
import com.interaction.pojo.Teacher;
import com.interaction.pojo.Teacherfunction;
import com.interaction.service.FunctioncomponentService;
import com.interaction.vo.ComponentVo;
import com.interaction.vo.FunctioncomponentVo;

@Service
public class FunctioncomponentServiceImpl implements FunctioncomponentService{

	@Resource
	private FunctioncomponentDAO functioncomponentDAOImpl;
	@Resource
	private MenuDAO menuDAOImpl;
	@Resource
	private AdminDAO adminDAOImpl;
	@Resource
	private TeacherfunctionDAO teacherfunctionDAOImpl;
	
	//查找所有组件
	@Override
	public List<FunctioncomponentVo> listAllFunctioncomponent() {
		List<Functioncomponent> fcpo = functioncomponentDAOImpl.listAllFunctioncomponent();
		List<FunctioncomponentVo> fcvo = p2v(fcpo);
		return fcvo;
	}

	private List<FunctioncomponentVo> p2v(List<Functioncomponent> fcpo) {
		if (fcpo == null || fcpo.size() == 0) {
			return null;
		}
		
		List<FunctioncomponentVo> fcvos = new ArrayList<FunctioncomponentVo>();
		for(Functioncomponent fc:fcpo){
			if (fc != null) {
				FunctioncomponentVo fcvo = p2v(fc);
				fcvos.add(fcvo);
			}
		}
		return fcvos;
	}

	private FunctioncomponentVo p2v(Functioncomponent fc) {
		if (fc == null) {
			return null;
		}
		
		FunctioncomponentVo fcvo = new FunctioncomponentVo();
		Admin admin = adminDAOImpl.findById(fc.getAdmin().getAid());
		if (admin != null) {
			fcvo.setAid(admin.getAid());
			fcvo.setAname(admin.getAname());
		}
		Menu menu = menuDAOImpl.findById(fc.getMenu().getMid());
		if (menu != null) {
			fcvo.setMid(menu.getMid());
			fcvo.setMname(menu.getMname());
		}
		
		fcvo.setFcname(fc.getFcname());
		fcvo.setDescription(fc.getDescription());
		fcvo.setFcid(fc.getFcid());
		fcvo.setState(fc.getState());
		fcvo.setType(fc.getType());
		fcvo.setUrl(fc.getUrl());
		return fcvo;
	}
	
	private Functioncomponent v2p(FunctioncomponentVo functioncomponentVo) {

		if (functioncomponentVo == null) {
			return null;
		}
		Functioncomponent fcpo = new Functioncomponent();
		fcpo.setFcid(functioncomponentVo.getFcid());
		fcpo.setAdmin(adminDAOImpl.findById(functioncomponentVo.getAid()));
		fcpo.setDescription(functioncomponentVo.getDescription());
		fcpo.setFcname(functioncomponentVo.getFcname());
		fcpo.setMenu(menuDAOImpl.findById(functioncomponentVo.getMid()));
		fcpo.setState(0);
		fcpo.setType(functioncomponentVo.getType());
		fcpo.setUrl(functioncomponentVo.getUrl());
		return fcpo;
	}


	//查看某一个开发人员的开发构件
	@Override
	public List<FunctioncomponentVo> listFunctioncomponentByAid(Integer aid) {
		List<Functioncomponent> fcpo = functioncomponentDAOImpl.listFunctioncomponentByAid(aid);
		List<FunctioncomponentVo> fcvo = p2v(fcpo);
		return fcvo;
	}

	//添加组件
	@Override
	public int addFunctioncomponent(FunctioncomponentVo functioncomponentVo) {
		Functioncomponent functioncomponent = v2p(functioncomponentVo);

		if (functioncomponent != null) {
			return functioncomponentDAOImpl.addFunctioncomponent(functioncomponent);
		}
		return -1;
	}

	//根据教师id列出该教师的所有已有功能构件
	@Override
	public List<FunctioncomponentVo> listByTid(Integer tid) {
		List<Teacherfunction> tfs = teacherfunctionDAOImpl.listByTid(tid);
		if (tfs == null || tfs.size() == 0) {
			return null;
		}
		List<FunctioncomponentVo> fcvos = new ArrayList<FunctioncomponentVo>();
		
		for(Teacherfunction tf:tfs){
			FunctioncomponentVo fcvo = p2v(functioncomponentDAOImpl.findById(tf.getFunctioncomponent().getFcid()));
			if (fcvo != null) {
				fcvos.add(fcvo);
			}
		}
		return fcvos;
	}

	//移除某个教师的某一功能构件
	@Override
	public void removeComponentByTidAndFcid(Integer tid, Integer fcid) {

		Teacherfunction teacherfunction = teacherfunctionDAOImpl.listByTidAndFcid(tid,fcid);
		if (teacherfunction != null) {
			teacherfunctionDAOImpl.deleteTeacherfunction(teacherfunction);
		}
	}

	//教师选择功能前的构件列表（不包括教师已经有的功能构件）
	@Override
	public List<FunctioncomponentVo> listFunctioncomponentExceptTid(Integer tid) {
		List<Functioncomponent> fcpos = functioncomponentDAOImpl.listStartFunctioncomponent();
		if (fcpos == null || fcpos.size() == 0) {
			return null;
		}
		
		List<Teacherfunction> tfs = teacherfunctionDAOImpl.listByTid(tid);
		if (tfs == null || tfs.size() == 0) {
			return p2v(fcpos);
		}
		
		List<FunctioncomponentVo> fcvos = new ArrayList<FunctioncomponentVo>();
		boolean flag = false;
		for(Functioncomponent fc:fcpos){
			for(Teacherfunction tf:tfs){
				if (fc.getFcid() == tf.getFunctioncomponent().getFcid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				fcvos.add(p2v(fc));
			}else{
				flag = false;
			}
		}
		return fcvos;
	}
	
	
	//教师配置新的功能构件
	@Override
	public int addTeacherFunctionComponent(Teacher teacher, String[] checkChoose) {

		Teacherfunction teacherfunction = new Teacherfunction();
		if (teacher == null ) {
			return -1;
		}
		
		teacherfunction.setTeacher(teacher);
		for (int i = 0; i < checkChoose.length; i++) {
			Functioncomponent functioncomponent = functioncomponentDAOImpl.findById(Integer.parseInt(checkChoose[i]));
			if (functioncomponent == null) {
				return -1;
			}
			teacherfunction.setFunctioncomponent(functioncomponent);
			int result = teacherfunctionDAOImpl.addTeacherFunction(teacherfunction);
			if (result == -1) {
				return -1;
			}
		}
		return 1;
	}

	//教师登录时，页面加载教师配置的功能
	@Override
	public List<ComponentVo> listTeacherFunction(Integer tid) {
		List<Teacherfunction> tfs = teacherfunctionDAOImpl.listByTid(tid);
		
		List<FunctioncomponentVo> fcvos = listByTid(tid);
		if (fcvos == null || fcvos.size() == 0) {
			return null;
		}
		
		List<ComponentVo> componentVos = fv2cv(fcvos);
		return componentVos;
	}

	//工具方法：将functioncomponentvo转换成componentvo
	private List<ComponentVo> fv2cv(List<FunctioncomponentVo> fcvos) {
		List<ComponentVo> cvos = new ArrayList<ComponentVo>();
		
		for (int i = 0; i < fcvos.size(); i++) {
			if (!fcvos.get(i).getFlag()) {
				ComponentVo componentVo = new ComponentVo();
				String mname = fcvos.get(i).getMname();
				List<FunctioncomponentVo> temp = new ArrayList<FunctioncomponentVo>();
				for (int j = i; j < fcvos.size(); j++) {
					if (fcvos.get(j).getFlag() == false && fcvos.get(j).getMname().equals(mname)) {
						temp.add(fcvos.get(j));
						fcvos.get(j).setFlag(true);
					}
				}
				componentVo.setMid(fcvos.get(i).getMid());
				componentVo.setMname(mname);
				componentVo.setFunctioncomponentVos(temp);
				cvos.add(componentVo);
			}
		}
		return cvos;
	}
	//根据ID删除
	@Override
	public void deleteByFcid(Integer fcid) {

		Functioncomponent functioncomponent = functioncomponentDAOImpl.findById(fcid);
		if (functioncomponent == null) {
			return;
		}
		
		functioncomponentDAOImpl.deleteFunctioncomponent(functioncomponent);
	}
	
	//根据id查找
	@Override
	public FunctioncomponentVo findByFcid(Integer fcid) {
		Functioncomponent functioncomponent = functioncomponentDAOImpl.findById(fcid);
		return p2v(functioncomponent);
	}
	
	@Override
	public int updateFunctioncomponent(FunctioncomponentVo functioncomponentVo) {

		if (functioncomponentVo == null) {
			return -1;
		}
		
		return functioncomponentDAOImpl.updateFunctioncomponent(v2p(functioncomponentVo));
	}
	
	//管理员启用组件功能
	@Override
	public void startFunctioncomponent(Integer fcid) {

		Functioncomponent functioncomponent = functioncomponentDAOImpl.findById(fcid);

		if (functioncomponent == null) {
			return;
		}
		functioncomponent.setState(1);
		functioncomponentDAOImpl.updateFunctioncomponent(functioncomponent);
	}
}
