<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
<link rel="stylesheet" href="${path}/resources/assets/css/index.css" />
</head>
<body>
	<footer class="py-5 bg-dark">
            <div class="container px-4 px-lg-5"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
    </footer>
	
	<!-- Scripts -->
	<script src="${path}/resources/assets/js/jquery.min.js"></script>
	<script src="${path}/resources/assets/js/jquery.scrolly.min.js"></script>
	<script src="${path}/resources/assets/js/browser.min.js"></script>
	<script src="${path}/resources/assets/js/breakpoints.min.js"></script>
	<script src="${path}/resources/assets/js/util.js"></script>
	<script src="${path}/resources/assets/js/main.js"></script>
</body>
</html>