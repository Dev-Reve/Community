package com.spring.community.likeboard.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.Utils.PagingVO;
import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

public interface LikeBoardService {
	
	// 일반게시판 조회수 높은 순으로 정렬해서 조회
	public List<LikeBoardVO> selboardList(PagingVO pvo);
	// 거래게시판 조회수 높은 순으로 정렬해서 조회
	public List<LikeBoardVO> seltradeList(PagingVO pvo);
	
	
}
