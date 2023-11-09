<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />


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
			<li><a href="${path}/main/index.do">홈으로</a></li>
			<li><a href="${path}/trade/tradeList.do">물물거래</a></li>
			<li><a href="${path}/gallery/main.do">갤러리</a></li>
			<li><a href="${path}/board/listboard.do">자유게시판</a></li>
			<li><a href="${path}//likeboard/likeboard.do">인기게시판</a></li>
			
			
			<c:choose>
          <c:when test="${isLogOn == true  && member!= null}">
       	    <li style="float:right;"><a href="${path}/member/myPage.do">마이페이지</a></li>
            <li style="float:right;"><a href="${path}/member/logout.do">로그아웃</a></li>
          </c:when>
          <c:otherwise>
	        <li style="float:right;"><a href="${path}/member/memberForm.do">회원가입</a></li>
			<li style="float:right;"><a href="${path}/member/loginForm.do">로그인</a></li>
	      </c:otherwise>
	   </c:choose> 
			
		
		</ul>
		
		
			
		
	</nav>
	    
	
</body>
</html>