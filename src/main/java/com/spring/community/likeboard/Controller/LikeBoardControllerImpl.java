package com.spring.community.likeboard.Controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.Utils.PagingVO;
import com.spring.community.likeboard.Service.LikeBoardService;
import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

@Controller("likeboardController")
public class LikeBoardControllerImpl extends HttpServlet implements LikeBoardController {
	
	@Autowired
	private LikeBoardService likeboardservice;
	
	@Autowired
	private LikeBoardVO vo;
	
	@Override
	@RequestMapping(value = "/likeboard/likeboard.do", method = RequestMethod.GET)
	public ModelAndView selcetLikeBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception {
		PagingVO pvo = new PagingVO();
		ModelAndView mav = new ModelAndView();
		
		String checksel = request.getParameter("");
		
		if(checksel == null) {
			checksel = "readcount";
		}
		
		pvo.setChecksel(checksel);
		
		List<LikeBoardVO> boadList = likeboardservice.selboardList(pvo);
		List<LikeBoardVO> tradeList = likeboardservice.seltradeList(pvo);
		
		mav.addObject("boardList", boadList);
		mav.addObject("tradeList", tradeList);
		mav.addObject("center", "/WEB-INF/views/likeboard/likeboard.jsp");
		mav.setViewName("main");

		System.out.println("mav / viewname : " + mav.getViewName());
		
		return mav;
	}
	
	
	
}
