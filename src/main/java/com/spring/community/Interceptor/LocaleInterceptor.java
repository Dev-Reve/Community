package com.spring.community.Interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.mainService.mainServiceImpl;
import com.spring.community.trade.vo.TradeVO;

public class LocaleInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private mainServiceImpl mainService;
	
	List<TradeVO> ReTradelist = new ArrayList<TradeVO>();
	List<GalleryVO> ReGallerylist = new ArrayList<GalleryVO>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 ReGallerylist = mainService.getRecentGarallyList();
		 ReTradelist  = mainService.getRecentTradeList();
		 
		 request.setAttribute("RecentGall", ReGallerylist);
		 request.setAttribute("RecentTrade", ReTradelist);
		 
		return true;
	}
	
	
	
	
	
	
}
