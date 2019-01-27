<%@page import="java.awt.print.Printable"%>
<%@page import="com.sist.retail.common.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
%>
	<%	
	String search_div="";
	String search_word="";
	/* search_div = StringUtil.nvl(request.getParameter("sido"),"");// 시/도
	search_word= StringUtil.nvl(request.getParameter("gugun"),"");// 구/군 */
	
	String page_size  = "90";//Page사이즈
	String page_num   = "1";//디폴트 값 1 
	
	int totalCnt   = 0;//총글수    
	int bottomCount= 10;//page 10 << < 1 2 3 4 5 6 7 8 9 10 > >>
	
	page_num   = StringUtil.nvl(request.getParameter("page_num"),"1");  //Page넘버 받아 온 값
	page_size  = StringUtil.nvl(request.getParameter("page_size"),"90"); //"10";//Page사이즈
	
	int o_page_size = Integer.parseInt(page_size);
	int o_page_num  = Integer.parseInt(page_num); //페이저 넘버 숫자로 변환. 
	
	//총 페이지 수 구하기
	String iTotalCnt = 
			(null == request.getAttribute("total_cnt"))?"0":request.getAttribute("total_cnt").toString();
			totalCnt = Integer.parseInt(iTotalCnt);
	System.out.println("totalCnt : "+totalCnt);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=context%>/resources/js/citySelector.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<title>Master_Order_Stores</title>
<style type="text/css">
#searchForm{
	margin-top: 100px;
}
</style>
<script type="text/javascript">
//검색 function
function doSearch() {
	var frm = document.frm;
  frm.action="do_search.do";
  frm.submit();
}

//현재 페이지 
function search_page(url,page_num){
	   console.log(url+":"+page_num);	
    var frm = document.frm;
    frm.page_num.value = page_num;
    frm.action=url;
    frm.submit();
 }
</script>
</head>
<body>
<h3>발주신청현황</h3>
	<div id="searchForm">
	<form name=frm method=post action="search.do">
		시/도: 
		 <select name='sido'
			onchange="change(this.selectedIndex);" class="form-control">
			<option value='전체'>전체</option>
			<option value='서울'>서울특별시</option>
			<option value='부산'>부산광역시</option>
			<option value='대구'>대구광역시</option>
			<option value='인천'>인천광역시</option>
			<option value='광주'>광주광역시</option>
			<option value='대전'>대전광역시</option>
			<option value='울산'>울산광역시</option>
			<option value='경기'>경기도</option>
			<option value='강원'>강원도</option>
			<option value='충북'>충청북도</option>
			<option value='충남'>충청남도</option>
			<option value='전북'>전라북도</option>
			<option value='전남'>전라남도</option>
			<option value='경북'>경상북도</option>
			<option value='경남'>경상남도</option>
			<option value='제주'>제주도</option>
		</select>
		 구/군: 
		 <select name='gugun' class="form-control">
			<option value=''>전체</option>
		</select>
		
		<input class="btn btn-success btn-sm" type="submit" name="button" value="조회"/>
	</form>
	<!-- 지점 정보 테이블 -->
  	<table id="listTable" class="table table-striped table-bordered">
 
	    <c:choose>
        <c:when test="${list.size()>0}">
        	<%int i=1; %>
            <c:forEach var="masterStoreVo" items="${list}">
            	<%i++; %>
				<td 
				<c:if test="${masterStoreVo.ordCd eq 'A3_03'}"><%out.print("bgcolor='#52E252'"); %></c:if>
				<c:if test="${masterStoreVo.ordCd eq 'A3_02'}"><%out.print("bgcolor='#5AD2FF'"); %></c:if>
				 class="text-center"><a href="order_print.do?stoId=${masterStoreVo.stoId}">
				 <c:out value="${masterStoreVo.stoNm}"/></a>
				</td>
				<c:set var="count" value="${count + 1}" /> 
				 <%if (i==5)
		   {   i=1;
			   			   %>
		    <tr>
		     <%} %>
		     </c:forEach>
		  
	     </c:when>
	     <c:otherwise>
	     <tr> 
	        <td colspan="99" class="text-center">등록된 지점이 없습니다.</td>
	     </tr>
	     </c:otherwise>
      </c:choose>
  	</table>
  	<!-- //지점 정보 테이블 -->
  	
  	<!-- Paging 처리 -->
   	<div class="form-inline text-center">
		<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "do_search.do", "search_page")%>
	</div>
	</div>
</body>
</html>