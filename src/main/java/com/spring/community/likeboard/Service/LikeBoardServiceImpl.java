package com.spring.community.likeboard.Service;

import java.util.List;

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
	@Autowired
	private LikeBoardVO vo;
	
	@Override
	public List selcetLikeBoard() throws Exception {
		List likeboardlist = boardDAO.selcetLikeBoard();
		
		return likeboardlist;
	}

}
