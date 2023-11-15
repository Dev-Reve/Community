package com.spring.community.member.Controller;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ConcurrentReferenceHashMap.ReferenceType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.spring.community.member.VO.KakaoInfo;
import com.spring.community.member.VO.MemberVO;
import com.spring.community.member.VO.OAuthToken;
import com.spring.community.member.service.MemberService;
import com.spring.community.member.service.MemberServiceImpl;

@Controller("KakaoController")
public class KakaoController {
	
	@Autowired
	MemberVO vo;
	 
	@Autowired
	MemberService service;
	
	//받은 인가코드는 클라이언트가 로그인 요청할 때 마다 랜덤으로 바뀌는 값
	@RequestMapping(value="/member/kakaoCallback", produces = "application/json;charset=UTF-8", method=RequestMethod.GET)
	public @ResponseBody ModelAndView kakaoLogin(String code, HttpServletRequest request, HttpServletResponse response) throws Exception { //1. code매개변수로 인가코드 받기
		System.out.println("##########: " + code);
		
		//2. POST방식으로 key=value 데이터를 카카오 서버쪽으로 Token 요청
		RestTemplate rt = new RestTemplate();
		
		//1) HTTP POST 메소드에 헤더영역에 Content-type 1개의 값 저장
		//→  카카오 서버로 요청 시 HTTP POST메소드의 바디영역에 담긴 데이터가 key, value 한 쌍의 형태라는 것을 웹브라우저를 통해 알려주기 위해 저장
		HttpHeaders headers = new HttpHeaders();
					headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
					
		//2) HTTP POST메소드 바디영역에 4개의 요청할 값 저장
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "24341d26e46f6a5b4c17148bfb5e6d48");
		params.add("redirect_uri", "http://localhost:8090/community/member/kakaoCallback");
		params.add("code", code);
		
		//3) 카카오서버에 token을 요청할 데이터를 담을 HttpEntity 객체 생성
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		//4) 카카오 서버에 Http Post메소드로 token 요청
		//→  exchange()메소드 호출의 반환 값으로 responseEntity변수에 token값을 문자열로 받아 저장
														  //토큰요청할 REST API방식의 요청주소,  요청메소드 POST, 요청할 헤더+바디 값, 응답받을 token 데이터 타입
		ResponseEntity<String> responseEntity = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);
		
		//JSONObject의 값들을 파싱하여 VO클래스의 각 변수에 매핑해서 저장시킬 라이브러리로 ObjectMapper 사용
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);
		
		System.out.println("kakaoLogin access_token: " + oAuthToken.getAccess_token());
		
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		//3. 받은 액세스 토큰을 카카오 서버로 전달하여 카카오 DB에 저장된 회원 정보 받기
		//   (POST방식으로 key=value데이터를 카카오 DB서버쪽으로 요청)
		RestTemplate rt2 = new RestTemplate();
		
		//1) HTTP POST 메소드에 헤더영역에 Content-type 1개의 값 저장
		//→  카카오 서버로 요청 시 HTTP POST메소드의 바디영역에 담긴 데이터가 key, value 한 쌍의 형태라는 것을 웹브라우저를 통해 알려주기 위해 저장
		HttpHeaders headers2 = new HttpHeaders();
					headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
					headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//2) HTTP POST 메소드 호출하여 카카오 DB 서버에 요청할 값을 전달할 때 바디영역에 담아서 전송할 데이터가 없으므로 작성하지 않음
					
		//3) 카카오 서버에 토큰을 요청할 데이터를 담을 HttpEntity 객체 생성
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		//4) 카카오 서버에 Http Post 메소드로 토큰 요청
		//   exchange()메소드 호출의 반환값으로 responseEntity 변수에 Token값을 문자열로 받아 저장
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest2, String.class);
					
		//응답 데이터 확인
		System.out.println(response2.getBody());
		
		//JSONObject값들을 파싱하여 VO클래스의 각 변수에 매핑해서 저장시킬 라이브러리로 ObjectMapper 사용
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoInfo kakaoInfo = objectMapper2.readValue(response2.getBody(), KakaoInfo.class);
		System.out.println("imgRepo: " + kakaoInfo.getProperties().getProfile_image());
		
		String id = kakaoInfo.getId();
		
		vo = service.selectMember(id); 
		ModelAndView mav = new ModelAndView(); 
		
		if(vo != null) {
			System.out.println("if문 탑승");
			Map<String, Object> map = new HashMap<String, Object>();
 			service.kakaoLogin(vo);
 			HttpSession session = request.getSession();
 			session.setAttribute("isLogOn", true);
 			session.setAttribute("member", vo);
 			session.setAttribute("isKakao", true);
 			
			mav.setViewName("redirect:/main/index.do");
		} else {
			System.out.println("else문 탑승");
			HttpSession session = request.getSession();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("name", kakaoInfo.getKakao_account().getName());
			map.put("email", kakaoInfo.getKakao_account().getEmail());
			map.put("fileName", kakaoInfo.getProperties().getProfile_image());
			mav.addObject("map", map);
			mav.addObject("center", "/WEB-INF/views/member/memberForm.jsp");
			mav.setViewName("main");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/kakao/logout", method = RequestMethod.GET)
	public ModelAndView kakaoLogout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.removeAttribute("isKakao");
		session.removeAttribute("isLogOn");
		session.removeAttribute("member");
		
		mav.setViewName("redirect:/main/index.do");
		
		return mav;
	}
	
	
}
