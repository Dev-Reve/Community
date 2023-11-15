<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${path}/resources/assets/css/main.css" />
		<style>
		.form {
		  display: flex;
		  margin: 0 auto;
		  align-content: center;
		  justify-content: center;
		  flex-direction: column;
		  gap: 10px;
		  width: 1200px;
		  background-color: #fff;
		  border-radius: 20px;
		  padding: 30px 20px;
		  box-shadow: 100px 100px 80px rgba(0, 0, 0, 0.03)
		}
		
		.title {
		  color: black;
		  font-weight: bold;
		  text-align: center;
		  font-size: 20px;
		  margin-bottom: 4px;
		}
		
		.sub {
		  text-align: center;
		  color: black;
		  font-size: 14px;
		  width: 100%;
		}
		
		.sub.mb {
		  margin-bottom: 1px;
		}
		
		.sub a {
		  color: rgb(23, 111, 211);
		}
		
		.avatar {
		  height: 125px;
		  width: 125px;
		  
		  border-radius: 50%;
		  align-self: center;
		  padding: 6px;
		  cursor: pointer;
		  box-shadow: 12.5px 12.5px 10px rgba(0, 0, 0, 0.015),100px 100px 80px rgba(0, 0, 0, 0.03);
		}
		
		.form button {
		  align-self: flex-end;
		}
		
		.input, button {
		  border: none;
		  outline: none;
		  width: 50%;
		  padding: 16px 10px;
		  background-color: rgb(247, 243, 243);
		  border-radius: 10px;
		  box-shadow: 12.5px 12.5px 10px rgba(0, 0, 0, 0.015),100px 100px 80px rgba(0, 0, 0, 0.03);
		}
		
		button {
		  margin-top: 12px;
		  background-color: rgb(23, 111, 211);
		  color: #fff;
		  text-transform: uppercase;
		  font-weight: bold;
		}
		
		.input:focus {
		  border: 1px solid rgb(23, 111, 211);
		}
		
		#file {
		  display: none;
		}
		.avatar {
		    position: relative;
		    width: 100px; /* 라벨의 너비에 맞게 조절 */
		    height: 100px; /* 라벨의 높이에 맞게 조절 */
		    overflow: hidden;
		    border-radius: 50%;
		    display: inline-block;
		  }
		
		  .avatar img {
		    width: 100%; /* 이미지의 너비를 부모인 라벨의 100%로 설정 */
		    height: 100%; /* 이미지의 높이를 부모인 라벨의 100%로 설정 */
		    object-fit: cover;
		    border-radius: 50%;
		  }		
		  
		  .avatar svg{
		  	background-color: rgb(23, 111, 211);
		  	border-radius: 50%;		  
		  }
		</style>
	</head>
	<body class="is-preload">
			                                                            
	<div id="mainbox">	
			<form class="form" action="${path}/member/UpdateMember.do" method="post" enctype="multipart/form-data">
				<input id="file" type="file" name="fileName" onchange="setThumbnail(event);">
		  		<label class="avatar" for="file">
		  			<img id="thumbnail" src="${path}/member/download.do?nickname=${memberVO.nickname}" alt="" style="border-radius: 50%; object-fit: cover;">
			  		<input type="hidden" name="fileName" value="${memberVO.fileName}">
		  		</label>
				<input type="text" name="id" value="${memberVO.id }" readonly/>
				<input type="password" name="password" placeholder="비밀번호 입력"/>
				<input type="password" name="passwordConfirm" placeholder="비밀번호 확인"/>
				<input type="hidden" name="password" value="${memberVO.password}">
				<input type="text" name="name" value="${memberVO.name }"/>
				<input type="text" name="ssn" value="${memberVO.ssn }" readonly/>
				<input type="text" name="nickname" value="${memberVO.nickname }"/>
				<input type="text" name="email" value="${memberVO.email }"/>
				<input type="text" id="sample6_postcode" name="addr1" value="${memberVO.addr1 }" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" name="addr2" value="${memberVO.addr2 }" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" name="addr3" value="${memberVO.addr3 }"placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" name="addr4" value="${memberVO.addr4 }" placeholder="참고항목"><br><br>
				<input type="submit" value="수정하기"/>
			</form>
	</div>	
					
		<!-- Scripts -->
			<script src="${path}/resources/assets/js/jquery.min.js"></script>
			<script src="${path}/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="${path}/resources/assets/js/browser.min.js"></script>
			<script src="${path}/resources/assets/js/breakpoints.min.js"></script>
			<script src="${path}/resources/assets/js/util.js"></script>
			<script src="${path}/resources/assets/js/main.js"></script>
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    function setThumbnail(event) {
        var input = event.target;
        var reader = new FileReader();
		var reset = document.getElementById('reset');
        reader.onload = function () {
          var thumbnail = document.getElementById('thumbnail');
          thumbnail.src = reader.result;
          reset.remove();
        };

        reader.readAsDataURL(input.files[0]);

        if (!input.files[0]) {
          var thumbnail = document.getElementById('thumbnail');
          thumbnail.src = '';
        }
      }
</script>
	</body>
</html>