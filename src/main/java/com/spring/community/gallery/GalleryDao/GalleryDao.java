package com.spring.community.gallery.GalleryDao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.community.gallery.vo.GalleryCommentVO;
import com.spring.community.gallery.vo.GalleryVO;

public interface GalleryDao {


	public int InsertGallery(Map map) throws DataAccessException;

	public int getInsertedNo() throws DataAccessException;

	public List<GalleryDao> getGalleryList() throws DataAccessException;

	public GalleryVO getGalleryInfo(int no) throws DataAccessException;

	public List<GalleryCommentVO> getComment(int no) throws DataAccessException;

	public void regComment(GalleryCommentVO comment) throws DataAccessException;

}
