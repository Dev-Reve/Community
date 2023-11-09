package com.spring.community.gallery.GalleryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.gallery.GalleryDao.GalleryDao;
import com.spring.community.gallery.GalleryDao.GalleryDaoImpl;
import com.spring.community.gallery.vo.GalleryVO;

@Service("galleryService")
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	GalleryDao galleryDao ;
	
	@Override
	public int InsertGallery(Map map) {
		return galleryDao.InsertGallery(map);
	}
	
	@Override
	public List<GalleryDao> getGalleryList() {
		return galleryDao.getGalleryList();
	}
}
