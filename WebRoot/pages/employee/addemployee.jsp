<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>��Աע��</title>

		<script type="text/javascript" language="javascript">
	 
     
      function checkForm(){
         var form = document.forms[0];
         
		
	     if(form.ename.value=="" || form.ename.value.length<=0){
			alert("�������û�����");
			form.ename.focus();
			return false;
		}
		if(form.epassword.value=="" || form.epassword.value.length<=0){
			alert("���������룡");
			form.epassword.focus();
			return false;
		}
		 if(form.erealname.value=="" || form.erealname.value.length<=0){
			alert("������������");
			form.erealname.focus();
			return false;
		}
		if(form.esex.value=="" || form.esex.value.length<=0){
			alert("�������Ա�");
			form.esex.focus();
			return false;
		}
		if(form.etel.value=="" || form.etel.value.length<=0){
			alert("��������ϵ�绰��");
			form.etel.focus();
			return false;
		}
		if(form.eaddress.value=="" || form.eaddress.value.length<=0){
			alert("�������ַ��");
			form.eaddress.focus();
			return false;
		}
		
		return true;
	}
	
</script>
	</head>

	<body >
			<div align="center">
			<h1>
				��Աע��
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/EmployeeServlet?method=saveEmployee" method="post"
				onsubmit="return checkForm()">
				<table border="1" class="table" align="center">
					
					<tr>
						
						<td>
							�û�����
						</td>
						<td>
							<input type="text" id="ename" name="ename"  >	
							<span style="color: red;">*</span>					
						</td>
						<td>
							���룺
						</td>
						<td>
							<input type="password" id="epassword" name="epassword" >	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							������
						</td>
						<td>
							<input type="text" id="erealname" name="erealname" >	
							<span style="color: red;">*</span>					
						</td>
						
						<td>
							��ϵ�绰��
						</td>
						<td>
							<input type="text" id="etel" name="etel"  >	
							<span style="color: red;">*</span>
						</td>
					</tr>
					<tr>
						
						<td>
							��ַ��
						</td>
						<td colspan="3">
							<input type="text" id="eaddress" name="eaddress"  >	
							<span style="color: red;">*</span>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<input type="submit" value="��  ��" />
							
							<input class="box" type="button" value="��  ��"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
