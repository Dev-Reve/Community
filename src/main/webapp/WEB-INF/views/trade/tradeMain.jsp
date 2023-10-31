<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="center" value="${center}" />

<head>
	<style type="text/css">
		.layout {
	        display: grid;
	        grid:
	          "sidebar . body" 1fr
	          / auto 1fr;
	      }
	
	      .sidebar { 
				grid-area: sidebar;
				position: sticky;
				top: 15%;
				height: 20%;
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
	</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<section class="layout">
		<div class="sidebar">
			<jsp:include page="/WEB-INF/views/trade/side.jsp" />
		</div>
		<div class="body" style="margin-left: 5%; margin-right: 5%;">
			<jsp:include page="${center}" />
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
