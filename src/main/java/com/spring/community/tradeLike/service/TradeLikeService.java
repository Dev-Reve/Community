package com.spring.community.tradeLike.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.vo.TradeLikeVO;

public interface TradeLikeService {

	public TradeLikeVO getClickStat(Map map) throws Exception;

	public void regLike(Map map) throws Exception;

	public void delLike(Map map) throws Exception;
	
}
