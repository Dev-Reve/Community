package com.spring.community.gallery.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/file")
public class GalleryFileController extends HttpServlet implements ServletContextAware{
	
	
	private static String CURR_IMAGE_REPO_PATH = "/resources/Board";
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value="/galleryList.do", method = RequestMethod.GET)
	private void GalleryList(@RequestParam("no") int no,
									HttpServletResponse response) throws Exception {
		
		//절대경로 설정
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		//파일을 내보낼 아웃풋 스트림객체 생성
		OutputStream out = response.getOutputStream();
		//저장된 파일이 있는 경로 설정하기
		String filePath = absPath + "/gallery/" + no + "/";
		System.out.println("갤러리게시판 썸네일 다운로드 경로 :" + filePath );
		
		//파일 가져오기
		File image = new File(filePath);
		
		File[] files = image.listFiles();
		
		String filename = "";
		
		//파일리스트가 null이 아니거나 files리스트 내부의 객체가 0개보다 많으면
		//파일이름을 포함한 파일 경로의 절대경로를 가져온다.
		if(files != null && files.length > 0) {
			filename = files[0].getAbsolutePath();
		}
		
		//가져온 절대 경로로 파일을 생성하고
		File file = new File(filename);
		
		//Thumbnails 클래스를 통하여 사진사이를 조절 한 후에 출력ㄴ
		Thumbnails.of(file).size(170, 190).toOutputStream(out);
		
		byte[] buffer = new byte[1024 * 8];
		
		out.write(buffer);
		
		
	}
  

}
