<%@page import="com.sist.retail.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String page_size = "10";//Page사이즈
	String page_num = "1";//디폴트 값 1 

	int totalCnt = 0;//총글수    
	int bottomCount = 10;//page 10 << < 1 2 3 4 5 6 7 8 9 10 > >>

	page_num = StringUtil.nvl(request.getParameter("page_num"), "1"); //Page넘버 받아 온 값
	page_size = StringUtil.nvl(request.getParameter("page_size"), "10"); //"10";//Page사이즈

	int o_page_size = Integer.parseInt(page_size);
	int o_page_num = Integer.parseInt(page_num); //페이저 넘버 숫자로 변환. 

	//총 페이지 수 구하기
	String iTotalCnt = (null == request.getAttribute("total_cnt")) ? "0"
			: request.getAttribute("total_cnt").toString();
	totalCnt = Integer.parseInt(iTotalCnt);
	System.out.println("totalCnt : "+totalCnt);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#listTable {
	margin-top: 100px;
}
</style>
<title>Master_OrderDetail</title>
<script type="text/javascript">
//Jquery start
$(document).ready(function() {
	// 발주 승인
	// ajax 통신
	$(document).on("click", '#order_update',function(){
		console.log("order_update");
		if (false == confirm("발주 승인 하시겠습니까?")){
			return;
		}
		console.log("stoId : "+ $("#stoId").val());
		//ajax 통신
		$.ajax({
			type : "POST", //get/post
			url : "order_update.do", //action
			dataType : "html", //html/Json
			data : {
				"stoId" : $("#stoId").val()
			},
			success : function(data) {//거래 성공시
				console.log("1success:" + data);
				var parseData = $.parseJSON(data);
				console.log("2success:" + parseData);
				if (parseData.flag != '0') {//1
					alert("발주 승인 완료!");
					location.reload();		// 새로고침
				} else {
					alert("발주 승인 실패!");
				}
			},
			error : function(xhr, status, error) {//실패시 수행
				console.log("error");
			},
			complete : function(data) {//항상수행
				console.log("complete");
			}

		});
		//--ajax 통신 end         	 
	});//--발주 승인
});
</script>
</head>
<body>
	<div class="form-inline pull-right">
		<button class="btn btn-success btn-sm" onclick="location.href='search.do'">목록으로</button>
	</div>
	<c:choose>
		<c:when test="${orderDetailList.size()>0}">
		<input type="hidden" id="stoId" name="stoId" value="${orderDetailList.get(0).stoId}">
			<table id="listTable"
				class="table table-striped table-hover table-bordered">
				<tr>
					<td colspan="2">매장정보</td>
				</tr>
				<tr>
					<td>점포번호</td>
					<td><c:out value="${orderDetailList.get(0).stoId}" /></td>
				</tr>
				<tr>
					<td>점포명</td>
					<td><c:out value="${orderDetailList.get(0).stoNm}" /></td>
				</tr>
				<tr>
					<td>발주날짜</td>
					<td><c:out value="${orderDetailList.get(0).ordDt}" /></td>
				</tr>
			</table>
			<table id="listTable"
				class="table table-striped table-hover table-bordered">
				<tbody>
					<tr>
						<th class="text-center">발주 상품</th>
						<th class="text-center">발주 수량</th>
					</tr>

					<c:forEach var="ProductStockVo" items="${orderDetailList}">
						<tr>
							<td class="text-left"><c:out value="${ProductStockVo.pdtNm}" /></td>
							<td class="text-center"><c:out
									value="${ProductStockVo.ordCnt}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${orderDetailList.get(0).ordCd eq 'A3_02' }">
				<div>
					<button class="btn btn-success btn-sm" id="order_update" name="order_update">발주승인</button>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
			<table id="listTable"
				class="table table-striped table-hover table-bordered">
				<tr>
					<td colspan="99" class="text-center">발주 상품이 없습니다.</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

	<!-- Paging 처리 -->
	<div class="form-inline text-center">
		<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "do_search.do",
					"search_page")%>
	</div>
</body>
</html>