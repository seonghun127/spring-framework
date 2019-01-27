<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String category  =  request.getParameter("category");

out.println("<select class='form-control input-sm' name='sel2' id='sel2'>");
out.println("<label class='col-lg-4 control-label'>상품</label>");
if(category.equals("라면")){
 out.println("<option value='00001'>육개장사발면</option> ");
 out.println("<option value='00002'>튀김우동큰사발</option> ");
 out.println("<option value='00003'>안성탕면</option> ");
 out.println("<option value='00004'>너구리얼큰</option> ");
 out.println("<option value='00005'>얼큰한토마토라면</option> ");
 out.println("<option value='00006'>신라면블랙사발</option> ");
 out.println("<option value='00007'>감자탕큰사발</option> ");
 out.println("<option value='00008'>감자탕면</option> ");
 out.println("<option value='00009'>짜왕매운맛큰사발</option> ");
 out.println("<option value='00010'>신라면큰사발</option> ");
 out.println("<option value='00011'>왕뚜껑</option> ");
 out.println("<option value='00012'>김치도시락</option> ");
 out.println("<option value='00013'>불짬뽕컵</option> ");
 out.println("<option value='00014'>도시락</option> ");
 out.println("<option value='00015'>팔도짜장면</option> ");
 out.println("<option value='00016'>팔도비빔면컵</option> ");
 out.println("<option value='00017'>팔도짜장면컵</option> ");
 out.println("<option value='00018'>도시락라볶이</option> ");
 out.println("<option value='00019'>칼칼한깻잎라면</option> ");
 out.println("<option value='00020'>굴진짬뽕</option> ");
 out.println("<option value='00021'>리얼치즈라면컵</option> ");
 out.println("<option value='00022'>리얼치즈라면</option> ");
 out.println("<option value='00023'>오동통면컵</option> ");
 out.println("<option value='00024'>진짬뽕컵</option> ");
 out.println("<option value='00025'>진라면순한컵</option> ");
 out.println("<option value='00026'>진짬뽕</option> ");
}else if(category.equals("과자")){
 out.println("<option value='00027'>쫄병안성탕면맛</option> "); 
 out.println("<option value='00028'>프레첼솔티카라멜맛</option> ");
 out.println("<option value='00029'>포테토칩비프와사비</option> ");
 out.println("<option value='00030'>웰치스후르트젤리</option> ");
 out.println("<option value='00031'>포테토칩참치마요</option> ");
 out.println("<option value='00032'>새우깡1200</option> ");
 out.println("<option value='00033'>꿀꽈배기1400</option> ");
 out.println("<option value='00034'>닭다리너겟130g</option> ");
 out.println("<option value='00035'>매운새우깡1200</option> ");
 out.println("<option value='00036'>포스틱1400</option> ");
 out.println("<option value='00037'>양파링</option> "); 
 out.println("<option value='00038'>쫄병스낵매콤90g</option> ");
 out.println("<option value='00039'>오징어집1400</option> ");
 out.println("<option value='00040'>자갈치1400</option> ");
 out.println("<option value='00041'>바나나킥1400</option> ");
 out.println("<option value='00042'>닭다리후라이드1400</option> ");
 out.println("<option value='00043'>조청유과1400</option> ");
 out.println("<option value='00044'>알새우칩1400</option> ");
 out.println("<option value='00045'>포테토칩60g</option> ");
 out.println("<option value='00046'>츄파춥스xxl</option> ");
 out.println("<option value='00047'>감자깡1400</option> "); 
 out.println("<option value='00048'>초코칩초코파이</option> ");
 out.println("<option value='00049'>왕꿈틀이복숭아맛</option> ");
 out.println("<option value='00050'>감자산맥핫윙맛</option> ");
 out.println("<option value='00051'>감자산맥통후추맛</option> ");
 out.println("<option value='00052'>통크</option> ");
 out.println("<option value='00053'>미니다이제초코</option> ");
 out.println("<option value='00054'>포카칩붉닭맛</option> ");
 out.println("<option value='00055'>포카칩갈릭쉬림프</option> ");
 out.println("<option value='00056'>초코파이딸기12입</option> ");
}
else if(category.equals("음료")){
	 out.println("<option value='00110'>백산수P2L</option> "); 
	 out.println("<option value='00111'>웰치라임민트캔250ml</option> ");
	 out.println("<option value='00112'>트로피바나나망고250</option> ");
	 out.println("<option value='00113'>밀키스요하이워터P</option> ");

}
else {
	 out.println("<option value=''>상품선택</option> "); 
	}
out.println("</select>");

%>