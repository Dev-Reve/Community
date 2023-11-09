<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${path}/resources/assets/css/Gallerystyles.css" rel="stylesheet" />
    </head>
    <body>
				<button class="cssbuttons-io-button" onclick="location.href='${path}/gallery/regGalleryForm.do'">
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
       			<c:forEach var="gall" items="${gallery}" varStatus="i">
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
       				</c:forEach>     
                </div>
            </div>
        </section>
    </body>
</html>
