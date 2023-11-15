package com.spring.community.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.community.member.VO.MemberVO;

public interface MemberService {

	//모든 회원을 조회 하는 기능 
	public List listMembers() throws DataAccessException;

	//회원 가입 추가 기능 
	public void addMembers(Map map) throws DataAccessException;



	//회원정보 수정 기능 
	public void UpdateMember(MemberVO vo) throws DataAccessException;
	
	//회원 삭제 기능 
	public void delMembers(String id) throws DataAccessException;
	
	


	MemberVO detailMembers(MemberVO memberVO) throws DataAccessException;

	List likelist(MemberVO memberVO) throws Exception;
	

	public MemberVO getMemberInfo(int no) throws Exception;

	public MemberVO getMemberId(String nickname) throws Exception;

	public MemberVO login(Map<String, String> loginInfo) throws Exception;

	
	
}
