package com.spring.community.Kakao.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("kakaoController")
public class KakaoControllerImpl extends HttpServlet implements KakaoController {
	
	@Override
	@RequestMapping(value="/kakao/login.do", method = RequestMethod.GET)
	public ModelAndView kakaologin (HttpServletResponse response, HttpServletRequest request) 
								throws Exception, IOException, JSONException {
		
		ModelAndView mav =  new ModelAndView();
		
		mav.addObject("center", "/WEB-INF/views/kakao/kakaologin.jsp");
		mav.setViewName("main");
        
        System.out.println("mav / viewname : " + mav.getViewName());
  
        return mav;
		
	}
	
	
	
}
