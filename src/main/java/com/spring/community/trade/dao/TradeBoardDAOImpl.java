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
		List<TradeVO> list = null;
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		list = sqlSession.selectList("mapper.trade.selectAllTrades", map);
		
		return list;
	}
	
	@Override
	public int getTradeCount() {
		if (sqlSession == null) {
	        // sqlSession이 null인 경우에 대한 처리
	        return 21; // 또는 다른 적절한 값 또는 예외 처리
	    }
		int count = sqlSession.selectOne("mapper.trade.getTradeCount");
		System.out.println("DAO: " + count);
		
		return count;
	}
}
