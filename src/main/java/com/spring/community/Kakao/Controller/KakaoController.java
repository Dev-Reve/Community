package com.spring.community.Kakao.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface KakaoController {

	ModelAndView kakaologin(HttpServletResponse response, HttpServletRequest request) 
								throws Exception, IOException;
	
	
}
