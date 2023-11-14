package com.spring.community.gallery.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.GalleryDao.GalleryDao;
import com.spring.community.gallery.GalleryService.GalleryService;
import com.spring.community.gallery.GalleryService.GalleryServiceImpl;
import com.spring.community.gallery.vo.GalleryCommentVO;
import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.tradeComment.vo.TradeCommentVO;

@Controller("GalleryController")
public class GalleryControllerImpl extends HttpServlet implements GalleryController, ServletContextAware {

	private static String CURR_IMAGE_REPO_PATH = "/resources/Board/gallery";
	private static String RESOURSE_PATH = "/resources/images";
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Autowired
	private GalleryService galleryservice ;

	@Autowired
	private GalleryVO vo;

	//갤러리 게시판 메인으로 가는 메소드
	@Override
	@RequestMapping(value = "/gallery/main.do", method = RequestMethod.GET)
	public ModelAndView GalleryMain(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		int pageNum = 1;
		
		String pageParam = request.getParameter("page");
		
		if(pageParam != null && !pageParam.isEmpty()) {
			pageNum = Integer.parseInt(pageParam);
		}
		
		
		List<GalleryDao> list = galleryservice.getGalleryList();
		
		int gall = list.size();
		System.out.println(pageNum + " 페이지 입니다.");
		System.out.println( gall + "개의 글이 있습니다.");
		
		mav.addObject("page", pageNum);
		mav.addObject("gallery", list);
		mav.addObject("center", "/WEB-INF/views/gallery/gallery.jsp");
		mav.setViewName("main");
		
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/gallery/regGalleryForm.do", method = RequestMethod.GET)
	public ModelAndView regGalleryForm(HttpServletRequest repuest, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/gallery/regGalleryForm.jsp");
		mav.setViewName("main");

		return mav;
	}
	
	@Override
	@RequestMapping(value="/gallery/galleryDetail.do")
	public ModelAndView GalleryDetail(@RequestParam int no) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		vo = galleryservice.getGalleryInfo(no);
		List<GalleryCommentVO> commentList = galleryservice.getComment(no);
		mav.addObject("commentList", commentList);
		mav.addObject("vo", vo);
		mav.addObject("center", "/WEB-INF/views/gallery/galleryDetail.jsp");
		mav.setViewName("main");
		return mav;
	}
	 @RequestMapping(value = "/gallery/regComment.do")
	 public ModelAndView regComment(@ModelAttribute("comment") GalleryCommentVO comment, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView mav = new ModelAndView();
		 
		 System.out.println("boardNo: " + comment.getBoardNo());
		 System.out.println("nickname: " + comment.getNickname());
		 System.out.println("parentNo: " + comment.getParentNo());
		 System.out.println("content: " + comment.getContent());
		 
		 galleryservice.regComment(comment); 
		 mav.setViewName("redirect:/gallery/galleryDetail.do?no=" + comment.getBoardNo());
		 
		 return mav;
	 }
	
	
	
	@Override
	@RequestMapping(value = "/gallery/image.do")
	public void downloadContent(@RequestParam("imageFileName") String imageFileName,
				   	 	 @RequestParam("no") int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OutputStream out = response.getOutputStream();
		
		System.out.println( "게시판 다운로드 " );
		
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		
		//다운로드할 파일위치의 파일경로 생성
		String filePath = absPath + "/" + no + "/" + imageFileName;
		// /resources/Board/gallery
		System.out.println( "게시판 다운로드 " + filePath);
		//이미지 파일을 접근해서 파일을 조작, 정보보기 등을 할 수 있는 파일 객체 생성
		File file = new File(filePath);

		response.setHeader("Cache-Control", "no-cache");
		
		response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(imageFileName, "UTF-8"));
		
		
		FileInputStream in = new FileInputStream(file);
							
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			int count = in.read(buffer);

