package com.spring.community.tradeLike.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.tradeLike.vo.TradeLikeVO;

public interface TradeLikeDAO {

	public TradeLikeVO getClickStat(Map map) throws DataAccessException;

	public void regLike(Map map) throws DataAccessException;

	public void delLike(Map map) throws DataAccessException;
	
}
