<%@page import="com.spring.community.board.BoardDAO.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Board</title>
<link rel="stylesheet" href="${Path}/resources/assets/css/board.css">
</head>
<body>
    <div class="centered-div">
        <h3>커뮤니티 게시판</h3>
    </div>
    <div class="board-container">
        <table id="board_trade" border="1" align="center">
            <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
            <!-- 더미 데이터 예제 -->
	<c:forEach var="board" items="${boardlist}">
            <tr>
                <td>${board.no }</td>
                <td>${board.title }</td>
                <td>${board.content }</td>
                <td>${board.writeDate }</td>
                <td>${board.nickName }</td>
                <td>${board.readCount }</td>
            </tr>
	</c:forEach>
        </table>
    </div>
</body>
</html>
