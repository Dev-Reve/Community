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
    </head>
    <body>
        <!-- Page Content-->
        <div class="container px-4 px-lg-5">
            <!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center my-5">
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="${path}/first/maintopdownload.do" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">오늘의 추천 영화</h1>
                    <p>그웬 스테이시와 힘을 합쳐 새로운 모험에 나서는 십 대 소년 마일스 모랄레스. 멀티버스에서 사악한 악당 스팟과 평행우주 속 수많은 영웅들을 만나게 된다.</p>
                    <a class="btn btn-primary" href="https://www.netflix.com/kr/title/81594921">넥플릭스에서 시청하기!</a>
                </div>
            </div>
            <!-- Call to Action-->
            <div class="bg-secondary my-5 py-4 ">
                <div class="card-body"><h2 class=" m-0">갤러리 게시물</h2><hr>
                <div class="row gx-4 gx-lg-5">              
                	<c:forEach var="gall" items="${ReG}" varStatus="i">
                	<div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body-footer">
                            <p class="card-text"><img src="${path}/first/galldownload.do?no=${gall.no}" alt="..." width="100%" />
                            	<br>
                            </p>
                        </div>
                        <div class="card-footer">
                        	<span style="color: black;"><a href="${path}/gallery/galleryDetail.do?no=${gall.no}">${gall.title}</a></span> <span style="color:red">[${gall.readCount}]</span><br>
                        	<span style="color: gray;">${gall.nickname}</span>
                        </div>
                          </div>
                    </div>
                    </c:forEach>
                         
                </div>
                </div>
            </div>
            <div class=" bg-secondary my-5 py-4 ">
                <div class="card-body"><h2 class=" m-0">거래 게시판</h2><hr>
                <div class="row gx-4 gx-lg-5">
                <c:forEach var="trd" items="${ReT}" varStatus="i">
                	<div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body-footer">
                            <p class="card-text"><img src="${path}/first/tradedownload.do?no=${trd.no}" alt="..." width="100%" />
                            	<br>
                            </p>
                        </div>
                        <div class="card-footer">
                        	<span style="color: black;">${trd.title}</span> <span style="color:red">[${trd.readCount}]</span><br>
                        	<span>가격 : ${trd.price}원</span><br>
                        	<span style="color: gray;">${trd.nickname}</span>
                        </div>
                          </div>
                    </div>
                    </c:forEach>
                
                </div>
                </div>
            </div>
        </div>  
    </body>
</html>
