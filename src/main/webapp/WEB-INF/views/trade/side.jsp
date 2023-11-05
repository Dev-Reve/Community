<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 style="margin-bottom: -10px;">카테고리</h5>
	<br>
	<a href="${path}/trade/tradeList.do?category=all">전체보기</a> <br>
	<a href="${path}/trade/tradeList.do?category=life">생활용품</a> <br>
	<a href="${path}/trade/tradeList.do?category=fasion">패션/뷰티</a> <br>
	<a href="${path}/trade/tradeList.do?category=digital">가전/디지털</a> <br>
	<a href="${path}/trade/tradeList.do?category=office">사무용품</a> <br>
	<a href="${path}/trade/tradeList.do?category=etc">기타</a> <br>
</body>
</html>