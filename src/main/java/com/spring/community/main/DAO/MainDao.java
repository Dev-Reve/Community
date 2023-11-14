package com.spring.community.main.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.trade.vo.TradeVO;

public interface MainDao {

	
		//갤러리 테이블에 있는 최신글 3개의 글번호, 파일 이름을 받아오기
		public List getRecentGarallyList()throws DataAccessException;
		
		//거래게시판 테이블에 있는 최신글 3개의 글번호, 파일이름, 가격 불러오기
		public List getRecentTradeList()throws DataAccessException;
}
