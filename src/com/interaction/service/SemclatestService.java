package com.interaction.service;

public interface SemclatestService {

	//学生提交课堂限时测试题
	public int submitSemclatest(int cid, int seid, int sid, int qid, int aid);
}
