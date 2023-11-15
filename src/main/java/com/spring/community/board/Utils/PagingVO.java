package com.spring.community.board.Utils;

import org.springframework.stereotype.Component;

@Component
public class PagingVO {
	
	private int nowPage;		// 현재페이지
	private int startPage;		// 시작페이지
	private int endPage;		// 끝 페이지
	private int total;			// 게시글 총 개수
	private int cntPerPage;		// 페이지당 글 개수
	private int lastPage;		// 마지막 페이지
	private int start;			// sql 조건 스타트
	private int end;			// sql 조건 엔드
	private int cntPage = 5;
	private String checksel;
	
	

	public PagingVO() {}
	
	public PagingVO(int total, int nowPage, int cntPerPage, String checksle) {
		
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
		
		setChecksel(checksle);
		
	}
	

	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage( (int)Math.ceil( (double)total/(double)cntPerPage ) );
	}
	
	// 시작 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPages) {
		setEndPage( (int)Math.ceil( (double)nowPage/(double)cntPage ) * cntPage );
		if( getLastPage()<getEndPage() ) {
			setEndPage( getLastPage() );
		}
		setStartPage(getEndPage() - cntPage + 1);
		if( getStartPage() < 1 ) {
			setStartPage(1);
		}
	}

	// DB 쿼리에 사용할 start, end 값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
	}
	
	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
				+ ", cntPage=" + cntPage + "]";	
	}
	
	public int getNowPage() {
		return nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotal() {
		return total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	public int getCntPage() {
		return cntPage;
	}


	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}


	public String getChecksel() {
		return checksel;
	}

	public void setChecksel(String checksel) {
		this.checksel = checksel;
	}
	
}