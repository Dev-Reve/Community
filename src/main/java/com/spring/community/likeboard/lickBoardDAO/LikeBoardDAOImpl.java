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

@Repository("likeboardDAO")
public class LikeBoardDAOImpl extends HttpServlet implements LikeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selcetLikeBoard() {
		
		
		
		return null;
	}

}
