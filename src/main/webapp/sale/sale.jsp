<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sist.retail.common.HlConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = request.getContextPath();
	context = HlConstant.HL_URL + context;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	.saleCenter{
		margin-top: 100px;
	}
</style>
<title>Sale</title>
<script>
$(document).ready(function(){
    $("#sale").on("click",function(){
    	var sex = 
            $("input:radio[name=sex]:checked").val();
    
    	 $.ajax({
             type:"POST",             //get/post
             async: true,             //동기/비동기 
             url:"sale.do",         //action
             dataType:"JSON",         //html/Json
             data:{
                    "pdtNo":$('#sel2').val(),
                    "salCnt":$('#num').val(),
                    "stoId":$('#stoId').val(),
                    "sex":sex,
                    "age":$('#age').val()
                    	
                },
             success:function(data){//거래 성공시
                 
                   alert(data.result);
                    location.reload();
                   
             },
             error:function(xhr,status,error){//실패시 수행
                 console.log("error");
             }
             
         });
       
     
       });
});

function sub(){
 var x = saleForm.sel1.value;
 $.ajax({  
  type: "POST", 
  url: "category.jsp",  
  data: "category="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );

}

function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.saleForm.sel2;
$(".sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}

</script>

</head>
<body>
<center class="saleCenter">
<form name="saleForm" role="form" class="form-inline">
<div class="form-group">
 <select class="form-control" name="sel1" onchange="sub();">

  <option value="">상품군</option>
  <option value="라면">라면</option>
  <option value="과자">과자</option>
  <option value="음료">음료</option>
 </select>
 <span class="sp1">
  <select class="form-control" name="sel2" id="sel2">

  <option>상품선택</option>
  </select>
 </span>
  <select class="form-control" name="num" id="num">
  <option value="">수량</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  </select>
  <div class="col-lg-8">
			<label class="radio-inline">
				<input type="radio"
				       name="sex" id="sex_m" value="A1_01"/>남자
			</label>  
			<label class="radio-inline">
			<input type="radio"
				      name="sex" id="sex_f" value="A1_02"/>여자
			</label>                                                   
  </div>
  <select class="form-control" id="age" name="age">

  <option value="">연령</option>
  <option value="A2_01">10대</option>
  <option value="A2_02">20대</option>
  <option value="A2_03">30대</option>
  <option value="A2_04">40대</option>
  <option value="A2_05">50대</option>
  <option value="A2_06">60대~</option>
 </select>
   <input type="hidden" id="stoId" class="stoId" value="<%=(String)session.getAttribute("stoId")%>">
</div>

</form>
<center>
	<br>
	<input class="btn btn-primary" id="sale" type="button" value="판매"/>
</center>
</center>
</body>
</html>