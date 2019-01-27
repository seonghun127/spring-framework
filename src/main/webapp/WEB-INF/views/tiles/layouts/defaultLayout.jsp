<%--
파일명 : defaultLayout.jsp
작성자 : Kang
Version : 0.5
History : 2017-12-14
 --%>
 
<%@ page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<%
   String context = request.getContextPath();
   
   context = HlConstant.HL_URL+context;
   //out.print("context:"+context);

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title><tiles:getAsString name="title"/></title>
<style>
html {
height: 100%;
}
body {
	margin: 0;
	height: 80%;
}
.container {
margin-top:5%;
min-height: 80%;
position: relative;
padding-bottom: 19px; /* footer height */
} 
footer {
position: relative;
height:20%;
bottom: 0;
left: 0;
right: 0;
color: white;
background-color: #333333;
}
</style>
</head>
<!--style="background-color: #1C2331"  -->
<body>
  <!-- header -->
  <tiles:insertAttribute name="header"></tiles:insertAttribute>
  <!-- body -->
  <div class="container" >
  	<tiles:insertAttribute name="body"></tiles:insertAttribute>
  </div>
  <!-- footer -->
  <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>