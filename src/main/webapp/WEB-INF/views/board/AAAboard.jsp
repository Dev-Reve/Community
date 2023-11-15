<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 검색 -->
	<form action="${Path}/board/getList.do" id="frm">
		<input type="hidden" name="pageNum" value="1" id="pageNum">
		<select name="kind" id="kind">
			<option class="s" value="title">제목</option>
			<option class="s" value="content">내용</option>
			<option class="s" value="nickname">작성자</option>
		</select>
		<input type="text" name="search" id="search" value="${pager.search}" />
		<button type="submit" id="btn">검색</button>
	</form>
	
	<!-- 페이징 -->
	<section id="paging">
		<button class="p" data-list-pn="${pager.startNum-1 }" type="button">이전</button>
		
		<c:forEach begin="${pager.startNum }" end="${pager.lastNum }" var="i">
			<span class="p" data-list-pn="${i }">${i }</span>
		</c:forEach>
		
		<c:if test="${!pager.lastCheck} ">
			<button class="p" data-list-pn="${pager.lastNum + 1 }" type="button">다음</button>
		</c:if>
	</section>

	<!-- 추가로 setKind 함수 호출 시 매개변수 넣어주기 -->
	<script type="text/javascript">
		setKind("${pager.kind}");
	
		function setKind(kind) {
			$(".s").each(function() {
				if($(this).val()==kind){
					$(this).prop("selected", true);
				}
			})
		};
		
		$(".p").click(function () {
			const n = $(this).atter("data-list-pn");
			$("#pageNum").val(n);
			$('#frm').submit();
			
		});
		
	</script>
	
</body>
</html>