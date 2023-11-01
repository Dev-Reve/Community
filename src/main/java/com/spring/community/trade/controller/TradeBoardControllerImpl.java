package com.spring.community.trade.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.file.vo.FileVO;
import com.spring.community.trade.service.TradeBoardService;
import com.spring.community.trade.vo.TradeVO;

@Controller("tradeController")
public class TradeBoardControllerImpl implements TradeBoardController {
	
	//1. LoggerFactory클래스를 이용하여 LogeerClass의 객체를 가져옴
	private static final Logger logger = LoggerFactory.getLogger(TradeBoardControllerImpl.class);
	
	//파일이 실제 업로드되는 폴더 경로 저장
	private static final String CURR_IMAGE_REPO_PATH = "/src/main/webapp/resources/Board/trade/ImagesRepo";
	
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
		 
		//입력한 값들 + 다중업로드 요청한 파일의 정보들을 저장할 Map 생성
		Map<String, String> map = upload(request, response);
		
		//request에서 값을 꺼내와 Enumeration배열에 저장 후 배열 자체를 리턴
		Enumeration enu = request.getParameterNames();
		
		int tradeNo = 0;
		 
		while (enu.hasMoreElements()) {
			String key = (String)enu.nextElement();
			
			String value = request.getParameter(key);
			 
			map.put(key, value);
		}
		
		tradeNo = tradeService.regTradeBoard(map);
		FileVO vo = new FileVO();
		System.out.println("tradeNo: " + tradeNo);
		
		List fileList = new ArrayList();
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			fileList.add(fileName);
		}
		vo.setTrade_no(tradeNo);
		vo.setFileNames(fileList);
		tradeService.regTradeFile(vo);
		
		/*for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			
			//temp폴더에 임시로 업로드된 파일에 접근하기 위해 File객체 생성
			File srcFile = new File(CURR_IMAGE_REPO_PATH + "/temp/" + fileName);
			//temp폴더 밖에 글번호 폴더를 생성하기 위해 경로를 만들어서 File객체 생성 후 저장
			File destDir = new File(CURR_IMAGE_REPO_PATH + "/" + tradeNo);
			//글번호 폴더 자동 생성
			destDir.mkdirs();
			//temp폴더에 임시로 업로드된 이미지 파일을 글번호를 이름으로하는 폴더로 이동
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}*/
		
	//	tradeService.regTradeFiles();
		ModelAndView mav = new ModelAndView("redirect:/trade/tradeList.do");
		
		
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
	 * @RequestMapping(value = "/trade/tradeDetail.do", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView viewTradeDetail(@RequestParam("no")
	 * int no, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * 
	 * //서비스 객체 메소드 호출 시 no를 전달하여 select vo = tradeService.viewTradeDetail(no);
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.addObject("tradevo", vo);
	 * mav.setViewName(getViewName(request));
	 * 
	 * return mav; }
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
	
	//파일을 업로드한 후 반환된 파일 이름이 저장된 fileList배열을 다시 map에 저장하는 기능의 메소드 
	private Map<String, String> upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		
		String encoding = "utf-8";
		
		Map<String, String> articleMap = new HashMap<String, String>();
		
		//글쓰기 할 때 첨부한 이미지파일을 저장할 폴더 경로에 접근할 File객체 생성
		File currentDirPath = new File(CURR_IMAGE_REPO_PATH);
		
		//업로드할 파일 데이터를 임시로 저장할 객체 메모리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//파일 업로드 시 사용할 임시 메모리 최대 크기를 1MB로 설정
		factory.setSizeThreshold(1024 * 1024 * 1);
		//임시 메모리에 파일 업로드 시 지정한 1MB 크기를 넘을 경우 업로드 될 폴더 경로 설정
		factory.setRepository(currentDirPath);
		
		//파일을 업로드할 임시 메모리 객체의 주소를 생성자쪽으로 전달해 저장한 파일 업로드를 실제 처리할 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(multipartRequest);
			
			//ArrayList배열에 저장된 DiskFileItem객체의 갯수만큼 반복
			for(int i=0; i<items.size(); i++) {
				//ArrayList배열에서 DiskFileItem객체를 얻기
				FileItem fileItem = (FileItem)items.get(i);
				
				//얻은 DiskFileItem객체의 정보가 첨부한 파일 요청이 아닐 경우
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + " = " + fileItem.getString(encoding));
					
					//articleForm.jsp페이지에서 입력한 글제목, 글내용만 따로 HashMap에 (key=value)형식으로 저장
					//HashMap에 저장된 모습 → {title = "입력한글제목", content = "입력한글내용"}
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} else { //얻은 DiskFileItem객체의 정보가 첨부한 파일인 경우
					System.out.println("요청한 <input>의 name속성값: " + fileItem.getFieldName());
					System.out.println("업로드 요청한 첨부 이미지 파일명: " + fileItem.getName());
					System.out.println("업로드 요청한 첨부 이미지 파일 크기: " + fileItem.getSize() + "bytes");
					
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						
						System.out.println(idx);
						
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
							System.out.println("첨부할 파일명에 /없음 : " + idx);
						}
						
						//업로드할 파일 명 얻기
						String fileName = fileItem.getName().substring(idx + 1);
						//업로드할 파일 경로 + 파일명 을 주소로 만들어서 접근할 file객체 생성
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						//실제 파일 업로드
						fileItem.write(uploadFile);
						
					}// 안쪽 if
					
				}// else문
			} //for문
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;//업로드한 파일명들이 저장된 ArrayList배열 반환 
		
	}// upload 메소드 닫는 기호 
	
	
}
