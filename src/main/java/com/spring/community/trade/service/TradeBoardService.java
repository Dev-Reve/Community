package com.spring.community.trade.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.vo.TradeVO;


public interface TradeBoardService {
	//거래 게시판 게시글 전부 조회해서 가져오는 메소드
	public Map<String, Object> listTradeBoards(String pageNum) throws Exception;
	
	//거래 게시판에 존재하는 레코드의 개수 반환하는 메소드
	public int getTotalPosts();

	//거래 게시판에서 카테고리를 클릭했을 때 카테고리별로 조회해서 가져오는 메소드
//	public List listTradeCategory(String category) throws Exception;
	
	//거래 게시판에서 게시글을 클릭했을 때 게시글을 조회해오는 메소드
//	public TradeVO viewTradeDetail(int no) throws Exception;
	
	//거래 게시판에서 글작성을 클릭했을 때 글을 등록하는메소드
	public void regTradeBoard(TradeVO vo) throws Exception;
	
	//거래 게시판에서 글 수정을 클릭했을 때 글을 수정하는 메소드
//	public void modTradeBoard(int no) throws Exception;
	
	//거래 게시판 글 삭제 시 호출할 메소드
//	public void delTradeBoard(int no) throws Exception;
	
	//거래 게시판 글 작성 시 파일 업로드 처리를 위한 메소드
//	public Map<String, String> upload() throws Exception;
	
	//거래 게시판 글 조회 시 파일 다운로드 처리를 위한 메소드
//	public byte[] fileDownloader() throws Exception;
}