			if(count == -1) break;
			out.write(buffer, 0, count);
		}//while	
		in.close();
		out.close();
	}
	 @Override
	 @RequestMapping(value = "/gallery/delComment.do", method = RequestMethod.GET)
	 public ModelAndView delComment(@RequestParam("no") int no, @RequestParam("boardNo") int boardNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 System.out.println(no);
		 
		//
		 galleryservice.delComment(no);
		 
		 ModelAndView mav = new ModelAndView();
		 
		 mav.setViewName("redirect:/gallery/galleryDetail.do?no=" + boardNo);
		 
		 return mav;
	 }
	
	
	@Override
	@RequestMapping(value = "/gallery/insertGallery.do")
	public ModelAndView InsertGallery(@RequestParam("files") List<MultipartFile> files,
			MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		Map map = new HashMap();

		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);

		// 글쓰기 작성 폼에서 요청한 파라미터들의 이름 배열로 저장
		Enumeration enu = request.getParameterNames();

		// Emumeration 배열에 저장된 값이 있으면?
		while (enu.hasMoreElements()) {
			// 배열에 저장된 파라미터 이름 받아오기
			String name = (String) enu.nextElement();
			// 받아온 이름으로 입력한 파라미터 값 받아오기
			String value = request.getParameter(name);
			// 받은 값들을 dao에 넘겨 insert 하기 위해 해쉬 맵에 저장하기
			map.put(name, value);
		}

		List<String> fileNames = new ArrayList<String>();

		// Interator로 받아온 file이름으로 배열에 저장
		for (MultipartFile file : files) {
			String Filename = file.getOriginalFilename();
			fileNames.add(Filename);

		}
		System.out.println(fileNames.toString());
		map.put("fileList", fileNames);

		int no = galleryservice.InsertGallery(map);
		List<String> fileList = fileProcess(request);
		
		map.put("filesList", fileList);
		
		for (MultipartFile file : files) {

			String originalFileName = file.getOriginalFilename();
			
			File galleryDir = new File(absPath + "/" + no);
			
			galleryDir.mkdirs(); // 디렉토리가 없으면 생성

			String filePath = absPath + "/" + no + "/" + originalFileName;
			
			File newFile = new File(filePath);

			// 파일을 복사
			file.transferTo(newFile);

		}

		ModelAndView mav = new ModelAndView("redirect:/gallery/main.do");

		return mav;
	}

	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		System.out.println(absPath);

		List<String> fileList = new ArrayList<String>();

//첨부된 파일들의 input 태그의 name속성값=CommonsMultipartFile객체 한쌍씩 저장된  LinkedKeyIterator 배열 자체를 반환 합니다. 
//{file1=[CommonsMultipartFile@42b715da], file2=[CommonsMultipartFile@78a15f55]}
//							0							1
		Iterator<String> fileNames = multipartRequest.getFileNames();

//LinkedKeyIterator 배열에  CommonsMultipartFile객체들이 저장되어 있는 동안 반복
		while (fileNames.hasNext()) {

//LinkedKeyIterator 배열에 저장된 첨부한 <input>태그의 name속성값을 반복해서 얻는다. 
			String fileName = fileNames.next(); // file1, file2

//<input type="file">태그에 첨부한 파일의 정보가 저장된 fileItem객체를 반환 받는다.
			/*
			 * 아래는 하나의 fileItem객체의 정보 입니다. name=duke.png, StoreLocation=C://Program
			 * Files//Apache Software Foundation//Tomcat
			 * 9.0//work//Catalina//localhost//pro28//
			 * upload_705ab047_18b7f334aa0__8000_00000002.tmp, size=4437bytes,
			 * isFormField=false, FieldName=file1
			 */
			MultipartFile mFile = multipartRequest.getFile(fileName);

//fileItem객체에서 실제 업로드(첨부)한 파일이름을 반복해서 가져오기
//duke.png, duke2.png
			String originFileName = mFile.getOriginalFilename();

//실제 업로드한 파일이름을 하나씩 반복해서  ArrayList배열에 추가 하여 저장
			fileList.add(originFileName); // [duke.png, duke2.png]

//c:\spring\image_repo\duke.png  업로드할 파일 경로   
//c:\spring\image_repo\duke2.jpg    업로드할 파일 경로
			File file = new File(absPath + "\\temp\\" + originFileName);

//첨부되어 업로드할 파일사이즈가 있는지  (업로드할 파일이 있는지) 체크 합니다.
			if (mFile.getSize() != 0) {

//업로드된 파일을 저장할위치가 존재 하지 않는지 확인합니다.
//파일이 존재 하지 않으면  업로드한 파일을 저장할 디렉토리와 파일을 생성해야 합니다. 
				if (!file.exists()) {

//c:\spring\image_repo
					File file1 = file.getParentFile();

//c:\spring\image_repo 업로드될 폴더 생성 
					file1.mkdirs();
				}

//임시로 저장된 fileItem객체를 지정된 대상 파일로 전송하며, 
//업로드한 파일을 원하는 위치에 저장하고 동일한 이름을 가진 기존파일을 덮어 씁니다.
				mFile.transferTo(new File(absPath + "\\temp\\" + originFileName));

			}
		}

		return fileList;// 업로드한 파일명들이 저장된 ArrayList배열 반환

	}// fileProcess 메소드 닫는 기호

}
