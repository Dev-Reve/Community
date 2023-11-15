package com.spring.community.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.community.member.VO.MemberVO;
import com.spring.community.member.VO.OAuthToken;
import com.spring.community.member.DAO.MemberDAO;

//부장 

//id속성값이 memberService인 
//<bean id="memberService" 
//    class="com.spring.member.service.MemberServiceImpl">을 자동 생성 해 줍니다. 
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	//id속성값이 memberDAO인
	//<bean id="memberDAO"
	//     class="com.spring.member.dao.MemberDAOImpl">을 자동 주입합니다. 
	@Autowired
	private MemberDAO memberDAO;// = new MemberDAOImpl();
	
	//모든 회원 정보 조회 기능 
	@Override
	public List listMembers() throws DataAccessException {
		
		//사원 MemberDAOImpl객체의 selectAllMembers()메소드를 호출해 
		//모든 회원 정보 조회 요청을 명령함!
		//조회된 회원정보들(MemberVO객체들)이 저장된  List배열을 반환 받음 
		List membersList = memberDAO.selectAllMembers();
	
		return membersList;//사장님 MemberControllerImpl객체에 반환 
	}

	//회원 가입 기능
	@Override
	public void addMembers(Map map) throws DataAccessException{
		memberDAO.InsertMember(map);
	}

	//회원 삭제 기능 
	@Override
	public void delMembers(String id) throws DataAccessException {
		memberDAO.DeleteMember(id);
	}

	@Override
	//like한 리스트불러오는메소드
	public List likelist(MemberVO memberVO) throws Exception {
		return memberDAO.likelistByNick(memberVO);
	
	}
	
	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	@Override
	public MemberVO detailMembers(MemberVO vo) throws DataAccessException {
		return memberDAO.oneMember(vo);
	}

	//회원정보 수정 기능 
	@Override
	public void UpdateMember(Map map) throws DataAccessException {
		memberDAO.UpdateMember(map);
	}
	
	//회원로그인 처리 기능
	@Override
	public MemberVO login(Map<String, String> loginInfo) throws Exception {
		return memberDAO.loginById(loginInfo);
	}
	
	//거래게시글에서 글쓴이의 정보를 가져오는 기능
	@Override
	public MemberVO getMemberInfo(int no) throws Exception {
		return memberDAO.getMemberInfo(no);
	}
	
	@Override
	public MemberVO getMemberId(String nickname) throws Exception {
		return memberDAO.getMemberId(nickname);
	}
	
	@Override
	public MemberVO selectMember(String id) throws Exception {
		return memberDAO.selectMember(id);
	}

	@Override
	public MemberVO kakaoLogin(MemberVO vo) throws Exception {
		
		return memberDAO.kakaoLogin(vo);
	}

	@Override
	public void addKakaoMember(Map map) throws Exception {
		System.out.println("카카오 서비스");
		memberDAO.insertNewKakaoMember(map);
	}
	
}