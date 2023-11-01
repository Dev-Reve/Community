package com.spring.community.file.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private int no;
	private List<String> fileNames;
	private List<String> fileRealNames;
	private int board_no;
	private int trade_no;
	
	public FileVO() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getFileRealNames() {
		return fileRealNames;
	}

	public void setFileRealNames(List<String> fileRealNames) {
		this.fileRealNames = fileRealNames;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(int trade_no) {
		this.trade_no = trade_no;
	}
	
	
	
}
