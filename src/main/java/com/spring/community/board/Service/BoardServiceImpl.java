package com.spring.community.board.Service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.board.BoardDAO.BoardDAO;
import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Utils.PagerVO;
import com.spring.community.board.Utils.PagingVO;

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

	@Override
	public List<BoardVO> getList(PagerVO pager) {
		
		pager.makeRow();
		Long totalCount = dao.totalCount(pager);
		
		pager.makeNum(totalCount);
		
		List<BoardVO> getBoardList = dao.getBoardList(pager);
		
		return getBoardList;
	}

	@Override
	public BoardVO boardInfo(String no, String name) {
		
		return dao.boardInfo(no, name);
		
	}

	@Override
	public void insertboard(BoardVO vo) {

		System.out.println("서비스" + vo.getContent());
		System.out.println("서비스" + vo.getTitle());
		System.out.println("서비스" + vo.getNickName());
		
		dao.insertboard(vo); 
	}
	

	
}
