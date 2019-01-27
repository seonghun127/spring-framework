<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;

	String page_size = "10";//Page사이즈
	String page_num = "1";//Page넘버

	int totalCnt = 0;//총글수    
	int bottomCount = 10;//page 10 << < 1 2 3 4 5 6 7 8 9 10 > >>

	page_size = StringUtil.nvl(request.getParameter("page_size"), "10"); //"10";//Page사이즈
	page_num = StringUtil.nvl(request.getParameter("page_num"), "1"); //"1";//Page넘버

	int o_page_size = Integer.parseInt(page_size);
	int o_page_num = Integer.parseInt(page_num);

	String iTotalCnt = (null == request.getAttribute("total_cnt"))
			? "0"
			: request.getAttribute("total_cnt").toString();
	totalCnt = Integer.parseInt(iTotalCnt);

	String stoId = (String) session.getAttribute("stoId");
%>
<script type="text/javascript">
	function search_page(url, page_num) {
		console.log(url + ":" + page_num);
		var frm = document.frm;
		frm.page_num.value = page_num;
		frm.action = url;
		frm.submit();
	};

	$(document).ready(function() {
		
		 
		$("#do_delete").on("click", function(){
			console.log("click delete")
			console.log("stoId:"+<%=stoId%>)
			$.ajax({
				type : "POST",
				async :true,
				url: "http://localhost:8080/retail/master/do_order_approval.do",
				dataType : "JSON", //html/Json
				data : {
					"stoId" :<%=stoId%>
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);

					console.log("2success:" + data.flag);
					location.reload();
				},
				error : function(xhr, status, error) {//실패시 수행
					console.log("error");
				},
				complete : function(data) {//항상수행
					console.log("complete");
				}
			});
		});
		
		
		$("#do_order").on("click", function() {
			var pdtNoList = new Array();
			var ordCntList = new Array();
			$('#table-1 > tbody >tr').each(function(idx, row) {
				var record = $(row);
				console.log(record + "record")
				var pdtNo = $(record).find("td").eq(0).text();
				var odtCnt = $(record).find("input[id='num']").val();
				console.log(idx + ":pdtNo:" + pdtNo);
				console.log(idx + ":odtCnt:" + odtCnt);
				console.log("row:" + row);
				pdtNoList.push(pdtNo);
				ordCntList.push(odtCnt);
			});
			var pdtNoList = JSON.stringify(pdtNoList);
			var ordCntList = JSON.stringify(ordCntList);

			console.log(":pdtNoList:" + pdtNoList);
			console.log(":ordCntList:" + ordCntList);

			//ajax 통신
			$.ajax({
				type : "POST",
				async : true,
				url : "http://localhost:8080/retail/master/do_order_insert.do", //action
				dataType : "JSON", //html/Json
				data : {
					"stoId":<%=stoId%>,
					"pdtNoList" : pdtNoList,
					"ordCntList" : ordCntList
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);

					console.log("2success:" + data.flag);
					location.reload();
					var flag = parseInt(data.flag);
				},
				error : function(xhr, status, error) {//실패시 수행
					console.log("error");
				},
				complete : function(data) {//항상수행
					console.log("complete");
				}
			})

		})
	});
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery 순서도 중요함!-->
<script src="<%=context%>/resources/js/jquery-1.12.4.js"></script>

