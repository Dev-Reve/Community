package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Utils.PagerVO;
import com.spring.community.board.Utils.PagingVO;

public interface BoardService {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public List selcetAllBoard() throws Exception;
	
	// 전체 글 개수 가져오는 메소드
	public int countBoard();
	
	// 조회하는 메소드
	public List selectBoard(PagingVO pvo);

	public List<BoardVO> getList(PagerVO pager);

	// 글 하나 조회하는 메소드
	public BoardVO boardInfo(String no);
	
	
}
