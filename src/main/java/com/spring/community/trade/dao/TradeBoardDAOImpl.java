package com.spring.community.trade.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.file.vo.FileVO;
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
		
		List<TradeVO> list = sqlSession.selectList("mapper.trade.selectAllTrades", map);
		
		return list;
	}
	
	@Override
	public int getTradeCount() {
		int count = sqlSession.selectOne("mapper.trade.getTradeCount");
		
		return count;
	}
	
	@Override
	public int regTradeBoard(Map map) throws DataAccessException {
		System.out.println("DAO까지 옴");
		sqlSession.insert("mapper.trade.insertTrade", map);
		int tradeNo = getNewTradeNo();
//		System.out.println("regTradeBoard에서 호출한 tradeNo" + tradeNo);
		return tradeNo;
	}
	
	@Override
	public void regTradeFile(FileVO vo) throws DataAccessException {
		System.out.println("DAO fileName: " + vo.getFileNames());
		List<String> fileNames = vo.getFileNames();
		int trade_no = vo.getTrade_no();
		
		for(String fileName : fileNames) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("fileName", fileName);
			param.put("trade_no", trade_no);
//			System.out.println("param.fileName: " + param.get("fileName"));
//			System.out.println("param.trade_no: " + param.get("trade_no"));
			sqlSession.insert("mapper.file.insertTradeFile", param);
		}
		
	}
	
	private int getNewTradeNo() throws DataAccessException {
		int tradeNo = sqlSession.selectOne("mapper.trade.getNewTradeNo");
//		System.out.println("getNewTradeNo에서 호출한 tradeNo" + tradeNo);
		return tradeNo+1;
	}
}