<title>재고조회</title>
</head>
<body>
	<h3>재고조회</h3>
	<div class="container">
		<form name="frm" id="frm" action="search.do" method="POST">
			<input type="hidden" name="page_num" id="page_num" /> 
			<input type="hidden" value="<%=stoId %>" name="stoId" id="stoId" /> 
			<select name="group" id="group" class="form-control input-sm">
				<option value="" <c:if test="${group eq ''}">selected</c:if>>전체</option>
				<option value="라면" <c:if test="${group eq '라면'}">selected</c:if>>라면</option>
				<option value="과자" <c:if test="${group eq '과자'}">selected</c:if>>과자</option>
				
			</select> 
			<input type="submit" class="btn btn-success btn-sm" value="조회" /> 
			<input type="button" class="btn btn-success btn-sm" id="do_order" value="발주하기" />
			<input type="button" id="do_delete"	class="btn btn-success btn-sm"
			<c:if test="${list.get(0).ordCd ne 'A3_03'}"> title="승인이 되지 않았습니다."</c:if>  value="발주완료" 
				<c:if test="${list.get(0).ordCd ne 'A3_03'}"> disabled="disabled"</c:if>
			/>			
			
			<input type="button" class="btn btn-success btn-sm" value="발주상태"  disabled="disabled"/>
				
			<c:if test="${list.get(0).ordCd eq null}">
			  <c:out value=': 발주대기'/>  
			</c:if>
			<c:if test="${list.get(0).ordCd eq 'A3_02'}">
			  <c:out value=': 발주신청'/>  
			</c:if>
			<c:if test="${list.get(0).ordCd eq 'A3_03'}">
			  <c:out value=': 승인'/>  
			</c:if>
			<table id="table-1"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">상품번호</th>
						<th class="text-center">상품군</th>
						<th class="text-center">상품명</th>
						<th class="text-center">가격</th>
						<th class="text-center">제조사</th>
						<th class="text-center">재고량</th>
						<th class="text-center">발주수량</th>
						
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${list.size()>0 }">
							<c:forEach var="ProductStockVo" items="${list }">
								<tr 
								  <c:if test="${ProductStockVo.stkCnt<10}">
								   		style="background-color:#BDBDBD"
								  </c:if>
								>
									<td class="text-left"><c:out
											value="${ProductStockVo.pdtNo }" /></td>
									<td class="text-left"><c:out
											value="${ProductStockVo.pdtGb }" /></td>
									<td class="text-left"><c:out
											value="${ProductStockVo.pdtNm }" /></td>
									<td class="text-left"><c:out
											value="${ProductStockVo.price }" /></td>
									<td class="text-left"><c:out
											value="${ProductStockVo.mkNm }" /></td>
									<td class="text-left"><c:out
											value="${ProductStockVo.stkCnt }" /></td>
									<td class="text-left"><input type="number" class="num"
										id="num" name="num" size="1" min="0" max="999"value="0" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td></td>
								<td colspan="99" class="text-center">등록된 게시글이
									없습니다!!!!!!!!!!!!!!!!</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				<!-- Paging << <1 2 3 4 5 6 7 8 9 10 > >> -->
				<div class="form-inline text-center">
					<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "search.do",
					"search_page")%>
				</div>
				<!--// Paging << <1 2 3 4 5 6 7 8 9 10 > >> -->
			</table>
			<table id="table-2"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">상품군</th>
						<th class="text-center">상품명</th>
						<th class="text-center">판매량</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="SaleProductVo" items="${list2 }">
						<tr>
							<td class="text-left"><c:out value="${SaleProductVo.pdtGb }" /></td>
							<td class="text-left"><c:out value="${SaleProductVo.pdtNm }" /></td>
							<td class="text-left"><c:out
									value="${SaleProductVo.sumsal }" /></td>
						</tr>
					</c:forEach> 

				</tbody>
			</table>
			<table id="table-3"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">상품군</th>
						<th class="text-center">상품명</th>
						<th class="text-center">판매량</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="SaleProductVo" items="${list3 }">
						<tr>
							<td class="text-left"><c:out value="${SaleProductVo.pdtGb }" /></td>
							<td class="text-left"><c:out value="${SaleProductVo.pdtNm }" /></td>
							<td class="text-left"><c:out
									value="${SaleProductVo.sumsal }" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
				<table id="table-5"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">상품군</th>
						<th class="text-center">상품명</th>
						<th class="text-center">판매량</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="SaleProductVo" items="${list4 }">
						<tr>
							<td class="text-left"><c:out value="${SaleProductVo.pdtGb }" /></td>
							<td class="text-left"><c:out value="${SaleProductVo.pdtNm }" /></td>
							<td class="text-left"><c:out
									value="${SaleProductVo.sumsal }" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<table id="table-4" class="table table-striped table-bordered">
				<tr>
					<th align="center">날씨</th>
					<th align="center">최고기온</th>
					<th align="center">최저기온</th>
					<th align="center">현재날씨</th>
				</tr>
				<tr>
					<c:if test="${Weather.weather ne null}">
						<td align="center"><img
							src="../images/weather/${Weather.weather }.png" width="30px;"
							height="30px;"></td>
					</c:if>
					<c:if test="${Weather.weather eq null}">
						<td align="center">날씨를 조회해 주세요</td>
					</c:if>
					<td align="center">${Weather.maxTemp }</td>
					<td align="center">${Weather.minTemp }</td>
					<td align="center">${Weather.curTemp }</td>
				</tr>
			</table>

		</form>
	</div>


</body>
</html>