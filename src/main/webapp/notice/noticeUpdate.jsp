<%@page import="com.sist.retail.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
	
	String memoNo = (String)session.getAttribute("memoNo");
	//String memoNo = request.getParameter("memoNo");
	//session.setAttribute("memoNo", memoNo);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notice_Update</title>

<script type="text/javascript">


$(document).ready(function() {
	//수정
	$("#do_update").on("click",function() {
		//alert($("#memoNo").val());
		if (false == confirm("완료하시겠습니까?"))
			return;

		//ajax 통신
		$.ajax({
			type : "POST", //get, post
			async : true, // 동기 / 비동기
			url : "update.do", //action
			dataType : "JSON", //html, json, xml ..
			data : {
				"memoNo" : $("#memoNo").val(),
				"title" : $("#title").val(),
				"con" : $("#con").val()
			},
			success : function(data) { //거래 성공시
				if (data.flag == 1) { // success = 1
					alert("성공!!!")
				    
					window.location.href="do_detail.do?memoNo="+$("#memoNo").val()+"&workdiv=10"
				} else {// fail = 2
					alert("실패!!!");
				}
			}
		});
		//--ajax 통신			
	});//--수정 end
});


</script>
</head>
<body>
	<h3>게시판</h3>
	<!-- Button Area -->
	<div class="form-inline pull-right">
		<button class="btn btn-success btn-sm" id="home" name="home" onclick="window.location.href='notice.jsp'">홈</button>
		<button class="btn btn-success btn-sm" id="do_update" name="do_update" onclick="do_update()">완료</button>
	</div>

	<form name="frm" id="frm" method="GET" class="form-inline">
	<input type="hidden" id="memoNo" name="memoNo" value="<%=session.getAttribute("memoNo") %>" >
		<table id="listTable" class="table table-striped table-hover table-bordered">
			<thead>
			</thead>
			<tbody>
				<tr>
					<input type="hidden" name="no" id="no" value="${noticeVo.no}" />
					<td class="text-center"> <input type="text" name="title" id="title" value="${noticeVo.title}" /></td>
					<td class="text-center" name="regDt" id="regDt"><c:out value="${noticeVo.regDt}" /></td>
				</tr>
			</tbody>
		</table>
		<textarea class="form-control" name="con" id="con">${noticeVo.con}</textarea>
	</form>
</body>
</html>