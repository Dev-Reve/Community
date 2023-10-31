package com.spring.community.trade.dao;

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
	public List selectAllTrades(int startRow, int endRow) throws DataAccessException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		System.out.println("dao startRow: " + startRow);
		System.out.println("dao endRow: " + endRow);
		
		List<TradeVO> list = sqlSession.selectList("mapper.trade.selectAllTrades", map);
		
		return list;
	}
	
	@Override
	public int getTradeCount() {
		int count = sqlSession.selectOne("mapper.trade.getTradeCount");
		System.out.println("DAO: " + count);
		
		return count;
	}
	
	@Override
	public void regTradeBoard(TradeVO vo) throws DataAccessException {
		sqlSession.insert("mapper.trade.insertTrade", vo);
	}
}
