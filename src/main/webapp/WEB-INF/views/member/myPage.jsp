<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
	</head>
	<body class="is-preload">
		<!-- Home -->
			<article id="top" class="wrapper style1">
				<div class="container">
					<div class="row">
						<div class="col-4 col-5-large col-12-medium">
							<span class="image fit"><img src="${path}/member/download.do?nickname=${member.nickname}" style="width:100%; height:100%; object-fit: cover; border: 1px solid lightgray;"/></span>
						</div>
						<div class="col-8 col-7-large col-12-medium">
							<header>
								<h1>반가워요 <strong>${member.nickname}님</strong>.</h1>
							</header>
							<p>And this is <strong>Miniport</strong>, a free, fully responsive HTML5 site template designed by <a href="http://twitter.com/ajlkn">AJ</a> for <a href="http://html5up.net">HTML5 UP</a> &amp; released under the <a href="http://html5up.net/license">CCA license</a>.</p>
							<a href="${path}/member/memberDetail.do" class="button large scrolly">내 정보 관리</a>
							<a href="${path}/member/memberDel.do?id=${member.id}" class="button large scrolly">회원 탈퇴</a>
						</div>
					</div>
				</div>
			</article>

		<article>
			<h2>내 관심목록</h2>
			<table>
			<hr>
				
				<c:forEach var="like" items="${liksList}">
				<tr style="border-bottom: 1px solid gray; height: 150px;">				
					<td width="10%" style="vertical-align: middle;">
						<img src="${path}/trade/thumbnail.do?no=${like.no}" style="width:100%; height: 125px; object-fit: cover; padding: 5px;">
					</td>
					<td style="padding: 10px; vertical-align: middle;">
						<a href="${path}/trade/tradeDetail.do?no=${like.no}" style="text-align: left; text-decoration: none; color: black;">${like.title}</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</article>
			
		
		<!-- Scripts -->
			<script src="${path}/resources/assets/js/jquery.min.js"></script>
			<script src="${path}/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="${path}/resources/assets/js/browser.min.js"></script>
			<script src="${path}/resources/assets/js/breakpoints.min.js"></script>
			<script src="${path}/resources/assets/js/util.js"></script>
			<script src="${path}/resources/assets/js/main.js"></script>

	</body>
</html>