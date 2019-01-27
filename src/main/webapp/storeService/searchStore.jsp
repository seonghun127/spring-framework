<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.retail.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
%>

<%	
	String search_div="";
	String search_word="";
	search_div = StringUtil.nvl(request.getParameter("sido"),"");// 시/도
	search_word= StringUtil.nvl(request.getParameter("gugun"),"");// 구/군
	
	String page_size  = "10";//Page사이즈
	String page_num   = "1";//Page넘버
	
	int totalCnt   = 0;//총글수    
	int bottomCount= 10;//page 10 << < 1 2 3 4 5 6 7 8 9 10 > >>
	
	page_num   = StringUtil.nvl(request.getParameter("page_num"),"1");  //Page넘버 받아 온 값
	page_size  = StringUtil.nvl(request.getParameter("page_size"),"10"); //"10";//Page사이즈
	
	int o_page_size = Integer.parseInt(page_size);
	int o_page_num  = Integer.parseInt(page_num); //페이저 넘버 숫자로 변환. 
	
	//총 페이지 수 구하기
	String iTotalCnt = 
		(null == request.getAttribute("total_cnt"))?"0":request.getAttribute("total_cnt").toString();
		totalCnt = Integer.parseInt(iTotalCnt);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap JavaScript -->
<script src="<%=context%>/resources/js/citySelector.js"></script>
   <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
      		width:1140px;
        height: 400px;
        margin-top: 100px; 
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        width: 100%;
        margin: 0%;
        padding: 0;
      }
    </style>

<title>Search_Store</title>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <script async defer
   src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXEHDw07Vd_5n6ISV9rYi14j8M_Lo8Ezk&callback=initMap">
</script>
    <script>
    function initMap(lat,lot) {
        if(lat==null){
             lat=37.566380;
             lot=126.977902;
         };
              var map = new google.maps.Map(document.getElementById('map'), {
               center: {lat: parseFloat(lat), lng: parseFloat(lot)},
               zoom: 11
             });
              var marker = null;
              var paramlat=null;
              var paramlot=null;
              
              <c:forEach items="${storelist}" var="list">
              paramlat="${list.lat}";
              paramlot="${list.lot}";
              var stoNm="${list.stoNm}";
              var ownId="${list.ownId}";
              var phoNo="${list.phoNo}";
              var adr="${list.adr}";
              paramlat="${list.lat}";
              paramlot="${list.lot}";
              var stoNm="${list.stoNm}";
              var ownId="${list.ownId}";
              var phoNo="${list.phoNo}";
              var adr="${list.adr}";
              var contents="지점명 : "+stoNm+"<br/>"+"점주명 : "+ownId+"<br/>"+"핸드폰번호 : "+phoNo+"<br/>"+"주소 : "+adr;
              addMarker(map,parseFloat(paramlat),parseFloat(paramlot),contents);
              </c:forEach>
           }
    function addMarker(map, lat, lot,contents) {
         var marker = new google.maps.Marker({
             position: {'lat' : lat,'lng' : lot},
             map: map
           });
         map.setCenter(marker.getPosition());
         var infowindow = new google.maps.InfoWindow({
				'content' : contents
				});
		google.maps.event.addListener(marker,'click', function() {
			infowindow.open(map, marker);
		});
     }
   
    //검색 function
    function doSearch() {
    	if($("#sido").val()=="전체") {
    		alert("시/도를 선택해주세요.");
    		return;
    	} 
    	var frm = document.frm;
      frm.action="do_search.do";
      frm.submit();
    }
    
    //현재 페이지 
    function search_page(url,page_num){
 	   console.log(url+":"+page_num);	
        var frm = document.frm;
        frm.page_num.value = page_num;
        frm.sido.value = sido;
        frm.gugun.value = gugun;
        frm.action=url;
        frm.submit();
     }
    
   </script>
</head>
<body>

  <div id="map"></div>


   	
   	<!-- 검색 기능 -->
   	<form name="frm" id="frm" action="do_search.do" method="GET"
    class="form-inline" >
    	<input type="hidden"  name="page_num" id="page_num" />  
	   	<table class="table" bordercolor="#1C2331">
	   		<tr>
	   			<div class="form-group col-lg6 col-sm6">
	          		<!-- 시/도 SELECT BOX -->
	          	<select name="sido" id="sido" class="form-control"
	          		onchange="change(this.selectedIndex);">
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
	             	
	             	<select name="gugun" id="gugun" class="form-control">
	                <option value="">전체</option>	        
	             </select>	                                     
	       	</div>
	   		</tr>
	   	</table>
	  </form>
	  <!-- 검색버튼 -->
	  <button class="btn btn-success" onclick="javascript:doSearch();">조회</button>
	  
   	<!-- //검색 기능 -->
   	
   	<!-- 지점 정보 테이블 -->
  	<table id="listTable" class="table" >
  		<thead>
  		<tr>
  			  <th class="text-center"><font>점포아이디</font></th>
	        <th class="text-center"><font>매장명</font></th>	        
	        <th class="text-center"><font>점주명</font></th>
	        <th class="text-center"><font>연락처</font></th>
	        <th class="text-center"><font>주소</font></th>
	    </tr>
	    </thead>
	    <c:choose>
        <c:when test="${pagingList.size()>0}">
            <c:forEach var="masterStoreVo" items="${pagingList}">
			     <tr>	 
			     	 <td align="center" ><c:out value="${masterStoreVo.stoId}"/></td>
			        <td align="center" ><c:out value="${masterStoreVo.stoNm}"/></td>	
			        <td align="center" ><c:out value="${masterStoreVo.ownId}"/></td>
			        <td align="center" ><c:out value="${masterStoreVo.phoNo}"/></td>
			        <td align="center" ><c:out value="${masterStoreVo.adr}"/></td>			  
			     </tr>
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
  	
</body>
</html>