package com.spring.community.tradeComment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.controller.TradeBoardControllerImpl;
import com.spring.community.trade.service.TradeBoardService;
import com.spring.community.trade.vo.TradeVO;
import com.spring.community.tradeComment.service.TradeCommentService;
import com.spring.community.tradeComment.vo.TradeCommentVO;

@Controller
public class TradeCommentControllerImpl implements TradeCommentController {
	//1. LoggerFactory클래스를 이용하여 LogeerClass의 객체를 가져옴
	private static final Logger logger = LoggerFactory.getLogger(TradeCommentControllerImpl.class);
	
	@Autowired
	private TradeCommentService service;
	
	@Autowired
	private TradeCommentVO vo;
	
	@Override
	@RequestMapping(value = "/tradeComment/commentList.do")
	public ModelAndView commentList(@RequestParam("no") int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("여기까지옴");
		List<TradeCommentVO> list = service.getCommentList(no);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav = new ModelAndView("redirect:/trade/tradeDetail.do");
		
		
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/tradeComment/regComment")
	public void regComment(@RequestAttribute("vo") TradeCommentVO vo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		
	}
	
	
	//request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구하는 메소드 
	private String getViewName(HttpServletRequest request) throws Exception {

		String contextPath = request.getContextPath();
		
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;

		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;

		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);

		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}

		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;

	}

}
