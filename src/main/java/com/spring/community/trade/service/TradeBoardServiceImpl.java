package com.spring.community.trade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.community.trade.dao.TradeBoardDAO;
import com.spring.community.trade.dao.TradeBoardDAOImpl;
import com.spring.community.trade.vo.TradeVO;

@Service("tradeService")
public class TradeBoardServiceImpl implements TradeBoardService {
	
	@Autowired
	TradeBoardDAO dao;
	
	@Override
	public Map<String, Object> listTradeBoards(String pageNum) throws Exception {
		int pageSize = 10;
		int pageBlock = 5;
		int count = 0;
		int no = 0;
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		count = dao.getTradeCount();
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<TradeVO> vo = new ArrayList<TradeVO>(); 
		
		if(count > 0) {
			vo = dao.selectAllTrades((startRow-1), endRow);
			no = count - (currentPage - 1 ) * pageSize;
		}
		
		map.put("pageSize", pageSize);
		map.put("pageBlock", pageBlock);
		map.put("count", count);
		map.put("no", no);
		map.put("currentPage", currentPage);
		map.put("vo", vo);
		
		
		return map;
	}
	
	@Override
	public int getTotalPosts() {
		return dao.getTradeCount();
	}
	
	@Override
	public int regTradeBoard(Map map) throws Exception {
		System.out.println("Service까지 옴");
		int tradeNo = dao.regTradeBoard(map);
//		System.out.println("service에서 받아온 tradeNo: " + tradeNo);
		return tradeNo;
	}
	
	@Override
	public TradeVO viewTradeDetail(int no) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTradeDetail(no);
	}
	
}
