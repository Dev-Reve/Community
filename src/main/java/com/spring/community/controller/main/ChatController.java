package com.spring.community.controller.main;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	@RequestMapping(value = "/chat") 
	public ModelAndView chat(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("center", "/WEB-INF/views/common/chat.jsp");
		mav.setViewName("main");
		return mav;
	}
	
}
