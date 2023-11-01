package com.spring.community.member.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.community.member.service.MemberService;
import com.spring.community.member.VO.MemberVO;
import com.spring.community.member.DAO.MemberDAO;
import com.spring.community.member.service.MemberServiceImpl;

//MVC중에 C  
//사장 
//id속성값이 memberController인 
//<bean id="memberController" 
//      class="com.spring.member.controller.MemberControllerImpl">을 자동 생성해 줍니다.
@Controller("memberController")
public class MemberControllerImpl  implements MemberController {
	
	//1. LoggerFactory클래스를 이용해 Logger클래스의 객체를 가져옵니다.
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	//파일이 실제 업로드되는 폴더 경로 저장
	private static final String CURR_IMAGE_REPO_PATH = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\wtpwebapps\\community\\resources\\profile\\ImagesRepo";
	//id속성값이 memberService인 
	//<bean id="memberService" 
	//    class="com.spring.member.service.MemberServiceImpl">을 자동 주입 해줍니다.
	@Autowired
	private MemberServiceImpl memberService; 

	//id속성값이 memberVO인
	//<bean id="memberVO" 
	//      class="com.spring.member.vo.MemberVO">을 자동 주입해 줍니다. 
	@Autowired
	private MemberVO memberVO;
	
	
	
	// /member/listMembers.do DB에 저장된 모든 회원 조회 요청 주소를 받았을때 호출 되는 메소드로
	
