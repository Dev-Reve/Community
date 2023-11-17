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
<link rel="stylesheet" href="${path}/resources/assets/css/side.css">
</head>
<body>
	<h5 style="margin-bottom: -10px;">카테고리</h5>
	<br>
	<a href="${path}/trade/tradeList.do?category=all">전체보기</a> <br>
	<a href="${path}/trade/tradeList.do?category=life">생활용품</a> <br>
	<a href="${path}/trade/tradeList.do?category=fashion">패션/뷰티</a> <br>
	<a href="${path}/trade/tradeList.do?category=digital">가전/디지털</a> <br>
	<a href="${path}/trade/tradeList.do?category=office">사무용품</a> <br>
	<a href="${path}/trade/tradeList.do?category=etc">기타</a> <br>
	<div>
	<hr>	
		<h6 style="margin-bottom: -10px;">오늘의 날씨</h6>
	<br>
		<table>

			<tr>
				<td>강수형태</td>
				<td>${pubDate.PTY}</td>
			</tr>
			<tr>
				<td>습도</td>
				<td>${pubDate.REH}</td>
			</tr>
			<tr>
				<td>1시간 강수량</td>
				<td>${pubDate.RN1}</td>
			</tr>
			<tr>
				<td>기온</td>
				<td>${pubDate.T1H}</td>
			</tr>
			<tr>
				<td>풍속</td>
				<td>${pubDate.WSD}</td>
			</tr>
		</table>
	</div>
</body>
</html>