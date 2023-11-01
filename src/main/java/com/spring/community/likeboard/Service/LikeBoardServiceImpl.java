package com.spring.community.likeboard.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.likeboard.lickBoardDAO.LikeBoardDAO;
import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

@Service("likeboardService")
public class LikeBoardServiceImpl extends HttpServlet implements LikeBoardService {
	
	@Autowired
	private LikeBoardDAO boardDAO;
	
	@Override
	public ModelAndView selcetLikeBoard(HttpServletRequest request, 
											HttpServletResponse response) 
												throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
