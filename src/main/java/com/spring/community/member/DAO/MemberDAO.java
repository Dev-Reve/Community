package com.spring.community.member.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.community.member.VO.MemberVO;

public interface MemberDAO {

	//모든 회원을 조회 하는 기능
	public List selectAllMembers() throws DataAccessException;

	//회원 추가 기능 
	public void InsertMember(Map map) throws DataAccessException;

	//회원 삭제 기능 
	public void DeleteMember(String id) throws DataAccessException;



	//회원정보 수정 기능
	public void UpdateMember(MemberVO vo) throws DataAccessException;

	//회원로그인 처리 기능
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;


	MemberVO oneMember(MemberVO memberVO) throws DataAccessException;

	//like한 리스트 불러오는 메소드
	public List likelistByNick(MemberVO memberVO);

	

	//거래 게시글 작성자의 정보를 가져오는 기능
	public MemberVO getMemberInfo(int no) throws DataAccessException;

	public MemberVO getMemberId(String nickname) throws DataAccessException;

	
	//
	
	//
	
	//
	
}
