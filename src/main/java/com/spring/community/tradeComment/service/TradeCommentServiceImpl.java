package com.spring.community.tradeComment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.tradeComment.dao.TradeCommentDAO;
import com.spring.community.tradeComment.vo.TradeCommentVO;

@Service
public class TradeCommentServiceImpl implements TradeCommentService {
	@Autowired
	private TradeCommentVO vo;
	@Autowired
	private TradeCommentDAO dao;
	
	@Override
	public List<TradeCommentVO> getCommentList(int no) throws Exception {
		return dao.getCommentList(no);
	}
}
