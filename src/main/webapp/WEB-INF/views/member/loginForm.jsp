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
		<style>
		#mainbox{
		
		height: 700px;
		width:  1200px;
		}
		
		
		</style>
		<c:choose>
			<c:when test="${result=='loginFailed' }">
			  <script>
			    window.onload=function(){
			      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
			    }
			  </script>
			</c:when>
		</c:choose>  
	</head>
	<body class="is-preload">
			<jsp:include page="../common/top.jsp" />
			
	<div id="mainbox">
		<form method="post" action="${path}/member/login.do">
			아이디:<input type="text" name="id"/>
			비밀번호:<input type="password" name="password"/>
			<input type="submit" value="로그인"/>
			<input type="reset" value="다시 작성"/>
		</form>
	</div>	
					
					<footer>
						<jsp:include page="../common/footer.jsp" />
					</footer>
				
		

		<!-- Contact -->
			<article id="contact" class="wrapper style4">
				<div class="container medium">
					
						<div class="col-12">
							<hr />
							<h3>Find me on ...</h3>
							<ul class="social">
								<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands fa-dribbble"><span class="label">Dribbble</span></a></li>
								<li><a href="#" class="icon brands fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
								<li><a href="#" class="icon brands fa-tumblr"><span class="label">Tumblr</span></a></li>
								<li><a href="#" class="icon brands fa-google-plus"><span class="label">Google+</span></a></li>
								<li><a href="#" class="icon brands fa-github"><span class="label">Github</span></a></li>
								<!--
								<li><a href="#" class="icon solid fa-rss"><span>RSS</span></a></li>
								<li><a href="#" class="icon brands fa-instagram"><span>Instagram</span></a></li>
								<li><a href="#" class="icon brands fa-foursquare"><span>Foursquare</span></a></li>
								<li><a href="#" class="icon brands fa-skype"><span>Skype</span></a></li>
								<li><a href="#" class="icon brands fa-soundcloud"><span>Soundcloud</span></a></li>
								<li><a href="#" class="icon brands fa-youtube"><span>YouTube</span></a></li>
								<li><a href="#" class="icon brands fa-blogger"><span>Blogger</span></a></li>
								<li><a href="#" class="icon brands fa-flickr"><span>Flickr</span></a></li>
								<li><a href="#" class="icon brands fa-vimeo"><span>Vimeo</span></a></li>
								-->
							</ul>
							<hr />
						</div>
					</div>
				</div>
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