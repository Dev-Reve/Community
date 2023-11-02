package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.spring.community.board.utils.PagingVO;
=======
import com.spring.community.board.Utils.PagingVO;
>>>>>>> branch 'min' of https://github.com/Dev-Reve/Community.git

public interface BoardService {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public List selcetAllBoard() throws Exception;
	
	// 전체 글 개수 가져오는 메소드
	public int countBoard();
	
	// 조회하는 메소드
	public List selectBoard(PagingVO pvo);
	
}
