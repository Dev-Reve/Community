package com.spring.community.tradeComment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeComment.vo.TradeCommentVO;

public interface TradeCommentController {
	//댓글 조회하는 메소드
	public ModelAndView commentList(int no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//댓글 등록하는 메소드
	public void regComment(TradeCommentVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
