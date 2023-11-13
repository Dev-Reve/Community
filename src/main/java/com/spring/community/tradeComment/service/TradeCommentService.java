package com.spring.community.tradeComment.service;

import java.util.List;

import com.spring.community.tradeComment.vo.TradeCommentVO;

public interface TradeCommentService {
	//댓글 조회해오는 메소드
	public List<TradeCommentVO> getCommentList(int no) throws Exception;
	
	//댓글을 등록하는 메소드
	public void regComment(TradeCommentVO comment) throws Exception;

	//댓글을 삭제하는 메소드
	public void delComment(int no) throws Exception;

	//댓글을 수정하는 메소드
	public TradeCommentVO modComment(TradeCommentVO commentVO);

}
