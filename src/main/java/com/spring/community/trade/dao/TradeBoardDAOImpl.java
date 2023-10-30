package com.spring.community.trade.dao;

import java.util.List;

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
	public List selectAllTrades() throws DataAccessException {
		List<TradeVO> list = null;
		
		list = sqlSession.selectList("mapper.trade.selectAllTrades");
		
		return list;
	}
}
