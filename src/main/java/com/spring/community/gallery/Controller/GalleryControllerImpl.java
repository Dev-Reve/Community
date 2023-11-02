package com.spring.community.gallery.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller("GalleryController")
public class GalleryControllerImpl extends HttpServlet implements GalleryController{
	private static final long serialVersionUID = 1L;
       
	private static String CURR_IMAGE_REPO_PATH = "C:\\WorkSpaceTeamPro\\community\\src\\main\\webapp\\resources\\images\\board\\Gallery";

	
	//	@Autowired
//	private LikeBoardService likeboardservice;
//	
//	@Autowired
//	private LikeBoardVO likeboardVO;
	
	
	@Override
	@RequestMapping(value = "/gallery/main.do", method = RequestMethod.GET)
	public ModelAndView GalleryMain() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/gallery/gallery.jsp");
		mav.setViewName("main");
		
		return mav;
	}
}
