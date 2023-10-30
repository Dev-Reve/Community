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
	public List listTradeBoards() throws Exception {
		List list = dao.selectAllTrades(); 
		
		return list;
	}
	
	
}
