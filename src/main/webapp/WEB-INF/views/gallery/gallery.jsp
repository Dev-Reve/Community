<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${path}/resources/assets/css/Gallerystyles.css" rel="stylesheet" />
    </head>
    <body class="is-preload">
    			<h1 style="text-align: center; padding - top:1em;"></h1>
    			<button class="cssbuttons-io-button writeBtn" onclick="location.href='${path}/gallery/regGalleryForm.do'">
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

        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <c:choose>
                	<c:when test="${empty gallery}">
                		<p>등록된 글이 없습니다.</p>
                	</c:when>
                	<c:otherwise>
             			<!-- 한페이지에 보여질 카드 수, 현재 페이지 번호, 시작 인덱스 값 , 종료 인덱스값 -->
                		<c:set var="cardPage" value="8" />
                		<c:set var="curPage" value = "${empty page ? 1 : page }" />
                		<c:set var="startIndex" value ="${(curPage - 1) * cardPage}" />     		
                		<c:set var="endIndex" value="${startIndex + cardPage}" />
			       			<c:forEach var="gall" items="${gallery}" varStatus="i">
			       			<c:if test="${i.index >= startIndex && i.index < endIndex }">
			       			<div class="col mb-5">
			                        <div class="card h-100">
			                            <!-- 썸네일 이미지 출력 구간-->
			                            <img class="card-img-top" src="${path}/file/galleryList.do?no=${gall.no}" alt="..." />                         
			                            <!-- Product details-->
			                            <div class="card-body p-4">
			                                <div class="text-center">
			                                    <!-- Product name-->
			                                    <h5 class="fw-bolder">${gall.title} </h5>
			                                    <h3 class="fw-bolder">${gall.nickname} </h3>
			                                    <div class="d-flex justify-content-center small text-warning mb-2">
			                                        <div class="bi-star-fill"></div>
			                                        <div class="bi-star-fill"></div>
			                                        <div class="bi-star-fill"></div>
			                                        <div class="bi-star-fill"></div>
			                                        <div class="bi-star-fill"></div>
			                                    </div>
			         
			                                </div>
			                            </div>
			                            <!-- Product actions-->
			                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
			                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="text-center">Add to cart</a></div>
			                            </div>
			                        </div>
			                    </div> 
			                    </c:if>   			
			       				</c:forEach>   
       				</c:otherwise> 
       				</c:choose> 
                </div>
            </div>
        </section>
     
         <div class="text-center">
         	<!-- 페이지 번호 표시하는 쪽 -->
         	<!-- 전체페이지 구하는 구문 (갤러리 / 페이지당 보여질 수 ) -->     	
         	<nav aria-label="Page navigation example">
						<ul class="pagination" style="float: right; margin-right: 20px">
         	<c:set var="totalPages" value="${fn:substringBefore((Math.ceil((gallery.size() + cardPage - 1) / cardPage)), '.')}" />
         	<c:set var="prePages" value="${curPage - 1}" />
         	<c:set var="nextPages" value="${curPage + 1 }" />       		
         			<c:if test="${ 0 < curPage &&  curPage > 1}">
         					<li class="page-item">
								<a class="page-link" href="${path}/gallery/main.do?page=${prePages}">
									<span aria-label="Previous">${prePages}</span>
								</a>
							</li>       			
         			</c:if>
							<li class="page-item">
								<a class="page-link" >
									<span aria-label="Cur" >${curPage}</span>
								</a>
							</li>     
         			<c:if test="${nextPages < totalPages}">
         					<li class="page-item">
								<a class="page-link" href="${path}/gallery/main.do?page=${nextPages}">
									<span aria-label="Next">${nextPages}</span>
								</a>
							</li>    
         			</c:if>
         			</ul>
         	</nav>
         </div>
    </body>
</html>
