package com.spring.community.main.mainService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.DAO.MainDaoImpl;
import com.spring.community.trade.vo.TradeVO;

@Service
public class mainServiceImpl implements mainService {
	
	
	@Autowired
	MainDaoImpl MainDao;
	
	 @Override
	public List<TradeVO> getRecentTradeList() {
		return MainDao.getRecentTradeList();
	}
	 
	 @Override
	public List<GalleryVO> getRecentGarallyList() {
		return MainDao.getRecentGarallyList();
	
	}
	
}
