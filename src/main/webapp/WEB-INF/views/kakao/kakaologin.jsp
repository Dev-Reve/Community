<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>카카오 로그인 테스트</h1>
	<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=aeff802be760dbabf27db5eeea16407d
&redirect_uri=http://localhost:8088/community/kakao/login">카카오 로그인</a>
</body>
</html>