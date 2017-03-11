package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Menu;
import com.interaction.pojo.Teacher;
import com.interaction.vo.ComponentVo;
import com.interaction.vo.FunctioncomponentVo;

public interface FunctioncomponentService {

	List<FunctioncomponentVo> listAllFunctioncomponent();

	List<FunctioncomponentVo> listFunctioncomponentByAid(Integer aid);

	int addFunctioncomponent(FunctioncomponentVo functioncomponentVo);

	List<FunctioncomponentVo> listByTid(Integer tid);

	void removeComponentByTidAndFcid(Integer tid, Integer fcid);

	List<FunctioncomponentVo> listFunctioncomponentExceptTid(Integer tid);

	int addTeacherFunctionComponent(Teacher teacher, String[] checkChoose);

	List<ComponentVo> listTeacherFunction(Integer tid);

}
