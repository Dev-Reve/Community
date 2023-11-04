package com.spring.community.tradeLike.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.dao.TradeLikeDAO;
import com.spring.community.tradeLike.vo.TradeLikeVO;

@Service
public class TradeLikeServiceImpl implements TradeLikeService {
	@Autowired
	TradeLikeDAO dao;
	
	@Autowired
	TradeLikeVO vo;
	
	@Override
	public TradeLikeVO getClickStat(Map map) throws Exception {
		return dao.getClickStat(map);
	}
	
	@Override
	public void regLike(Map map) {
		dao.regLike(map);
	}
	
	@Override
	public void delLike(Map map) throws Exception {
		dao.delLike(map);
	}
}
