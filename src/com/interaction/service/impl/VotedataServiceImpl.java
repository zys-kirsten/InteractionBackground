package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.VotedataDAO;
import com.interaction.pojo.Votedata;
import com.interaction.service.VotedataService;
import com.interaction.vo.VotedataVo;

@Service
public class VotedataServiceImpl implements VotedataService{
	@Resource
	private VotedataDAO votedataDAOImpl;

	//根据研讨课ID和投票问题的ID查找回答某一问题的所有学生，并按答案的不同进行分组
	@Override
	public List<VotedataVo> listCurrentVotedataBySeidAndVqid(int seid, int vqid) {
		List<Votedata> ltvp = votedataDAOImpl.listCurrentVotedataBySeidAndVqid(seid,vqid);
		return p2v(ltvp);
	}

	private List<VotedataVo> p2v(List<Votedata> ltvp) {
		List<VotedataVo> result = new ArrayList<VotedataVo>();
		int numA = 0;
		int numB = 0;
		int numC = 0;
		int numD = 0;
		
		if (ltvp != null && ltvp.size()!=0) {
			for (Votedata votedata : ltvp) {
				if (votedata.getStuAnswer().equals("A")) {
					numA++;
				}else if (votedata.getStuAnswer().equals("B")) {
					numB++;
				}else if (votedata.getStuAnswer().equals("C")) {
					numC++;
				}else {
					numD++;
				}
			}
			VotedataVo vo = new VotedataVo();
			vo.setStuAnswer("A");
			vo.setStuNum(numA);
			result.add(vo);
			
			vo = new VotedataVo();
			vo.setStuAnswer("B");
			vo.setStuNum(numB);
			result.add(vo);
			
			vo = new VotedataVo();
			vo.setStuAnswer("C");
			vo.setStuNum(numC);
			result.add(vo);
			
			vo = new VotedataVo();
			vo.setStuAnswer("D");
			vo.setStuNum(numD);
			result.add(vo);
		}
		return result;
	}

}
