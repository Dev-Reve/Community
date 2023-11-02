package com.spring.community.board.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.Utils.PagingVO;


public interface BoardController {
	
	public ModelAndView selcetBoard(PagingVO vo, ModelAndView mav,
			@RequestParam(value="nowPage", required = false) String nowPage,
			@RequestParam(value="cntPerPage", required = false)String cntPerPage,
			@RequestParam(value = "checksel", required = false) String checkel)
			throws Exception;
	
}
