<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String stoId="11111";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<body>
	<form name="frm" id="frm" action="do_searchTodaySal.do" method="POST">
		<input type="hidden"  name="stoId" id="stoId" value="<%=stoId%>"/> 
		<input type="submit" value="전송">
	</form>
	
	<table>
		<tr>
			<th>상품번호</th>
			<th>상품군</th>
			<th>상품명</th>
			<th>가격</th>
			<th>수량</th>
			<th>매출액</th>
		</tr>
		
	 	<c:choose>
	       <c:when test="${saleList.size()>0}">
	       	 <c:set var="totalSal" value="0" />
	           <c:forEach var="saleTodayVo" items="${saleList}">
	            <c:set var= "totalSal" value="${totalSal+(saleTodayVo.salCnt*saleTodayVo.price)}"/> 
			     <tr>	 
			     	 <td class="text-left"><c:out value="${saleTodayVo.pdtNo}"/></td>
			        <td class="text-left"><c:out value="${saleTodayVo.pdtGb}"/></td>	
			        <td class="text-left"><c:out value="${saleTodayVo.pdtNm}"/></td>
			        <td class="text-left"><c:out value="${saleTodayVo.price}"/></td>
			        <td class="text-left"><c:out value="${saleTodayVo.salCnt}"/></td>
			        <td class="text-left"><c:out value="${saleTodayVo.salCnt*saleTodayVo.price}"/></td>			  
			     </tr>
		     </c:forEach>
		      <tr>
		     	<td>총 합계:</td>
		     	<td><c:out value="${totalSal}"/></td>
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