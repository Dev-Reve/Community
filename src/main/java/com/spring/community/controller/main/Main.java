package com.spring.community.controller.main;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main {
	
	@RequestMapping(value = "/main/index.do", method = RequestMethod.GET)
	public ModelAndView main() {
		System.out.println("index호출");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("center", "/WEB-INF/views/common/First.jsp");
		mav.setViewName("main");
		
		return mav;
	}
	
}