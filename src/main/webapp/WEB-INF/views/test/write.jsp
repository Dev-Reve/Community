<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	request.setCharacterEncoding("UTF-8");
	String contextPath = request.getContextPath();
	String nowPage = request.getParameter("nowPage");
	String nowBlock = request.getParameter("nowBlock");
	String loginNick = (String)session.getAttribute("m_nickname");
	String noticeCheck;
%>
<c:set var="loginNick" value="${ m_nickname }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="/MVCBoard/style.css"/>
<script type="text/javascript" src="<%=contextPath %>/src/main/resources/ckeditor/ckeditor.js"></script>
<title>Insert title here</title>

		<link rel="stylesheet" href="<%=contextPath%>/eq/css/myCss.css">
	<style type="text/css">
		
		a{
			text-decoration: none;
			color: black;
		}
		
		.textin{
			height: 30px;
		}
		
		.writetb{
			margin-top: 30px;
			width:100%;
		}
		
	</style>
</head>
<body>
<center>
<table class="writetb">
  <tr height="40"> 
    <td width="100%" style="text-align: left"> &nbsp;&nbsp;&nbsp; 
    	<img alt="�۾���" src="<%=contextPath%>/eq/img/banner/writebanner.jpg" width="98%">

    </td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center">
    <hr>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center"> 
        <table width="95%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td height="20" colspan="3"></td>
          </tr>
          <tr> 
            <td colspan="3" valign="top">
            <div align="center"> 
                <table width="100%" height="373" border="0" cellpadding="0" cellspacing="1" class="border1">
                  <tr> 
                    <td width="13%" height="40"  class="text2">
                    	<div align="center">�� �� ��</div>
                    </td>
                    <td width="34%"  style="text-align: left">
                    	
                    <%	if(loginNick == null){//�α��� ���� �ʾ������
                    	 %>
                    	<input type="text" name="writer" size="20" class="textin"/>
                    <%}else{ %>
                    	<input type="text" name="writer" size="20" class="textin" value="<%=loginNick%>" readonly />
                    <%} %>
                    <!-- 0321 ���¿�: �������� ��� �������� �ۼ��� �� �ִ� üũ�ڽ� �߰� -->
                    <c:if test="${ loginNick == 'admin' }">
                   		&nbsp;<input type="checkbox" name="noticeCheck" id="noticeCheck">�����۷� ����
                    </c:if>
                    </td>
                    <td width="13%" height="40" class="text2">
                    	<div align="center">�� ��й�ȣ</div>
                    </td>
                     <td width="34%" style="text-align: left">
                    	<input type="password" name="password" class="textin" size="20"/>
                    </td>
                   </tr>
                             
                  <tr> 
                    <td height="40" class="text2">
                    	<div align="center">��&nbsp;&nbsp;&nbsp;��</div>
                    </td>
                    <td colspan="3" style="text-align: left">
                    	<input type="text" name="title" size="70" class="textin" id="title_"/>
                    </td>
                  </tr>
                  <tr> 
                    <td class="text2">
                    	<div align="center">�� &nbsp;&nbsp; ��</div>
                    </td>
                    <td colspan="3" style="text-align: left">
                    	<textarea name="p_content" rows="20" cols="110" id="p_content"></textarea>
                    	<script type="text/javascript">
						 	CKEDITOR.replace('p_content'
						                , {height: 500                                                  
						                 });
						</script>
                    	<p id="resultInput"></p>
                    </td>
                  </tr>
                </table>
              </div>
              </td>
          </tr>
          <tr> 
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr> 
            <td width="48%">
            <!-- ��� ��ư -->
            <div align="right">
            	<a href="" id="registration1">
            		<img src="<%=contextPath%>/eq/img/okwrite.png" width="100px" border="0"/>
           		</a>
            </div>
            </td>
            <td width="10%">
            <!-- ��Ϻ��� -->
            <div align="center">
            	<a href="" id="list">
            		<img src="<%=contextPath%>/eq/img/listgo.png" width="100px" border="0"/>
            	</a>
            </div>
            </td>
            <td width="42%" id="resultInsert"></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table><br><br>
</form>
</center>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script type="text/javascript">
	
		
		$("#list").click(function(event) {
			event.preventDefault();
			//board���̺� ����� ���� ��ȸ �ϴ� ��û!
			location.href = "<%=contextPath%>/com/listByRecent.bo?nowPage=<%=nowPage%>&nowBlock=<%=nowBlock%>";
			
		})
		
	
	
		//���� ������ �Է��ϰ� ��� �̹����� ���ΰ� �ִ� <a>�±׸� Ŭ�� ������
		$("#registration1").click(function(event) {
			event.preventDefault();
			
			//�ۼ��� ���� �Է��� <input>�� ������
			var writer = $("input[name=writer]").val();
			//�������� �Է¹޾� ��´�.
			var title = $("input[name=title]").val();
			//�۳����� �Է¹޾� ��´�.
			var content = CKEDITOR.instances.p_content.getData();
			content = content.substr(3, content.length);
			content = content.substr(0, content.length - 5);
			
			//�ۺ�й�ȣ�� �Է¹޾� ��´�.
			var pass = $("input[name=password]").val();
			
			if(writer == "" || title == "" || content == "" || pass == ""){
				
				$("#resultInput").text("�ۼ����� ��� �Է��Ͽ� �ּ���!").css("color","red");
				
			}else{
				if($("#noticeCheck").is(":checked")) {
					$.ajax({
						type:"post",
						async:true,
							url:"<%=contextPath%>/com/noticePro.bo",
						data:{
							w : writer,
							t : title,
							c : content,
							p : pass
						},
						dataType : "text",
						success:function(data){
							console.log(data);
							
							if(data == "1"){
								location.href = "<%=contextPath%>/com/listByRecent.bo?nowPage=<%=nowPage%>&nowBlock=<%=nowBlock%>";
							}else{//"0"
								$("#resultInsert").text("�� �ۼ� ����!").css("color","red");
							}
						}
						
					});
				} else {
					$.ajax({
						type:"post",
						async:true,
							url:"<%=contextPath%>/com/writePro.bo",
						data:{
							w : writer,
							t : title,
							c : content,
							p : pass
						},
						dataType : "text",
						success:function(data){
							console.log(data);
							
							if(data == "1"){
								location.href = "<%=contextPath%>/com/listByRecent.bo?nowPage=<%=nowPage%>&nowBlock=<%=nowBlock%>";
							}else{//"0"
								$("#resultInsert").text("�� �ۼ� ����!").css("color","red");
							}
						}
						
					});
				}
			}
		})
		
	</script>
</body>
</html>