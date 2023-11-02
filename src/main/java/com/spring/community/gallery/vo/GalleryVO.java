package com.spring.community.gallery.vo;


import org.springframework.stereotype.Component;

@Component("galleryVO")
public class GalleryVO {
	private int no; // 글 번호, 자동으로 증가하지 않음
	private String title; // 제목
	private String content; // 게시글 내용
	private String[] fileName; // 파일 이름 배열
	private String[] fileRealName; // 실제 파일 이름 배열
	private String writeDate; // 작성일
	private String nickName; // FK, 작성자 닉네임, MEMBER 테이블의 nickName을 참조
	private int readCount = 0; // 조회수, 기본값 0
	
	public GalleryVO() {}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public String[] getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String[] fileRealName) {
		this.fileRealName = fileRealName;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
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
	
	
}