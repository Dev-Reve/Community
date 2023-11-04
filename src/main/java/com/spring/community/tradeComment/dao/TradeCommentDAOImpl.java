package com.spring.community.tradeComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.community.tradeComment.vo.TradeCommentVO;

@Repository
public class TradeCommentDAOImpl implements TradeCommentDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<TradeCommentVO> getCommentList(int no) throws DataAccessException {
		return sqlSession.selectList("mapper.tradeComment.getCommentList", no);
	}

}
