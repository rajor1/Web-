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
    
    <title>修改密码</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
      function checkForm(){
         var form = document.forms[0];
         if(form.epasswordold.value=="" || form.epasswordold.value.length<=0){
			alert("请输入原始密码！");
			form.epasswordold.focus();
			return false;
		}
		if(form.epasswordc.value!=form.epasswordold.value){
			alert("原始密码不正确！");
			form.epasswordold.focus();
			return false;
		}
		if(form.epassword.value=="" || form.epassword.value.length<=0){
			alert("请输入新密码！");
			form.epassword.focus();
			return false;
		}
		if(form.epasswordagin.value=="" || form.epasswordagin.value.length<=0){
			alert("请输入确认密码！");
			form.epasswordagin.focus();
			return false;
		}
		if(form.epassword.value!=form.epasswordagin.value){
			alert("两次输入密码不一致！");
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
				修改密码
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/EmployeeServlet?method=updatePassword" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							编号：
						</td>
						<td >
							${employee.ecode }	
							<input type="hidden" name="eid" id="eid" value="${employee.eid }">				
						</td>
						
					</tr>
					<tr>
						
						<td>
							用户名：
						</td>
						<td>
							${employee.ename }					
						</td>
					</tr>
					<tr>
						<td>
							原始密码：
						</td>
						<td>
							<input type="password"  id="epasswordold" name="epasswordold" >
							<input type="hidden" id="epasswordc" name="epasswordc" value="${employee.epassword }">	
								<span style="color: red;">*</span>			
						</td>
					</tr>
					<tr>
						<td>
							姓名：
						</td>
						<td>
							${employee.erealname }					
						</td>
					</tr>
					<tr>
						<td>
							新密码：
						</td>
						<td>
							<input type="password"  id="epassword" name="epassword" >	
							<span style="color: red;">*</span>	
						</td>
						
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>	
							<input type="password" id="epasswordagin" name="epasswordagin" >	
						</td>
						
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="提交" />
							
							<input class="box" type="button" value="返  回"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
  </body>
</html>