	@Override
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) 
											throws Exception {	
		
	//요청한 주소를 이용해 응답할 값을 마련
		//부장 MemberServiceImpl객체의 listMembers()메소드를 호출하여
		//모든 회원 조회 요청을 명령함!
		//웹브라우저로 응답할 조회한 정보들이 담긴  List배열을 반환 받는다.
		List membersList = memberService.listMembers();
	
	//응답할 뷰 이름 얻기 	
		//요청 URL주소  /member/listMembers.do 에서  .do를 제외한 /listMembers뷰이름얻기
		String viewName = getViewName(request); 
		

	//응답할 값 과 응답할 뷰 이름을  ModelAndView객체 메모리에 바인딩(저장)
		ModelAndView mav = new ModelAndView();
					 //응답할 데이터 저장
					 mav.addObject("membersList", membersList);	
					//뷰 이름 저장 
					 mav.setViewName(viewName);
		
		return mav;//디스팩처 서블릿으로 ModelAndView객체 반환 
		
	}
	
	//여러 요청주소에 대해 한개의 메소드를 호출할 경우 정규식을 이용해 매핑하는 역할을 합니다. 
	//요청한 주소가 Form.do로 끝나는 주소로 요청하면 Form메소드가 호출되게 작성 
	@Override     
	@RequestMapping(value={"/member/memberForm.do"}, method=RequestMethod.GET )
	public ModelAndView Form(HttpServletRequest request, 
							       HttpServletResponse response) 
									throws Exception {	
		
		String viewName = getViewName(request);//   /member/memberForm
		System.out.println(viewName); // /member/memberForm
		
		ModelAndView mav = new ModelAndView();
					 mav.addObject("center", "/WEB-INF/views"+viewName +".jsp");
					 mav.setViewName("main");
		
		return mav;
	}
	
	
	
		//요청한 주소가 loginForm.do 주소로 요청하면 Form메소드가 호출되게 작성 
		@Override     
		@RequestMapping(value={"/member/loginForm.do"}, method=RequestMethod.GET )
		public ModelAndView Form2(HttpServletRequest request, 
								       HttpServletResponse response) 
										throws Exception {	
			
			String viewName = getViewName(request);//   /member/memberForm
			System.out.println(viewName); // /member/memberForm
			
			ModelAndView mav = new ModelAndView();
			 mav.addObject("center", "/WEB-INF/views"+viewName +".jsp");
			 mav.setViewName("main");

			return mav;
		}
	
	/*
	RedirectAttributes클래스를 이용해 재요청시 로그인창으로 로그인 실패메세지를 매개변수로 전달할수있습니다
	회원정보를 조회해서 해당회원정보가 존재하면 로그인상태와 조회된 회원정보를 session에 저장합니다
	그리고 로그아웃 요청했을때 session의 정보를 모두 삭제합니다
	*/
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
							
	public ModelAndView login(
												//로그인실패시 리다이렉트하여 실패메시지를 전달할 객체를 매개변수로 받는다
												  RedirectAttributes rAttr,
												  HttpServletRequest request,
												  HttpServletResponse response
												  )throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		memberVO.setId(id);
		System.out.println(id);
		memberVO.setPassword(password);
		System.out.println(password);
		memberVO = memberService.login(memberVO);
		
		//입력한 아디비번에 해당하는 회원정보가 조회가 되면?
		if(memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO); //조회된 회원정보 저장
			session.setAttribute("isLogOn", true); //로그인 상태값을 true로 저장

			mav.addObject("center", "/WEB-INF/views/common/index.jsp");
			mav.setViewName("main");
			
		}else {
			
			rAttr.addAttribute("result","loginFailed");//로그인 실패시 시랲메세지를 로그인창으로 전달하기 위해 저장
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}
	@Override
	@RequestMapping(value = "member/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
							   HttpServletResponse response)throws Exception {
		
		HttpSession session = request.getSession();
		//로그아웃 요청시 세션에 저장된 로그인정보와 조회한 회원정보를 삭제합니다
		
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		
		return mav;
	}
	
	
	// 회원가입 요청 주소 /member/addMember.do를 받았을때....
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(
								  MultipartHttpServletRequest multipartRequest,
								  HttpServletResponse response) 
										  throws Exception {	
		
		
			//업로드할 파일명 또는 입력한 데이터가 한글일 경우 인코딩 방식 UTF-8로 설정
			multipartRequest.setCharacterEncoding("UTF-8");
			
			//파일업로드후 반환된 파일이름 배열로 반환
			List fileList = fileProcess(multipartRequest);
			
			String id = multipartRequest.getParameter("id");
	         String password = multipartRequest.getParameter("password");
	         String name = multipartRequest.getParameter("name");
	         String ssn = multipartRequest.getParameter("ssn");
	         String nickname = multipartRequest.getParameter("nickname");
	         String email = multipartRequest.getParameter("email");
	         String addr1 = multipartRequest.getParameter("addr1");
	         String addr2 = multipartRequest.getParameter("addr2");
	         String addr3 = multipartRequest.getParameter("addr3");
	         String addr4 = multipartRequest.getParameter("addr4");
	         String fileName = (String) fileList.get(0);
	    
	         System.out.println(id);
	         System.out.println(password);
	         System.out.println(name);
	         System.out.println(ssn);
	         System.out.println(nickname);
	         System.out.println(email);
	         System.out.println(addr1);
	         System.out.println(addr2);
	         System.out.println(addr3);
	         System.out.println(addr4);
	         System.out.println(fileName);
	         
	         
	         
	         memberVO.setId(id);
	         memberVO.setPassword(password);
	         memberVO.setName(name);
	         memberVO.setSsn(ssn);
	         memberVO.setNickname(nickname);
	         memberVO.setEmail(email);
	         memberVO.setAddr1(addr1);
	         memberVO.setAddr2(addr2);
	         memberVO.setAddr3(addr3);
	         memberVO.setAddr4(addr4);
	         memberVO.setFileName(fileName);
	         
	       //부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 INSERT명령!
	 		memberService.addMembers(memberVO);
		
	         
		String viewName = getViewName(multipartRequest);
		System.out.println(viewName); 	
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/common/index.jsp");
		mav.setViewName("main");
			
		//회원가입 후 모든회원을 조회 하는 재요청 주소 작성 
		return mav;
 
	}
	
	
	
	//회원삭제 기능 
	///member/memberDel.do
	@Override
	@RequestMapping(value="/member/memberDel.do", method=RequestMethod.GET)
	public ModelAndView memberDel(@RequestParam("id") String id,
									HttpServletRequest request, 
									HttpServletResponse response) 
									throws Exception {
			
		request.setCharacterEncoding("UTF-8");

	
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 DELETE명령!
		memberService.delMembers(id);		 
			
		//회원 삭제후 모든회원을 조회 하는 재요청 주소 작성 
		return new ModelAndView("redirect:/member/listMembers.do");
	}
	
	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	// 수정링크를 누르면 요청 주소   ->  /member/memberDetail.do
	@Override
	@RequestMapping(value="/member/memberDetail.do", method=RequestMethod.GET)
	public ModelAndView memberDetail(HttpServletRequest request, 
									 HttpServletResponse response) 
											 throws Exception {
		
		request.setCharacterEncoding("UTF-8");
			
		//요청한 값(수정할 회원을 조회 하기 위한 아이디값) 얻기
		String id = request.getParameter("id");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 SELECT명령!
		MemberVO vo = memberService.detailMembers(id);		 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("membervo",vo);
		mav.setViewName( getViewName(request) ); // /memberDetail
	 
		return mav;
		
		
	}
	
	//수정 요청 /member/UpdateMember.do 주소를 받았을때
	@Override
	@RequestMapping(value="/member/UpdateMember.do", method=RequestMethod.POST)
	public ModelAndView UpdateMember(@ModelAttribute("member") MemberVO member,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("UTF-8");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 수정할 id를 전달하여 UPDATE명령!
		memberService.UpdateMember(member);		 
			
		//회원 수정후 모든회원을 조회 하는 재요청 주소 작성 
		return  new ModelAndView("redirect:/member/listMembers.do");
		
	
	}
	

	
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
				File file = new File(CURR_IMAGE_REPO_PATH + "\\" + originFileName);
				
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
					mFile.transferTo( new File(CURR_IMAGE_REPO_PATH + "\\" + originFileName) );
					
				}
			
			}
			
			return fileList;//업로드한 파일명들이 저장된 ArrayList배열 반환 
			
			
		}// fileProcess 메소드 닫는 기호 




	
}
