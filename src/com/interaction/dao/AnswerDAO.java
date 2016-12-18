package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Answer;

public interface AnswerDAO  {

	public int addAnswer(Answer answer);

	public List<Answer> listByQid(Integer qid);

	public int deleteAnswer(Answer answer);

	public int updateAnswer(Answer answer);
}