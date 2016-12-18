package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Question;

public interface QuestionDAO {

	public int addQuestion(Question question);

	public Question findById(Integer qid);

	public List<Question> listByChapter(Integer cid, Integer chapter);

	public List<Question> listByCourse(Integer cid);

	public int deleteQuestion(Question question);

	public int updateQuestion(Question question);

	public List<Question> listByContent(Integer cid, String inputValue);
	
}