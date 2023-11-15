package com.spring.community.boardcomment.VO;

import org.springframework.stereotype.Component;

@Component("boardCommentVO")
public class BoardCommentVO {
	private int no;
	private String content;
	private String nickName;
	private String writeDate;
	private int parentNo;
	private int childNo;
	private int boardNo;
	
	public int getNo() {
		return no;
	}
	public String getContent() {
		return content;
	}
	public String getNickName() {
		return nickName;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public int getParentNo() {
		return parentNo;
	}
	public int getChldNo() {
		return childNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public void setChldNo(int childNo) {
		this.childNo = childNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
}
