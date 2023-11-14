package com.spring.community.gallery.GalleryDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.community.gallery.vo.GalleryCommentVO;
import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.tradeComment.vo.TradeCommentVO;

@Repository("galleryDao")
public class GalleryDaoImpl implements GalleryDao {

	@Autowired
	SqlSession sqlSession;

	@Override
	public int InsertGallery(Map map) throws DataAccessException{
		
		sqlSession.insert("mapper.gallery.insertGallery", map);
		int no = getInsertedNo();
		return no;	
	}
	
	@Override
	public int getInsertedNo()throws DataAccessException{
		int no = sqlSession.selectOne("mapper.gallery.getInsertedNo");
		return no;
	}
	
	//갤러리 글 모두 조회해오기
	@Override
	public List<GalleryDao> getGalleryList() throws DataAccessException{
		List <GalleryDao> list = sqlSession.selectList("mapper.gallery.GallerylIST");
		return list;
	}
	
	//갤러리 글 하나 조회해오기
	@Override
	public GalleryVO getGalleryInfo(int no) throws DataAccessException {
		GalleryVO vo = sqlSession.selectOne("mapper.gallery.GalleryInfo", no);
		return vo;
	}
	
	@Override
	public List<GalleryCommentVO> getComment(int no) throws DataAccessException {
		return sqlSession.selectList("mapper.galleryComment.getCommentList", no);
	}
	
	@Override
	public void regComment(GalleryCommentVO comment) throws DataAccessException {
		sqlSession.insert("mapper.galleryComment.regComment", comment);
	}
	
	@Override
	public void delComment(int no) {
		sqlSession.delete("mapper.galleryComment.delComment", no);
	}
}
