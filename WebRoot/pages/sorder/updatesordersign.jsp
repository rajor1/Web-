<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>场地预定</title>
		<script type='text/javascript' src='${base }/js/meizzDate.js'></script>
		<script type="text/javascript" language="javascript">
	 
     function usersList(){
	
	 window.open("${base }/servlet/SorderServlet?method=findAllUsers","addStu",
"resizable=true,top=250," +"left=450,width=700,height=450,toolbar=no,menubar=no,scrollbars=no");
	}
	
	function siteList(){
	 window.open("${base }/servlet/SorderServlet?method=findAllSite","addStu",
"resizable=true,top=250," +"left=450,width=700,height=450,toolbar=no,menubar=no,scrollbars=no");
	}
      function checkForm(){
         var form = document.forms[0];
         
		
	     if(form.uid.value=="" || form.uid.value.length<=0){
			alert("请选择用户名！");
			form.uname.focus();
			return false;
		}
		if(form.sid.value=="" || form.sid.value.length<=0){
			alert("请选择场地！");
			form.sname.focus();
			return false;
		}
		
		if(form.otel.value=="" || form.otel.value.length<=0){
			alert("请输入联系电话！");
			form.otel.focus();
			return false;
		}
		if(form.odate.value=="" || form.odate.value.length<=0){
			alert("请输入预定日期！");
			form.odate.focus();
			return false;
		}
		if(form.otime.value=="" || form.otime.value.length<=0){
			alert("请输入预定日期！");
			form.otime.focus();
			return false;
		}
		return true;
	}
	
	
</script>
	</head>

	<body >
			<div align="center">
			<h1>
				场地预定
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/SorderServlet?method=updateSorder" method="post"
				onsubmit="return checkForm()">
				<table border="1" class="table" align="center">
					
					<tr>
						
						<td>
							用户名：
						</td>
						<td >
							${sorder.uname }
							<input type="hidden" name="uid" id="uid" value="${sorder.uid }">
							<input type="hidden" name="sid" id="sid" value="${sorder.sid }">
							<input type="hidden" name="oid" id="oid" value="${sorder.oid }">
							<input type="hidden" name="updatetype" id="updatetype" value="${updatetype }">
						</td>
						
					</tr>
					<tr>
						<td >
							姓名：
						</td>
						<td >
								${sorder.urealname }				
						</td>
					</tr>
					<tr>
						<td >
							场地名称：
						</td>
						<td>
								${sorder.sname }		
						</td>
					</tr>
					<tr>	
						<td>
							联系电话：
						</td>
						<td>
							${sorder.otel }
							<input type="hidden" name="otel" id="otel" value="${sorder.otel }">
							<input type="hidden" name="odate" id="odate" value="${sorder.odate }">
							<input type="hidden" name="otime" id="otime" value="${sorder.otime}">

						</td>
					</tr>
					<tr>	
						<td>
							预定日期：
						</td>
						<td>
							${sorder.odate }
						</td>
					</tr>
					<tr>	
						<td>
							预定时间：
						</td>
						<td>
								${sorder.otime}
						</td>
					</tr>
					<tr>	
						<td>
							开单时间：
						</td>
						<td>
								<select id="ostart" name="ostart" >
								<%String time ="";
								String  hh ="";
								  for(int i=7;i<22;i++)
								  {
								  hh=String.format(i+"","%2d");
								 %>
								<option value="<%=hh+":00" %>"><%=hh+":00" %></option>
								<option value="<%=hh+":30" %>"><%=hh+":30" %></option>
								<%} %>
								</select>
							<span style="color: red;">*</span>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
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
