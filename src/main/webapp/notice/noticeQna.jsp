<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.sist.retail.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String qaNo = request.getParameter("qaNo");
	session.setAttribute("qaNo", qaNo);
	String stoId=(String)session.getAttribute("stoId");
	request.setAttribute("stoId", stoId);
	String search_div = "";//검색구분
	String search_word = "";//검색어
	String page_size = "10";//Page사이즈
	String page_num = "1";//Page넘버

	int totalCnt = 0;//총글수    
	int bottomCount = 10;//page 10 << < 1 2 3 4 5 6 7 8 9 10 > >>

	search_div = StringUtil.nvl(request.getParameter("search_div"), "");//검색구분
	search_word = StringUtil.nvl(request.getParameter("search_word"), "");// "";//검색어
	page_size = StringUtil.nvl(request.getParameter("page_size"), "10"); //"10";//Page사이즈
	page_num = StringUtil.nvl(request.getParameter("page_num"), "1"); //"1";//Page넘버

	int o_page_size = Integer.parseInt(page_size);
	int o_page_num = Integer.parseInt(page_num);
	
	// 게시글 넘버링 계산을 위한 set
	request.setAttribute("o_page_num", o_page_num);
	request.setAttribute("o_page_size", o_page_size);
	
	String iTotalCnt = (null == request.getAttribute("total_cnt"))
			? "0"
			: request.getAttribute("total_cnt").toString();
	totalCnt = Integer.parseInt(iTotalCnt);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Notice_Qna</title>
<script type="text/javascript">
	
	$(document).ready(function(){
		 $("#listTable>tbody").on("click","tr",function(){
			 var tr= $(this);
			 var td= tr.children();
	        var id= td.eq(4).text();
	
	        var frm = document.frm;	     
	         
	        frm.qaNo.value=id;	   
	         
	        frm.action="qnaSearchOne.do";	       
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
		// form 태그 실행
		var frm = document.frm;
		frm.action = "qnaSearch.do";
		frm.submit();
	}
	
	function search_page(url, page_num) {
		console.log(url + ":" + page_num);
		var frm = document.frm;
		frm.page_num.value = page_num;
		frm.action = url;
		frm.submit();
	}
</script>
<style>
   #btn {cursor:pointer;}
</style>
</head>
<body>
	<h3>건의사항</h3>

	<!-- Button Area -->
	<div class="form-inline pull-right">
		<!-- <button class="btn btn-success btn-sm" onclick="doSearch()">조회</button> -->
		<button class="btn btn-success btn-sm"
			onclick="location='noticeQnaInsert.do'">등록</button>
	</div>
	<!-- // Button Area -->

	<form name="frm" id="frm" method="GET" class="form-inline">
		<input type="hidden" name="qaNo" id="qaNo" value=""/>
		<input type="hidden" name="page_num" id="page_num" />
		<!-- 검색영역 -->

		<table class="table">
			<tr>
				<td class="text-center"><font class="font2">구분</font>
					<div class="form-group col-lg6 col-sm6">
						<select name="search_div" id="search_div"
							class="form-control input-sm">
							<option value="0"><font class="font2">전체</font></option>
							<option value="10"
								<%if (search_div.equals("10"))
				out.print("selected='selected'");%>><font class="font2">제목</font></option>
							<option value="20"
								<%if (search_div.equals("20"))
				out.print("selected='selected'");%>><font class="font2">작성자</font></option>
						</select> <input type="text" class="form-control input-sm" id="search_word"
							name="search_word" value="<%=search_word%>">
							<button class="btn btn-success btn-sm" onclick="doSearch()">검색</button>
					</div>
				</td>
			</tr>
		</table>
		<!--// 검색영역 -->

		<table id="listTable"
			class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th class="text-center"><font class="font1">NO</font></th>
					<th class="text-center"><font class="font1">제목</font></th>
					<th class="text-center"><font class="font1">작성자</font></th>
					<th class="text-center"><font class="font1">등록일</font></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${qnaList.size()>0}">
						<c:forEach var="noticeQnaVo" items="${qnaList}" varStatus="status">
							<tr>
								<td class="text-center" id="btn"><c:out value="${noticeQnaVo.totalNo - ((o_page_num-1) * o_page_size + status.index)}" /></td>
								<td class="text-center" id="btn"><c:out value="${noticeQnaVo.title}" /></a></td>
								<td class="text-center" id="btn"><c:out value="${noticeQnaVo.stoNm}" /></td>
								<td class="text-center" id="btn"><c:out value="${noticeQnaVo.qaDt}" /></td>
								<td class="text-center" id="btn" style="display: none"><c:out value="${noticeQnaVo.qaNo}"/></td>
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
		<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "qnaSearch.do",
					"search_page")%>
	</div>
	<!-- Paging << < 1 2 3 4 5 6 7 8 9 10 > >> End-->
</body>
</html>