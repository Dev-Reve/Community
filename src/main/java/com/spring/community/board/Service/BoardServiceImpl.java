package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.BoardDAO.BoardDAO;
import com.spring.community.board.BoardVO.BoardVO;

@Service("boardService")
public class BoardServiceImpl extends HttpServlet implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	@Autowired
	private BoardVO vo;
	
	@Override
	public List selcetAllBoard() throws Exception {
		
		List boardlist = dao.selcetAllBoard();
		
		return boardlist;
	}

}
