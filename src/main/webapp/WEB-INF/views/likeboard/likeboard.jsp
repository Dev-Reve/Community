<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    
    <meta charset="utf-8" />
    <title>LikeBoard</title>
    <style>
        /* Add CSS styles for center-aligning the div */
        .centered-div {
            text-align: center;
            margin-top: 20px; /* 필요에 따라 margin-top 값을 조정할 수 있습니다 */
        }
        /* Add CSS styles to display board containers side by side with spacing */
        .board-container {
            display: inline-block;
            width: 45%; /* 각 테이블을 더 가깝게 배치하려면 width 값을 조정할 수 있습니다 */
            margin: 10px; /* 테이블 사이의 간격을 조정할 수 있습니다 */
            vertical-align: top; /* 테이블을 상단 정렬로 배치합니다 */
        }
    </style>
</head>
<body>
    <div class="centered-div">
        <h1>인기 게시판</h1>
    </div>
    <div class="board-container">
        <table id="board_community" border="1">
            <caption>커뮤니티</caption>
            <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>추천수</th>
            </tr>
            <!-- 더미 데이터 예제 -->
            <tr>
                <td>1</td>
                <td>게시물 제목 1</td>
                <td>게시물 내용 1</td>
                <td>2023-10-30</td>
                <td>작성자1</td>
                <td>100</td>
                <td>10</td>
            </tr>
            <tr>
                <td>2</td>
                <td>게시물 제목 2</td>
                <td>게시물 내용 2</td>
                <td>2023-10-31</td>
                <td>작성자2</td>
                <td>150</td>
                <td>20</td>
            </tr>
        </table>
    </div>
    <div class="board-container">
        <table id="board_trade" border="1">
            <caption>중고거래</caption>
            <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>추천수</th>
            </tr>
            <!-- 더미 데이터 예제 -->
            <tr>
                <td>1</td>
                <td>게시물 제목 1</td>
                <td>게시물 내용 1</td>
                <td>2023-10-30</td>
                <td>작성자1</td>
                <td>100</td>
                <td>10</td>
            </tr>
            <tr>
                <td>2</td>
                <td>게시물 제목 2</td>
                <td>게시물 내용 2</td>
                <td>2023-10-31</td>
                <td>작성자2</td>
                <td>150</td>
                <td>20</td>
            </tr>
        </table>
    </div>
</body>
</html>
