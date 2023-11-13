package com.spring.community.likeboard.likeBoardVO;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

@Component("likeboardVO")
public class LikeBoardVO {
	
	private int no;
	private String title;
	private String content;
	private String writedate;
	private String nickname;
	private int readcount;
	private String filename;
	
	
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
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
	public void setNo(int no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
