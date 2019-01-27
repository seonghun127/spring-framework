<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
	
	String stoId=(String)session.getAttribute("stoId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<title>Join</title>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXEHDw07Vd_5n6ISV9rYi14j8M_Lo8Ezk&callback=initMap">
   
</script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
 //경위도변환
	function geoCode() {
      
      var faddr = $('#adr').val();
      var geocoder;
      
      geocoder = new google.maps.Geocoder();
      geocoder.geocode({
         'address' : faddr
      }, function(results, status) {
         if (status == google.maps.GeocoderStatus.OK) {
        	 var lot = results[0].geometry.location.lng(); //경도
        	 var lat = results[0].geometry.location.lat(); //위도
         } else {
            var lat = "";
            var lot = "";
            alert("경/위도로 변환할 수 없습니다.")
         }		
			document.getElementById('lat').value = lat;
			document.getElementById('lot').value = lot;

			document.getElementById('lat').focus();
         
         return;
      });
   }

//우편번호
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 도로명 주소를 선택했을 경우
						if (data.userSelectedType === 'R') { 
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							
							//법정동명이 있을 경우 추가한다.
							/* if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							} */
							
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('adr').value = fullAddr+" ";

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('adr').focus();
					}
				}).open();
	}
	
	function do_goback(){
		window.location.href="mainPage.do";
	}
</script>

</head>
<body>
<h3>가입</h3>
<div class="container">
		<div class="col-lg-12"></div>
		<div class="col-lg-12"></div>
<form name="frm" id="frm" action="insert.do" method="POST" class="form-horizontal">
	<table id="listTable" class="table table-striped table-hover table-bordered">
			<tr>
				<td><font class="font2">점포번호</font>
					<input type="text" name="stoId" id="stoId" class="form-control input-sm" placeholder="점포번호" maxlength="5" disabled="disabled" value="${masterStoreVo.stoId}">
				</td>
			</tr>
			<tr>
				<td><font class="font2">비밀번호</font>
					<input type="password" name="password" id="password" class="form-control input-sm" placeholder="비밀번호" maxlength="5" disabled="disabled" value="${masterStoreVo.pwd}">
				</td>
			</tr>
			<tr>
				<td><font class="font2">점포명</font>
					<input type="text" name="stoNm" id="stoNm" class="form-control input-sm" placeholder="점포명" maxlength="5">
				</td>
			</tr>
			<tr>
				<td><font class="font2">점주명</font>
					<input type="text" name="ownId" id="ownId" class="form-control input-sm" placeholder="점주명" maxlength="5">
				</td>
			</tr>
			<tr>	
				<td><font class="font2">연락처</font>
					<input type="text" name="phoNo" id="phoNo" class="form-control input-sm" placeholder="연락처" maxlength="11">
				</td>
			</tr>
			<tr>
				<td><font class="font2">주소</font>
					<input type="text" name="adr" id="adr" class="form-control input-sm" placeholder="주소">
					<input class="btn btn-primary" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					<input class="btn btn-primary" type="button" onclick="geoCode()" id="address" name="address" value="경/위도 변환">
				</td>
			</tr>
			<tr>	
				<td><font class="font2">경/위도</font>
					<input type="text" name="lot" id="lot" class="form-control input-sm" placeholder="경도" maxlength="25">
					<input type="text" name="lat" id="lat" class="form-control input-sm" placeholder="위도" maxlength="25">
				</td>
			</tr>			
			<tr>
				<td>
				<input type="submit" class="btn btn-success btn-sm" value="가입" />
				<input type="button" class="btn btn-success btn-sm" onclick="do_goback()" value="취소">
				</td>
			</tr>
	</table>
</form>
</div>
</body>
</html>