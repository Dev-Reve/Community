package com.spring.community.gallery.GalleryService;

import java.util.List;
import java.util.Map;

import com.spring.community.gallery.GalleryDao.GalleryDao;
import com.spring.community.gallery.vo.GalleryCommentVO;
import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.tradeComment.vo.TradeCommentVO;

public interface GalleryService {
	
	//갤러리 게시판 목록 인서트
	public int InsertGallery(Map map);

	public List<GalleryDao> getGalleryList();

	public GalleryVO getGalleryInfo(int no);

	public List<GalleryCommentVO> getComment(int no);

	public void regComment(GalleryCommentVO comment);

	public void delComment(int no);

	public void delgallery(int no);
}
