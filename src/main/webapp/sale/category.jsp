<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String category  =  request.getParameter("category");

out.println("<select class='form-control input-sm' name='sel2' id='sel2'>");
out.println("<label class='col-lg-4 control-label'>��ǰ</label>");
if(category.equals("���")){
 out.println("<option value='00001'>�������߸�</option> ");
 out.println("<option value='00002'>Ƣ��쵿ū���</option> ");
 out.println("<option value='00003'>�ȼ�����</option> ");
 out.println("<option value='00004'>�ʱ�����ū</option> ");
 out.println("<option value='00005'>��ū���丶����</option> ");
 out.println("<option value='00006'>�Ŷ������</option> ");
 out.println("<option value='00007'>������ū���</option> ");
 out.println("<option value='00008'>��������</option> ");
 out.println("<option value='00009'>¥�ոſ��ū���</option> ");
 out.println("<option value='00010'>�Ŷ��ū���</option> ");
 out.println("<option value='00011'>�նѲ�</option> ");
 out.println("<option value='00012'>��ġ���ö�</option> ");
 out.println("<option value='00013'>��«����</option> ");
 out.println("<option value='00014'>���ö�</option> ");
 out.println("<option value='00015'>�ȵ�¥���</option> ");
 out.println("<option value='00016'>�ȵ��������</option> ");
 out.println("<option value='00017'>�ȵ�¥�����</option> ");
 out.println("<option value='00018'>���ö�����</option> ");
 out.println("<option value='00019'>ĮĮ�Ѳ��ٶ��</option> ");
 out.println("<option value='00020'>����«��</option> ");
 out.println("<option value='00021'>����ġ������</option> ");
 out.println("<option value='00022'>����ġ����</option> ");
 out.println("<option value='00023'>���������</option> ");
 out.println("<option value='00024'>��«����</option> ");
 out.println("<option value='00025'>����������</option> ");
 out.println("<option value='00026'>��«��</option> ");
}else if(category.equals("����")){
 out.println("<option value='00027'>�̺��ȼ������</option> "); 
 out.println("<option value='00028'>����ÿ��Ƽī����</option> ");
 out.println("<option value='00029'>������Ĩ�����ͻ��</option> ");
 out.println("<option value='00030'>��ġ���ĸ�Ʈ����</option> ");
 out.println("<option value='00031'>������Ĩ��ġ����</option> ");
 out.println("<option value='00032'>�����1200</option> ");
 out.println("<option value='00033'>�ܲʹ��1400</option> ");
 out.println("<option value='00034'>�ߴٸ��ʰ�130g</option> ");
 out.println("<option value='00035'>�ſ�����1200</option> ");
 out.println("<option value='00036'>����ƽ1400</option> ");
 out.println("<option value='00037'>���ĸ�</option> "); 
 out.println("<option value='00038'>�̺���������90g</option> ");
 out.println("<option value='00039'>��¡����1400</option> ");
 out.println("<option value='00040'>�ڰ�ġ1400</option> ");
 out.println("<option value='00041'>�ٳ���ű1400</option> ");
 out.println("<option value='00042'>�ߴٸ��Ķ��̵�1400</option> ");
 out.println("<option value='00043'>��û����1400</option> ");
 out.println("<option value='00044'>�˻���Ĩ1400</option> ");
 out.println("<option value='00045'>������Ĩ60g</option> ");
 out.println("<option value='00046'>�����佺xxl</option> ");
 out.println("<option value='00047'>���ڱ�1400</option> "); 
 out.println("<option value='00048'>����Ĩ��������</option> ");
 out.println("<option value='00049'>�ղ�Ʋ�̺����Ƹ�</option> ");
 out.println("<option value='00050'>���ڻ��������</option> ");
 out.println("<option value='00051'>���ڻ�������߸�</option> ");
 out.println("<option value='00052'>��ũ</option> ");
 out.println("<option value='00053'>�̴ϴ���������</option> ");
 out.println("<option value='00054'>��īĨ�Ӵ߸�</option> ");
 out.println("<option value='00055'>��īĨ����������</option> ");
 out.println("<option value='00056'>�������̵���12��</option> ");
}
else if(category.equals("����")){
	 out.println("<option value='00110'>����P2L</option> "); 
	 out.println("<option value='00111'>��ġ���ӹ�Ʈĵ250ml</option> ");
	 out.println("<option value='00112'>Ʈ���ǹٳ�������250</option> ");
	 out.println("<option value='00113'>��Ű�������̿���P</option> ");

}
else {
	 out.println("<option value=''>��ǰ����</option> "); 
	}
out.println("</select>");

%>