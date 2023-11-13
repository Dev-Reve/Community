<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<c:set var="center" value="${center}" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
<link rel="stylesheet" href="${path}/resources/assets/css/index.css" />
<link rel="stylesheet" href="${path}/resources/assets/css/side.css" />
<link rel="icon" type="image/x-icon" href="${path}/resources/aseets/icon/favicon.ico" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>네꺼내꺼내꺼네꺼</title>
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
<div class="chat" style="position: fixed; bottom: 20px; right: 20px; background-color: gray; width: 50px; height: 50px; border-radius: 50%;">
	<a href="${path}/chat" style="text-decoration: none;" target="_blank">
		<i class="fa-solid fa-message" style="padding: 1.1em; width: 40px; height: 40px; color:white;"></i>
	</a>
</div>
</section>
<script type="text/javascript">
	var id = '${member.id}';
	console.log(id);
	if(id == null || id == '') {
		$('.chat').hide();
	}
</script>
</body>
 		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</html>