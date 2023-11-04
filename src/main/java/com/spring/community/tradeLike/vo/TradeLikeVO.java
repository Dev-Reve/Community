package com.spring.community.tradeLike.vo;

import org.springframework.stereotype.Component;

@Component
public class TradeLikeVO {
	private int no;
	private int tradeNo;
	private String nickname;
	
	public TradeLikeVO() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
