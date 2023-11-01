package com.spring.community.main.FileController;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.service.TradeBoardService;
import com.spring.community.trade.vo.TradeVO;

import net.coobird.thumbnailator.Thumbnails;


//메인 페이지를 요청할때 불러올 때 처리할 컨트롤러
// 처리 내용
// 1. 갤러리 게시판 최신글 3개 조회를 해와서 크기 조절 후 출력하기
// 2. 거래 게시판 최신글 3개 조회를 해서 크기 조절 후 출력하기 
@Controller
@RequestMapping("/first")
public class MainFileEditControllerImpl implements MainFileEditController {
	
	
	private static String CURR_IMAGE_REPO_PATH = "C:\\WorkSpaceTeamPro\\community\\src\\main\\webapp\\resources\\images";
	
	@RequestMapping(value="/download.do", method=RequestMethod.GET)
	protected void download(@RequestParam("imageFileName") String imageFileName,
							HttpServletResponse response) throws Exception{
		
		OutputStream out = response.getOutputStream();
		
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		
		File image = new File(filePath);
		
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		File thumbnail = new File( CURR_IMAGE_REPO_PATH + "\\" + "mainThumbnail" + "\\" + fileName +".png");
		if(image.exists()) {
			Thumbnails.of(image).size(220, 220).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		byte[] buffer =new byte[1024 * 8];
		out.write(buffer);	
	}

}
