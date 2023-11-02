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
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js">
			$(function() {

			});
		</script>
    <style>
		.like {
		  padding: 0 20px 0 10px;
		  border-radius: 10px;
		  box-shadow: 0px 0px 5px 7px #e7413373;
		  background-color: #e74133;
		  color: white;
		  font-size: 17px;
		  border: none;
		  display: flex;
		  align-items: center;
		  transition: all .5s ease-in-out;
		  letter-spacing: 2px;
		}
		
		.like:hover {
		  background-color: #f54d3e;
		  transition: all .5s ease-in-out;
		  box-shadow: 0px 0px 5px 3px #e7413373;
		}
		
		.like::before {
		  content: "";
		  background-image: url("data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iNzUycHQiIGhlaWdodD0iNzUycHQiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDc1MiA3NTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiA8cGF0aCBkPSJtMzc2LjMyIDU1Mi4zYy0wLjM4NjcyIDAtMC43ODEyNS0wLjAxNTYyNS0xLjE3MTktMC4wNTA3ODEtMS4wNzgxLTAuMDc0MjE5LTIuMTM2Ny0wLjI2NTYyLTMuMTU2Mi0wLjU0Njg4LTIuNzMwNS0wLjU5Mzc1LTUuMjkzLTEuODUxNi03LjM0MzgtMy43ODEybC0xMzcuNTQtMTI5LjY2Yy00NC40NTMtNDEuOTAyLTQ5LjQ4LTExNS40Ni0xMS4yMTUtMTYzLjk3IDE5LjA4Mi0yNC4xODQgNDUuNzctMzguNjk1IDc1LjE1Mi00MC44NTUgMjguOTMtMi4xMTcyIDU2Ljg2MyA4LjAzMTIgNzguNjggMjguNTk4bDYuMjY1NiA1LjkwMjMgNi4yNjU2LTUuOTAyM2MyMS44MzItMjAuNTcgNDkuODA1LTMwLjY5MSA3OC42OTEtMjguNTk4IDI5LjM4MyAyLjE2NDEgNTYuMDY2IDE2LjY3NiA3NS4xNDUgNDAuODU1IDM4LjI2MiA0OC41MTIgMzMuMjM0IDEyMi4wNy0xMS4yMTUgMTYzLjk3bC0xMzcuNTQgMTI5LjY3Yy0yLjk5MjIgMi44MTY0LTYuOTM3NSA0LjM3NS0xMS4wMjMgNC4zNzV6bS03Ny44MTItMzA3LjAxYy0xLjY5NTMgMC0zLjM5NDUgMC4wNjI1LTUuMTAxNiAwLjE4NzUtMjAuMjgxIDEuNDk2MS0zOC44NTIgMTEuNjkxLTUyLjI4MSAyOC43MTEtMjguMjE1IDM1Ljc3My0yNC42MTMgODkuOTEgOC4wMjczIDEyMC42OGwxMjYuODQgMTE5LjU5IDEyNi44NC0xMTkuNTljMzIuNjQ1LTMwLjc3MyAzNi4yNDYtODQuOTEgOC4wMjczLTEyMC42OC0xMy40MjItMTcuMDItMzEuOTg0LTI3LjIxNS01Mi4yNy0yOC43MTEtMTkuODI4LTEuNDY0OC0zOS4xMDUgNS42MjExLTU0LjI4NSAxOS45MzRsLTE3LjI4NSAxNi4yOTNjLTYuMTk1MyA1LjgzOTgtMTUuODU5IDUuODM5OC0yMi4wNDcgMGwtMTcuMjg1LTE2LjI5M2MtMTMuODcxLTEzLjA3OC0zMS4xNzYtMjAuMTE3LTQ5LjE4LTIwLjExN3oiIGZpbGw9IiNmZmYiLz4KPC9zdmc+Cg==");
		  background-size: 100%;
		  background-repeat: no-repeat;
		  color: transparent;
		  position: relative;
		  width: 50px;
		  height: 50px;
		  display: block;
		  margin-right: 5px;
		  transition: all .9s ease-in-out;
		}
		
		.like:hover:before {
		  background-image: url("data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iNzUycHQiIGhlaWdodD0iNzUycHQiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDc1MiA3NTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiA8cGF0aCBkPSJtMzY5Ljg0IDU1MC4yOGMwLjQ3MjY2IDAuNDcyNjYgMC45NDUzMSAwLjQ3MjY2IDAuOTQ1MzEgMC45NDUzMSA2NS4zNTUtNTEuNjIxIDE5My43LTE0OC4yMyAxOTMuNy0yNDkuMTEgMC01NS44ODMtNDUuNDY1LTEwMS4zNS0xMDEuMzUtMTAxLjM1LTM5Ljc4MSAwLTc0LjM1MiAyMy4yMDctOTAuOTI2IDU2LjgyOC0wLjQ3MjY2IDAuOTQ1MzEtMS40MjE5IDMuMzE2NC0xLjQyMTkgMy4zMTY0cy0wLjk0NTMxLTEuODk0NS0wLjk0NTMxLTIuMzY3MmMtMTYuMTAyLTM0LjA5LTUwLjY3Mi01Ny43Ny05MC45MjYtNTcuNzctNTUuODgzIDAtMTAxLjM1IDQ1LjQ2MS0xMDEuMzUgMTAxLjM0IDAgMTAxLjgyIDEyNy44NyAxOTcuMDEgMTkyLjI3IDI0OC4xNnoiIGZpbGw9IiNmZmYiLz4KPC9zdmc+Cg==");
		  transition: all .9s ease-in-out;
		  transform: rotate(-1turn);
		}
		
	      .swiper-slide {
	        text-align: center;
	        font-size: 18px;
	        background: #fff;
	
	        /* Center slide text vertically */
	        display: -webkit-box;
	        display: -ms-flexbox;
	        display: -webkit-flex;
	        display: flex;
	        -webkit-box-pack: center;
	        -ms-flex-pack: center;
	        -webkit-justify-content: center;
	        justify-content: center;
	        -webkit-box-align: center;
	        -ms-flex-align: center;
	        -webkit-align-items: center;
	        align-items: center;
	      }
	
	      .swiper-slide img {
	        display: block;
	        width: 100%;
	        height: 100%;
	        object-fit: scale-down;
	      }
	      .swiper-button-next,
		  .swiper-button-prev {
			width: 30px; /* 버튼 너비 조절 */
			height: 30px; /* 버튼 높이 조절 */
			background-color: #000; /* 버튼 배경색 설정 */
			color: #fff; /* 버튼 텍스트 색상 설정 */
			border-radius: 50%; /* 원 모양으로 버튼 설정 */
			font-size: 20px; /* 텍스트 크기 설정 */
		  }
		  
		}
    </style>
	</head>
	<body class="is-preload">
		<!-- Home -->
		<h1 style="text-align: center; padding-top: 1em;">거래 게시판</h1>
		<hr>
		<form action="${path}/trade/modTrade.do" method="post" enctype="multipart/form-data">
			<div style="display: flex; flex-direction: column;">
				<c:set var="vo" value="${vo}" />
				<input type="hidden" name="no" value="${vo.no}">
				<div class="swiper mySwiper" style="width: 300px; height: 300px; float: left;">
				    <div class="swiper-wrapper">
						<div class="swiper-slide">
							<c:forEach var="fileName" items="${vo.fileNames}">
				      			<img src="${path}/trade/imageList.do?no=${vo.no}&imageFileName=${fileName}" style="width: 100%; height: 100%; object-fit:cover;">
					    	</c:forEach>
						</div>
				    </div>
				   <div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				    <div class="swiper-pagination"></div>
			  	</div>
			  	<div style="margin-left: 1em;">
					<h4>${vo.title}</h4>
					<small>가격: ${vo.price}원</small>
					<p style="margin-top: 9.3em;">
					유저닉네임 또는 프사&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 주소 들어가는 곳
					</p>
					<span><small></small></span>
					<div>
						<button class="like" type="button" > <span>찜하기</span></button>
						
					</div>
			  	</div>
			</div>
		</form>
		
		
		<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
		<script type="text/javascript">
			var swiper = new Swiper(".mySwiper", {
				spaceBetween: 30,
			    centeredSlides: true,
			    autoplay: {
			      delay: 2500,
			      disableOnInteraction: false,
			    },
			    pagination: {
			      el: ".swiper-pagination",
			      clickable: true,
			    },
			    navigation: {
			      nextEl: ".swiper-button-next", // 다음 버튼 요소 선택자
			      prevEl: ".swiper-button-prev"  // 이전 버튼 요소 선택자
			    },
		    });
		</script>
	</body>
</html>