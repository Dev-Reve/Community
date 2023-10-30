package com.spring.community.trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.trade.dao.TradeBoardDAO;

@Service("tradeService")
public class TradeBoardServiceImpl implements TradeBoardService {
	
	@Autowired
	TradeBoardDAO dao;
	
	@Override
	public List listTradeBoards(int startRow, int endRow) throws Exception {
		List list = dao.selectAllTrades(startRow, endRow); 
		
		return list;
	}
	
	@Override
	public int getTotalPosts() {
		return dao.getTradeCount();
	}
	
}
