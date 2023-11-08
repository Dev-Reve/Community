package com.spring.community.pubdata.Controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class PubdataController {
	
	private static String SERVICEKEY = "Dglnu%2FfJuIk6BVkrIGSgocWWgQU%2BqAQ27%2BAAbaJydH%2BuyHdxudOog2ma2sDFWy03MFpdlJNc75zYrB2sZd2NHQ%3D%3D";
	private static String TODAY = new SimpleDateFormat("yyyMMdd").format(new Date()) ;
	private static String COORD_X = "98";
	private static String COORD_Y = "76";

	public static void main(String[] args) throws IOException, JSONException {
		
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
        // System.out.println(sb.toString());
        
        // -- json 파싱 부분 --
        String result = sb.toString();
        //System.out.println("result : " + result);
        
        // response 키를 가지고 데이터를 파싱
        JSONObject jsonPasing = new JSONObject(result);
        //System.out.println("jsonpasing : " + jsonPasing);
        
        JSONObject response =  jsonPasing.getJSONObject("response");
        //System.out.println("response : " + response);
        
        JSONObject body = response.getJSONObject("body");
        //System.out.println("body : " + body);
        
        JSONObject items = body.getJSONObject("items");
        //System.out.println("items : " + items);
        JSONArray item = items.getJSONArray("item");
        
        for(int i=0; i<item.length(); i++) {
        	items = item.getJSONObject(i);
        	String category = items.getString("category");
        	String obsrValue = items.getString("obsrValue");
        	
        	System.out.print(category + " ");
        	System.out.println(obsrValue);
        	
        	
        }
        
	}

}