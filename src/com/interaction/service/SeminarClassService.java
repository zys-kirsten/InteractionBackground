package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Seminarclass;
import com.interaction.vo.GroupNumsVo;
import com.interaction.vo.GroupVo;
import com.interaction.vo.SeminarClassVo;
import com.interaction.vo.SeminarscoreVo;
import com.interaction.vo.SeminarStudentNo;

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

	//查询当前选择研讨课的情况
	public List<SeminarStudentNo> listCurrentSelectSeminarStuNumber(int cid);

	//学生选课
	public int stuSelectSeminar(int cid, int seid, int sid);

	//列出所有已经分好的组
	public List<GroupVo> listGroup(int seid);

	//找到某个学生自己的分组
	public Integer findMyGroupNum(Integer seid,int sid);

	//查找除了自己组以外的其他组的组号
	public List<GroupNumsVo> listOtherGroupNums(int seid, int groupNum);

	//根据组号查找属于该组的同学
	public List<Seminarclass> listByGroupNum(int seid, int groupNum);

	//列出学生id为sid的那一组的其他同学
	public List<SeminarClassVo> listMyGroupOtherStu(int seid, int sid, int groupNum);

	//学生查询自己研讨课成绩
	public SeminarscoreVo stuFindMySeminarScore(int seid, int sid);

}
