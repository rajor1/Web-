<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${base }">
    
    <title>�޸�����</title>
    <link href="${base }/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
      function checkForm(){
         var form = document.forms[0];
         if(form.upasswordold.value=="" || form.upasswordold.value.length<=0){
			alert("������ԭʼ���룡");
			form.upasswordold.focus();
			return false;
		}else
		if(form.upasswordc.value!=form.upasswordold.value){
			alert("ԭʼ���벻��ȷ��");
			form.upasswordold.focus();
			return false;
		}else
		if(form.upassword.value=="" || form.upassword.value.length<=0){
			alert("�����������룡");
			form.upassword.focus();
			return false;
		}else
		if(form.upasswordagin.value=="" || form.upasswordagin.value.length<=0){
			alert("������ȷ�����룡");
			form.upasswordagin.focus();
			return false;
		}else
		if(form.upassword.value!=form.upasswordagin.value){
			alert("�����������벻һ�£�");
			form.upasswordagin.focus();
			return false;
		}
		else
		{
		return true;
		}
	}
	
</script>
	</head>

	<body >
		<div align="center">
			<h1>
				�޸�����
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/UsersServlet?method=updatePassword" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						
						<td>
							�û�����
						</td>
						<td>
							${users.uname }	
							<input type="hidden" name="uid" id="uid" value="${users.uid }">		
										
						</td>
					</tr>
					
					<tr>
						
						<td>
							������
						</td>
						<td>
							${users.urealname }					
						</td>
					</tr>
					<tr>
						<td>
							ԭʼ���룺
						</td>
						<td>
							<input type="password"  id="upasswordold" name="upasswordold" >
							<input type="hidden" id="upasswordc" name="upasswordc" value="${users.upassword }">	
								<span style="color: red;">*</span>			
						</td>
					</tr>
					
					<tr>
						<td>
							�����룺
						</td>
						<td>
							<input type="password"  id="upassword" name="upassword" >	
							<span style="color: red;">*</span>	
						</td>
						
					</tr>
					<tr>
						<td>
							ȷ�����룺
						</td>
						<td>	
							<input type="password" id="upasswordagin" name="upasswordagin" >	
						</td>
						
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="�ύ" />
							
							<input class="box" type="button" value="��  ��"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
  </body>
</html>
