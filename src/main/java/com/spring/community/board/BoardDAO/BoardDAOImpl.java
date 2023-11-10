package com.spring.community.board.BoardDAO;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Utils.PagerVO;
import com.spring.community.board.Utils.PagingVO;

@Repository("boardDAO")
public class BoardDAOImpl extends HttpServlet implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selcetAllBoard() {

		List<BoardVO> boardlist = sqlSession.selectList("mapper.board.selectAllboardlist");
	
		return boardlist;
	}

	@Override
	public int countBoard() {
		
		int countBoard = sqlSession.selectOne("mapper.board.countBoard");
		
		return countBoard;
	}

	@Override
	public List<BoardVO> selcetBoard(PagingVO pvo) {
		
		List<BoardVO> selectBoard = sqlSession.selectList("mapper.board.selectBoard", pvo);
		
		return selectBoard;
	}

	@Override
	public Long totalCount(PagerVO pager) {
		
		Long getTotalCount = sqlSession.selectOne("mapper.board.getTotalCount", pager);
		
		return getTotalCount;
	}

	@Override
	public List<BoardVO> getBoardList(PagerVO pager) {
		
		List<BoardVO> getBoardList = sqlSession.selectList("mapper.board.getBoardList", pager);
		
		return getBoardList;
	}

	@Override
	public BoardVO boardInfo(String no) {
		
		BoardVO vo = new BoardVO();
		
		vo = sqlSession.selectOne("mapper.board.getBoardInfo", no);
		
		return vo;
	}

	@Override
	public void insertboard(BoardVO boardVO) {
		System.out.println("다오" + boardVO.getContent());
		System.out.println("다오" + boardVO.getTitle());
		System.out.println("다오" + boardVO.getNickName());
		System.out.println(boardVO.getNickName().length());
		
		sqlSession.insert("mapper.board.addBoard", boardVO);

	}                                   
}
