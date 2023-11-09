<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 style="margin-bottom: -10px;">카테고리</h5>
	<br>
	<a href="#">전체보기</a> <br>
	<a href="#">생활용품</a> <br>
	<a href="#">패션/뷰티</a> <br> 
	<a href="#">가전/디지털</a> <br>
	<a href="#">사무용품</a> <br>
	<a href="#">기타</a> <br>
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
				<td>동서 바람성분</td>
				<td>${pubDate.UUU}</td>
			</tr>
			<tr>
				<td>풍향</td>
				<td>${pubDate.VEC}</td>
			</tr>
			<tr>
				<td>북남 바람성분</td>
				<td>${pubDate.VVV}</td>
			</tr>			
			<tr>
				<td>풍속</td>
				<td>${pubDate.WSD}</td>
			</tr>
		</table>
	</div>
</body>
</html>