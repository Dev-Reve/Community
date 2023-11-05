<%@page import="com.spring.community.trade.dao.TradeBoardDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>
<html>
	<head>
		<title>거래 게시판</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(function() {
				var isLogon = '${isLogOn}';
				console.log(isLogon);
				if(isLogon != 'true') {
					$('.writeBtn').hide();
				}
			});
		</script>
    <style>
		table img {
	 		width: 70%; 
/* 			height: 150px; */
		}
		
		table td {
			text-align: center;
		}
		
		.cssbuttons-io-button {
		  float: right;
		  margin-bottom: 10px;
		  margin-right: 20px;
		  background: #a370f0;
		  color: white;
		  font-family: inherit;
		  padding: 0.35em;
		  padding-left: 1.2em;
		  font-size: 17px;
		  font-weight: 500;
		  border-radius: 0.9em;
		  border: none;
		  letter-spacing: 0.05em;
		  display: flex;
		  align-items: center;
		  box-shadow: inset 0 0 1.6em -0.6em #714da6;
		  overflow: hidden;
		  position: relative;
		  height: 2.8em;
		  padding-right: 3.3em;
		  cursor: pointer;
		}
		
		.cssbuttons-io-button .icon {
		  background: white;
		  margin-left: 1em;
		  position: absolute;
		  display: flex;
		  align-items: center;
		  justify-content: center;
		  height: 2.2em;
		  width: 2.2em;
		  border-radius: 0.7em;
		  box-shadow: 0.1em 0.1em 0.6em 0.2em #7b52b9;
		  right: 0.3em;
		  transition: all 0.3s;
		}
		
		.cssbuttons-io-button:hover .icon {
		  width: calc(100% - 0.6em);
		}
		
		.cssbuttons-io-button .icon svg {
		  width: 1.1em;
		  transition: transform 0.3s;
		  color: #7b52b9;
		}
		
		.cssbuttons-io-button:hover .icon svg {
		  transform: translateX(0.1em);
		}
		
		.cssbuttons-io-button:active .icon {
		  transform: scale(0.95);
		}

		
    </style>
	</head>
	<body class="is-preload">
		<!-- Home -->
			<h1 style="text-align: center; padding-top:1em;">거래 게시판</h1>
			<hr>
					<button class="cssbuttons-io-button writeBtn" onclick="location.href='${path}/trade/regTradeForm.do'">
					  글작성
					  <div class="icon">
					    <svg
					      height="24"
					      width="24"
					      viewBox="0 0 24 24"
					      xmlns="http://www.w3.org/2000/svg"
					    >
					      <path d="M0 0h24v24H0z" fill="none" />
					      <path d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z" fill="currentColor" />
					    	</svg>
						</div>
					</button>

					<table class="table table-striped">
						<thead>
							<tr style="text-align: center; background-color: darkgray; ">
								<th style="width: 7%;">글번호</th>
								<th style="width: 10%;">이미지</th>
								<th style="width: 43%;">제목</th>
								<th style="width: 15%;">닉네임</th>
								<th style="width: 15%;">작성일</th>
								<th style="width: 10%;">조회수</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${empty tradeList.vo}">
							<tr height="40px">
								<td colspan="5" style="text-align:center;"><font size="3" color="white">작성된 내용이 없습니다.</font></td>
							</tr>
						</c:if>
				<c:if test="${not empty tradeList.vo}">
					<c:set var="no" value="${tradeList.no}" />
						<c:forEach var="vo" items="${tradeList.vo}" varStatus="loop">
						<c:set var="index" value="${loop.index + 1}" />
							<tr class="boardArticles" onclick="location.href='${path}/trade/tradeDetail.do?no=${vo.no}'">
								<td class="boardNo">${index + ((tradeList.currentPage-1) * 10)}</td>
								<td class="boardImg">
									<img src="${path}/trade/thumbnail.do?no=${vo.no}">
								</td>
								<td class="boardTitle">${vo.title}</td>
								<td class="boardUser">${vo.nickname}</td>
								<td class="boardWriteDate">${vo.writeDate}</td>
								<td class="boardReadCount">${vo.readCount}</td>
							</tr>
						</c:forEach>
						</c:if>
						</tbody>
					</table>
					<nav aria-label="Page navigation example">
						
						
					</nav>

	</body>
</html>