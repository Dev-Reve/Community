package com.spring.community.controller.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.mainService.mainServiceImpl;
import com.spring.community.trade.vo.TradeVO;



@Controller
public class Main {
		
	@RequestMapping(value = "/main/first.do", method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(value="ReGallerylist") List<GalleryVO> ReGallerylist,
							 @RequestParam(value="ReTradelist") List<TradeVO> ReTradelist
							) {
		
		System.out.println("index호출");
		
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
		mav.addObject("center", "/WEB-INF/views/t/write.jsp");
		mav.setViewName("main");
			
		return mav;
	}
}