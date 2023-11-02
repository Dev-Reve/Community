package com.spring.community.trade.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.trade.service.TradeBoardService;
import com.spring.community.trade.vo.TradeVO;

import net.coobird.thumbnailator.Thumbnails;

@Controller("tradeController")
public class TradeBoardControllerImpl implements TradeBoardController, ServletContextAware {
	
	//1. LoggerFactory클래스를 이용하여 LogeerClass의 객체를 가져옴
	private static final Logger logger = LoggerFactory.getLogger(TradeBoardControllerImpl.class);
	
	//파일이 실제 업로드되는 폴더 경로 저장
	private static final String CURR_IMAGE_REPO_PATH = "/resources/Board/trade";
	private static final String RESOURCE_PATH = "/resources/images/";
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Autowired
	private TradeBoardService tradeService;
	
	@Autowired
	private TradeVO vo;
	
	@Override
	@RequestMapping(value = "/trade/tradeList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listTradeBoards(String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> tradeMap = new HashMap<String, Object>();
		tradeMap = tradeService.listTradeBoards(pageNum);
		
		//응답할 뷰 이름 얻기 
		String viewName = getViewName(request);
		
		//응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩
		ModelAndView mav = new ModelAndView();
		//응답할 값 저장
		mav.addObject("tradeList", tradeMap);
		mav.addObject("center", "/WEB-INF/views/trade/tradeBoard.jsp");
		//뷰명 저장
		mav.setViewName("trade/tradeMain");
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/trade/*Form.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//응답할 뷰 이름 얻기 
		String viewName = getViewName(request);
		logger.info("-------: " + viewName);
		
		//응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views" + viewName + ".jsp");
		mav.setViewName("trade/tradeMain");
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/trade/regTrade.do", method = {RequestMethod.GET, RequestMethod.POST}) 
	public ModelAndView regTradeBoard(@RequestParam("files") List<MultipartFile> files, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		request.setCharacterEncoding("UTF-8");
		
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		
		//입력한 값들 + 다중업로드 요청한 파일의 정보들을 저장할 Map 생성
		Map map = new HashMap();;
		
		//request에서 값을 꺼내와 Enumeration배열에 저장 후 배열 자체를 리턴
		Enumeration enu = request.getParameterNames();
		
		while (enu.hasMoreElements()) {
			String key = (String)enu.nextElement();
			
			String value = request.getParameter(key);
			 
			map.put(key, value);
		}
		List<String> fileNames = new ArrayList<String>();
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			fileNames.add(fileName);
		}
		map.put("fileList", fileNames);
		
		int no = tradeService.regTradeBoard(map);
		List fileList = fileProcess(request);
		map.put("filesList", fileList);
		
		for(MultipartFile file : files) {
	        String originalFileName = file.getOriginalFilename();
	        
	        // 폴더 생성
	        File tradeDir = new File(absPath + "/" + no);
	        tradeDir.mkdirs();
	        
	        String filePath = absPath + "/" + no + "/" + originalFileName; // 파일 경로를 글 번호 폴더에 변경
	        File dest = new File(filePath);
	        
	        // 파일을 해당 폴더로 이동
	        file.transferTo(dest);
	        
	        fileNames.add(originalFileName);
	    }
		
		
		ModelAndView mav = new ModelAndView();
					 mav.addObject("map", map);
					 mav.setViewName("redirect:/trade/tradeDetail.do?no=" + no);
		return mav; 
	}
	
	@Override
	@RequestMapping(value = "/trade/thumbnail.do")
	public void thumbnail(@RequestParam("no") int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//사진을 내려받기 위한 출력 스트림 통로 객체 생성
		OutputStream out = response.getOutputStream();
		//사진이 저장된 경로를 찾아가기 위해 절대 경로 저장 
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		String resourcePath = servletContext.getRealPath(RESOURCE_PATH);
		
		//다운로드할 파일위치의 파일경로 생성
		String filePath = absPath + "/" + no;
		
		//이미지 파일을 접근해서 파일을 조작, 정보보기 등을 할 수 있는 파일 객체 생성
		File images = new File(filePath);
		//파일경로에 존재하는 이미지 파일들을 담을 파일배열 생성
		File[] files = images.listFiles();
		
		String imageFileName = "";
		
		//만약 경로내에 파일이 존재한다면 첫번째 파일의 경로 반환
		if(files != null && files.length > 0) {
			imageFileName  = files[0]. getAbsolutePath();
		} else {
			imageFileName  = resourcePath + "a.jpg";
			System.out.println(imageFileName);
		}
		File image = new File(imageFileName);
		
		Thumbnails.of(imageFileName).size(220, 220).toOutputStream(out);
		
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		
	}
	
	@Override
	@RequestMapping(value = "/trade/imageList.do")
	public void download(@RequestParam("imageFileName") String imageFileName,
				   	 	 @RequestParam("no") int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//사진을 내려받기 위한 출력 스트림 통로 객체 생성
		OutputStream out = response.getOutputStream();
		//사진이 저장된 경로를 찾아가기 위해 절대 경로 저장 
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		String resourcePath = servletContext.getRealPath(RESOURCE_PATH);
		
		//다운로드할 파일위치의 파일경로 생성
		String filePath = absPath + "/" + no + "/" + imageFileName;
		
		//이미지 파일을 접근해서 파일을 조작, 정보보기 등을 할 수 있는 파일 객체 생성
		File file = new File(filePath);
		
		//캐시를 사용하지 않고 항상 최신 데이터를 보여주기 위한 header 설정
		response.setHeader("Cache-Control", "no-cache");
		//다운로드할 파일명을 response객체에 설정
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		
		//다운로드할 파일을 바이트 데이터 단위로 읽어들일 입력스트림통로 생성
		FileInputStream in = new FileInputStream(file);
				
				
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			//매개변수로 지정된 바이트 배열의 데이터를 읽어들이기
			int count = in.read(buffer);
			
			//읽어들일 파일의 내용이 없으면 while을 빠져나감
			if(count == -1) break;
			
			//출력 스트림 통로를 통해 한번에 8kb씩 브라우저로 전송하여 다운로드
			out.write(buffer, 0, count);
		}//while
		
		in.close();
		out.close();
	}
	
	
	@RequestMapping(value = "/trade/tradeDetail.do", method = {RequestMethod.GET, RequestMethod.POST}) 
	public ModelAndView viewTradeDetail(@RequestParam("no") int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		request.setCharacterEncoding("UTF-8");
	
		//서비스 객체 메소드 호출 시 no를 전달하여 select 
		vo = tradeService.viewTradeDetail(no);
//		System.out.println(vo.getTitle());
//		System.out.println(vo.getContent());
//		System.out.println(vo.getCategory());
//		System.out.println(vo.getNo());
//		System.out.println("price: " + vo.getPrice());
//		System.out.println(vo.getSellStat());
		
//		String[] fileNames = vo.getFileNames();
//		for(String fileName : fileNames) {
//			System.out.println(fileName);
//		}

		ModelAndView mav = new ModelAndView(); 
		mav.addObject("vo", vo);
		mav.addObject("center", "/WEB-INF/views/trade/tradeDetail.jsp");
		mav.setViewName("trade/tradeMain");
	
		return mav; 
	}
		
	
	/*
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/categoriedList.do") public ModelAndView
	 * listTradeCategory(@RequestParam("category") String category,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception {
	 * List categoriedList = tradeService.listTradeCategory(category);
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * //응답할 뷰 이름 얻기 String viewName = getViewName(request);
	 * 
	 * //응답할 값과 뷰명을 ModelAndView객체 메모리에 바인딩 ModelAndView mav = new ModelAndView();
	 * //응답할 값 저장 mav.addObject("categoriedList", categoriedList); //뷰명 저장
	 * mav.setViewName(viewName);
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/modTrade.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView modTradeBoard(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * tradeService.modTradeBoard(no);
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/trade/tradeList.do");
	 * 
	 * return mav; }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/trade/delTrade.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView delTradeBoard(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { request.setCharacterEncoding("UTF-8");
	 * 
	 * tradeService.delTradeBoard(no);
	 * 
	 * return new ModelAndView("redirect:/trade/tradeList.do"); }
	 */

	//request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구하는 메소드 
	private String getViewName(HttpServletRequest request) throws Exception {

		String contextPath = request.getContextPath();
		
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;

		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;

		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);

		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}

		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;

	}
	
	//파일을 업로드한 후 반환된 파일 이름이 저장된 fileList배열을 반환하는 메소드 
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) 
						 		    throws Exception{
		String absPath = servletContext.getRealPath(CURR_IMAGE_REPO_PATH);
		System.out.println(absPath);
		
		List<String> fileList = new ArrayList<String>();
		
		//첨부된 파일들의 input 태그의 name속성값=CommonsMultipartFile객체 한쌍씩 저장된  LinkedKeyIterator 배열 자체를 반환 합니다. 
		//{file1=[CommonsMultipartFile@42b715da], file2=[CommonsMultipartFile@78a15f55]}
		//							0							1
		Iterator<String>  fileNames = multipartRequest.getFileNames();
		
		//LinkedKeyIterator 배열에  CommonsMultipartFile객체들이 저장되어 있는 동안 반복
		while(fileNames.hasNext()) {
			
			//LinkedKeyIterator 배열에 저장된 첨부한 <input>태그의 name속성값을 반복해서 얻는다. 
			String fileName = fileNames.next(); // file1, file2
			
			//<input type="file">태그에 첨부한 파일의 정보가 저장된 fileItem객체를 반환 받는다.
			/*
			아래는 하나의 fileItem객체의 정보 입니다. 
			name=duke.png, 
			StoreLocation=C://Program Files//Apache Software Foundation//Tomcat 9.0//work//Catalina//localhost//pro28//upload_705ab047_18b7f334aa0__8000_00000002.tmp,
			size=4437bytes, isFormField=false, FieldName=file1
			*/
			MultipartFile mFile = multipartRequest.getFile(fileName);
			
			//fileItem객체에서 실제 업로드(첨부)한 파일이름을 반복해서 가져오기
			//duke.png, duke2.png
			String originFileName = mFile.getOriginalFilename();
			
			//실제 업로드한 파일이름을 하나씩 반복해서  ArrayList배열에 추가 하여 저장
			fileList.add(originFileName); //[duke.png, duke2.png]
			
			//c:\spring\image_repo\duke.png  업로드할 파일 경로   
			//c:\spring\image_repo\duke2.jpg    업로드할 파일 경로
			File file = new File(absPath + "\\temp\\" + originFileName);
			
			//첨부되어 업로드할 파일사이즈가 있는지  (업로드할 파일이 있는지) 체크 합니다.
			if(mFile.getSize() != 0) { 
			
				//업로드된 파일을 저장할위치가 존재 하지 않는지 확인합니다.
				//파일이 존재 하지 않으면  업로드한 파일을 저장할 디렉토리와 파일을 생성해야 합니다. 
				if(! file.exists()) {
					
					//c:\spring\image_repo
					File file1 =   file.getParentFile();
					
					//c:\spring\image_repo 업로드될 폴더 생성 
					file1.mkdirs();
				}
				
				//임시로 저장된 fileItem객체를 지정된 대상 파일로 전송하며, 
				//업로드한 파일을 원하는 위치에 저장하고 동일한 이름을 가진 기존파일을 덮어 씁니다.
				mFile.transferTo( new File(absPath + "\\temp\\" + originFileName) );
				
			}
		}
		
		return fileList;//업로드한 파일명들이 저장된 ArrayList배열 반환 
		
	}// fileProcess 메소드 닫는 기호 
	
	
	
}
