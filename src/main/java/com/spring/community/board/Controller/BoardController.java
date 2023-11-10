package com.spring.community.board.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Utils.PagerVO;
import com.spring.community.board.Utils.PagingVO;


public interface BoardController {
	
	public ModelAndView selcetBoard(PagingVO vo, ModelAndView mav,
								@RequestParam(value="nowPage", required = false) String nowPage,
								@RequestParam(value="cntPerPage", required = false)String cntPerPage,
								@RequestParam(value = "checksel", required = false) String checkel)
								throws Exception;

	public ModelAndView selectInfo (HttpServletRequest request, HttpServletResponse response)
										throws Exception;

	public ModelAndView addCommnet(HttpServletRequest request, HttpServletResponse response)
										throws Exception;
	
	public ModelAndView insertForm(HttpServletRequest request, HttpServletResponse response) 
										throws Exception;
	

	public ModelAndView insertboard(BoardVO vo) throws Exception;
	
////	public ModelAndView insertboard(HttpServletRequest request, HttpServletResponse response) 
//							throws Exception;
}
