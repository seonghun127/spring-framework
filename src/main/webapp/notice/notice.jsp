<%@page import="com.sist.retail.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String divCdChk=(String)session.getAttribute("divCd");
	String chk = StringUtil.nvl(request.getParameter("chk"), "");
	if(divCdChk==null || chk.equals("t")) {
		String divCd = request.getParameter("divCd");
		session.setAttribute("divCd", divCd);
	}
	
	String search_div = ""; //검색구분
	String search_word = ""; //검색어
	String page_size = "10"; //페이지 사이즈
	String page_num = "1"; //페이지 숫자
	
	int totalCnt = 0; //총 글수
	int bottomCnt = 10; //page 10	<< < 1 2 3 4 5 6 7 8 9 10 > >>
	
	search_div = StringUtil.nvl(request.getParameter("search_div"), "");
	search_word = StringUtil.nvl(request.getParameter("search_word"), "");
	page_size = StringUtil.nvl(request.getParameter("page_size"), "10");
	page_num = StringUtil.nvl(request.getParameter("page_num"), "1");
	
	int o_page_size = Integer.parseInt(page_size);
	int o_page_num = Integer.parseInt(page_num);
	
	request.setAttribute("o_page_size", o_page_size);
	request.setAttribute("o_page_num", o_page_num);

	String iTotalCnt 
		= (null == request.getAttribute("total_cnt")) ? "0" : request.getAttribute("total_cnt").toString();
	totalCnt = Integer.parseInt(iTotalCnt);
%>
<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Notice</title>

<script type="text/javascript">
	//단건조회
	$(document).ready(function(){
		 $("#listTable>tbody").on("click","tr",function(){
			 var tr= $(this);
			 var td= tr.children();
	        var id= td.eq(4).text();

	        var frm = document.frm;	     
	          
	         frm.memoNo.value=id;	   
	         console.log("memoNo:");
	          
	         frm.action="do_detail.do";	       
	         frm.submit(); 
		 });
	});
	
	// 조회
	function doSearch() {
		// 전체 조회 시 검색창 내용 reset
		var search_div = document.getElementById('search_div');
		if(search_div.value == '0'){
			var input = document.getElementById('search_word');
			input.value = null;
		}
		
		var frm = document.frm;
		frm.action = "search.do";
		frm.submit();
	}
	
	// paging
	function search_page(url, page_num) {
		console.log(url + ":" + page_num);
		var frm = document.frm;
		frm.page_num.value = page_num;
		frm.action = url;
		frm.submit();
	}

	// 등록
	function doSave() {
		var frm = document.frm;
		frm.action = "noticeInsert.do";
		frm.submit();
	}
</script>
<style>
   #btn {cursor:pointer;}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.divCd eq 'A4_01' }">
			<h3>공지사항</h3>
		</c:when>
		<c:when test="${sessionScope.divCd eq 'A4_02' }">
			<h3>커뮤니티</h3>
		</c:when>
	</c:choose>
	
	<!-- Button Area -->
	<div class="form-inline pull-right">
		<c:choose>
			<c:when test="${sessionScope.divCd eq 'A4_01' }">
				<c:choose>
					<c:when test="${gntCd eq '0'}">
						<button class="btn btn-success btn-sm" onclick="doSave()">등록</button>
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${sessionScope.divCd eq 'A4_02' }">
					<button class="btn btn-success btn-sm" onclick="doSave()">등록</button>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>

	<form name="frm" id="frm" method="GET" class="form-inline">
		<input type="hidden" name="memoNo" id="memoNo" value=""/>
		<input type="hidden" name="page_num" id="page_num" />
		<!-- 검색영역 Start-->
		<table class="table">
			<tr>
				<td class="text-center"><font class="font1">구분</font>
					<div class="form-group col-lg6 col-sm6">
						<select name="search_div" id="search_div"
							class="form-control input-sm">
							<option value="0">전체</option>
							<!-- 조회를 눌러도 구분자 유지 -->
							<option value="10" <%if (search_div.equals("10")) out.print("selected='selected'");%>><font class="font2">제목</font></option>
							<option value="20" <%if (search_div.equals("20")) out.print("selected='selected'");%>><font class="font2">내용</font></option>
						</select> <input type="text" class="form-control input-sm" id="search_word" name="search_word" value="<%=search_word%>">
						<button class="btn btn-success btn-sm" onclick="doSearch()">검색</button>
					</div>
				</td>
			</tr>
		</table>
		<!-- 검색영역 END -->

		<table id="listTable"
			class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th class="text-center"><font class="font1">NO</font></th>
					<th class="text-center"><font class="font1">제목</font></th>
					<th class="text-center"><font class="font1">작성자</font></th>
					<th class="text-center"><font class="font1">등록일</font></th>
					<th class="text-center" style="display: none">memoNo</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${list.size()>0}">
						<c:forEach var="noticeVo" items="${list}" varStatus="status">
							<tr>
								<td class="text-center" id="btn"><fmt:formatNumber value="${noticeVo.totalNo-((o_page_num-1)*o_page_size+status.index)}" groupingUsed="true" /></td>
								<td class="text-center" id="btn"><c:out value="${noticeVo.title}" /></td>
								<td class="text-center" id="btn"><c:out value="${noticeVo.stoNm}" /></td>
								<td class="text-center" id="btn"><c:out value="${noticeVo.regDt}" /></td>
								<td class="text-center" id="btn" style="display: none"><c:out value="${noticeVo.memoNo}"/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="99" class="text-center"><font class="font2">등록된 게시글이 없습니다.</font></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>
	<!-- Paging << < 1 2 3 4 5 6 7 8 9 10 > >> -->
	<div class="form-inline text-center">
		<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCnt, "search.do", "search_page")%>
	</div>
	<!-- Paging << < 1 2 3 4 5 6 7 8 9 10 > >> End-->
</body>
</html>