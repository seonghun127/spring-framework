<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
	String gntCd = StringUtil.nvl((String)session.getAttribute("gntCd"), "1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery 순서도 중요함!-->
<script src="<%=context%>/resources/js/jquery-1.12.4.js"></script>

<!-- Bootstrap 반응형 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap css -->
<link rel="stylesheet"
	href="<%=context%>/resources/css/bootstrap.css">
<!--  Bootstrap theme -->
<link rel="stylesheet"
	href="<%=context%>/resources/css/bootstrap-theme.min.css">
<!-- Bootstrap JavaScript -->
<script src="<%=context%>/resources/js/bootstrap.min.js"></script>

<style type="text/css">
thead font{
	color: white;
}
#home{
			 height: 53px;  
            width: 100%;       
            position: relative;
            background:white;
			  opacity: 0.9;
}


#topMenu {
            height: 50.5px;  
            width: 105%;       /* [변경] 하위 메뉴와 동일하게 맞춤 */
            position: relative;
            background:black;
			  opacity: 0.9;
           
    }
    #topMenu ul {           /* 메인 메뉴 안의 ul을 설정함: 상위메뉴의 ul+하위 메뉴의 ul */
        list-style-type: none;  
        margin: 0px;            
        padding: 0px; 
                  
    }
    #topMenu ul li {            /* 메인 메뉴 안에 ul 태그 안에 있는 li 태그의 스타일 적용(상위/하위메뉴 모두) */
        color: white;               
        background-color: #2d2d2d;  
        float: left;                
        line-height: 50.5px;          
        vertical-align: middle;     
        text-align: center;         
        -position: relative;
        <%if(gntCd.equals("1")) {%> 
        width: 20%; <%}
        				%>
        <%if(gntCd.equals("0")) {
        	%> width: 33%;
        <%}
     	%>
    }
    .menuLink, .submenuLink {           /* 상위 메뉴와 하위 메뉴의 a 태그에 공통으로 설정할 스타일 */
        text-decoration:none;               
        display: block;                     
        width: 100% ;                       
        font-size: 12px;                    
        font-weight: bold;                  
        font-family: "Trebuchet MS", Dotum; 
    }
   
    .submenu {              /* 하위 메뉴 스타일 설정 */
        position: absolute;     
        height: 0px;            
        overflow: hidden;       
        transition: height .2s; 
        -webkit-transition: height .2s; 
        -moz-transition: height .2s; 
        -o-transition: height .2s; 
        width: 100%;           
        left: 0;   
                 
			opacity: 0.9;
        background-color: #2d2d2d; /* [추가] 하위 메뉴 전체에 배경색 설정 */
    }
    .submenu li {
        display: inline-block;

    }
    .topMenuLi:HOVER .submenu { 
        height: 60px;
        color: white;          
    }
    .link{
		  display: block;
		  color: white;
	}
	.link:HOVER{
		  display: block;
		  color: white;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<nav id="topMenu" >
		<!-- 사용자 관점 헤더 -->
		<%if(gntCd.equals("1")) {%>
		<ul>
			<li class="home">
			<a style="text-decoration: none" class="navbar-brand"  href="<%=context%>/master/mainPage.do"><font>RETAIL_HOME</font></a>
			</li>
			<li class="topMenuLi">
			<a style="text-decoration: none;color: white;" href="#"><font>Sale</font></a>
				<ul class="submenu">
				<li><a style="text-decoration: none" class="link"  href="<%=context%>/sale/sale.do"><font>판매</font></a></li>
				</ul>
			</li>
			<li class="topMenuLi">
			<a style="text-decoration: none;color: white;" href="#"><font>Logistics Management</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link"  href="<%=context%>/logisticsManagement/stockView.do"><font>재고보기(발주)</font></a></li>
			</ul>
			</li>
			<li class="topMenuLi">
			<font><a style="text-decoration: none;color: white;" href="#"><font>Store / Service</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link" href="<%=context%>/storeService/searchStore.do"><font>매장검색</font></a></li>
				<li><a style="text-decoration: none" class="link" href="<%=context%>/storeService/todaySale.do"><font>오늘매출</font></a></li>
				<li><a style="text-decoration: none" class="link" href="<%=context%>/storeService/termSale.do"><font>기간별매출</font></a></li>
				<li><a style="text-decoration: none" class="link" href="<%=context%>/notice/notice.do?divCd=A4_02&chk=t"><font>커뮤니티</font></a></li>
			</ul>
			</li>
			<li class="topMenuLi">
			<a style="text-decoration: none;color: white;" href="#"><font>Notice</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link"  href="<%=context%>/notice/notice.do?divCd=A4_01&chk=t"><font>공지사항</font></a></li>
				<li><a style="text-decoration: none" class="link"  href="<%=context%>/notice/qnaSearch.do"><font>건의사항</font></a></li>
			</ul>
			</li>
		</ul>
		<!-- 마스터 관점 헤더-->
		<%}
		if(gntCd.equals("0")) {
		%>
		<ul>
			<li class="home">
			<a style="text-decoration: none" class="navbar-brand"  href="<%=context%>/master/mainPage.do"><font>RETAIL_HOME</font></a>
			</li>
			<li class="topMenuLi">
				<a style="text-decoration: none;color: white;" href="#"><font>Notice</font></a>
				<ul class="submenu">
					<li><a style="text-decoration: none" class="link"  href="<%=context%>/notice/notice.do?divCd=A4_01&chk=t"><font>공지사항</font></a></li>
					<li><a style="text-decoration: none" class="link" href="<%=context%>/notice/notice.do?divCd=A4_02&chk=t"><font>커뮤니티</font></a></li>
					<li><a style="text-decoration: none" class="link"  href="<%=context%>/notice/qnaSearch.do"><font>건의사항</font></a></li>
				</ul>
			</li>
		</ul>
		<ul>
			<li class="topMenuLi">
				<a style="text-decoration: none;color: white;" href="#"><font>Master</font></a>
				<ul class="submenu">
					<li><a style="text-decoration: none" class="link" href="<%=context%>/storeService/searchStore.do"><font>지점관리</font></a></li>		
					<li><a style="text-decoration: none" class="link" href="<%=context%>/master/search.do"><font>발주신청현황</font></a></li>
					<li><a style="text-decoration: none" class="link"  href="<%=context%>/master/masterData.do"><font>데이터분석</font></a></li>
				</ul>
			</li>
		</ul>
		<%} 
		%>
		
</nav>
</body>
</html>