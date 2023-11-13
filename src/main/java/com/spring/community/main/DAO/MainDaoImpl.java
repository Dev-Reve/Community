package com.spring.community.main.DAO;

import java.util.ArrayList;
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
	SqlSession sqlSession;
	

	@Override
	public List getRecentTradeList() {
		System.out.println("dao에는 온다 트레이드!!");
		List list = new ArrayList();
		try {
			
		 list = sqlSession.selectList("mapper.main.getRecentTrade");
		
		}catch (Exception e) {
			System.out.println("트레이드에서 오류 : " + e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List getRecentGarallyList() throws DataAccessException {
		System.out.println("dao에는 온다 갤러리");
		List list = new ArrayList();
		System.out.println("dao에 도착후 mapper호출");
		try {
			
		 list = sqlSession.selectList("mapper.gallery.getRecentGallery");
		 System.out.println("dao 매퍼 호출 후");
		 
		}catch (Exception e) {
			System.out.println("갤러리에서 오류 : " + e);
			e.printStackTrace();
		}
		return list;
	}

}
