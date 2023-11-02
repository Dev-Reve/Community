package com.spring.community.board.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.spring.community.board.utils.PagingVO;


public interface BoardController {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public ModelAndView selcetAllBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception;
	
=======
import com.spring.community.board.Utils.PagingVO;


public interface BoardController {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public ModelAndView selcetAllBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception;
>>>>>>> branch 'min' of https://github.com/Dev-Reve/Community.git
	public ModelAndView selcetBoard(PagingVO vo, ModelAndView mav,
			@RequestParam(value="nowPage", required = false) String nowPage,
			@RequestParam(value="cntPerPage", required = false)String cntPerPage)
			throws Exception;
	
}
