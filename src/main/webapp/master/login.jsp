<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;

	session.setAttribute("stoId", request.getParameter("stoId"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery 순서도 중요함!-->
<script src="<%=context%>/resources/js/jquery-1.12.4.js"></script>

<!-- Bootstrap 반응형 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap css -->
<link rel="stylesheet"
	href="<%=context%>/resources/css/bootstrap.min.css">
<!-- login css -->	
<link rel="stylesheet"
	href="<%=context%>/resources/css/login.css">
<!--  Bootstrap theme -->
<link rel="stylesheet"
	href="<%=context%>/resources/css/bootstrap-theme.min.css">

<!-- Bootstrap JavaScript -->
<script src="<%=context%>/resources/js/bootstrap.min.js"></script>
<title>Login</title>
<script>
function check() {
	var stoId=$('#stoId').val();

		$.ajax({
			type : "POST", //get, post
			async : true, // 동기 / 비동기
			url : "login.do", //action delete.do로 보내기'
			dataType : "JSON", //html, json, xml ..
			data : {
				"stoId" : $('#stoId').val(),
				"pwd"   : $('#pwd').val()
			},
			success : function(data) { //거래 성공시
			
				 if(data.check==1){
					 
					 window.location.href="mainPage.do"
					 //?stoId="+ encodeURI(stoId , "UTF-8"); 
				}
				 else if(data.check==0){
					alert("비밀번호나 아이디가틀립니다.");
					window.location.href="login.jsp";
				} 
			}
		});//-- ajax통신 end 
	};
	
	window.history.forward();
	function noBack(){window.history.forward();}
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
</head>
<body>
	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
	<div>SIST<span>Retail</span></div>
	</div>
		<br>

		<form name="frm" id="frm" method="POST" class="login" >
	 		<input type="text" name="stoId" id="stoId"  placeholder="점포아이디" maxlength="5" >
	 		<input type="password" name="pwd" id="pwd"  placeholder="비밀번호" maxlength="10" >
			<input type="button" class="btn btn-success" onclick="check()" value="로그인" />
		</form>


</body>
</html>