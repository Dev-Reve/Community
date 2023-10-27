package com.spring.community.member.service;

import java.util.List;
import org.springframework.dao.DataAccessException;

public interface MemberService {
	
	//회원 추가(가입)기능
	public void addMember() throws DataAccessException;
	
	//모든 회원을 조회하는 기능
	public List listMembers() throws DataAccessException;
	
}
