package com.spring.community.controller.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.mainService.mainServiceImpl;
import com.spring.community.trade.vo.TradeVO;



@Controller
public class Main {
	
	@Autowired
	private mainServiceImpl mainservice;
	List<TradeVO> ReTradelist = new ArrayList<TradeVO>();
	List<GalleryVO> ReGallerylist = new ArrayList<GalleryVO>();
	
	@RequestMapping(value = "/main/first.do", method = RequestMethod.GET)
	public ModelAndView main() {
		
		System.out.println("index호출");
		
		
		ReTradelist = mainservice.getRecentTradeList();
		
		ReGallerylist = mainservice.getRecentGarallyList();
		
		
		
		ModelAndView mav = new ModelAndView();
		
		//받아온 VO객체들을 저장하여 보냄
		mav.addObject("ReT", ReTradelist);
		mav.addObject("ReG", ReGallerylist);
		mav.addObject("center", "/WEB-INF/views/common/First.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@RequestMapping(value = "/main/index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		
		System.out.println("index호출");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/common/First.jsp");
		mav.setViewName("main");
			
		return mav;
	}
	@RequestMapping(value = "/main/test.do", method = RequestMethod.GET)
	public ModelAndView test() {
		
		System.out.println("index호출");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/test/write.jsp");
		mav.setViewName("main");
			
		return mav;
	}
}