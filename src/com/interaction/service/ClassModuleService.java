package com.interaction.service;

import java.util.List;

import com.interaction.vo.ClassModuleVo;

public interface ClassModuleService {

	int addClassModule(ClassModuleVo classModuleVo);

	int updateClassModule(ClassModuleVo classModuleVo);

	List<ClassModuleVo> listByCourse(Integer cid);

	ClassModuleVo findById(Integer cmid);

	int deleteClassModuleById(Integer cmid);

	Integer listOneAttribute(int seid, String condition);

}
