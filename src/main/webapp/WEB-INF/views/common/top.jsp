<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
<c:set var="path" value="${pageContext.request.contextPath}" />
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />


>>>>>>> 7c3f62d67bee94e959326dde79865b0db1a993de
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
<link rel="stylesheet" href="${path}/resources/assets/css/index.css" />
</head>
<body>
	<nav id="nav">
		<ul class="container">
			<li><a href="${path}/main/index.do">집으로</a></li>
			<li><a href="${path}/trade/tradeList.do">물물거래</a></li>
			<li><a href="#portfolio">Portfolio</a></li>
			<li><a href="#contact">Contact</a></li>
			
			<li style="float:right;"><a href="${path}/member/memberForm.do">회원가입</a></li>
			<li style="float:right;"><a href="${path}/member/loginForm.do">로그인</a></li>
		</ul>
		
		
			
		
	</nav>
	    
	
</body>
</html>