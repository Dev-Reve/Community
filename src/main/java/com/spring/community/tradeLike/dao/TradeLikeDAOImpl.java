package com.spring.community.tradeLike.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.vo.TradeLikeVO;

@Repository
public class TradeLikeDAOImpl implements TradeLikeDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public TradeLikeVO getClickStat(Map map) throws DataAccessException {
		return sqlSession.selectOne("mapper.tradeLike.getClickStat", map);
	}
	
	@Override
	public void regLike(Map map) throws DataAccessException {
		sqlSession.insert("mapper.tradeLike.regLike", map);
	}
	
	@Override
	public void delLike(Map map) throws DataAccessException {
		sqlSession.delete("mapper.tradeLike.delLike", map);
	}
}
