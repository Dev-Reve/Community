package com.spring.community.trade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	//파일이 실제 업로드되는 폴더 경로 저장
	private static final String CURR_IMAGE_REPO_PATH = "/src/main/webapp/resources/Board/trade/ImagesRepo";
	
}
