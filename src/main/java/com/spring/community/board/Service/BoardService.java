package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface BoardService {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public List selcetAllBoard() throws Exception;
	
	
}
