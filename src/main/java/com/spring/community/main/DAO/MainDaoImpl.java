package com.spring.community.main.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.trade.vo.TradeVO;
@Repository("mainDao")
public class MainDaoImpl implements MainDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<GalleryVO> getRecentGarallyList() throws DataAccessException {
		List<GalleryVO> list = sqlSession.selectList("mapper.main.selectAllTrades");
		return null;
	}

	@Override
	public List<TradeVO> getRecentTradeList() throws DataAccessException {
		List<TradeVO> list = sqlSession.selectList("mapper.main.selectAllTrades");
		return list;
	}

}
