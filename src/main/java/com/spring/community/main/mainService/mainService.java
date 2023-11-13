package com.spring.community.main.mainService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;
import com.spring.community.trade.vo.TradeVO;

public interface mainService {
	
	//갤러리 테이블에 있는 최신글 3개의 글번호, 파일 이름을 받아오기
	public List getRecentGarallyList();
	
	//거래게시판 테이블에 있는 최신글 3개의 글번호, 파일이름, 가격 불러오기
	public List getRecentTradeList();
	
}
