package com.spring.community.gallery.GalleryService;

import java.util.List;
import java.util.Map;

import com.spring.community.gallery.GalleryDao.GalleryDao;
import com.spring.community.gallery.vo.GalleryVO;

public interface GalleryService {
	
	//갤러리 게시판 목록 인서트
	public int InsertGallery(Map map);

	public List<GalleryDao> getGalleryList();

	public GalleryVO getGalleryInfo(int no);
}
