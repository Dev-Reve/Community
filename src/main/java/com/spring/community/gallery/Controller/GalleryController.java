package com.spring.community.gallery.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.vo.GalleryVO;

public interface GalleryController {

	//갤러리 게시판 요청하는 메소드
	public ModelAndView GalleryMain(HttpServletRequest request, HttpServletResponse response)throws Exception;

	//갤러리 글쓰기 폼으로 이동하는 메소드
	public ModelAndView regGalleryForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//갤러리 글쓰기 메소드
	public ModelAndView InsertGallery(List<MultipartFile> files, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//갤러리 글 폼 
	public ModelAndView GalleryDetail(int no) throws Exception;
} 
