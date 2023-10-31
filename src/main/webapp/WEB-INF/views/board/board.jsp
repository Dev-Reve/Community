<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />

<%
    request.setCharacterEncoding("utf-8");
    int totalrecord = 0;
    int numPerPage = 5;
    int pagePerBlock = 5;
    int totalPage = 0;
    int totalBlock = 0;
    int nowPage = 1;
    int nowBlock = 1;
    int beginPerPage = 0;

    List list = (List) request.getAttribute("boardlist");
    totalrecord = list.size();
    totalPage = (int) Math.ceil((double) totalrecord / numPerPage);
    totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);

    if (request.getParameter("nowPage") != null) {
        try {
            nowPage = Integer.parseInt(request.getParameter("nowPage"));
        } catch (NumberFormatException e) {
            nowPage = 1; // 예외 발생 시 기본값 설정
        }
    }
    if (request.getParameter("nowBlock") != null) {
        try {
            nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
        } catch (NumberFormatException e) {
            nowBlock = 1; // 예외 발생 시 기본값 설정
        }
    }

    beginPerPage = (nowPage - 1) * numPerPage;
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
            <c:forEach var="board" items="${boardlist}" begin="${beginPerPage}" end="${beginPerPage + numPerPage - 1}">
                <tr>
                    <td>${board.no}</td>
                    <td>${board.title}</td>
                    <td>${board.content}</td>
                    <td>${board.writeDate}</td>
                    <td>${board.nickName}</td>
                    <td>${board.readCount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="page_control">
        <c:if test="${nowBlock > 1}">
            <a href="${Path}/board.jsp?nowBlock=<%=nowBlock - 1%>&nowPage=<%=(nowBlock - 2) * pagePerBlock + 1%>">
                <<< 이전<%=pagePerBlock%>개
            </a>
        </c:if>
        <%
		    int currentPage = nowPage;
		    int beginPerPage = (currentPage - 1) * numPerPage;
		    int end = beginPerPage + numPerPage - 1;
		%>
        <c:forEach var="blockNum" begin="1" end="${totalBlock}">
            <c:choose>
                <c:when test="${blockNum == nowBlock}">
                    <span>${blockNum}</span>
                </c:when>
                <c:otherwise>
                    <a href="${Path}/board.jsp?nowBlock=${blockNum}&nowPage=${(blockNum - 1) * pagePerBlock + 1}">
                        ${blockNum}
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${nowBlock < totalBlock}">
            <a href="${Path}/board.jsp?nowBlock=<%=nowBlock + 1%>&nowPage=<%=nowBlock * pagePerBlock + 1%>">
                다음<%=pagePerBlock%>개 >>>
            </a>
        </c:if>
    </div>
</body>
</html>