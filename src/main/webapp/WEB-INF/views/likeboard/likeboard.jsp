<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>LikeBoard</title>
    <style>
        /* 테이블 중앙 정렬 스타일 */
        .board-container {
            text-align: center; /* 텍스트 가운데 정렬 */
            margin: 0 auto; /* 수평 가운데 정렬 */
        }
        table {
            width: 80%; /* 테이블 너비 지정 (조절 가능) */
            border-collapse: collapse; /* 테이블 셀 경계 병합 */
            margin: 0 auto; /* 수평 가운데 정렬 */
            border: 1px solid black; /* 테이블 셀 경계 스타일 지정 */
        }
        th, td {
            padding: 5px; /* 셀 내용 여백 (조절 가능) */
            border: 1px solid black; /* 테이블 셀 경계 스타일 지정 */
        }
        caption {
            font-weight: bold; /* 테이블 캡션 텍스트 굵게 표시 (선택적) */
        }
    </style>
</head>
<body>
    <div class="centered-div">
        <h3>인기 게시판</h3>
    </div>
    <div class="board-container">
        <table id="board_community" border="1" >
            <caption><strong>커뮤니티 게시판</strong></caption>
            <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
            <c:forEach items="${boardList}" var="blist">
                <tr>
                    <td>${blist.no}</td>
                    <td>${blist.title}</td>
                    <td>${blist.content}</td>
                    <td>${blist.nickname}</td>
                    <td>${blist.writedate}</td>
                    <td>${blist.readcount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="board-container">
        <table id="board_trade" border="1">
            <caption><strong>중고거래 게시판</strong></caption>
            <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
            <c:forEach items="${tradeList}" var="tlist">
                <tr>
                    <td>${tlist.no}</td>
                    <td>${tlist.title}</td>
                    <td>${tlist.content}</td>
                    <td>${tlist.nickname}</td>
                    <td>${tlist.writedate}</td>
                    <td>${tlist.readcount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>