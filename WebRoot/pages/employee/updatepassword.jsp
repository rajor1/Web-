<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>�޸�����</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
      function checkForm(){
         var form = document.forms[0];
         if(form.epasswordold.value=="" || form.epasswordold.value.length<=0){
			alert("������ԭʼ���룡");
			form.epasswordold.focus();
			return false;
		}
		if(form.epasswordc.value!=form.epasswordold.value){
			alert("ԭʼ���벻��ȷ��");
			form.epasswordold.focus();
			return false;
		}
		if(form.epassword.value=="" || form.epassword.value.length<=0){
			alert("�����������룡");
			form.epassword.focus();
			return false;
		}
		if(form.epasswordagin.value=="" || form.epasswordagin.value.length<=0){
			alert("������ȷ�����룡");
			form.epasswordagin.focus();
			return false;
		}
		if(form.epassword.value!=form.epasswordagin.value){
			alert("�����������벻һ�£�");
			form.epasswordagin.focus();
			return false;
		}
		
		return true;
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
			<form action="${base }/servlet/EmployeeServlet?method=updatePassword" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							��ţ�
						</td>
						<td >
							${employee.ecode }	
							<input type="hidden" name="eid" id="eid" value="${employee.eid }">				
						</td>
						
					</tr>
					<tr>
						
						<td>
							�û�����
						</td>
						<td>
							${employee.ename }					
						</td>
					</tr>
					<tr>
						<td>
							ԭʼ���룺
						</td>
						<td>
							<input type="password"  id="epasswordold" name="epasswordold" >
							<input type="hidden" id="epasswordc" name="epasswordc" value="${employee.epassword }">	
								<span style="color: red;">*</span>			
						</td>
					</tr>
					<tr>
						<td>
							������
						</td>
						<td>
							${employee.erealname }					
						</td>
					</tr>
					<tr>
						<td>
							�����룺
						</td>
						<td>
							<input type="password"  id="epassword" name="epassword" >	
							<span style="color: red;">*</span>	
						</td>
						
					</tr>
					<tr>
						<td>
							ȷ�����룺
						</td>
						<td>	
							<input type="password" id="epasswordagin" name="epasswordagin" >	
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
