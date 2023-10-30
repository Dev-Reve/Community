<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
	<head>
		<title>Miniport by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <style>
      .layout {
        display: inline-grid;
        grid:
          "sidebar . body" 1fr
          / auto 1fr;
      }

      .sidebar { 
				grid-area: sidebar;
				position: sticky;
				top: 15%;
				height: 30%;
				width: 100%;
				margin: 0 20px;
				padding: 0 10px;
				color: black;
				border-right: 1px solid gray;
			}
		.sidebar a {
			width: 100%;
			margin-left: 14px;
			text-decoration: none;
			color: black;
			list-style: none;
			border-bottom: 1px solid lightgray;
			line-height: 2em;
		}
		
		a:hover {
			color: darkgray;
		}
		.body {
			margin: 0 auto;
			justify-content: center;
		}
		
		table img {
		width: 70%;
		}

    </style>
	</head>
	<body class="is-preload">
		<!-- Home -->
		<article id="top" class="wrapper style1">
			<h1 style="text-align: center;">거래 게시판</h1>
			<hr>
			<section class="layout">
				<div class="sidebar">
					<h5 style="margin-bottom: -10px;">카테고리</h5>
					<br>
					<a href="#">전체보기</a> <br>
					<a href="#">생활용품</a> <br>
					<a href="#">패션/뷰티</a> <br>
					<a href="#">가전/디지털</a> <br>
					<a href="#">사무용품</a> <br>
					<a href="#">기타</a> <br>
				</div>
				<div class="body" style="margin-left: 5%">
					<table class="table table-striped">
						<thead>
							<tr style="text-align: center; background-color: darkgray;">
								<th style="width: 10%;">이미지</th>
								<th style="width: 40%;">제목</th>
								<th style="width: 15%;">닉네임</th>
								<th style="width: 15%;">작성일</th>
								<th style="width: 10%;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
							<tr class="boardArticles">
								<td class="boardImg"><img src="${path}/resources/images/a.jpg"></td>
								<td class="boardTitle">글 제목입니다</td>
								<td class="boardUser">유저명</td>
								<td class="boardWriteDate">2023-10-12</td>
								<td class="boardReadCount">12</td>
							</tr>
						</tbody>
					</table>
				</div>
        	</section>
		</article>

	</body>
</html>