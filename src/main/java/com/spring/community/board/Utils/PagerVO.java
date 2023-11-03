package com.spring.community.board.Utils;

import org.springframework.stereotype.Component;

@Component
public class PagerVO {
	
	private Long totalCount;	// 전체 글의 갯수
	private Long totalPage;		// 전체 페이지의 수
	private Long perPage;		// 한 페이지 당 글의수
	private Long totalBlock;	// 전체 블럭의 수
	private Long perBlock;		// 페이지 당 블럭의 수
	private Long curBlock;		// 현재 블럭 번호
	private Long startNum;		// 페이지 시작번호
	private Long lastNum;		// 페이지 끝번호
	private boolean lastCheck;	// 마지막 블럭 조사
	private Long startRow;		// 첫 시작 행
	private String kind;		// 조회할 게시판 테이블의 컬럼명
	private String search;		// 입력한 검색어
	private Integer pageNum;	// 현제 페이지 번호
	
	public void makeRow() {
		this.startRow = (this.getPageNum()-1) * this.getPerPage();
	}
	
	public void makeNum(Long totalCount) {
		// 전체 페이지 개수 구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
		
		// 총 페이징 개수 구하기
		Long perBlock = 5L;
		Long totalBlock = totalPage/perBlock;
		if(totalBlock%perBlock != 0) {
			totalBlock++;
		}
		
		// pageNum 으로 현재 블럭 번호 구하기
		Long curBlock = this.getPageNum()/perBlock;
		if(this.getPageNum()%perBlock != 0) {
			curBlock++;
		}
		
		// curBlock 으로 시작번호, 끝번호 구하기
		startNum = (curBlock-1)*perBlock+1;
		lastNum = curBlock*perBlock;
		
		if(curBlock==totalBlock) {
			lastCheck=true;
			lastNum=totalPage;
		}
	}
	
	public Integer getPageNum() {
		if(this.pageNum==null || this.pageNum<1) {
			this.pageNum=1;
		}
		return pageNum;
	}
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return this.search;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public Long getPerPage() {
		return perPage;
	}

	public Long getTotalBlock() {
		return totalBlock;
	}

	public Long getPerBlock() {
		return perBlock;
	}

	public Long getCurBlock() {
		return curBlock;
	}

	public Long getStartNum() {
		return startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public boolean getLastCheck() {
		return lastCheck;
	}

	public Long getStartRow() {
		return startRow;
	}

	public String getKind() {
		return kind;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}

	public void setTotalBlock(Long totalBlock) {
		this.totalBlock = totalBlock;
	}

	public void setPerBlock(Long perBlock) {
		this.perBlock = perBlock;
	}

	public void setCurBlock(Long curBlock) {
		this.curBlock = curBlock;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public void setLastCheck(Boolean lastCheck) {
		this.lastCheck = lastCheck;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
