package com.spring.community.tradeComment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.community.tradeComment.vo.TradeCommentVO;

public interface TradeCommentDAO {

	public List<TradeCommentVO> getCommentList(int no) throws DataAccessException;

	public void regComment(TradeCommentVO comment) throws DataAccessException;

	public void delComment(int no) throws DataAccessException;

	public TradeCommentVO modComment(TradeCommentVO commentVO) throws DataAccessException;

}