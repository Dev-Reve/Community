package com.spring.community.member.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.community.member.VO.MemberVO;

public interface MemberController {

	//모든 회원 정보 조회 기능
	public ModelAndView listMembers(HttpServletRequest reuqest, 
									HttpServletResponse response) throws Exception;
	
	//회원가입 화면 이동 기능
//	public String memberForm(HttpServletRequest reuqest, 
//							 HttpServletResponse response) throws Exception;
//	//회원가입 기능
//	public String addMember(HttpServletRequest request, 
//						    HttpServletResponse response) throws Exception;
	
	
	
	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	public ModelAndView memberDetail(HttpServletRequest request, 
									 HttpServletResponse response) throws Exception;
	
	//회원 한명 정보 수정 기능
//	public String   UpdateMember(HttpServletRequest request, 
//			 	    			 HttpServletResponse response) throws Exception;
	
	
	//회원정보 삭제 기능
//	public String memberDel(HttpServletRequest request, 
//		    				HttpServletResponse response) throws Exception;

	//회원정보 삭제 기능 
	ModelAndView memberDel(String id, 
						   HttpServletRequest request, 
						   HttpServletResponse response) throws Exception;

	
	//회원가입 화면 요청
	ModelAndView Form(HttpServletRequest request, 
					  HttpServletResponse response) throws Exception;

	

	//회원로그아웃 처리 기능
	ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;


	//회원가입요청
//	ModelAndView addMember(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

	//로그인 화면 요청
	ModelAndView Form2(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//회원로그인 처리 기능
//	ModelAndView login(RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)
//			throws Exception;
	//마이페이지 화면
	ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception;


	ModelAndView UpdateMember(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ModelAndView addMember(MultipartFile fileName, MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception;

	ModelAndView login(String id, String password, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception;


	



	
	
	
	//회원로그아웃 처리 기능
	
	
	
}
