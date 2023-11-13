<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="Path" value="${pageContext.request.contextPath}" />
<c:set var="id" value="${member.id} " />
<c:set var="name" value="${member.nickname} " />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .styled-table {
        border-collapse: collapse;
        width: 100%;
        margin-bottom: 20px;
    }

    .styled-table th {
        background-color: #f2f2f2;
        font-weight: bold;
        text-align: left;
    }

    .styled-table tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    .styled-table tr:nth-child(odd) {
        background-color: #ffffff;
    }

    .styled-table td {
        padding: 8px 12px;
        text-align: left;
        border: 1px solid #ddd;
    }

    .content-area {
        padding: 20px;
    }
    
    a {
	  text-decoration: none;
	}
	
    .comment-input {
      width: 90%;
      display: none; /* 초기에는 숨겨진 상태로 설정 */
    }
</style>
<script type="text/javascript">
function area() {
    var commentInput = document.querySelector('.comment-input');
    if (commentInput.style.display === 'none') {
      commentInput.style.display = 'block'; // 보이게 만듭니다.
    } else {
      commentInput.style.display = 'none'; // 숨깁니다.
    }
  }
</script>
</head>
<body>
    <h3>글 상세보기</h3>
    
    <table class="styled-table" border="1">
        <tr>
            <th>제목:</th>
          	<td>${vo.title}</td>
        </tr>
        <tr>
            <th>작성자:</th>
            <td>${vo.nickname}</td>
        </tr>
        <tr>
            <th>작성일:</th>
            <td>${vo.writeDate}</td>
        </tr>
        <tr>
            <th>조회수:</th>
          	  <td>${vo.readCount}</td>
        </tr>
    </table>
    <div class="content-area">
       
    </div>
<%--     <c:choose> --%>
<%--         <c:when test="${not empty boardInfo.fileName}"> --%>
<%--             <p>파일: ${boardInfo.fileName}</p> --%>
<!--             <hr> -->
<%--         </c:when> --%>
<%--         <c:otherwise> --%>
<!--             <p>등록된 파일이 없습니다.</p> -->
<!--             <hr> -->
<%--         </c:otherwise> --%>
<%--     </c:choose> --%>
    
   	<b>댓글(0) <a href="javascript:void(0);" onclick="area()"> + </a></b><br>
    <div class="comment-input">
      <input type="text" name="comment" style="width: 90%;"> 
      <a href="${Path}/board/addCommnet.do?no=${boardInfo.no}">댓글 작성</a> </div>
    <hr>
    <a href="${Path}/board/listboard.do">목록으로 가기</a>
</body>

</html>