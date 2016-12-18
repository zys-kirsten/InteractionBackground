package com.interaction.vo;

import java.util.List;

import com.interaction.pojo.Answer;


public class QuestionVo {

	private Integer qid;
	private Integer chapter;
	private String content;
	
	private Integer cid;
	private String cname;
	
	private List<Answer> answers ; //答案集合
	
	
	public QuestionVo(){}

	public QuestionVo(Integer qid, Integer chapter, String content,
			Integer cid,  List<Answer> answers) {
		super();
		this.qid = qid;
		this.chapter = chapter;
		this.content = content;
		this.cid = cid;
		this.answers = answers;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "QuestionVo [qid=" + qid + ", chapter=" + chapter + ", content=" + content + ", cid=" + cid + ", cname="
				+ cname + ", answers=" + answers + "]";
	}

	
}
