<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />
<c:set var="id" value="${member.id} " />
<c:set var="name" value="${member.nickname} " />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .styled-table {
        border-collapse: collapse;
        width: 100%;
        margin-bottom: 20px;
    }

    .styled-table th {
        background-color: #f2f2f2;
        font-weight: bold;
        text-align: left;
    }

    .styled-table tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    .styled-table tr:nth-child(odd) {
        background-color: #ffffff;
    }

    .styled-table td {
        padding: 8px 12px;
        text-align: left;
        border: 1px solid #ddd;
    }

    .content-area {
        padding: 20px;
    }
    
    a {
	  text-decoration: none;
	}
	
    .comment-input {
      width: 90%;
      display: none; /* 초기에는 숨겨진 상태로 설정 */
    }
</style>

</head>
<body>
    <h3>글 상세보기</h3>
    
    <table class="styled-table" border="1">
        <tr>
            <th>제목:</th>
          	<td>${vo.title}</td>
        </tr>
        <tr>
            <th>작성자:</th>
            <td>${vo.nickname}</td>
        </tr>
        <tr>
            <th>작성일:</th>
            <td>${vo.writeDate}</td>
        </tr>
        <tr>
            <th>조회수:</th>
          	  <td>${vo.readCount}</td>
        </tr>
    </table>
    <div class="content-area">
     	<div class="row" style="border-top: 1px solid gray; min-height: 400px; margin-top: 1em;">
				<c:forEach var="fileName" items="${vo.fileName}">
					<div>
						  <img src="${Path}/gallery/image.do?no=${vo.no}&imageFileName=${fileName}" style="width: 100%; height: 100%; object-fit:scale-down;"><br>
					</div>
			   </c:forEach>
		</div>  	   
    </div>
    <div class="row" style="border-top: 1px solid gray; min-height: 400px; border-bottom: 1px solid gray; margin-top: 1em;">
				<div class="col-md-12">
					${vo.content}
				</div>
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
							<img src="${path }/member/download.do?nickname=${list.nickname}" style="width: 30px; height: 30px; border-radius: 60%; float: left; object-fit: scale-down; border: 1px solid lightgray; margin-right: 1em; background: white;">
								<b>${list.nickname}</b> | <small>${list.writeDate}</small> &nbsp;&nbsp;
								<c:if test="${not empty member.id}">
									<small><a href="javascript:recommentForm(${loop.index}, ${list.no})"><i class="fa-regular fa-comment"></i></a></small> &nbsp;
								</c:if>
								<c:if test="${member.nickname eq list.nickname}">
									<small><a href="javascript:modForm(${loop.index})"><i class="fa-regular fa-pen-to-square"></i></a></small> &nbsp;
									<small><a href="${path}/gallery/delComment.do?no=${list.no}&boardNo=${vo.no}"><i class="fa-regular fa-trash-can"></i></a></small>
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
    <a href="${Path}/board/listboard.do">목록으로 가기</a>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
		<script type="text/javascript">
		const url = 'http://localhost:8090/';
		var resultImg = "${path}/trade/thumbnail.do?no=${vo.no}";
		const shareTitle = '${vo.title}';
		const shareImg = url + 'community/trade/thumbnail.do?no=${vo.no}';
		const shareUrl = url + 'community/trade/tradeDetail.do?no=${vo.no}';
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
					url: '${path}/gallery/modComment.do',
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
				recForm.innerHTML = '<form action="${path}/gallery/regComment.do" method="post">' + 
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
			
			function kakaoShare() {
				Kakao.Share.createDefaultButton({
				    container: '#kakaotalk-sharing-btn',
				    objectType: 'feed',
				    content: {
				      title: shareTitle,
				      imageUrl:
				        shareImg,
				      link: {
				        // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
				        mobileWebUrl: shareUrl,
				        webUrl: shareUrl,
				      },
				    },
				    social: {
				      likeCount: 286,
				      commentCount: 45,
				      sharedCount: 845,
				    },
				    buttons: [
				      {
				        title: '웹으로 보기',
				        link: {
				          mobileWebUrl: shareUrl,
				          webUrl: shareUrl,
				        },
				      },
				      {
				        title: '앱으로 보기',
				        link: {
				          mobileWebUrl: shareUrl,
				          webUrl: shareUrl,
				        },
				      },
				    ],
				  });
			}
			
		</script>
</body>

</html>