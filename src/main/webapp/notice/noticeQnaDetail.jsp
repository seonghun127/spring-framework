<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.sist.retail.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String stoId=(String)session.getAttribute("stoId");

	request.setAttribute("stoId", stoId);

	String qaNo = request.getParameter("qaNo");
	session.setAttribute("qaNo", qaNo);
	
	String workDiv=StringUtil.nvl(request.getParameter("workDiv"),"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Notice_QnaDetail</title>
<script type="text/javascript">
	var div_count=0;

	function add_item() {

	// pre_set 에 있는 내용을 읽어와서 처리..
	var div = document.createElement('div');
	div.innerHTML = document.getElementById('pre_set').innerHTML;
	document.getElementById('field').appendChild(div);

	var addAns = document.getElementById("addAns");
	if (addAns.style.display == 'none') {
		addAns.style.display = 'block';
	} else {
		addAns.style.display = 'none';
	}
}

function add_update(index) {
	
	// pre_set 에 있는 내용을 읽어와서 처리..
	if(div_count==0){
		var div = document.createElement('div');
		div.innerHTML = document.getElementById('updateAnsCon'+index).innerHTML;
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

function remove_item(obj) {
	// obj.parentNode 를 이용하여 삭제
	document.getElementById('field').removeChild(obj.parentNode);

	var addAns = document.getElementById("addAns");
	if (addAns.style.display == 'none') {
		addAns.style.display = 'block';
	} else {
		addAns.style.display = 'none';
	}

}

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

function doAdd() {
	var frm = document.frm;
	frm.action = "ansInsert.do";
	frm.submit();
}

//Jquery start
$(document).ready(function() {
 	//request값 가져오기 
	var url = location.href;
	var parameters = (url.slice(url.indexOf('=') + 1, url.length)).split('&');
	var workDiv=parameters[0];
	
	//수정 버튼 활성화 여부.
	if(workDiv=="do_qna_update") {
		$("#title").prop("disabled",false);
		$("#qaCon").prop("disabled",false);
	}
	
		//alert("document ready")

		// 댓글 추가
		// 동적 이벤트 활성
		$(document).on("click", '#do_add',function(){
			console.log("do_reply_add");
			if (false == confirm("답변 등록 하시겠습니까?")){
				div_count = 0;
				return;
			}
			div_count = 0;
			var ansCon = $(this).parent().find("textarea[id='ansCon']").val();
			console.log("ansCon : "+ $(this).parent().find("textarea[id='ansCon']").val());
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "ansInsert.do", //action
				dataType : "html", //html/Json
				data : {
					"ansCon" : ansCon,
					"qaNo" : $("#qaNo").val()
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);
					var parseData = $.parseJSON(data);
					console.log("2success:" + parseData);
					if (parseData.flag == "1") {//1
						alert("답변 등록 성공!");
						location.reload();		// 새로고침
					} else {
						alert("답변 등록 실패!");
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
		});//--댓글 추가
		
		
		// 댓글 수정
		// 동적 이벤트 활성
		$(document).on("click", '#updateBtn',function(){
			console.log("do_reply_update");
			if (false == confirm("답변 수정 하시겠습니까?"))
				return;
			var index = $(this).parent().find("input[id='index']").val();
			var ansCon = $(this).parent().find("textarea[id='updateAns']").val();
			console.log("updateAns : "+ $(this).parent().find("textarea[id='updateAns']").val());
			console.log("ansCon : " + ansCon);
			console.log("index : " + index);
			console.log("ansNo : "+ $("#ansNo"+index).val())
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "ansUpdate.do", //action
				dataType : "html", //html/Json
				data : {
					"ansCon" : ansCon,
					"qaNo" : $("#qaNo").val(),
					"ansNo": $("#ansNo"+index).val()
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);
					var parseData = $.parseJSON(data);
					console.log("2success:" + parseData);
					if (parseData.flag == "1") {//1
						alert("답변 수정 성공!");
						location.reload();		// 새로고침
					} else {
						alert("답변 수정 S실패!");
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
		});//--댓글 수정
		
		// 댓글 삭제
		// 동적 이벤트 활성
		$(document).on("click", '#delete',function(){
			console.log("do_reply_delete");
			if (false == confirm("답변 삭제 하시겠습니까?"))
				return;
			var index = $(this).parent().find("input[id='index']").val();
			console.log("index : "+index);
			console.log("ansNo : "+ $("#ansNo"+index).val())
			//ajax 통신
			$.ajax({
				type : "POST", //get/post
				url : "ansDelete.do", //action
				dataType : "html", //html/Json
				data : {
					"ansNo": $("#ansNo"+index).val()
				},
				success : function(data) {//거래 성공시
					console.log("1success:" + data);
					var parseData = $.parseJSON(data);
					console.log("2success:" + parseData);
					if (parseData.flag == "1") {//1
						alert("답변 삭제 성공!");
						location.reload();		// 새로고침
					} else {
						alert("답변 삭제 실패!");
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
		});//--댓글 삭제
	}); 
	 
	function do_qna_delete(){
		var frm = document.frm;
		frm.action = "do_qna_delete.do?"+$("#qaNo").val();
		if (false == confirm("삭제 하시겠습니까?"))
			return;
		frm.submit();
	}
	
	//조회,수정 구분
	function do_qna_update() {
		var frm = document.frm;
		frm.action = "qnaSearchOne.do";
		frm.workDiv.value="do_qna_update";
		frm.submit();
	}
	
	//Update Query로 이동
	function do_updateTrans() {
		var frm = document.frm;
		frm.action="do_qna_update.do";
		frm.submit();
	}
	
	function do_goback(){
		history.go(-1);
	}
</script>
</head>
<body>
	<h3>게시글 상세보기</h3>
	<!-- Button Area -->
	<div class="form-inline pull-right">
		<button class="btn btn-success btn-sm" onclick="location.href='qnaSearch.do'">목록으로</button>
		<!-- 수정완료 버튼 여부 -->
		<c:choose>
			<c:when test="${param.workDiv eq 'do_qna_update' and stoId eq QnaDetailList.get(0).stoId}">
				<button class="btn btn-success btn-sm" id="do_updateTrans" name="do_updateTrans" onclick="do_updateTrans()">완료</button>
				<button class="btn btn-success btn-sm" id="do_goback" name="do_goback" onclick="do_goback()">취소</button>
			</c:when>
			<c:when test="${param.workDiv eq 'do_qna_update' and stoId eq '00001'}">
				<button class="btn btn-success btn-sm" id="do_updateTrans" name="do_updateTrans" onclick="do_updateTrans()">완료</button>
				<button class="btn btn-success btn-sm" id="do_goback" name="do_goback" onclick="do_goback()">취소</button>
			</c:when>
			<c:when test="${stoId eq QnaDetailList.get(0).stoId or stoId eq '00001'}">
				<button class="btn btn-success btn-sm" id="do_qna_update" name="do_qna_update" onclick="do_qna_update()">수정</button>
				<button class="btn btn-success btn-sm" id="do_qna_delete" name="do_qna_delete" onclick="do_qna_delete()">삭제</button>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>			
	</div>
	<!-- // Button Area -->
							
	<form name="frm" id="frm" method="GET" class="form-inline">
	<table id="listTable" class="table table-striped table-bordered">
			<input type="hidden" name="workDiv" id="workDiv" value=""/>
			<input type="hidden" id="qaNo" name="qaNo" value="<%=session.getAttribute("qaNo") %>">
		<tr>
			<th>제목</th>
			<td>
			<input type="text" name="title" id="title" value="<c:out value="${QnaDetailList.get(0).title}"/>" disabled="disabled"/>
			</td>
			<th>등록일</th>
			<td>${QnaDetailList.get(0).qaDt}</td>
		</tr>
		<tr>
			<th>문의</th>
			<th>작성자</th>
			<td>${QnaDetailList.get(0).stoNm}</td>
		</tr>
		<tr>
			<td colspan="4">
			<textarea class="form-control form-control-lg" name="qaCon" id="qaCon" disabled="disabled" cols="220" rows="5">${QnaDetailList.get(0).qaCon}</textarea>
			</td>
		</tr>
		<tbody>
			<c:choose>
				<c:when test="${QnaDetailList.get(0).ansCon.equals(null) == false }">
					<c:forEach var="noticeQnaVo" items="${QnaDetailList}" varStatus="status">
						<tr>
							<th class="text-center">작성자</th>
							<td>관리자</td>
							<th>등록일</th>
							<td><c:out value="${noticeQnaVo.ansDt}" /></td>
						</tr>
						<tr>
							<td>답변내용</td>
							<td id="ansContent${status.index}" class="text-center"><c:out value="${noticeQnaVo.ansCon}" /></td>
					
							<td id="updateAnsCon${status.index}" style="display: none">
								<textarea name="updateAns" id="updateAns" cols="40" rows="5" autofocus="autofocus" required wrap="hard" >${noticeQnaVo.ansCon}</textarea>
								<input type="button" id="updateBtn" value="수정하기">
								<input type="button" value="취소" onclick="remove_update(this,${status.index})">
								<input type="hidden" id=index name=index value="${status.index}"> 
							</td>
							<td id="newAnsCon${status.index}"></td>
							<td>
								<c:choose>
									<c:when test="${stoId eq '00001'}">
										<input type="button" id="update" value="수정" onclick="add_update(${status.index})">
										<input type="button" id="delete" name="delete" value="삭제">
										<input type="hidden" id=index name=index value="${status.index}">  
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</td>
							<input type="hidden" id="ansNo${status.index}" name="ansNo" value="${noticeQnaVo.ansNo}">
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</form>
		
	<div id="pre_set" style="display: none">
		<!-- <input type="text" name="ansCon" id="ansCon" style="width: 800px; height: 200px"><br> -->
		<textarea name="ansCon" id="ansCon" cols="40" rows="10" autofocus="autofocus" required wrap="hard" placeholder="답변하실 내용을 입력하세요." ></textarea>
		<button class="btn btn-success btn-sm" id="do_add">등록하기</button>
		<button class="btn btn-success btn-sm" onclick="remove_item(this)">취소</button>
		<input type="hidden" id="qaNo" name="qaNo" value="${QnaDetailList.get(0).qaNo}">
	</div>
	
	<!-- <form id="frm" name="frm" method="post" action="ansInsert.do"> -->
		<div id="field">
			
		</div>
	<!-- </form> -->
	
	<c:choose>
		<c:when test="${stoId eq '00001'}">
			<div id="addAns">
				<button class="btn btn-success btn-sm" onclick="add_item()">답변하기</button>
			</div>
		</c:when>
	</c:choose>
	
	<!-- 이전글 다음글 -->
	<table class="table">
		<tr>
			<th>
				<c:if test="${QnaDetailList.get(0).preQaNo ne null}">
					<button class="btn btn-success btn-sm" onclick="location.href='qnaSearchOne.do?qaNo=${QnaDetailList.get(0).preQaNo}'">
						이전글
					</button>
				</c:if>
			</th>
			<th>
				${QnaDetailList.get(0).preTitle}
			</th>
		</tr>
		<tr>
			<th>
				<c:if test="${QnaDetailList.get(0).nextQaNo ne null}">
					<button class="btn btn-success btn-sm" onclick="location.href='qnaSearchOne.do?qaNo=${QnaDetailList.get(0).nextQaNo}'">
						다음글
					</button>
				</c:if>
			</th>
			<th>
					${QnaDetailList.get(0).nextTitle}
			</th>
		</tr>
	</table>
	<!-- // -->
</body>
</html>