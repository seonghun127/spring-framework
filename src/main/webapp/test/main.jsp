<%@page import="com.sist.retail.common.StringUtil"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;

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
	href="<%=context%>/resources/css/bootstrap.min.css">

<!--  Bootstrap theme -->
<link rel="stylesheet"
	href="<%=context%>/resources/css/bootstrap-theme.min.css">

<!-- Bootstrap JavaScript -->
<script src="<%=context%>/resources/js/bootstrap.min.js"></script>
<style type="text/css">
#topMenu {
            height: 70px;  
            width: 100%;       /* [변경] 하위 메뉴와 동일하게 맞춤 */
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
        line-height: 70px;          
        vertical-align: middle;     
        text-align: center;         
        -position: relative; 
        width: 33.33333%;        
    }
    .menuLink, .submenuLink {           /* 상위 메뉴와 하위 메뉴의 a 태그에 공통으로 설정할 스타일 */
        text-decoration:none;               
        display: block;                     
        width: 100%;                       
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
<nav class="navbar navbar-default" id="topMenu" >
		<ul>
			<li class="topMenuLi">
			<a style="text-decoration: none;color: white;" href="#"><font>Logistics Management</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link"  href="#"><font>재고보기(발주)</font></a></li>
			</ul>
			
			<li class="topMenuLi">
			<font><a style="text-decoration: none;color: white;" href="#"><font>Store / Service</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link" href="#"><font>매장검색</font></a></li>
				<li><a style="text-decoration: none" class="link" href="#"><font>매출확인</font></a></li>
				<li><a style="text-decoration: none" class="link" href="#"><font>커뮤니티</font></a></li>
			</ul>
			<li class="topMenuLi">
			<a style="text-decoration: none;color: white;" href="#"><font>Notice</font></a>
			<ul class="submenu">
				<li><a style="text-decoration: none" class="link"  href="#"><font>공지사항</font></a></li>
				<li><a style="text-decoration: none" class="link"  href="#"><font>건의사항</font></a></li>
			</ul>
		</ul>
		
</nav>
</body>
</html>