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
<title>글 상세보기</title>
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
    
    .comment-input2 {
      width: 90%;
      display: none; /* 초기에는 숨겨진 상태로 설정 */
    }

  .comment-container {
    width: 70%;
    margin: 20px auto;
    border: 1px solid #ccc;
    padding: 10px;
    border-radius: 8px;
  }

  .comment {
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    background-color: #f9f9f9;
  }

  .comment h6 {
    margin: 0;
    font-size: 16px;
  }

  .comment p {
    margin: 0;
    font-size: 14px;
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

function area2() {
    var commentInput2 = document.querySelector('.comment-input2');
    if (commentInput2.style.display === 'none') {
      commentInput2.style.display = 'block'; // 보이게 만듭니다.
    } else {
      commentInput2.style.display = 'none'; // 숨깁니다.
    }
}
</script>
</head>
<body>
    <h3>글 상세보기</h3>
    
    <table class="styled-table" border="1">
        <tr>
            <th>제목:</th>
            <td>${boardInfo.title}</td>
        </tr>
        <tr>
            <th>작성자:</th>
            <td>${boardInfo.nickName}</td>
        </tr>
        <tr>
            <th>작성일:</th>
            <td>${boardInfo.writeDate}</td>
        </tr>
        <tr>
            <th>조회수:</th>
            <td>${boardInfo.readCount}</td>
        </tr>
    </table>
    <div class="content-area">
        ${boardInfo.content}
    </div>
    <c:choose>
        <c:when test="${not empty boardInfo.fileName}">
            <p>파일: ${boardInfo.fileName}</p>
            <hr>
        </c:when>
        <c:otherwise>
            <p>등록된 파일이 없습니다.</p>
            <hr>
        </c:otherwise>
    </c:choose>
    
    <div class="comment-container">
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                <c:choose>
                    <c:when test="${member.name == comment.nickName}">
                        <h6>${comment.nickName}&nbsp;&nbsp;&nbsp;${comment.writeDate}&nbsp;&nbsp;&nbsp;
                            <a href="#" onclick="area2()">수정</a>&nbsp;
                            <a href="${Path}/board/delComment.do?no=${comment.no}">삭제</a></h6>
                        
                        <div class="comment-input2">
                            <form action="${Path}/board/editComment.do" method="GET">
                                <input type="hidden" name="cno" value="${comment.no}">
                                <input type="hidden" name="bno" value="${boardInfo.no}">
                                <input type="text" name="comment" style="width: 90%;" value="${comment.content}">
                                <button type="submit">댓글 수정</button>
                            </form>
                        </div>
                        
                        <p>${comment.content}</p><hr>
                    </c:when>
                    <c:otherwise>
                        <h6>${comment.nickName}&nbsp;&nbsp;&nbsp;${comment.writeDate}</h6>
                        <p>${comment.content}</p><hr>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
    
    <b>댓글 <a href="javascript:void(0);" onclick="area()"> + </a></b><br>
    <div class="comment-input">
        <form action="${Path}/board/addComment.do" method="GET">
            <input type="hidden" name="no" value="${boardInfo.no}">
            <input type="hidden" name="name" value="${member.name}">
            <input type="text" name="comment" style="width: 90%;">
            <button type="submit">댓글 작성</button>
        </form>
    </div>
    
    <hr>
    
    <c:choose>
        <c:when test="${nextTitle.next != '없음'}">
            다음 글 : <a href="${Path}/board/boardInfo.do?no=${boardInfo.no+1}&name=${member.name}">${nextTitle.next}</a><br>
        </c:when>
        <c:otherwise>
            다음 글 : 다음 글이 없습니다. <br>
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${nextTitle.before != '없음'}">
            이전 글 : <a href="${Path}/board/boardInfo.do?no=${boardInfo.no-1}&name=${member.name}">${nextTitle.before}</a><br>
        </c:when>
        <c:otherwise>
            이전 글 : 이전 글이 없습니다.
        </c:otherwise>
    </c:choose>
    
    <hr>
    <a href="${Path}/board/listboard.do">목록으로 가기</a>
</body>

</html>
