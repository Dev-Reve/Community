package com.spring.community.board.BoardVO;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

<<<<<<< HEAD
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
	
	
=======
@Component
public class BoardVO {
	
	private int no;
	private String title;
	private String content;
	private String writeDate;
	private String nickName;
	private int readCount;
	private String fileName;
	private String fileRealName;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
>>>>>>> branch 'min' of https://github.com/Dev-Reve/Community.git
}
