package com.spring.community.board.BoardVO;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

@Component("boardVO")
public class BoardVO {
	
	private int no;
	private String title;
	private String content;
	private String writedate;
	private String nickname;
	private int readcount;
	private String filename;
	private String filerealname;
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWritedate() {
		return writedate;
	}
	public String getNickname() {
		return nickname;
	}
	public int getReadcount() {
		return readcount;
	}
	public String getFilename() {
		return filename;
	}
	public String getFilerealname() {
		return filerealname;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}
	
	
}
