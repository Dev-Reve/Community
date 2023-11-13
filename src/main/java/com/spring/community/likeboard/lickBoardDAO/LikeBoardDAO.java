package com.spring.community.likeboard.lickBoardDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.Utils.PagingVO;
import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

public interface LikeBoardDAO {
	
	public List<LikeBoardVO> selboardList(PagingVO pvo);

	public List<LikeBoardVO> seltradeList(PagingVO pvo);
	
	
}
