package com.spring.community.tradeComment.service;

import java.util.List;

import com.spring.community.tradeComment.vo.TradeCommentVO;

public interface TradeCommentService {
	//댓글 조회해오는 메소드
	public List<TradeCommentVO> getCommentList(int no) throws Exception;

}
