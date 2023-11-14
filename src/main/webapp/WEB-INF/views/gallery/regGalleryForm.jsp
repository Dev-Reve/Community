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
		<link rel="stylesheet" href="${path}/resources/assets/css/form.css" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  		<script src="http://code.jquery.com/jquery-latest.min.js" ></script>
	</head>
	<body class="is-preload">
		<!-- Home -->
		<h1 style="text-align: center; padding-top: 1em;">갤러리 게시판</h1>
		<hr>
		<form action="${path}/gallery/insertGallery.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tr style="width: 100%">
					<th class="title">글 제목</th>
					<td class="content" colspan="3">
						<input type="text" name="title" placeholder="제목을 입력해주세요.">
					</td>
				</tr>
				<tr style="width: 100%">
					<th class="title">작성자</th>
					<td class="content" style="text-align: left;">
						<input type="hidden" name="nickName" value="user1">user1
					</td>
				</tr>				
				<tr style="width: 100%">
					<th class="title">글 내용</th>
					<td class="content" colspan="3">
						<textarea name="content" rows="20" cols="50" ></textarea>
					</td>
				</tr>
				<tr style="width: 100%">
					<th class="title">첨부파일</th>
					<td class="content" style="text-align: left;">
						<input type="file" name="files"  accept=".jpg, .png" multiple="multiple" onchange="setThumbnail(event);" required>
					</td>
				</tr>
				<tr style="width: 100%">
					<th class="title">미리보기</th>
					<td>
						<div id="image_container"></div>
					</td>
				</tr>
			</table>
			<button class="btn" type="submit">
			  <strong>작성완료</strong>
			  <div id="container-stars">
			    <div id="stars"></div>
			  </div>
			  <div id="glow">
			    <div class="circle"></div>
			    <div class="circle"></div>
			  </div>
			</button>
			<button class="btn" type="reset">
			  <strong>다시작성</strong>
			  <div id="container-stars">
			    <div id="stars"></div>
			  </div>
			  <div id="glow">
			    <div class="circle"></div>
			    <div class="circle"></div>
			  </div>
			</button>
		</form>
	<script type="text/javascript">
	
	function setThumbnail(event) {
	    var imageContainer = document.getElementById("image_container");
	    imageContainer.innerHTML = "";
	    for (var image of event.target.files) {
	        var reader = new FileReader();
	        reader.onload = function(event) {
	            var img = document.createElement("img");

	            img.setAttribute("src", event.target.result);
	            img.style.maxWidth = "200px"; // 최대 너비 설정
	            img.style.maxHeight = "200px"; // 최대 높이 설정

	            // 이미지 클릭 시 다운로드 이벤트 추가
	            img.addEventListener("click", function() {
	            	
	            	img.setAttribute("data-filename", image.name);
	                var filename = this.getAttribute("data-filename");
	                // 이미지를 다운로드할 수 있도록 링크 생성
	                var downloadLink = document.createElement("a");
	                downloadLink.href = event.target.result; // 이미지 URL
	                downloadLink.download = "image.png"; // 이미지 파일명 지정
	                downloadLink.click(); // 링크 클릭 (다운로드 시작)
	            });
			
	            var div = document.createElement("div");
	            div.appendChild(img);
	            imageContainer.appendChild(div);
	        };
	        reader.readAsDataURL(image);
	    }
	}
		
	</script>
	</body>
</html>