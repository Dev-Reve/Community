package com.spring.community.board.Controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Service.BoardService;

@Controller("boardController")
public class BoardControllerImpl extends HttpServlet implements BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private BoardVO vo;
	
	@Override
	@RequestMapping(value="/board/listboard.do", method = RequestMethod.GET)
	public ModelAndView selcetAllBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception {
		
		List boardlist = boardservice.selcetAllBoard();
			
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boardlist", boardlist);
		mav.setViewName("board/board");
		mav.addObject("center", "/WEB-INF/views/board/board.jsp");
		mav.setViewName("main");
		
		System.out.println("mav / viewname : " + mav.getViewName());
		
		return mav;
	}
	
	
	
}
