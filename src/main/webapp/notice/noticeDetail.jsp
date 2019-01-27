<%@page import="com.sist.retail.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
	String memoNo = request.getParameter("memoNo");
	session.setAttribute("memoNo", memoNo);
	
	String workDiv=StringUtil.nvl(request.getParameter("workDiv"), "");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Notice_Detail</title>

<script type="text/javascript">

var div_count=0;

$(document).ready(function(){
	//request값 가져오기 
	var url = location.href;
	var parameters = (url.slice(url.indexOf('=') + 1, url.length)).split('&');
	var workDiv=parameters[0];
	
	//수정 버튼 활성화 여부.
	if(workDiv=="update") {
		$("#title").prop("disabled",false);
		$("#con").prop("disabled",false);
	}
	//--
		
		// 댓글 추가
		// 동적 이벤트 활성
		$(document).on("click", '#do_add',function(){
			if(sessionStorage.getItem("stoId")=="") {
				alert("로그인 해주세요.");
				return;
			}
			div_count = 0;
			var comment = $("#comment").val();
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "commentInsert.do", //action
				dataType : "html", //html/Json
				data : {
					"cmtCon" : comment
				},
				success : function(data) {//거래 성공시
					var parseData = $.parseJSON(data);
					if (parseData.flag == "1") {//1					
						location.reload();		// 새로고침
					} else {
						alert("답변 등록 실패!");
					}
				},
				error : function(xhr, status, error) {//실패시 수행
					console.log("error");
					alert("댓글등록 실패");
				},
				complete : function(data) {//항상수행
					console.log("complete");
				}
			});
			//--ajax 통신 end         	 
		});//--댓글 추가
		
		
		// 댓글 수정
		// 동적 이벤트 활성
		$(document).on("click", '#updateBtn',function(){
			var index = $(this).parent().find("input[id='indexHidden']").val();
			var comCon = $(this).parent().find("textarea[id='updateContent']").val();
			
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "commentUpdate.do", //action
				dataType : "html", //html/Json
				data : {
					"cmtCon" : comCon,
					"cmtNo": $("#cmtNo"+index).val()
				},
				success : function(data) {//거래 성공시
					var parseData = $.parseJSON(data);
					if (parseData.flag == "1") {//1						
						location.reload();
					} else {						
					}
				},
				error : function(xhr, status, error) {//실패시 수행
					alert("수정 오류");
					console.log("error");
				},
				complete : function(data) {//항상수행
					console.log("complete");
				}
			});
			//--ajax 통신 end         	 
		});//--댓글 수정
	
		// 댓글 삭제
		// 동적 이벤트 활성
		$(document).on("click", '#delete',function(){
			if (false == confirm("답변 삭제 하시겠습니까?"))
				return;
			var index = $(this).parent().find("input[id='index']").val();
			
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "commentDelete.do", //action
				dataType : "html", //html/Json
				data : {
					"cmtNo": $("#cmtNo"+index).val()
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);
					var parseData = $.parseJSON(data);
					console.log("2success:" + parseData);
					if (parseData.flag == "1") {//1					
						location.reload();		// 새로고침
					} else {
						alert("답변 삭제 실패!");
					}
				},
				error : function(xhr, status, error) {//실패시 수행
					alert("실패");
					console.log("error");
				},
				complete : function(data) {//항상수행
					console.log("complete");
				}

			});
			//--ajax 통신 end         	 
		});//--댓글 삭제	
}); 

function do_update() {
	var frm = document.frm;
	frm.action = "do_detail.do";
	frm.workDiv.value="update";
	frm.submit();
}

function do_delete(){
	var frm = document.frm;
	frm.action = "do_delete.do";
	if (false == confirm("삭제 하시겠습니까?"))
		return;
	frm.submit();
}

//Update Query로 이동
function do_updateTrans() {
	var frm = document.frm;
	frm.action="update.do";
	frm.submit();
}

// 이전글,다음글
function pn_title(){
	var frm = document.frm;
	frm.action = "do_detail.do";
	frm.submit();
}

//업데이트 창 띄우기
function add_update(index) {
		// pre_set 에 있는 내용을 읽어와서 처리..
		if(div_count==0){
			var div = document.createElement('div');
			div.innerHTML = document.getElementById('updateDiv'+index).innerHTML;
			document.getElementById('newAnsCon'+index).appendChild(div);
			var ansContent = document.getElementById('ansContent'+index);
			if (ansContent.style.display == 'none') {
				ansContent.style.display = 'block';
			} else {
				ansContent.style.display = 'none';
			}
			
			div_count += 1;
		}else{
			return;
		}
	}
//업데이트 창 지우기 
function remove_update(obj,index) {
	// obj.parentNode 를 이용하여 삭제
	document.getElementById('newAnsCon'+index).removeChild(obj.parentNode);

	var ansContent = document.getElementById('ansContent'+index);
	if (ansContent.style.display == 'none') {
		ansContent.style.display = 'block';
	} else {
		ansContent.style.display = 'none';
	}
	div_count = 0;	
}

function do_goback(){
	history.go(-1);
}

