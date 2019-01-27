<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.retail.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

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
   <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%; 
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 75%;
        width: 70%;
        margin: 10%;
        padding: 0;
      }
    </style>

<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <script async defer
   src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXEHDw07Vd_5n6ISV9rYi14j8M_Lo8Ezk&callback=initMap">
</script>
	<script type="text/javascript">
		  var cnt = new Array();
	     cnt[0] = new Array('전체');
	     cnt[1] = new Array('전체','강남구','강동구','강북구','강서구','관악구','광진구','구로구','금천구','노원구','도봉구','동대문구','동작구','마포구','서대문구','서초구','성동구','성북구','송파구','양천구','영등포구','용산구','은평구','종로구','중구','중랑구');
	     cnt[2] = new Array('전체','강서구','금정구','남구','동구','동래구','부산진구','북구','사상구','사하구','서구','수영구','연제구','영도구','중구','해운대구','기장군');
	     cnt[3] = new Array('전체','남구','달서구','동구','북구','서구','수성구','중구','달성군');
	     cnt[4] = new Array('전체','계양구','남구','남동구','동구','부평구','서구','연수구','중구','강화군','옹진군');
	     cnt[5] = new Array('전체','광산구','남구','동구','북구','서구');
	     cnt[6] = new Array('전체','대덕구','동구','서구','유성구','중구');
	     cnt[7] = new Array('전체','남구','동구','북구','중구','울주군');
	     cnt[8] = new Array('전체','고양시','과천시','광명시','구리시','군포시','남양주시','동두천시','부천시','성남시','수원시','시흥시','안산시','안양시','오산시','의왕시','의정부시','평택시','하남시','가평군','광주시','김포시','안성시','양주군','양평군','여주군','연천군','용인시','이천군','파주시','포천시','화성시');
	     cnt[9] = new Array('전체','강릉시','동해시','삼척시','속초시','원주시','춘천시','태백시','고성군','양구군','양양군','영월군','인제군','정선군','철원군','평창군','홍천군','화천군','황성군');
	     cnt[10] = new Array('전체','제천시','청주시','충주시','괴산군','단양군','보은군','영동군','옥천군','음성군','진천군','청원군');
	     cnt[11] = new Array('전체','공주시','보령시','서산시','아산시','천안시','금산군','논산군','당진군','부여군','서천군','연기군','예산군','청양군','태안군','홍성군');
	     cnt[12] = new Array('전체','군산시','김제시','남원시','익산시','전주시','정읍시','고창군','무주군','부안군','순창군','완주군','임실군','장수군','진안군');
	     cnt[13] = new Array('전체','광양시','나주시','목포시','순천시','여수시','여천시','강진군','고흥군','곡성군','구례군','담양군','무안군','보성군','신안군','여천군','영광군','영암군','완도군','장성군','장흥군','진도군','함평군','해남군','화순군');
	     cnt[14] = new Array('전체','경산시','경주시','구미시','김천시','문겅시','상주시','안동시','영주시','영천시','포항시','고령군','군위군','봉화군','성주군','영덕군','영양군','예천군','울릉군','울진군','의성군','청도군','청송군','칠곡군');
	     cnt[15] = new Array('전체','거제시','김해시','마산시','밀양시','사천시','울산시','진주시','진해시','창원시','통영시','거창군','고성군','남해군','산청군','양산시','의령군','창녕군','하동군','함안군','함양군','합천군');
	     cnt[16] = new Array('전체','서귀포시','제주시','남제주군','북제주군');
	     function change(add) {
	     sel=document.frm.gugun
	       /* 옵션메뉴삭제 */
	       for (i=sel.length-1; i>=0; i--){
	         sel.options[i] = null
	         }
	       /* 옵션박스추가 */
	       for (i=0; i < cnt[add].length;i++){                     
	                         sel.options[i] = new Option(cnt[add][i], cnt[add][i]);
	         }         
	     }
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
	   	<table class="table">
	   		<tr>
	   			<div class="form-group col-lg6 col-sm6">
	          		<!-- 시/도 SELECT BOX -->
	          	<select name="sido" id="sido" class="input"
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
	             	
	             	<select name="gugun" id="gugun" class="class=select">
	                <option value="">전체</option>	        
	             </select>	                                     
	       	</div>
	   		</tr>
	   	</table>
	  </form>
	  <!-- 검색버튼 -->
	  <button class="btn btn-success btn-sm" onclick="javascript:doSearch();">조회</button>
	  
   	<!-- //검색 기능 -->
   	
   	<!-- 지점 정보 테이블 -->
  	<table id="listTable" class="table table-striped table-hover table-bordered">
  		<tr>
  			  <th class="text-center">점포아이디</th>
	        <th class="text-center">매장명</th>	        
	        <th class="text-center">점주명</th>
	        <th class="text-center">연락처</th>
	        <th class="text-center">주소</th>
	    </tr>
	    
	    <c:choose>
        <c:when test="${pagingList.size()>0}">
            <c:forEach var="masterStoreVo" items="${pagingList}">
			     <tr>	 
			     	 <td class="text-left"><c:out value="${masterStoreVo.stoId}"/></td>
			        <td class="text-left"><c:out value="${masterStoreVo.stoNm}"/></td>	
			        <td class="text-left"><c:out value="${masterStoreVo.ownId}"/></td>
			        <td class="text-left"><c:out value="${masterStoreVo.phoNo}"/></td>
			        <td class="text-left"><c:out value="${masterStoreVo.adr}"/></td>			  
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