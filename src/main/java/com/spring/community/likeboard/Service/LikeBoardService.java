package com.spring.community.likeboard.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

public interface LikeBoardService {
	
	LikeBoardVO selcetAllLikeBoard(HttpServletRequest request, HttpServletResponse response);
	
	
}
