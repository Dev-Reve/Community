package com.spring.community.likeboard.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

public interface LikeBoardService {
	
	// 추천수? 좋아요? 높은 순으로 조회해서 가져오는 메소드
	public List selcetLikeBoard() throws Exception;
	
	
}
