<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${base }">
    
    <title>修改密码</title>
    <link href="${base }/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
      function checkForm(){
         var form = document.forms[0];
         if(form.upasswordold.value=="" || form.upasswordold.value.length<=0){
			alert("请输入原始密码！");
			form.upasswordold.focus();
			return false;
		}else
		if(form.upasswordc.value!=form.upasswordold.value){
			alert("原始密码不正确！");
			form.upasswordold.focus();
			return false;
		}else
		if(form.upassword.value=="" || form.upassword.value.length<=0){
			alert("请输入新密码！");
			form.upassword.focus();
			return false;
		}else
		if(form.upasswordagin.value=="" || form.upasswordagin.value.length<=0){
			alert("请输入确认密码！");
			form.upasswordagin.focus();
			return false;
		}else
		if(form.upassword.value!=form.upasswordagin.value){
			alert("两次输入密码不一致！");
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
				修改密码
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/UsersServlet?method=updatePassword" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						
						<td>
							用户名：
						</td>
						<td>
							${users.uname }	
							<input type="hidden" name="uid" id="uid" value="${users.uid }">		
										
						</td>
					</tr>
					
					<tr>
						
						<td>
							姓名：
						</td>
						<td>
							${users.urealname }					
						</td>
					</tr>
					<tr>
						<td>
							原始密码：
						</td>
						<td>
							<input type="password"  id="upasswordold" name="upasswordold" >
							<input type="hidden" id="upasswordc" name="upasswordc" value="${users.upassword }">	
								<span style="color: red;">*</span>			
						</td>
					</tr>
					
					<tr>
						<td>
							新密码：
						</td>
						<td>
							<input type="password"  id="upassword" name="upassword" >	
							<span style="color: red;">*</span>	
						</td>
						
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>	
							<input type="password" id="upasswordagin" name="upasswordagin" >	
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
