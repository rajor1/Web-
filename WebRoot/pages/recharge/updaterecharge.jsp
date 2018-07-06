<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<%@ include file="/pages/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>会员充值维护</title>

		<script type="text/javascript" language="javascript">
	 
     
      function checkForm(){
         var form = document.forms[0];
         
		
	     if(form.rmoney.value=="" || form.rmoney.value.length<=0){
			alert("请输入金额！");
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
				会员充值
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/RechargeServlet?method=updateRecharge" method="post"
				onsubmit="return checkForm()">
				<table border="1" class="table" align="center">
					
					<tr>
						
						<td>
							用户名：
						</td>
						<td>
						${users.uname }
							<input type="hidden" name="rid" value="${recharge.rid }">	
							<input type="hidden" name="uid" value="${recharge.uid }">	
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
							充值金额：
						</td>
						<td>
							<input type="text" id="rmoney" name="rmoney" value="${recharge.rmoney }" >	
							<span style="color: red;">*</span>
						</td>
					</tr>
					
					
					<tr>
						<td >
							<input type="submit" value="提  交" />
							
							<input class="box" type="button" value="返  回"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
