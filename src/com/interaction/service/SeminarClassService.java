package com.interaction.service;

import java.util.List;

import com.interaction.vo.SeminarClassVo;

public interface SeminarClassService {

	//列出某一研讨课的选课同学名单
	public List<SeminarClassVo> listOneSeminarClass(Integer seid); 

	//列出某一门课的所有研讨课的选课名单
	public List<List<SeminarClassVo>> listAllSeminarClass(Integer cid);
}
