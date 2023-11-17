<%@page import="com.spring.community.trade.dao.TradeBoardDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>
<html>
	<head>
		<title>채팅</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<%-- 		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" /> --%>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <style>
	    	.coolinput {
			  display: flex;
			  flex-direction: column;
			  width: fit-content;
			  position: static;
			}
			
			.coolinput label.text {
			  font-size: 1rem;
			  color: #818CF8;
			  font-weight: 700;
			  position: relative;
			  margin: 0 0 0 7px;
			  padding: 0 3px;
			  border: 2px #818CF8 solid;
			  border-radius: 5px;
			  background: #e8e8e8;
			  width: fit-content;
			}
			
			.coolinput input[type=text].input {
			  padding: 11px 10px;
			  font-size: 0.75rem;
			  border: 2px #818CF8 solid;
			  border-radius: 5px;
			  background: #e8e8e8;
			}
			
			.coolinput input[type=text].input:focus {
			  outline: none;
			}
			
			button:hover {
			  transform: translateY(-3px);
			  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.3);
			}
			
			button:active {
			  transform: scale(0.95);
			  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
			}
			
			button span {
			  display: block;
			  margin-left: 0.4em;
			  transition: all 0.3s;
			}
			
			button svg {
			  width: 18px;
			  height: 18px;
			  fill: white;
			  transition: all 0.3s;
			}
			
			button .svg-wrapper {
			  display: flex;
			  align-items: center;
			  justify-content: center;
			  width: 30px;
			  height: 30px;
			  border-radius: 50%;
			  background-color: rgba(255, 255, 255, 0.2);
			  margin-right: 0.5em;
			  transition: all 0.3s;
			}
			
			button:hover .svg-wrapper {
			  background-color: rgba(255, 255, 255, 0.5);
			}
			
			button:hover svg {
			  transform: rotate(45deg);
			}
			
			.button {
			  height: 50px;
			  width: 200px;
			  position: relative;
			  background-color: transparent;
			  cursor: pointer;
			  border: 2px solid #252525;
			  overflow: hidden;
			  border-radius: 30px;
			  color: #333;
			  transition: all 0.5s ease-in-out;
			}
			
			.btn-txt {
			  z-index: 1;
			  font-weight: 800;
			  letter-spacing: 4px;
			}
			
			.type1::after {
			  content: "";
			  position: absolute;
			  left: 0;
			  top: 0;
			  transition: all 0.5s ease-in-out;
			  background-color: #333;
			  border-radius: 30px;
			  visibility: hidden;
			  height: 10px;
			  width: 10px;
			  z-index: -1;
			}
			
			.button:hover {
			  box-shadow: 1px 1px 200px #252525;
			  color: #fff;
			  border: none;
			}
			
			.type1:hover::after {
			  visibility: visible;
			  transform: scale(100) translateX(2px);
			}
			
			#chatWindow{
				border:1px solid black; 
				width:270px; 
				height:310px; 
				overflow:scroll; 
				padding:5px;
			}
			#chatMessage{
				width:236px; 
				height:30px;
				}
			
			#closeBtn{
				margin-bottom:3px; 
				position:relative; 
				top:2px; 
				left:-2px;
			}
			#chatId{
				width:158px; 
				height:24px; 
				border:1px solid #AAAAAA; 
				background-color:#EEEEEE;
			}
			.myMsg{
				text-align:right;
				padding-right: 10px;
			}
			.mychatBox {
			  display: inline-block;
			  position: relative;
			  background-color: #93DAFF	;
			  border-radius: 10px;
			  color: #fff;
			  padding: 7px 12px;
			  margin-bottom: 10px;
			}
			.chatBox {
			  display: inline-block;
			  position: relative;
			  background-color: #8c8c8c	;
			  border-radius: 10px;
			  color: #fff;
			  padding: 7px 12px;
			  margin-bottom: 10px;
			}
			.chatBoxSecret {
			  display: inline-block;
			  position: relative;
			  background-color: #8c8c8c	;
			  border-radius: 10px;
			  color: #0064FF;
			  padding: 7px 12px;
			  margin-bottom: 10px;
			  max-width: 230px;
			}
			
			.socket {
			  justify-content: center;
			  align-items: center;
			  display: flex;
			}
			
	    </style>
	</head>
	<body class="is-preload">
		<!-- Home -->
		<h1 style="text-align: center; padding-top:1em;">접속자 동시 채팅</h1>
		<hr>
		
		<div id="chatWindow" style="width: 100%;"></div>
		<br>
		<div style="width: 100%">
			<div class="coolinput" style="width: 100%; display: inline;">
			    <label for="input" class="text">${member.nickname}</label>
			    <input type="text" placeholder="채팅을 입력해주세요." name="input" class="input" id="chatMessage" onkeypress="enterKey();" style="width: calc(100% - 250px); margin-right: 1.5em;">
			</div>
			<div style="display: inline-block; width: 120px;">
			<button id="sendBtn" onclick="sendMessage();" style="width: 120px; height: 42px; vertical-align: middle; font-size: 16px; background: linear-gradient(to bottom, #4dc7d9 0%,#66a6ff 100%); 
															 color: white; padding: 0.8em 1.2em; display: flex; margin-bottom: 40px; align-items: center; justify-content: center; border: none; border-radius: 25px; 
															 box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2); transition: all 0.3s;">
				<div class="svg-wrapper-1">
				    <div class="svg-wrapper">
				      	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
					        <path fill="none" d="M0 0h24v24H0z"></path>
					        <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
				      	</svg>
				    </div>
			  	</div>
				<span>전송</span>
			</button>
			</div>
		</div>
		<button id="closeBtn" class="button type1" onclick="disconnectBtn();">
		  	<span class="btn-txt">연결종료</span>
		</button>
		
		<script type="text/javascript">
			var webSocket = new WebSocket("ws://localhost:8090/community/chat");
			var chatWindow, chatMessage, chatId;
		
			// 채팅창이 열리면 대화창, 메시지 입력창, 대화명 표시란으로 사용할 DOM 객체 저장
			window.onload = function() {
			    chatWindow = document.getElementById("chatWindow");
			    chatMessage = document.getElementById("chatMessage");
			    chatId = '${member.nickname}';    
			}
		
			// 메시지 전송
			function sendMessage() {
			    // 대화창에 표시
			    chatWindow.innerHTML += "<div class='myMsg'><div class='mychatBox'>" + chatMessage.value + "</div></div>"
			    webSocket.send(chatId + '|' + chatMessage.value);  // 서버로 전송
			    chatMessage.value = "";  // 메시지 입력창 내용 지우기
			    chatWindow.scrollTop = chatWindow.scrollHeight;  // 대화창 스크롤
			}
		
			// 서버와의 연결 종료
			function disconnect() {
			    webSocket.close();
			}
		
			// 엔터 키 입력 처리
			function enterKey() {
			    if (window.event.keyCode == 13) {  // 13은 'Enter' 키의 코드값
			        sendMessage();
			    }
			}
		
			// 웹소켓 서버에 연결됐을 때 실행
			webSocket.onopen = function(event) {   
				chatWindow.innerHTML += "<span class='socket'>웹소켓 서버에 연결되었습니다.</span><br/>";
			};
		
			// 웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때) 실행
			webSocket.onclose = function(event) {
				chatWindow.innerHTML += "<span class='socket'>웹소켓 서버가 종료되었습니다.</span><br/>";
			};
		
			// 에러 발생 시 실행
			webSocket.onerror = function(event) { 
			    console.log(event);
			    chatWindow.innerHTML += "<span class='socket'>채팅 중 에러가 발생하였습니다.</span><br/>";
			}; 
		
			// 메시지를 받았을 때 실행
			webSocket.onmessage = function(event) { 
			    var message = event.data.split("|");  // 대화명과 메시지 분리
			    var sender = message[0];   // 보낸 사람의 대화명
			    var content = message[1];  // 메시지 내용
			    if (content != "") {
			        if (content.match("/")) {  // 귓속말
			            if (content.match(("/" + chatId))) {  // 나에게 보낸 메시지만 출력
			            	var temp = content.replace(("/" + chatId), "[귓속말] : ");
			                chatWindow.innerHTML += "<div>" + sender + "<br><div class='chatBoxSecret'>" + temp + "</div></div>";
			            }
			        }
			        else {  // 일반 대화
			        	chatWindow.innerHTML += "<div><img src='${path}/member/download.do?nickname=" + sender + "' style='border-radius: 50%; width: 30px; height: 30px; object-fit:cover;'>" + sender + " <br><div class='chatBox' style='margin-left:30px;'>" + content + "</div></div>";
			        }
			    }
			    chatWindow.scrollTop = chatWindow.scrollHeight; 
			};
		</script>
	</body>
</html>