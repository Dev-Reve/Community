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
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="https://dummyimage.com/900x400/dee2e6/6c757d.jpg" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">아웃스타그램</h1>
                    <p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
                    <a class="btn btn-primary" href="#!">Call to Action!</a>
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
                        	<span style="color: black;">${gall.title}</span> <span style="color:red">[${gall.readCount}]</span><br>
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
                            <p class="card-text"><img src="${path}/first/galldownload.do?no=${trd.no}" alt="..." width="100%" />
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
            <!-- Content Row-->
            <div class="row gx-4 gx-lg-5">
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body card-body-footer">
                            <h2 class="card-title">Card One</h2>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt pariatur voluptatem sunt quam eaque, vel, non in id dolore voluptates quos eligendi labore.</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body card-body-footer">
                            <h2 class="card-title">Card Two</h2>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt pariatur voluptatem sunt quam eaque, vel, non in id dolore voluptates quos eligendi labore.</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body card-body-footer">
                            <h2 class="card-title">Card Three</h2>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem magni quas ex numquam, maxime minus quam molestias corporis quod, ea minima accusamus.</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
                    </div>
                </div>
            </div>
        </div>  
    </body>
</html>
