<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>会员注册</title>
<link href="${base }/css/page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript">
	  var uname = [<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>'${item.uname}'</c:forEach>];
     var flag=0;
      function checkForm(){
         var form = document.forms[0];
	     if(form.uname.value=="" || form.uname.value.length<=0){
			alert("请输入用户名！");
			form.uname.focus();
			return false;
		}
		checkname();
		if(flag==1)
			{
				alert("用户名已存在！");
				form.uname.focus();
				return false;
			}
		if(form.urealname.value=="" || form.urealname.value.length<=0){
			alert("请输入姓名！");
			form.urealname.focus();
			return false;
		}
		if(form.upassword.value=="" || form.upassword.value.length<=0){
			alert("请输入密码！");
			form.upassword.focus();
			return false;
		}
		if(form.usex.value=="" || form.usex.value.length<=0){
			alert("请输入性别！");
			form.usex.focus();
			return false;
		}
		if(form.utel.value=="" || form.utel.value.length<=0){
			alert("请输入联系电话！");
			form.utel.focus();
			return false;
		}
		if(form.uaddress.value=="" || form.uaddress.value.length<=0){
			alert("请输入地址！");
			form.uaddress.focus();
			return false;
		}
		
		
		return true;
	}
	function checkname()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<uname.length;i++)
		{
			if(uname[i]==form.uname.value)
			{
				alert("用户名已存在！");
				form.uname.focus();
				flag=1;
				return false;
			}
			else
			{
				flag=0;
			}
		}
	}
</script>
	</head>

	<body >
		<div align="center">
			<h1>
				会员注册
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/UsersServlet?method=saveUsers" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							用户名：
						</td>
						<td>
							<input type="text" id="uname" name="uname" onblur="checkname()">
							<input type="hidden" id="addtype" name="addtype" value="<%=request.getParameter("addtype") %>">
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							姓名：
						</td>
						<td>
							<input type="text" id="urealname" name="urealname" >
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							密码：
						</td>
						<td>
							<input type="password" id="upassword" name="upassword" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
					<td>
							性别：
						</td>
						<td>
							<select id="usex" name="usex" >
							<option value="">--</option>
							<option value="男">男</option>
							<option value="女">女</option>
							</select>
							<span style="color: red;">*</span>					
						</td>
						</tr>
					<tr>
						<td>
							联系电话：
						</td>
						<td>
							<input type="text" id="utel" name="utel" >
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							地址：
						</td>
						<td>
							<input type="text" id="uaddress" name="uaddress" >
							<span style="color: red;">*</span>						
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
