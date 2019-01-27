<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String context = request.getContextPath();   
	context = HlConstant.HL_URL+context;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Term_Sale</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
	<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/demos/style.css" />
	<script src="<%=context%>/resources/js/datepicker-ko.js"></script>
	<style type="text/css">
	.termSalDiv{
		margin-top: 150px;
	}
	</style>

	<script>
	 $(function() {
		$( "#startDate" ).datepicker({
		 showOn: "both",
		 dateFormat: "yy-mm-dd",
		 defaultDate: "+1w",
		 changeMonth: true,
		 onClose: function( selectedDate ) {
		  $("#endDate").datepicker( "option", "minDate", selectedDate );
		 }
		});
		 
		//끝일. 시작일보다는 길어야 되게끔
		$( "#endDate" ).datepicker({
		 showOn: "both",
		 dateFormat: "yy-mm-dd",
		 defaultDate: "+1w",
		 changeMonth: true,
		 onClose: function( selectedDate ) {
		  $("#startDate").datepicker( "option", "maxDate", selectedDate );
		 }
		});
	 });
	 
	//검색 function
	    function doSearch() {
	    	if($("#startDate").val()=="") {
	    		alert("시작 날짜를 정하세요.");
	    		return;
	    	} 
	    	if($("#endDate").val()=="") {
	    		alert("종료 날짜를 정하세요.");
	    		return;
	    	} 
	    	
	    	var frm = document.frm;
	      frm.action="do_searchTermSal.do";
	      frm.submit();
	    }
		
		function doExcelDown() {
			if($("#startDate").val()=="") {
	    		alert("시작 날짜를 정하세요.");
	    		return;
	    	} 
	    	if($("#endDate").val()=="") {
	    		alert("종료 날짜를 정하세요.");
	    		return;
	    	} 
	    	
	    	var frm=document.frm;
	    	frm.action="excelDownTermsale.do";
	    	frm.submit();
		}
	</script>
</head>
	<body>
	<div class="termSalDiv">
		<form id="frm" name="frm" method="POST" action="do_searchTermSal.do" >
		 <p>
		  	시작일 : <input type="text" id="startDate" name="startDt" value="${startDt}"/>
		 </p>
		 <p>
		 	종료일 : <input type="text" id="endDate" name="endDt" value="${endDt}"/>
		 </p>
	 	</form>
	 	
	 	 <button class="btn btn-success btn-sm" onclick="javascript:doSearch();">조회</button>
	 	 <button class="btn btn-success btn-sm" onclick="javascript:doExcelDown();">엑셀다운</button>
	 	<table class="table table-striped table-hover table-bordered">
	 		<tr>
	 			<th class="text-center"><font>매출 날짜</font></th>
	 			<th class="text-center"><font>매출 총액</font></th>
	 		</tr>
	 		<c:choose>
		       <c:when test="${termSaleList.size()>0}">
		       	<c:set var="totalSal" value="0" />
		           <c:forEach var="saleTermVo" items="${termSaleList}">
		              <c:set var= "totalSal" value="${totalSal+saleTermVo.totalSal}"/>      
				     <tr>	 
				     	 <td class="text-left"><c:out value="${saleTermVo.salDt}"/></td>				  
				        <td class="text-left"><fmt:formatNumber value="${saleTermVo.totalSal}" pattern="#,###"/></td>	       	  
				     </tr>
			     </c:forEach>
			     <tr>
			     	<td>총 합계:</td>
			     	<td><fmt:formatNumber value="${totalSal}" pattern="#,###"/></td>
			     </tr>
		     </c:when>
		     <c:otherwise>
		     <tr> 
		        <td colspan="99" class="text-center">정보가 없습니다.</td>
		     </tr>
		     </c:otherwise>
     	</c:choose>
	 	</table>
	 	</div>
	</body>
</html>