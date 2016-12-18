package com.interaction.service;

import java.util.List;

import com.interaction.vo.QuestionVo;

public interface QuestionService {
	//添加问题
	public int addQuestion(QuestionVo questionVo);
    //列出某一门课某一章节的问题
	public List<QuestionVo> listQuestionByChapter(Integer cid, Integer chapter);
    //列出某一门课的所有问题
	public List<QuestionVo> listQuestionByCourse(Integer cid);
	public int deleteQuestionById(Integer qid);
	public QuestionVo findById(Integer qid);
	public int updateQuestion(QuestionVo questionVo);
	public List<QuestionVo> findByCondition(Integer cid, String condition, String inputValue);

}
