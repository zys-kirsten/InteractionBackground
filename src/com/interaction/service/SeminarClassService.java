package com.interaction.service;

import java.util.List;

import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;

public interface SeminarClassService {

	//列出某一研讨课的选课同学名单
	public List<SeminarClassVo> listOneSeminarClass(Integer seid); 

	//列出某一门课的所有研讨课的选课名单
	public List<List<SeminarClassVo>> listAllSeminarClass(Integer cid);

	//列车该门研讨课已经登录的学生
	public List<SeminarClassVo> listLoginStudents(Integer seId);

	//对已登录的学生进行分组
	public List<GroupVo> divideGroup(int seId);

	//教师确认分组结果
	public void confirmGroup(int seId);
}
