package com.spring.community.member.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/* 회원정보 관련 컨트롤러 인터페이스 */
public interface MemberController {
	
	//회원을 DB에 추가시키는 기능의 메소드
	ModelAndView addMember(HttpServletRequest request, HttpServletResponse response);
	
	//DB에 저장된 모든 회원들을 조회하는 기능 
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response);
	
	
	
}
