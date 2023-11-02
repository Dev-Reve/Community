<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="Path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<script>
	function selChange() {
		var sel = document.getElementById('checksel').value;
		location.href="${Path}/board/listboard.do?nowPage=${paging.nowPage}&cntPerPage=10&checksel="+sel;
	}
</script>

<meta charset="utf-8" />
<title>Board</title>
<link rel="stylesheet" href="${Path}/resources/assets/css/board.css">
</head>
<body>
<h2>게시판</h2>
<div id="outter">
	<div style="float: right;">
		<select id="checksel" name="sel" onchange="selChange()">
			<option value="no" >
				<c:if test="${paging.checksel == no}">selected</c:if>최신순</option>
			<option value="readCount" >조회순</option>
				<c:if test="${paging.checksel == readCount}">selected</c:if>조회순</option>
		</select>
	</div> <!-- 옵션선택 끝 -->
	<table border="1">
		<tr>
			<th>글 번호</th>
            <th>글 제목</th>
            <th>글 내용</th>
            <th>작성일</th>
            <th>작성자</th>
            <th>조회수</th>	
		</tr>
		<c:forEach items="${boardlist}" var="list">
			<tr>
				<td>${list.no }</td>
				<td>${list.title }</td>
				<td>${list.content }</td>
				<td>${list.writeDate } </td>
				<td>${list.nickName }</td>
				<td>${list.readCount }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="글쓰기" style="float: right;" onclick="location.href='/write'"><br>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="${Path}/board/listboard.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&checksel=${paging.checksel}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="${Path}/board/listboard.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&checksel=${paging.checksel}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="${Path}/board/listboard.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&checksel=${paging.checksel}">&gt;</a>
		</c:if>
	</div>
</div>
</body>
</html>
