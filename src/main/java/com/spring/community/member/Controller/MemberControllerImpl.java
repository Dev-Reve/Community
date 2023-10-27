package com.spring.community.member.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.member.VO.MemberVO;
import com.spring.community.member.service.MemberService;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO vo;
	
	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		return null;
	}
	
}
