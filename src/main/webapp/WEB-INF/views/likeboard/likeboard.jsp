<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>LikeBoard</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
        text-align: center;
    }

    .centered-div {
        color: white;
        padding: 20px;
    }

    .board-container {
        margin: 20px;
        background-color: #ffffff;
        border: 1px solid #e9ecef;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    caption {
        font-size: 1.5em;
        font-weight: bold;
        margin-bottom: 10px;
        caption-side: top; /* 캡션을 테이블 위쪽으로 올림 */
    }

    th, td {
        padding: 15px;
        border: 1px solid #e9ecef;
        text-align: center;
    }

    th {
        background-color: #f8f9fa;
        text-align: center;
        color: black;
    }

    tr:nth-child(even) {
        background-color: #f8f9fa;
        text-align: center;
    }

    tr:hover {
        background-color: #cce5ff;
        text-align: center;
    }
</style>
</head>
<body>
    <div class="centered-div">
        <h3>인기 게시판</h3>
    </div>
    <div class="a" style="display: flex; flex-direction: column;">
        <!-- 첫 번째 .board-container에 배경색 제거 -->
        <div class="board-container">
            <table id="board_community">
                <caption>커뮤니티 게시판</caption>
                <tr align="center">
                    <th>글 번호</th>
                    <th>글 제목</th>
                    <th>작성일</th>
                    <th>작성자</th>
                    <th>조회수</th>
                </tr>
                <c:forEach items="${boardList}" var="blist">
                    <tr>
                        <td>${blist.no}</td>
                        <td><a href="${Path}/board/boardInfo.do?no=${blist.no}&name=${member.name}">${blist.title}</a></td>
                        <td>${blist.nickname}</td>
                        <td>${blist.writedate}</td>
                        <td>${blist.readcount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!-- 두 번째 .board-container는 그대로 둠 -->
        <div class="board-container">
            <table id="board_trade">
                <caption>중고거래 게시판</caption>
                <tr align="center">
                    <th>글 번호</th>
                    <th>글 제목</th>
                    <th>작성일</th>
                    <th>작성자</th>
                    <th>조회수</th>
                </tr>
                <c:forEach items="${tradeList}" var="tlist">
                    <tr>
                        <td>${tlist.no}</td>
                        <td><a href="${Path}/trade/tradeDetail.do?no=${tlist.no}">${tlist.title}</a></td>
                        <td>${tlist.nickname}</td>
                        <td>${tlist.writedate}</td>
                        <td>${tlist.readcount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
