<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Today_Sale</title>

<script type="text/javascript">
function doSearch() {
  var frm = document.frm;
  frm.action="do_searchTodaySal.do";
  frm.submit();
}

function doExcelDown() {
  var frm = document.frm;
  frm.action="excelDownTodaysale.do";
  frm.submit();
}
</script>

</head>
<body>
	<form name="frm" id="frm" action="do_searchTodaySal.do" method="POST">
	</form>
	
	<button class="btn btn-success btn-sm" onclick="javascript:doExcelDown();">액셀다운</button>
	
	<table class="table">
	<thead>
		<tr>
			<th class="text-center"><font>상품번호</font></th>
			<th class="text-center"><font>상품군</font></th>
			<th class="text-center"><font>상품명</font></th>
			<th class="text-center"><font>가격</font></th>
			<th class="text-center"><font>수량</font></th>
			<th class="text-center"><font>매출액</font></th>
		</tr>
	</thead>
	 	<c:choose>
	       <c:when test="${saleList.size()>0}">
	       	 <c:set var="totalSal" value="0" />
	           <c:forEach var="saleTodayVo" items="${saleList}">
	            <c:set var= "totalSal" value="${totalSal+(saleTodayVo.salCnt*saleTodayVo.price)}"/> 
			     <tr>	 
			     	 <td class="text-center"><c:out value="${saleTodayVo.pdtNo}"/></td>
			        <td class="text-center"><c:out value="${saleTodayVo.pdtGb}"/></td>	
			        <td class="text-center"><c:out value="${saleTodayVo.pdtNm}"/></td>
			        <td class="text-center"><fmt:formatNumber value="${saleTodayVo.price}" pattern="#,###"/></td>
			        <td class="text-center"><c:out value="${saleTodayVo.salCnt}"/></td>
			        <td class="text-center">
			        	<fmt:formatNumber value="${saleTodayVo.price*saleTodayVo.salCnt}" pattern="#,###"/>
			        </td>			  
			     </tr>
		     </c:forEach>
		      <tr>
		      	<td colspan="5" class="text-right">
		      		총 합계: 
		      	</td>
		     	<td class="text-center">		     	
		     		<fmt:formatNumber value="${totalSal}" pattern="#,###"/>		    
		     	</td>		   
		     </tr>
	     </c:when>
	     <c:otherwise>
	     <tr> 
	        <td colspan="99" class="text-center">금일 판매 내역이 없습니다.</td>
	     </tr>
	     </c:otherwise>
     </c:choose>
	</table>
</body>
</html>