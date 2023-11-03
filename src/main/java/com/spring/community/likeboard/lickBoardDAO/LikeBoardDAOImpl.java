package com.spring.community.likeboard.lickBoardDAO;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.Utils.PagingVO;
import com.spring.community.likeboard.likeBoardVO.LikeBoardVO;

@Repository("likeboardDAO")
public class LikeBoardDAOImpl extends HttpServlet implements LikeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<LikeBoardVO> selboardList(PagingVO pvo){
		
		List<LikeBoardVO> boardList = sqlSession.selectList("mapper.likeboard.boardList", pvo);
		
		return boardList;
	}

	@Override
	public List<LikeBoardVO> seltradeList(PagingVO pvo) {
		
		List<LikeBoardVO> tradeList = sqlSession.selectList("mapper.likeboard.tradeList", pvo);
		
		return tradeList;
	}
		
	
}
