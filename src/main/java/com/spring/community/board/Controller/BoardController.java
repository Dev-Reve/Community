package com.spring.community.board.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


public interface BoardController {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public ModelAndView selcetAllBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception;
	
	
	
	
	
	
}
