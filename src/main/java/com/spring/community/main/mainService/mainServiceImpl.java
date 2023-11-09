package com.spring.community.main.mainService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.DAO.MainDao;
import com.spring.community.trade.vo.TradeVO;

@Service("mainService")
public class mainServiceImpl implements mainService {
	
	
	@Autowired
	MainDao mainDao;
	
	 @Override
	public List getRecentTradeList() {
		return mainDao.getRecentTradeList();
	}
	 
	 @Override
	public List getRecentGarallyList() {
		return mainDao.getRecentGarallyList();
	
	}
	
}
