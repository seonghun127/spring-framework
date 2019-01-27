<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
	
	String stoId=(String)session.getAttribute("stoId");
	//String stoId=request.getParameter("stoId");
    //String pwd = request.getParameter("pwd");
		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Main_Page</title>
<script>
function update() {
		var frm=document.frm;
		frm.action="beforeUpdate.do";
	   frm.submit(); 
	};
function logout(){
	 if (false == confirm("로그아웃 하시겠습니까?"))
		return; 
	//<c:remove var="test" scope="session" />
	//session.removeAttribute("stoId");
	 //window.location.href="login.jsp"
	//session.invalidate();
	 window.location.href="login.jsp";
};

function reg(){
	 window.location.href="masterStoreReg.do";
};

window.history.forward();
function noBack(){window.history.forward();}
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
<body>
	<h3 align="center"><%=session.getAttribute("stoId")%> 지점</h3>
	<div class="container">
		<div class="col-lg-12"></div>
		<div class="col-lg-12"></div>
		<form name="frm" id="frm" method="POST" class="form-horizontal">
		<input type="hidden" name="stoId" id="stoId" value="<%=session.getAttribute("stoId")%>">
		<input type="hidden" name="divCd" id="divCd" value=""/>
			<table id="listTable"
				class="table table-striped table-hover table-bordered">

				<c:choose>
					<c:when test="${sessionScope.stoId eq '00001'}">
					<td class="text-center">
						<input type="button" class="btn btn-success btn-sm" onclick="reg()" value="지점등록"/>
					</td>						
						</c:when>
						<c:otherwise></c:otherwise>
				</c:choose>
					<td class="text-center">
						<input type="button" class="btn btn-success btn-sm" onclick="update()" value="회원정보수정"/>
					</td>
					<td class="text-center">
						<input type="button" class="btn btn-success btn-sm" onclick="logout()" value="로그아웃"/>
					</td>
			</table>
		</form>
	</div>
</body>
</html>