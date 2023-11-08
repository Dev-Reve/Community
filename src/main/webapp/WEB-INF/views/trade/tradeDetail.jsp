<%@page import="com.spring.community.trade.dao.TradeBoardDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<c:set var="vo" value="${vo}" />

<!DOCTYPE html>
<html>
	<head>
		<title>거래 게시판</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
		<!-- CSS -->
		<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
		<!-- JS -->
		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
		<script src="https://kit.fontawesome.com/3c365b85f4.js" crossorigin="anonymous"></script>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
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
		
		.like:hover::before {
		  background-image: url("data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iNzUycHQiIGhlaWdodD0iNzUycHQiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDc1MiA3NTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiA8cGF0aCBkPSJtMzY5Ljg0IDU1MC4yOGMwLjQ3MjY2IDAuNDcyNjYgMC45NDUzMSAwLjQ3MjY2IDAuOTQ1MzEgMC45NDUzMSA2NS4zNTUtNTEuNjIxIDE5My43LTE0OC4yMyAxOTMuNy0yNDkuMTEgMC01NS44ODMtNDUuNDY1LTEwMS4zNS0xMDEuMzUtMTAxLjM1LTM5Ljc4MSAwLTc0LjM1MiAyMy4yMDctOTAuOTI2IDU2LjgyOC0wLjQ3MjY2IDAuOTQ1MzEtMS40MjE5IDMuMzE2NC0xLjQyMTkgMy4zMTY0cy0wLjk0NTMxLTEuODk0NS0wLjk0NTMxLTIuMzY3MmMtMTYuMTAyLTM0LjA5LTUwLjY3Mi01Ny43Ny05MC45MjYtNTcuNzctNTUuODgzIDAtMTAxLjM1IDQ1LjQ2MS0xMDEuMzUgMTAxLjM0IDAgMTAxLjgyIDEyNy44NyAxOTcuMDEgMTkyLjI3IDI0OC4xNnoiIGZpbGw9IiNmZmYiLz4KPC9zdmc+Cg==");
		  transition: all .9s ease-in-out;
		  transform: rotate(-1turn);
		}
		
		.active::before {
		   background-image: url("data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iNzUycHQiIGhlaWdodD0iNzUycHQiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDc1MiA3NTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiA8cGF0aCBkPSJtMzY5Ljg0IDU1MC4yOGMwLjQ3MjY2IDAuNDcyNjYgMC45NDUzMSAwLjQ3MjY2IDAuOTQ1MzEgMC45NDUzMSA2NS4zNTUtNTEuNjIxIDE5My43LTE0OC4yMyAxOTMuNy0yNDkuMTEgMC01NS44ODMtNDUuNDY1LTEwMS4zNS0xMDEuMzUtMTAxLjM1LTM5Ljc4MSAwLTc0LjM1MiAyMy4yMDctOTAuOTI2IDU2LjgyOC0wLjQ3MjY2IDAuOTQ1MzEtMS40MjE5IDMuMzE2NC0xLjQyMTkgMy4zMTY0cy0wLjk0NTMxLTEuODk0NS0wLjk0NTMxLTIuMzY3MmMtMTYuMTAyLTM0LjA5LTUwLjY3Mi01Ny43Ny05MC45MjYtNTcuNzctNTUuODgzIDAtMTAxLjM1IDQ1LjQ2MS0xMDEuMzUgMTAxLjM0IDAgMTAxLjgyIDEyNy44NyAxOTcuMDEgMTkyLjI3IDI0OC4xNnoiIGZpbGw9IiNmZmYiLz4KPC9zdmc+Cg==");
		}
		
      .swiper-slide {
	        text-align: center;
	        font-size: 10px;
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
	      
      .signupBtn {
      	  float: right;
		  width: 130px;
		  height: 40px;
		  border-radius: 30px;
		  border: none;
		  display: flex;
		  align-items: center;
		  justify-content: flex-start;
		  padding-left: 20px;
		  gap: 9px;
		  color: white;
		  background: linear-gradient(to right,rgb(128, 128, 255),rgb(183, 128, 255));
		  position: relative;
		  cursor: pointer;
		  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.212);
		}
		
		.arrow {
		  position: absolute;
		  right: 7.5px;
		  background-color: rgb(255, 255, 255);
		  width: 25px;
		  height: 25px;
		  display: flex;
		  align-items: center;
		  justify-content: center;
		  border-radius: 50%;
		}
		
		.signupBtn:hover .arrow {
		  animation: slide-in-left 0.7s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		
		@keyframes slide-in-left {
		  0% {
		    transform: translateX(-10px);
		    opacity: 0;
		  }
		
		  100% {
		    transform: translateX(0);
		    opacity: 1;
		  }
	  	}	
	  	
	  	.button {
	  	  margin-top: 1em;
		  font-size: 14px;
		  font-weight: bold;
		  border: 0px;
		  color: white;
		  background-color: #1877f2;
		  padding: 1rem 2rem;
		  border-radius: 15px;
		  cursor: pointer;
		}
		
		.button:hover {
		  background-image: linear-gradient(45deg,
				#B799FF 0%,
				#ACBCFF 50%,
				#AEE2FF 75%,
				#E6FFFD 100%);
		  color: black;
		  animation: slide 10s linear infinite;
		}
		
		@keyframes slide {
		  100% {
		    background-position: 50rem;
		  }
		}
    </style>
	</head>
	<body class="is-preload">
		<!-- Home -->
		<h1 style="text-align: center; padding-top: 1em;">거래 게시판</h1>
		<hr>
		<div class="container-fluid" style="display: flex; flex-direction: column;">
			<form action="${path}/trade/modTrade.do" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-4">
					<input type="hidden" name="no" value="${vo.no}">
						<div class="swiper mySwiper" style="width: 400px; height: 400px; float: left;">
						    <div class="swiper-wrapper">
								<c:forEach var="fileName" items="${vo.fileNames}">
									<div class="swiper-slide">
						      			<img src="${path}/trade/imageList.do?no=${vo.no}&imageFileName=${fileName}" style="width: 100%; height: 100%; object-fit:scale-down;">
									</div>
						    	</c:forEach>
							</div>
							<div class="swiper-button-next"></div>
							<div class="swiper-button-prev"></div>
						    <div class="swiper-pagination"></div>
			  			</div>
		  			</div>
					<div class="col-md-8">
						<h4>${vo.title}</h4>
						<small>가격: ${vo.price}원</small>
						<p style="margin-top: 12em">
							${vo.nickname} | ${memInfo.addr3}
						<span><small></small></span>
						<div>	
							<c:if test="${like == false or empty like}">
								<button class="like" type="button" onclick="like();"> <span>찜하기</span></button>
							</c:if>
							<c:if test="${like == true}">
								<button class="like active" type="button" onclick="like();"> <span>찜하기</span></button>
							</c:if>
							
						</div>
						</p>
					</div>
				</div>
			</form>
			<div class="row" style="border-top: 1px solid gray; min-height: 400px; border-bottom: 1px solid gray; margin-top: 1em;">
				<div class="col-md-12">
					${vo.content}
				</div>
			</div>
			
			<div>
				<button type="button" class="button" onclick="location.href='${path}/trade/tradeList.do'">글 목록</button>
				<c:if test="${vo.nickname == member.nickname}">
					<button type="button" class="button subBtn" onclick="location.href='${path}/trade/modTradeForm.do?no=${vo.no}'">글 수정</button>
					<button type="button" class="button subBtn" onclick="location.href='${path}/trade/delTrade.do?no=${vo.no}'">글 삭제</button>
				</c:if>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<form action="${path}/trade/regComment.do" method="post">
						<input type="hidden" name="boardNo" value="${vo.no}">
						<input type="hidden" name="nickname" value="${member.nickname}">
						<input type="text" name="content" style="width: calc(100% - 150px); float: left; height: 2.3em; border: 1px solid lightgray; padding-left: 15px;" placeholder="댓글을 입력해주세요">
						<button class="signupBtn" type="submit">
						  	댓글작성
						 	<span class="arrow">
						    	<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512" fill="rgb(183, 128, 255)"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"></path></svg>
						 	</span>
						</button>
					</form>
				</div>
				<c:if test="${empty commentList }">
					<div class="col-md-12">
						<div  style="padding-left: 1em; background-color: lightgray; vertical-align: middle;">
						<span>아직 등록된 댓글이 없습니다.</span>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty commentList}">
					<c:forEach var="list" items="${commentList}" varStatus="loop">
						<div class="col-md-12">
							<div style="float: left; line-height: 2em; margin-left: 1em; width: calc(100% - 125px); ">
							<img src="${path }/resources/images/a.jpg" style="width: 30px; height: 30px; border-radius: 60%; float: left; object-fit: scale-down; border: 1px solid lightgray; margin-right: 1em; background: white;">
								<b>${list.nickname}</b> | <small>${list.writeDate}</small> &nbsp;&nbsp;
								<c:if test="${not empty member.id}">
									<small><a href="javascript:recommentForm(${loop.index}, ${list.no})"><i class="fa-regular fa-comment"></i></a></small> &nbsp;
								</c:if>
								<c:if test="${member.nickname eq list.nickname}">
									<small><a href="javascript:modForm(${loop.index})"><i class="fa-regular fa-pen-to-square"></i></a></small> &nbsp;
									<small><a href="${path}/trade/delComment.do?no=${list.no}&boardNo=${vo.no}"><i class="fa-regular fa-trash-can"></i></a></small>
								</c:if>
								<br>
								<c:choose>
									<c:when test="${list.level > 1}">
										<c:forEach begin="1" end="${list.level}" step="1">
											<span style="padding-left: 20px"></span>
										</c:forEach>
										└ 
									</c:when>
								</c:choose>
								<span class="content" style="display: inline-block;">&nbsp;${list.content}</span>
								<span class="comment" style="display: none; width: 100%;">
									<input type="text" name="content" value="${list.content}" style="width: calc(100% - 150px); float: left; height: 2.3em; border: 1px solid lightgray; padding-left: 15px; color:black;">
									<button class="signupBtn" type="button" onclick="javascript: modComment(${list.no}, ${vo.no}, ${loop.index})">
									  	댓글수정
									 	<span class="arrow">
									    	<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512" fill="rgb(183, 128, 255)"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"></path></svg>
									 	</span>
									</button>
								</span>
							</div> 
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		
		<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
		<script type="text/javascript">
			var swiper = new Swiper(".mySwiper", {
				spaceBetween: 30,
			    centeredSlides: true,
			    autoplay: {
			      delay: 3000,
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
			
			$(function() {
				var nickname = '${member.nickname}';
				
				if(nickname.length == 0){
					$('.signupBtn').on('click', function(e) {
						e.preventDefault();
						
						alert('로그인이 필요한 기능입니다'); 
						location.href='${path}/member/loginForm.do';
					});
				}
			});
			
			function like() {
				var nickname = '${member.nickname}';
				alert('좋아여~\r\n' + nickname);
				
				if(nickname.length == 0 && nickname== '') {
					alert('로그인이 필요한 기능입니다.');
				} else {
					location.href = '${path}/trade/likeTrade.do?no='+${vo.no}+'&nickname='+nickname;
				}
			}
			
			function modForm(index) {
				var _content = $('.content');
				var content = _content[index];
				var _comment = $('.comment');
				var comment = _comment[index];
				
				if(content.style.display == "none") {
					content.style.display="inline-block";
					comment.style.display="none";
				} else if (content.style.display == "inline-block") {
					content.style.display="none";
					comment.style.display="inline-block";
				}
			}
			
			function modComment(no, boardNo, index) {
				var comment = $('.comment')[index];
				var content = comment.children[0].value;
				var i_content = $('.content')[index];
// 				console.log(no);
// 				console.log(boardNo);
// 				console.log(index);
				
				$.ajax({
					url: '${path}/trade/modComment.do',
					type: 'POST',
					dataType: 'text',
					data: {no: no, boardNo: boardNo, content: content},
					success: function(data) {
						i_content.innerText=data;
						
						i_content.style.display="inline-block";
						comment.style.display="none";
					}
				});
				
			}
			
			function recommentForm(index, parentNo) {
				var recForm = document.createElement("div");
				recForm.innerHTML = '<form action="${path}/trade/regComment.do" method="post">' + 
										'<input type="hidden" name="nickname" value="${member.nickname}">' + 
										'<input type="hidden" name="boardNo" value="${vo.no}">' + 
										'<input type="hidden" name="parentNo" value="' + parentNo + '">' + 
										'<input type="text" name="content" style="width: calc(100% - 150px); float: left; height: 2.3em; border: 1px solid lightgray; padding-left: 15px; color: black;" placeholder="댓글을 입력해주세요">' +
										'<button class="signupBtn" type="submit" >' +
									  		'댓글작성' +
					 						'<span class="arrow">' +
					    						'<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512" fill="rgb(183, 128, 255)"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"></path></svg>' +
					 						'</span>' + 
										'</button>'
									'</form>';
				
				// 클릭한 댓글 다음에 댓글 작성 폼 추가
			    var comment = document.getElementsByClassName("content")[index];
			    comment.parentElement.appendChild(recForm);
			}
			
		</script>
	</body>
</html>