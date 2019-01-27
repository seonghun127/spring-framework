<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
		
	String stoId=(String)session.getAttribute("stoId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login</title>
<script type="text/javascript">

function do_goback(){
	var frm = document.frm;
	frm.action="mainPage.do";
	frm.submit();
}

//수정
$(document).ready(function() {
	
$("#do_update").on("click",function() {
	var stoId=$('#stoId').val();
			//console.log("do_update:" + do_update);
			if (false == confirm("수정하시겠습니까?"))
				return false; 
				
			//ajax 통신
			$.ajax({
				type : "POST", //get, post
				async : true, // 동기 / 비동기
				url : "afterUpdate.do", //action
				dataType : "JSON", //html, json, xml ..
				data : {
					"stoId" : $("#stoId").val(),
					"stoNm" : $("#stoNm").val(),
					"pwd" : $("#pwd").val(),
					"ownId" : $("#ownId").val(),
					"phoNo" : $("#phoNo").val(),
					"adr" : $("#adr").val()

				},
				success : function(data) { //거래 성공시
				
					if (data.flag == 1) { // success = 1
						window.location.href="mainPage.do";
					//?stoId="+ encodeURI(stoId , "UTF-8");
					} else {// fail = 2
						alert("UPDATE FAIL!!!");
					}
				}
			});
			//--ajax 통신			
		});//--수정 end

});
	
</script>
</head>
<body>
<center>
	<div class="container">
		<div class="col-lg-12"></div>
		<div class="col-lg-12"></div>
		<form name="frm" id="frm" method="POST"
			class="form-horizontal">
			<table id="listTable"
				class="table table-striped table-hover table-bordered">
				<h3>수정화면</h3>
				<thead>
					<tr>
						<td>아이디 <input type="text" name="stoId" id="stoId"  value="<%=session.getAttribute("stoId") %>"  disabled="disabled"
							class="form-control input-sm" placeholder="점포아이디" maxlength="5">
						</td>
					</tr>
					<tr>
						<td>지점명 <input type="text" name="stoNm" id="stoNm" value="${masterStoreVo.stoNm}" disabled="disabled"
							class="form-control input-sm" placeholder="지점명" maxlength="10">
						</td>
					</tr>
					<tr>
						<td>비밀번호 <input type="password" name="pwd" id="pwd" value="${masterStoreVo.pwd}"
							class="form-control input-sm" placeholder="비밀번호" maxlength="10">
						</td>
					</tr>
					<tr>
						<td>점주명 <input type="text" name="ownId" id="ownId" value="${masterStoreVo.ownId}"
							class="form-control input-sm" placeholder="점주명" maxlength="10">
						</td>
					</tr>
					<tr>
						<td>전화번호 <input type="text" name="phoNo" id="phoNo" value="${masterStoreVo.phoNo}" 
							class="form-control input-sm" placeholder="전화번호" maxlength="10">
						</td>
					</tr>
					<tr>
						<td>주소 <input type="text" name="adr" id="adr" value="${masterStoreVo.adr}" disabled="disabled"
							class="form-control input-sm" placeholder="주소" maxlength="50">
						</td>
					</tr>
					<tr>
						<td class="text-center">
						<button class="btn btn-success btn-sm" id="do_update" name="do_update">완료</button>
						<input type="button" class="btn btn-success btn-sm" onclick="do_goback()" value="취소">
						</td>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</center>
</body>
</html>