package com.interaction.service;

import java.util.List;

import com.interaction.pojo.Course;
import com.interaction.pojo.Seminar;
import com.interaction.vo.SeminarVo;

public interface SeminarService {
	public int addSeminar(SeminarVo seminarVo);
	public Seminar findById(Integer seid);
	public List<SeminarVo> listByCourse(Integer cid);
	public List<SeminarVo> listByTeacher(Integer tid);
	public List<List<SeminarVo>> listAllByTeacher(Integer tid);
	public List<Course> listHaveSeminarCourseByTeacher(Integer tid);
	public int deleteSeminarById(Integer seId);
	public int updateSeminar(SeminarVo seminarVo);
	public Seminar findBySeName(Integer cid, String string);
	public void executeCourseSelect(int parseInt, String string);
	public List<SeminarVo> listByCidAndSid(int cid, int sid);
	public List<List<SeminarVo>> listSelectSeminar(int cid);

}