</script>
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
			<button class="btn btn-success btn-sm" onclick="location.href='notice.do?divCd=A4_01'">목록으로</button>
		</c:when>
		<c:when test="${sessionScope.divCd eq 'A4_02' }">
			<button class="btn btn-success btn-sm" onclick="location.href='notice.do?divCd=A4_02'">목록으로</button>
		</c:when>
	</c:choose>
		<!-- 수정 버튼여부 session값과 등록id일치시 -->
	<c:choose>
		<c:when test="${param.workDiv eq 'update' and sessionScope.stoId eq noticeVo.stoId}">
			<button class="btn btn-success btn-sm" id="do_updateTrans" name="do_updateTrans" onclick="do_updateTrans()">완료</button>
			<button class="btn btn-success btn-sm" id="do_goback" name="do_goback" onclick="do_goback()">취소</button>
		</c:when>
		<c:when test="${param.workDiv eq 'update' and sessionScope.stoId eq '00001'}">
			<button class="btn btn-success btn-sm" id="do_updateTrans" name="do_updateTrans" onclick="do_updateTrans()">완료</button>
			<button class="btn btn-success btn-sm" id="do_goback" name="do_goback" onclick="do_goback()">취소</button>
		</c:when>
		<c:when test="${sessionScope.stoId eq noticeVo.stoId or stoId eq '00001'}">
			<button class="btn btn-success btn-sm" id="do_update" name="do_update" onclick="do_update()">수정</button>
			<button class="btn btn-success btn-sm" id="do_delete" name="do_delete" onclick="do_delete()">삭제</button>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	</div>
	
	<form name="frm" id="frm" method="GET" class="form-inline">
		<table id="listTable" class="table table-striped table-bordered">
			<input type="hidden" name="workDiv" id="workDiv" value=""/>
			<input type="hidden" name="no" id="no" value="${noticeVo.no}" />
			<input type="hidden" name="memoNo" id="memoNo" value="<%=session.getAttribute("memoNo")%>" />
			<br/><br/><br/>
			<tr>
				<th height="5">제목</th>
				<td height="5">
				<input type="text" class="form-control"
				name="title" id="title" value="<c:out value="${noticeVo.title}"/>" disabled="disabled"/>
				</td >
				<th height="5">등록일</th>
				<td height="5"><c:out value="${noticeVo.regDt}" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea class="form-control form-control-lg" name="con" id="con" disabled="disabled"
					cols="220" rows="5">${noticeVo.con}</textarea>
				</td>
			</tr>	
		</table>
		<c:choose>		
			<c:when test="${sessionScope.divCd eq 'A4_02'}">
				<input type="text" class="form-control" size="40" name="comment" id="comment"
				placeholder="댓글"/>
				<input type="button" class="btn btn-success btn-sm" id="do_add" onclick="insertComment()" value="댓글달기"/>
			</c:when>
			<c:otherwise>
			</c:otherwise>			
		</c:choose>
	</form>
	
	<!-- 댓글 뿌려주는 부분 -->
	<c:choose>
		<c:when test="${sessionScope.divCd eq 'A4_02'}">
			<table class="table">
				<c:forEach var="commentVo" items="${clist}" varStatus="status">
					<tr>
						<td><c:out value="${commentVo.stoNm}" /></td>
						<td><input type="hidden" id="cmtNo${status.index}" value="${commentVo.cmtNo}"></td>
						<td id="ansContent${status.index}"><c:out value="${commentVo.cmtCon}" />
							<!-- session값과 stoId비교하여 버튼 여부 결정-->
							<c:choose>
								<c:when test="${sessionScope.stoId eq commentVo.stoId}">							
										<input type="button" id="update" value="수정" onclick="add_update(${status.index})">
										<input type="button" id="delete" name="delete" value="삭제"> 
										<input type="hidden" id="index" name="index" value="${status.index}"> 							
								</c:when>
							</c:choose>
						</td>
						<td id="updateDiv${status.index}" style="display: none">
							<textarea name="updateContent" id="updateContent" cols="40" rows="10" autofocus="autofocus" required wrap="hard">${commentVo.cmtCon}</textarea>
							<button id="updateBtn" >수정하기</button>
							<input type="button" value="취소" onclick="remove_update(this,${status.index})">
							<input type="hidden" id="indexHidden" name="indexHidden" value="${status.index}"> 
						</td>
						<td id="newAnsCon${status.index}"></td>										
						<input type="hidden" id="ansNo${status.index}" name="ansNo" value="${noticeQnaVo.ansNo}">
					<%-- 	 <input type="hidden" id="cmtNo${status.index}" name="cmtNo" value="${commentVo.cmtNo}"/>--%>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<!-- // -->
	
	<!-- 이전글 다음글 -->
	<table class="table">
			<tr>
				<th>
					<c:if test="${noticeVo.preMemoNo ne null}">
						<button class="btn btn-success btn-sm" onclick="location.href='<%=context%>/notice/do_detail.do?memoNo=${noticeVo.preMemoNo}'">
							이전글
						</button>
					</c:if>
				</th>
				<th>
					${noticeVo.preTitle}
				</th>
			</tr>
			<tr>
				<th>
					<c:if test="${noticeVo.nextMemoNo ne null}">
						<button class="btn btn-success btn-sm" onclick="location.href='<%=context%>/notice/do_detail.do?memoNo=${noticeVo.nextMemoNo}'">
							다음글 
						</button>
					</c:if>
				</th>
				<th>
					${noticeVo.nextTitle}
				</th>
			</tr>
	</table>
	<!-- // -->
	
	<!-- 수정완료 버튼 여부 -->
</body>
</html>