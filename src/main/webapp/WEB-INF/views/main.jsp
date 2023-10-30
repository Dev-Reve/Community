<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
	</head>
<c:set var="center" value="${center}" />

<jsp:include page="/WEB-INF/views/common/top.jsp" />
	
<jsp:include page="${center}" />

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</html>