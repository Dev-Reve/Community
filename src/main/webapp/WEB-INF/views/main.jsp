<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="center" value="${center}" />

<jsp:include page="/WEB-INF/views/common/top.jsp" />
	
<jsp:include page="${center}" />

<jsp:include page="/WEB-INF/views/common/footer.jsp" /> 
	
