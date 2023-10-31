package com.spring.community.board.BoardDAO;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.board.BoardVO.BoardVO;

@Repository("boardDAO")
public class BoardDAOImpl extends HttpServlet implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selcetAllBoard() {

		List<BoardVO> boardlist = sqlSession.selectList("mapper.board.selectAllboardlist");
	
		return boardlist;
	}
}
