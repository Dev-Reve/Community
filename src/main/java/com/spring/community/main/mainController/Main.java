package com.spring.community.main.mainController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.mainService.mainService;
import com.spring.community.trade.vo.TradeVO;

@Controller("MainController")
public class Main {

	// 기상청 단기 데이터 추가 1.
		private static String SERVICEKEY = "Dglnu%2FfJuIk6BVkrIGSgocWWgQU%2BqAQ27%2BAAbaJydH%2BuyHdxudOog2ma2sDFWy03MFpdlJNc75zYrB2sZd2NHQ%3D%3D";
		private static String TODAY = new SimpleDateFormat("yyyMMdd").format(new Date()) ;
		private static String COORD_X = "98";
		private static String COORD_Y =	 "76";

    @Autowired
    private mainService mainService;
    
    
    @RequestMapping(value = "/main/index.do", method = RequestMethod.GET)
    public ModelAndView main() throws Exception{
        System.out.println("firstmain 호출");

        List<GalleryVO> ReGallerylist = mainService.getRecentGarallyList();
        List<TradeVO> ReTradelist = mainService.getRecentTradeList();
        
        
        ModelAndView mav = new ModelAndView();
        
        
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(TODAY, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(COORD_X, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(COORD_Y, "UTF-8")); /*예보지점의 Y 좌표값*/
        
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET");
        
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        // -- json 파싱 부분 --
        String result = sb.toString();
        //System.out.println("result : " + result);
        
        // response 키를 가지고 데이터를 파싱
        JSONObject jsonPasing = new JSONObject(result);
        //System.out.println("jsonpasing : " + jsonPasing);
        
        JSONObject response =  jsonPasing.getJSONObject("response");
        //System.out.println("response : " + response);
        
        if (response.has("body")) {
            JSONObject body = response.getJSONObject("body");

            // 나머지 코드
            JSONObject items = body.getJSONObject("items");
            JSONArray item = items.getJSONArray("item");

            Map pubData = new HashMap();

            for (int i = 0; i < item.length(); i++) {
                items = item.getJSONObject(i);
                String category = items.getString("category");
                String obsrValue = items.getString("obsrValue");

                System.out.print(category + " ");
                System.out.println(obsrValue);
                pubData.put(category, obsrValue);
            }
            mav.addObject("pubDate", pubData);
        } else {
            System.out.println("The 'body' key is not found in the JSON response.");
        }
        
        mav.addObject("ReT", ReTradelist);
        mav.addObject("ReG", ReGallerylist);
        mav.addObject("center", "/WEB-INF/views/common/First.jsp");
        mav.setViewName("main");

        return mav;
    }

    
}
