package com.spring.community.member.DAO;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.community.member.VO.MemberVO;


//사원  DB작업을 직접 하는 클래스 

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{


	//SqlSessionTemplate을 주입받기 위해 참조변수 선언
	
	//action-mybatis.xml파일에 작성해 놓은
	// <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate"></bean>
	// 의 id를 이용하여 아래의 sqlSession변수에 @Autowired로 자동 주입 합니다.
	@Autowired
	private SqlSession sqlSession;
	
	
	//모든 회원 조회 
	@Override
	public List selectAllMembers() throws DataAccessException {
				
		List<MemberVO> membersList = null;
		
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		
		return membersList;
	
	}//selectAllMembers메소드 닫는 기호 

	//회원 추가 기능 
	@Override
	public void InsertMember(Map map) throws DataAccessException {
		System.out.println(map.get("id"));
		System.out.println(map.get("password"));
		System.out.println(map.get("name"));
		System.out.println(map.get("ssn"));
		System.out.println(map.get("nickname"));
		System.out.println(map.get("email"));
		System.out.println(map.get("addr1"));
		System.out.println(map.get("addr2"));
		System.out.println(map.get("addr3"));
		System.out.println(map.get("addr4"));
		System.out.println(map.get("fileName"));
		sqlSession.insert("mapper.member.insertMember", map); 
	}

	//회원 삭제 기능 
	@Override
	public void DeleteMember(String id) throws DataAccessException {
		
		sqlSession.delete("mapper.member.deleteMember", id);
		
	}

	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	@Override
	public MemberVO oneMember(MemberVO memberVO) throws DataAccessException {

		  MemberVO vo = (MemberVO)sqlSession.selectOne("mapper.member.selectMemberById", memberVO);
		
		  
		  return vo;
	}

	//회원정보 수정기능 
	@Override
	public void UpdateMember(MemberVO memberVO) throws DataAccessException {
				
		sqlSession.update("mapper.member.updateMember", memberVO);
		
	}

	//회원로그인 처리 기능
	@Override
	public MemberVO loginById(Map<String, String> map) throws DataAccessException{
		
		System.out.println("loginById: "+map.get("id"));
		System.out.println("loginById: "+map.get("password"));
		
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", map);
		
		return vo;
	}


	public List likelistByNick(MemberVO memberVO) throws DataAccessException{
		
	List<MemberVO>likeList = sqlSession.selectList("mapper.member.likelistByNick",memberVO);
			
			return likeList;
	}


	//게시글 작성자 정보 가져오기
	@Override
	public MemberVO getMemberInfo(int no) throws DataAccessException {
		System.out.println("DAO에서 받아온 no값: " + no);
		return sqlSession.selectOne("mapper.member.getMemberInfo", no);
	}
	
	//회원의 id 가져오기
	@Override
	public MemberVO getMemberId(String nickname) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.getMemberId", nickname);
	}
	

	public int idCheck(String id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.CheckById",id);
	}
}//MemberDAOImpl클래스 닫는 기호 