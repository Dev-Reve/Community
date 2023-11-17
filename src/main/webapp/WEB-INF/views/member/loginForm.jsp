<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
		<style>
		.form-container {
		  margin: 50px auto;
		  width: 350px;
		  height: 500px;
		  background-color: #fff;
		  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
		  border-radius: 10px;
		  box-sizing: border-box;
		  padding: 20px 30px;
		}
		
		.title {
		  text-align: center;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		  margin: 10px 0 30px 0;
		  font-size: 28px;
		  font-weight: 800;
		}
		
		.form {
		  width: 100%;
		  display: flex;
		  flex-direction: column;
		  gap: 18px;
		  margin-bottom: 15px;
		}
		
		.input {
		  border-radius: 20px;
		  border: 1px solid #c0c0c0;
		  outline: 0 !important;
		  box-sizing: border-box;
		  padding: 12px 15px;
		}
		
		.page-link {
		  text-decoration: underline;
		  margin: 0;
		  text-align: end;
		  color: #747474;
		  text-decoration-color: #747474;
		}
		
		.page-link-label {
		  cursor: pointer;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		  font-size: 9px;
		  font-weight: 700;
		}
		
		.page-link-label:hover {
		  color: #000;
		}
		
		.form-btn {
		  padding: 10px 15px;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		  border-radius: 20px;
		  border: 0 !important;
		  outline: 0 !important;
		  background: teal;
		  color: white;
		  cursor: pointer;
		  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
		}
		
		.form-btn:active {
		  box-shadow: none;
		}
		
		.sign-up-label {
		  margin: 0;
		  font-size: 10px;
		  color: #747474;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		}
		
		.sign-up-link {
		  margin-left: 1px;
		  font-size: 11px;
		  text-decoration: underline;
		  text-decoration-color: teal;
		  color: teal;
		  cursor: pointer;
		  font-weight: 800;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		}
		
		.buttons-container {
		  width: 100%;
		  display: flex;
		  flex-direction: column;
		  justify-content: flex-start;
		  margin-top: 20px;
		  gap: 15px;
		}
		
		.kakaoBtn {
		  border-radius: 20px;
		  width: 100%;
		  box-sizing: border-box;
		  cursor: pointer;
		  display: flex;
		  justify-content: center;
		  align-items: center;
		  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
		        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
		  font-size: 11px;
		  gap: 5px;
		}
		
		.apple-login-button {
		  background-color: #000;
		  color: #fff;
		  border: 2px solid #000;
		}
		
		.google-login-button {
		  border: 2px solid #747474;
		}
		
		.apple-icon,
		    .google-icon {
		  font-size: 18px;
		  margin-bottom: 1px;
		}
		</style>
		<c:choose>
			<c:when test="${result=='loginFailed' }">
			  <script>
			    window.onload=function(){
			      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
			    }
			  </script>
			</c:when>
		</c:choose> 
		
	</head>
	<body class="is-preload">
		 <div class="form-container">
			 <form class="form" method="post" action="${path}/member/login.do">
		      	<p class="title">다시 보니 반가워요! :)</p>
		        	<input type="text" name="id" class="input" placeholder="아이디">
		        	<input type="password" name="password" class="input" placeholder="비밀번호">
		        	<button class="form-btn" type="submit">로그인</button>
			      	<p class="sign-up-label">
			        아직 계정이 없으신가요? <span class="sign-up-link"><a href="${path}/member/memberForm.do" style="text-decoration: none; color:teal;">회원가입하러 가기</a></span>
		      	</p>
		      	<div class="buttons-container">
	        		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=24341d26e46f6a5b4c17148bfb5e6d48&redirect_uri=http://localhost:8090${path}/member/kakaoCallback&prompt=select_account'">
						<img class="kakaoBtn" src="${path}/resources/images/kakao_login.png">
					</a>
		      	</div>
	      	</form>
    	</div>
	
		<!-- Scripts -->
			<script src="${path}/resources/assets/js/jquery.min.js"></script>
			<script src="${path}/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="${path}/resources/assets/js/browser.min.js"></script>
			<script src="${path}/resources/assets/js/breakpoints.min.js"></script>
			<script src="${path}/resources/assets/js/util.js"></script>
			<script src="${path}/resources/assets/js/main.js"></script>
			
	</body>
</html>