package com.spring.community.trade.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.vo.TradeVO;

@Repository("tradeDAO")
public class TradeBoardDAOImpl implements TradeBoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List selectAllTrades(Map map) throws DataAccessException {
		System.out.println(map.get("startRow"));
		System.out.println(map.get("endRow"));
		
		List<TradeVO> list = sqlSession.selectList("mapper.trade.selectAllTrades", map);
		
		return list;
	}
	
	@Override
	public int getTradeCount(Map map2) {
		int count = sqlSession.selectOne("mapper.trade.getTradeCount", map2);
		
		return count;
	}
	
	@Override
	public int regTradeBoard(Map map) throws DataAccessException {
		System.out.println("DAO까지 옴");
		System.out.println("fileList: " + map.get("fileList"));
		
		sqlSession.insert("mapper.trade.insertTrade", map);
		
		int tradeNo = getNewTradeNo();
//		System.out.println("regTradeBoard에서 호출한 tradeNo" + tradeNo);
		return tradeNo;
	}
	
	
	private int getNewTradeNo() throws DataAccessException {
		int tradeNo = sqlSession.selectOne("mapper.trade.getNewTradeNo");
//		System.out.println("getNewTradeNo에서 호출한 tradeNo" + tradeNo);
		return tradeNo;
	}
	
	@Override
	public TradeVO selectTradeDetail(int no) throws DataAccessException {
		return sqlSession.selectOne("mapper.trade.selectTradeDetail", no);
	}
	
	@Override
	public void delTradeBoard(int no) throws DataAccessException {
		sqlSession.delete("mapper.trade.delTrade", no);
	}
	
	@Override
	public void modTradeBoard(Map map) throws DataAccessException {
		sqlSession.update("mapper.trade.updateTrade", map);
	}
	
}
