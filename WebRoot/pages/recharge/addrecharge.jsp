<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<%@ include file="/pages/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>��Ա��ֵ</title>

		<script type="text/javascript" language="javascript">
	 
     
      function checkForm(){
         var form = document.forms[0];
         
		
	     if(form.rmoney.value=="" || form.rmoney.value.length<=0){
			alert("�������");
			form.rmoney.focus();
			return false;
		}
		
		
		return true;
	}
	
</script>
	</head>

	<body >
			<div align="center">
			<h1> 
				��Ա��ֵ 
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/RechargeServlet?method=saveRecharge" method="post"
				onsubmit="return checkForm()">
				<table border="1" class="table" align="center">
					
					<tr>
						
						<td>
							�û�����
						</td>
						<td>
							${ruser.uname }
							<input type="hidden" name="uid" value="${ruser.uid }">	
						</td>
						</tr>
					<tr>
						<td>
							������
						</td>
						<td>
							${ruser.urealname }				
						</td>
					</tr>
					<tr>
						
						
						<td>
							��ֵ��
						</td>
						<td>
							<input type="text" id="rmoney" name="rmoney"  >	
							<span style="color: red;">*</span>
						</td>
					</tr>
					
					
					<tr>
						<td >
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
