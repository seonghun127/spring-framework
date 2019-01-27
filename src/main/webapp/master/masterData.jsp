<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript"
    src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>
$(document).ready(function(){
    $("#sexRank").on("click",function(){
    	$('#chart_div1').empty();
    	$('#chart_div2').empty();
    	$('#chart_div3').empty();
    	$('#chart_div4').empty();
    	$('#chart_div5').empty();
    	$('#chart_div6').empty();
    	var mpdtArray=new Array();
    	var wpdtArray=new Array();
    	var msalArray=new Array();
    	var wsalArray=new Array();
    	 $.ajax({
             type:"POST",             //get/post
             async: true,             //동기/비동기 
             url:"rank.do",         //action
             dataType:"JSON",         //html/Json
             data:{
                    div:"sex"
                    	
                },
             success:function(data){//거래 성공시
            	 
            	 $('#rankTable').empty();
            	 $('#rankTable').append("<thead><tr><th class='text-center'><font>성별</font></th><th class='text-center'><font>상품명</font></th><th class='text-center'><font>판매량</font></th><th class='text-center'><font>판매순위</font></th></tr></thead><tbody>");
            	 data.forEach( function( v, i ){
            		 if(v.sex=="남자"){
            			 msalArray.push(v.salCnt);
            			 mpdtArray.push(v.pdtNm);
            		 }
            		 if(v.sex=="여자"){
            			 wsalArray.push(v.salCnt);
            			 wpdtArray.push(v.pdtNm);
            		 }
            		 $('#rankTable').append("<tr> <td class='text-center'>"+v.sex+"</td> <td class='text-center'>"+v.pdtNm+"</td><td class='text-center'>"+v.salCnt+"</td><td class='text-center'>"+v.rank+"</td></tr>");
 
            		});
            	 $('#rankTable').append("</tbody>");
            	 google.charts.load('current', {
         	        packages : [ 'corechart', 'bar' ]
         	    });
         	    google.charts.setOnLoadCallback(drawMultSeries);
         	    google.charts.setOnLoadCallback(drawMultSeries2);
         	   
         	    function drawMultSeries() {
         	        
         	         var data = new google.visualization.DataTable();
         	         data.addColumn('string', '제품명');
         	         data.addColumn('number', '남자판매량');
         	        //alert('data생성');
         	        for (var i = 0; i < mpdtArray.length; i++) {	  
         	            var msalCnt = msalArray[i];
         	            var pdtNm=mpdtArray[i];
         	             data.addRows([
         	                            [pdtNm,msalCnt]
         	                        ]);
         	 
         	        }
         	        var options = {
         	            title : '남성 판매량 통계',
         	            is3D: true,
         	            'width':550,
                      'height':250
     
         	        };
 					
         	        var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
         	        $('#chart_div1').css('display','block');
         	        chart.draw(data, options);
         	      	
     				  
         	    }
         	   function drawMultSeries2() {
        	        
       	         var data = new google.visualization.DataTable();
       	         data.addColumn('string', '제품명');
       	         data.addColumn('number', '여자판매량');
       	        //alert('data생성');
       	        for (var i = 0; i < wpdtArray.length; i++) {	  
       	            var wsalCnt = wsalArray[i];
       	            var pdtNm = wpdtArray[i];
       	             data.addRows([
       	                            [pdtNm,wsalCnt]
       	                        ]);
       	 
       	        }
       	        var options = {
       	            title : '여성 판매량 통계',       	      
       	         	  is3D: true,
       	         	 'width':550,
                    'height':250
       	        };
					
       	        var chart =new google.visualization.PieChart(document.getElementById('chart_div2'));
       	        $('#chart_div2').css('display','block');
       	        chart.draw(data, options);
       	      	
   				  
       	    	}
                    
             },
             error:function(xhr,status,error){//실패시 수행
                 console.log("error");
             }
             
         });
    	  
     
       }); 
    $("#ageRank").on("click",function(){
    	$('#chart_div1').empty();
    	$('#chart_div2').empty();
    	$('#chart_div3').empty();
    	$('#chart_div4').empty();
    	$('#chart_div5').empty();
    	$('#chart_div6').empty();
    	var ApdtArray=new Array();
    	var BpdtArray=new Array();
    	var CpdtArray=new Array();
    	var DpdtArray=new Array();
    	var EpdtArray=new Array();
    	var FpdtArray=new Array();
    	var AsalArray=new Array();
    	var BsalArray=new Array();
    	var CsalArray=new Array();
    	var DsalArray=new Array();
    	var EsalArray=new Array();
    	var FsalArray=new Array();
    	 $.ajax({
             type:"POST",             //get/post
             async: true,             //동기/비동기 
             url:"rank.do",         //action
             dataType:"JSON",         //html/Json
             data:{
                    div:"age"
                    	
                },
             success:function(data){//거래 성공시
            	 $('#rankTable').empty();
            	 $('#rankTable').append("<thead><tr><th class='text-center'><font>연령</font></th><th class='text-center'><font>상품명</font></th><th class='text-center'><font>판매량</font></th><th class='text-center'><font>판매순위</font></th></tr></thead>");    
            	 data.forEach( function( v, i ){
            		 if(v.age=="10대"){
            			 AsalArray.push(v.salCnt);
            			 ApdtArray.push(v.pdtNm);
            		 }
            		 if(v.age=="20대"){
            			 BsalArray.push(v.salCnt);
            			 BpdtArray.push(v.pdtNm);
            		 }
            		 if(v.age=="30대"){
            			 CsalArray.push(v.salCnt);
            			 CpdtArray.push(v.pdtNm);
            		 }
            		 if(v.age=="40대"){
            			 DsalArray.push(v.salCnt);
            			 DpdtArray.push(v.pdtNm);
            		 }
            		 if(v.age=="50대"){
            			 EsalArray.push(v.salCnt);
            			 EpdtArray.push(v.pdtNm);
            		 }
            		 if(v.age=="60대이상"){
            			 FsalArray.push(v.salCnt);
            			 FpdtArray.push(v.pdtNm);
            			 
            		 }
            		 $('#rankTable').append("<tr> <td class='text-center'>"+v.age+"</td> <td class='text-center'>"+v.pdtNm+"</td><td class='text-center'>"+v.salCnt+"</td><td class='text-center'>"+v.rank+"</td></tr>");
 
            		});
            	 google.charts.load('current', {
         	        packages : [ 'corechart', 'bar' ]
         	    });
         	    google.charts.setOnLoadCallback(drawMultSeries);
         	    google.charts.setOnLoadCallback(drawMultSeries2);
         	    google.charts.setOnLoadCallback(drawMultSeries3);
         	    google.charts.setOnLoadCallback(drawMultSeries4);
            	 google.charts.setOnLoadCallback(drawMultSeries5);
          	 google.charts.setOnLoadCallback(drawMultSeries6);
         	    function drawMultSeries() {
         	        
         	         var data = new google.visualization.DataTable();
         	         data.addColumn('string', '제품명');
         	         data.addColumn('number', '판매량');
         	        //alert('data생성');
         	        for (var i = 0; i < ApdtArray.length; i++) {  	  
         	            var salCnt = AsalArray[i];
         	            var pdtNm=ApdtArray[i];
         	             data.addRows([
         	                            [pdtNm,salCnt]
         	                        ]);
         	 
         	        }
         	        var options = {
         	            title : '10대 판매량 통계',         	           
         	           'width':550,
                     'height':250,
         	            is3D: true
           	       
         	        };
 					
         	        var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
         	        $('#chart_div1').css('display','block');
         	        chart.draw(data, options);
         	      	
     				  
         	    }
         	   function drawMultSeries2() {
        	        
       	         var data = new google.visualization.DataTable();
       	         data.addColumn('string', '제품명');
       	         data.addColumn('number', '판매량');
       	        //alert('data생성');
       	        for (var i = 0; i < BpdtArray.length; i++) {  	  
       	            var salCnt = BsalArray[i];
       	            var pdtNm=BpdtArray[i];
       	             data.addRows([
       	                            [pdtNm,salCnt]
       	                        ]);
       	 
       	        }
       	        var options = {
       	            title : '20대 판매량 통계',         	           
       	         'width':550,
                 'height':250,
     	            is3D: true
       	        };
					
       	        var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
       	        $('#chart_div2').css('display','block');
       	        chart.draw(data, options);
       	      	
   				  
       	    }
         	  function drawMultSeries3() {
      	        
        	         var data = new google.visualization.DataTable();
        	         data.addColumn('string', '제품명');
        	         data.addColumn('number', '판매량');
        	        //alert('data생성');
        	        for (var i = 0; i < CpdtArray.length; i++) {  	  
        	            var salCnt = CsalArray[i];
        	            var pdtNm=CpdtArray[i];
        	             data.addRows([
        	                            [pdtNm,salCnt]
        	                        ]);
        	 
        	        }
        	        var options = {
        	            title : '30대 판매량 통계',         	           
        	            'width':550,
                        'height':250,
            	            is3D: true
        	        };
 					
        	        var chart = new google.visualization.PieChart(document.getElementById('chart_div3'));
        	        $('#chart_div3').css('display','block');
        	        chart.draw(data, options);
        	      	
    				  
        	    }
         	 function drawMultSeries4() {
     	        
       	         var data = new google.visualization.DataTable();
       	         data.addColumn('string', '제품명');
       	         data.addColumn('number', '판매량');
       	        //alert('data생성');
       	        for (var i = 0; i < DpdtArray.length; i++) {  	  
       	            var salCnt = DsalArray[i];
       	            var pdtNm=DpdtArray[i];
       	             data.addRows([
       	                            [pdtNm,salCnt]
       	                        ]);
       	 
       	        }
       	        var options = {
       	            title : '40대 판매량 통계',         	           
       	         'width':550,
                 'height':250,
     	            is3D: true
       	        };
					
       	     var chart = new google.visualization.PieChart(document.getElementById('chart_div4'));
 	        		$('#chart_div4').css('display','block');
       	        chart.draw(data, options);
       	      	
   				  
       	    }
         	function drawMultSeries5() {
    	        
      	         var data = new google.visualization.DataTable();
      	         data.addColumn('string', '제품명');
      	         data.addColumn('number', '판매량');
      	        //alert('data생성');
      	        for (var i = 0; i < EpdtArray.length; i++) {  	  
      	            var salCnt = EsalArray[i];
      	            var pdtNm=EpdtArray[i];
      	             data.addRows([
      	                            [pdtNm,salCnt]
      	                        ]);
      	 
      	        }
      	        var options = {
      	            title : '50대 판매량 통계',         	           
      	          'width':550,
                  'height':250,
      	            is3D: true
      	        };
					
      	      var chart = new google.visualization.PieChart(document.getElementById('chart_div5'));
  	        $('#chart_div5').css('display','block');
      	        chart.draw(data, options);
      	      	
  				  
      	    }
         	function drawMultSeries6() {
    	        
      	         var data = new google.visualization.DataTable();
      	         data.addColumn('string', '제품명');
      	         data.addColumn('number', '판매량');
      	        //alert('data생성');
      	        for (var i = 0; i < FpdtArray.length; i++) {  	  
      	            var salCnt = FsalArray[i];
      	            var pdtNm=FpdtArray[i];
      	             data.addRows([
      	                            [pdtNm,salCnt]
      	                        ]);
      	 
      	        }
      	        var options = {
      	            title : '60대이상 판매량 통계',         	           
      	          'width':550,
                  'height':250,
      	            is3D: true
      	        };
					
      	      var chart = new google.visualization.PieChart(document.getElementById('chart_div6'));
  	        $('#chart_div6').css('display','block');
      	        chart.draw(data, options);
      	      	
  				  
      	    }
                    
             },
             error:function(xhr,status,error){//실패시 수행
                 console.log("error");
             }
             
         });
    	  
     
       });
    $("#timeRank").on("click",function(){
    	$('#chart_div1').empty();
    	$('#chart_div2').empty();
    	$('#chart_div3').empty();
    	$('#chart_div4').empty();
    	$('#chart_div5').empty();
    	$('#chart_div6').empty();
    	var ApdtArray=new Array();
    	var BpdtArray=new Array();
    	var CpdtArray=new Array();
    	var DpdtArray=new Array();
    	var AsalArray=new Array();
    	var BsalArray=new Array();
    	var CsalArray=new Array();
    	var DsalArray=new Array();
    
    	 $.ajax({
             type:"POST",             //get/post
             async: true,             //동기/비동기 
             url:"rank.do",         //action
             dataType:"JSON",         //html/Json
             data:{
                    div:"time"
                    	
                },
             success:function(data){//거래 성공시
            	 $('#rankTable').empty();	
            	 $('#rankTable').append("<thead><tr><th class='text-center'><font>시간</font></th><th class='text-center'><font>상품명</font></th><th class='text-center'><font>판매량</font></th><th class='text-center'><font>판매순위</font></th></tr></thead>");           	
            	 data.forEach( function( v, i ){
            		 if(v.time=="오전"){
            			 AsalArray.push(v.salCnt);
            			 ApdtArray.push(v.pdtNm);
            		 }
            		 if(v.time=="오후"){
            			 BsalArray.push(v.salCnt);
            			 BpdtArray.push(v.pdtNm);
            		 }
            		 if(v.time=="저녁"){
            			 CsalArray.push(v.salCnt);
            			 CpdtArray.push(v.pdtNm);
            		 }
            		 if(v.time=="새벽"){
            			 DsalArray.push(v.salCnt);
            			 DpdtArray.push(v.pdtNm);
            		 }
            		 $('#rankTable').append("<tr> <td class='text-center'>"+v.time+"</td> <td class='text-center'>"+v.pdtNm+"</td><td class='text-center'>"+v.salCnt+"</td><td class='text-center'>"+v.rank+"</td></tr>");
 
            		});
            	 google.charts.load('current', {
         	        packages : [ 'corechart' ]
         	    });
         	    google.charts.setOnLoadCallback(drawMultSeries);
         	    google.charts.setOnLoadCallback(drawMultSeries2);
         	    google.charts.setOnLoadCallback(drawMultSeries3);
         	    google.charts.setOnLoadCallback(drawMultSeries4);
         	    function drawMultSeries() {
         	        
         	         var data = new google.visualization.DataTable();
         	         data.addColumn('string', '제품명');
         	         data.addColumn('number', '판매량');
         	        //alert('data생성');
         	        for (var i = 0; i < ApdtArray.length; i++) {  	  
         	            var salCnt = AsalArray[i];
         	            var pdtNm=ApdtArray[i];
         	             data.addRows([
         	                            [pdtNm,salCnt]
         	                        ]);
         	 
         	        }
         	        var options = {
         	            title : '오전 판매량 통계',         	                    	       
         	           'width':550,
                       'height':250,
           	            is3D: true
           	       
         	        };
 					
         	        var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
         	        $('#chart_div1').css('display','block');
         	        chart.draw(data, options);
         	      	
     				  
         	    }
         	   function drawMultSeries2() {
        	        
       	         var data = new google.visualization.DataTable();
       	         data.addColumn('string', '제품명');
       	         data.addColumn('number', '판매량');
       	        //alert('data생성');
       	        for (var i = 0; i < BpdtArray.length; i++) {  	  
       	            var salCnt = BsalArray[i];
       	            var pdtNm=BpdtArray[i];
       	             data.addRows([
       	                            [pdtNm,salCnt]
       	                        ]);
       	 
       	        }
       	        var options = {
       	            title : '오후 판매량 통계',         	           
       	         'width':550,
                 'height':250,
     	            is3D: true
       	        };
					
       	        var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
       	        $('#chart_div2').css('display','block');
       	        chart.draw(data, options);
       	      	
   				  
       	    }
         	  function drawMultSeries3() {
      	        
        	         var data = new google.visualization.DataTable();
        	         data.addColumn('string', '제품명');
        	         data.addColumn('number', '판매량');
        	        //alert('data생성');
        	        for (var i = 0; i < CpdtArray.length; i++) {  	  
        	            var salCnt = CsalArray[i];
        	            var pdtNm=CpdtArray[i];
        	             data.addRows([
        	                            [pdtNm,salCnt]
        	                        ]);
        	 
        	        }
        	        var options = {
        	            title : '저녁 판매량 통계',         	           
        	            'width':550,
                        'height':250,
            	            is3D: true
 					};
        	        var chart = new google.visualization.PieChart(document.getElementById('chart_div3'));
        	        $('#chart_div3').css('display','block');
        	        chart.draw(data, options);
        	      	
    				  
        	    }
         	 function drawMultSeries4() {
     	        
       	         var data = new google.visualization.DataTable();
       	         data.addColumn('string', '제품명');
       	         data.addColumn('number', '판매량');
       	        //alert('data생성');
       	        for (var i = 0; i < DpdtArray.length; i++) {  	  
       	            var salCnt = DsalArray[i];
       	            var pdtNm=DpdtArray[i];
       	             data.addRows([
       	                            [pdtNm,salCnt]
       	                        ]);
       	 
       	        }
       	        var options = {
       	             title : '새벽 판매량 통계',         	           
       	         		'width':550,
                 		'height':250,
     	            		is3D: true
       	        };	
					
       	     var chart = new google.visualization.PieChart(document.getElementById('chart_div4'));
 	        		$('#chart_div4').css('display','block');
       	        chart.draw(data, options);
       	      	
   				  
       	    }
                    
             },
             error:function(xhr,status,error){//실패시 수행
                 console.log("error");
             }
             
         });
    	  
     
       }); 
});
</script>
</head>
<body>
<h3>마스터데이터</h3>
<div class="container">
<input class="btn btn-success" id="sexRank" type="button" value="성별 판매 순위">
<input class="btn btn-success" id="ageRank"  type="button" value="연령별 판매 순위">
<input class="btn btn-success" id="timeRank"  type="button" value="시간별 판매 순위">
<table id="chartTable">
<tr>
<td><div id="chart_div1"style="width: 600px;display: none;"></div></td>
<td><div id="chart_div2"style="width: 600px;display: none;"></div></td>
</tr>
<tr>
<td><div id="chart_div3"style="width: 600px;display: none;"></div></td>
<td><div id="chart_div4"style="width: 600px;display: none;"></div></td>
</tr>
<tr>
<td><div id="chart_div5"style="width: 600px;display: none;"></div></td>
<td><div id="chart_div6"style="width: 600px;display: none;"></div></td>
</tr>
</table>
<table class="table table-striped table-bordered" id="rankTable">   
</table>
</div>
</body>
</html>