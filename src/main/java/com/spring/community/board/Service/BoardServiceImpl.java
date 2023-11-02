package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.board.BoardDAO.BoardDAO;
import com.spring.community.board.BoardVO.BoardVO;

import com.spring.community.board.utils.PagingVO;

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

	@Override
	public int countBoard() {
		return dao.countBoard();
	}

	@Override
	public List<BoardVO> selectBoard(PagingVO pvo) {
		return dao.selcetBoard(pvo);
	}

}
