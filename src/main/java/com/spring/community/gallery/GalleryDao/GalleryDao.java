package com.spring.community.gallery.GalleryDao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.community.gallery.vo.GalleryVO;

public interface GalleryDao {


	public int InsertGallery(Map map);

	public int getInsertedNo() throws DataAccessException;

	public List<GalleryDao> getGalleryList();

}
