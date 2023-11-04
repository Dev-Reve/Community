package com.spring.community.tradeLike.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.service.TradeLikeService;
import com.spring.community.tradeLike.vo.TradeLikeVO;

@Controller
public class TradeLikeControllerImpl implements TradeLikeController {
	
	//1. LoggerFactory클래스를 이용하여 LogeerClass의 객체를 가져옴
	private static final Logger logger = LoggerFactory.getLogger(TradeLikeControllerImpl.class);
	
	@Autowired
	private TradeLikeService service;
	
	@Autowired
	private TradeLikeVO vo;
	
	@Override
	@RequestMapping(value = "/tradeLike/clickLike.do")
	public ModelAndView clickLike(@RequestParam("nickname") String nickname,@RequestParam("no") int  no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		int chk = 0;
		map.put("nickname", nickname);
		map.put("no", no);
		vo = service.getClickStat(map);
		ModelAndView mav = new ModelAndView();
		if(vo == null) {
			System.out.println("노값 > 예스값");
			service.regLike(map);
			chk = 1;
		} else {
			System.out.println("예스값 > 노값");
			service.delLike(map);
			chk = 0;
		}
		mav.addObject("chk", chk);
		
		mav.setViewName("redirect:/trade/tradeDetail.do?no=" + no);
		
		return mav;
	}
	
}
