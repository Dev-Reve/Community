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
				   category; //카테고리
				   
	public TradeVO() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSellStat() {
		return sellStat;
	}

	public void setSellStat(int sellStat) {
		this.sellStat = sellStat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
