package com.spring.community.trade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.service.TradeBoardService;
import com.spring.community.trade.vo.TradeVO;

@Controller("tradeController")
public class TradeBoardControllerImpl implements TradeBoardController {
	
	//1. LoggerFactory클래스를 이용하여 LogeerClass의 객체를 가져옴
	private static final Logger logger = LoggerFactory.getLogger(TradeBoardControllerImpl.class);
	
	@Autowired
	private TradeBoardService tradeSevrvice;
	
	@Autowired
	private TradeVO vo;
	
	@Override
	@RequestMapping(value = "/trade/tradeList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listTradeBoards(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int pageSize = 10;
		int startRow = (page - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		
		List tradeList = tradeSevrvice.listTradeBoards(startRow, endRow);
		
		int totalPosts = tradeSevrvice.getTotalPosts(); // 전체 게시물 수
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		//응답할 뷰 이름 얻기 
		String viewName = getViewName(request);
		
		//응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩
		ModelAndView mav = new ModelAndView();
		//응답할 값 저장
		mav.addObject("tradeList", tradeList);
		mav.addObject("center", "/WEB-INF/views/trade/tradeBoard.jsp");
		//뷰명 저장
		mav.setViewName("main");
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/trade/*Form.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//응답할 뷰 이름 얻기 
		String viewName = getViewName(request);
		logger.info("-------: " + viewName);
		
		//응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		
		return mav;
	}

	/*
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/categoriedList.do") public ModelAndView
	 * listTradeCategory(@RequestParam("category") String category,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception {
	 * List categoriedList = tradeSevrvice.listTradeCategory(category);
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * //응답할 뷰 이름 얻기 String viewName = getViewName(request);
	 * 
	 * //응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩 ModelAndView mav = new ModelAndView();
	 * //응답할 값 저장 mav.addObject("categoriedList", categoriedList); //뷰명 저장
	 * mav.setViewName(viewName);
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/tradeDetail.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView viewTradeDetail(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * //서비스 객체 메소드 호출 시 no를 전달하여 select vo = tradeSevrvice.viewTradeDetail(no);
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.addObject("tradevo", vo);
	 * mav.setViewName(getViewName(request));
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/regTrade.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView regTradeBoard(@ModelAttribute("vo")
	 * TradeVO vo, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * tradeSevrvice.regTradeBoard(vo); ModelAndView mav = new
	 * ModelAndView("redirect:/trade/tradeList.do");
	 * 
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/modTrade.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView modTradeBoard(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * tradeSevrvice.modTradeBoard(no);
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/trade/tradeList.do");
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/delTrade.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView delTradeBoard(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { request.setCharacterEncoding("UTF-8");
	 * 
	 * tradeSevrvice.delTradeBoard(no);
	 * 
	 * return new ModelAndView("redirect:/trade/tradeList.do"); }
	 */
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
