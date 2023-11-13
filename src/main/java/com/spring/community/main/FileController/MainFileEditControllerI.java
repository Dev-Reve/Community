package com.spring.community.main.FileController;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.web.context.ServletContextAware;
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
public class MainFileEditControllerI implements ServletContextAware {

	private static String CURR_IMAGE_REPO_PATH = "/resources/Board";
	private static String CURR_MAIN_IMAGE = "resources/images";
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/galldownload.do", method = RequestMethod.GET)
	private void download1(@RequestParam("no") int no, HttpServletResponse response) throws Exception {
		// /resources/Board
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		System.out.println(absPath);
		OutputStream out = response.getOutputStream();
		String filePath = absPath + "/" + "gallery" + "/" + no + "/";

		System.out.println(filePath);

		File image = new File(filePath);

		File[] Files = image.listFiles();

		String filename = "";
		if (Files != null && Files.length > 0) {
			filename = Files[0].getAbsolutePath();
		}

		File file = new File(filename);

		Thumbnails.of(file).size(170, 170).toOutputStream(out);

		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
	}

	@RequestMapping(value = "/tradedownload.do", method = RequestMethod.GET)
	private void download2(@RequestParam("no") int no, HttpServletResponse response) throws Exception {
		// /resources/Board
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		System.out.println(absPath);
		OutputStream out = response.getOutputStream();

		String filePath = absPath + "/" + "trade" + "/" + no + "/";

		System.out.println(filePath);

		File image = new File(filePath);

		File[] Files = image.listFiles();

		String filename = "";
		if (Files != null && Files.length > 0) {
			filename = Files[0].getAbsolutePath();
		}

		File file = new File(filename);

		Thumbnails.of(file).size(170, 170).toOutputStream(out);

		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
	}
	@RequestMapping(value = "/maintopdownload.do", method = RequestMethod.GET)
	private void download3(HttpServletResponse response) throws Exception {
		// /resources/Board
		String absPath = servletContext.getRealPath(CURR_MAIN_IMAGE);
		System.out.println(absPath);
		OutputStream out = response.getOutputStream();

		String filePath = absPath;

		System.out.println(filePath);

		File image = new File(filePath);

		File[] Files = image.listFiles();

		String filename = "";
		if (Files != null && Files.length > 0) {
			filename = Files[0].getAbsolutePath();
		}

		File file = new File(filename);

		Thumbnails.of(file).size(900, 400).toOutputStream(out);

		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
	}

}
