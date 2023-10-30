package com.spring.community.likeboard.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

@Service
public class LikeBoardServiceImpl extends HttpServlet implements LikeBoardService {

	@Override
	public LikeBoardVO selcetAllLikeBoard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
