<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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
		<form class="form" action="${path}/member/addMember.do" method="post" enctype="multipart/form-data">
		  <span class="title">회원가입</span>
		  <span class="sub mb">만나서 반갑습니다:)</span>
		 <input id="file" type="file" name="fileName" onchange="setThumbnail(event);">
		  <label class="avatar" for="file">
		  		<span> 
		  			<svg id="reset" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><g stroke-width="0" id="SVGRepo_bgCarrier"></g><g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g><g id="SVGRepo_iconCarrier"> <path fill="#ffffff" d="M17.1813 16.3254L15.3771 14.5213C16.5036 13.5082 17.379 12.9869 18.2001 12.8846C19.0101 12.7837 19.8249 13.0848 20.8482 13.8687C20.8935 13.9034 20.947 13.9202 21 13.9202V15.024C21 19.9452 19.9452 21 15.024 21H8.976C4.05476 21 3 19.9452 3 15.024V13.7522C3.06398 13.7522 3.12796 13.7278 3.17678 13.679L4.45336 12.4024C5.31928 11.5365 6.04969 10.8993 6.71002 10.4791C7.3679 10.0605 7.94297 9.86572 8.50225 9.86572C9.06154 9.86572 9.6366 10.0605 10.2945 10.4791C10.9548 10.8993 11.6852 11.5365 12.5511 12.4024L16.8277 16.679C16.9254 16.7766 17.0836 16.7766 17.1813 16.679C17.2789 16.5813 17.2789 16.423 17.1813 16.3254Z" opacity="0.1"></path> <path stroke-width="2" stroke="#ffffff" d="M3 8.976C3 4.05476 4.05476 3 8.976 3H15.024C19.9452 3 21 4.05476 21 8.976V15.024C21 19.9452 19.9452 21 15.024 21H8.976C4.05476 21 3 19.9452 3 15.024V8.976Z"></path> <path stroke-linecap="round" stroke-width="2" stroke="#ffffff" d="M17.0045 16.5022L12.7279 12.2256C9.24808 8.74578 7.75642 8.74578 4.27658 12.2256L3 13.5022"></path> <path stroke-linecap="round" stroke-width="2" stroke="#ffffff" d="M21.0002 13.6702C18.907 12.0667 17.478 12.2919 15.1982 14.3459"></path> <path stroke-width="2" stroke="#ffffff" d="M17 8C17 8.55228 16.5523 9 16 9C15.4477 9 15 8.55228 15 8C15 7.44772 15.4477 7 16 7C16.5523 7 17 7.44772 17 8Z"></path> </g></svg>		  
		  			<img id="thumbnail" src="" alt="">
		  		</span>	  		
		  	</label>
		 	<small style="text-align: center;">프로필 사진 등록</small>
		    <input type="text" name="id" placeholder="아이디를 입력해주세요">
		    <input type="password" name="password" placeholder="비밀번호를 입력해주세요"> 
		    <input type="text" name="name" placeholder="이름을 입력해주세요">
		    <input type="text" name="ssn" placeholder="주민등록번호를 하이픈(-)을 포함하여 입력해주세요">
		    <input type="text" name="nickname" placeholder="닉네임을 입력해주세요">
		    <input type="email" name="email" placeholder="이메일을 입력해주세요">
		    <input type="text" id="sample6_postcode" name="addr1" placeholder="우편번호">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="sample6_address" name="addr2" placeholder="주소"><br>
			<input type="text" id="sample6_detailAddress" name="addr3" placeholder="상세주소">
			<input type="text" id="sample6_extraAddress" name="addr4" placeholder="참고항목"><br><br>
		    <input type="submit" value="회원가입하기"/>
		</form>
	</div>	
					

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