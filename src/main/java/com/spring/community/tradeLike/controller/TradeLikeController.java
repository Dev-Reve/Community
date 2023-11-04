package com.spring.community.tradeLike.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.vo.TradeLikeVO;

public interface TradeLikeController {

	public ModelAndView clickLike(String nickname, int no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
