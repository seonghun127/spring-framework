<%@page import="com.sist.retail.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
    
<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Notice_Insert</title>
<script type="text/javascript">
function do_goback(){
	history.go(-1);
}
</script>

</head>
<body>
<h3>게시글 작성</h3>
<div class="container">
		<div class="col-lg-12"></div>
		<div class="col-lg-12"></div>
<form name="frm" id="frm" action="noticeInsert.do" method="POST" class="form-horizontal">
	<table id="listTable" class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<td><font class="font2">제목</font>
					<input type="text" name="title" id="title" class="form-control input-sm" placeholder="제목" maxlength="30" required>
				</td>
			</tr>
			<tr>
				<td>
					<font class="font2">내용</font>
					<textarea name="con" id="con" class="form-control input-sm" placeholder="내용" maxlength="4000" required></textarea>
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" class="btn btn-success btn-sm" value="작성" />
				<input type="button" class="btn btn-success btn-sm" onclick="do_goback()" value="취소">
				</td>
			</tr>
		</thead>
	</table>
</form>
</div>
</body>
</html>