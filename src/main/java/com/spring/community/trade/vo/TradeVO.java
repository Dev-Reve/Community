package com.spring.community.trade.vo;

import org.springframework.stereotype.Component;

@Component("tradeVO")
public class TradeVO {
	private int no, //글번호
				readCount, //조회수 
				price, //가격
				sellStat; //판매상태
	private String title, //글제목
				   content, //글내용
				   nickname, //유저닉네임
				   writeDate, //작성일
				   category, //카테고리
				   fileName, //첨부파일명
				   fileRealName; //첨부파일 실제명
				   
	public TradeVO() {}
	
	
}
