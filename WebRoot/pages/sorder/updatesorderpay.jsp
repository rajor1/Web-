<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.bean.Sorder"%>
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
         
		
		if(!checkramaining())
		return false;
		return true;
	}
	
	function  setspend(){
	
		var sprice ='${site.sprice}';
		var stime = '${sorder.ostart}';
		var etime = document.forms[0].oetime.value;
		var stimesplit=stime.split(":");
		if(stimesplit[1]=='30')
		stime=stimesplit[0]+".5";
		else
		stime=stimesplit[0];
		var etimesplit=etime.split(":");
		if(etimesplit[1]=='30')
		etime=etimesplit[0]+".5";
		else
		etime=etimesplit[0];
		 document.forms[0].spend.value=sprice*(etime-stime);
	}
	function  checkramaining(){
	var spendtype = document.forms[0].spendtype.value;
		var ramaining ='${susers.remaining}';
		//alert(ramaining);
		var spend = document.forms[0].spend.value;
		if(parseInt(spend)>parseInt(ramaining)&&spendtype=='账户余额')
		{
		alert("余额不足！");
		return false;
		}
		else
		return true;
		
	}
</script>
	</head>

	<body onload="setspend()">
			<div align="center">
			<h1>
				结账
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
						<td >
							价格：
						</td>
						<td>
								${site.sprice }		
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
							<input type="hidden" name="ostart" id="ostart" value="${sorder.ostart}">
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
							开始时间：
						</td>
						<td>
								${sorder.ostart}
						</td>
					</tr>
					<tr>	
						<td>
							账户余额：
						</td>
						<td>
							${susers.remaining }
						</td>
					</tr>
					<tr>	
						<td>
							结束时间：
						</td>
						<td>
								<select id="oetime" name="oetime" onchange="setspend()">
								<%String time ="";
								String  hh ="";
								Sorder sorder = (Sorder)request.getAttribute("sorder");
								String stime[]=sorder.getOstart().split(":");
								int h=Integer.parseInt(stime[0])+1;
								
								if(stime[1].equals("00"))
								{
								%>
								<option value="<%=stime[0]+":30" %>"><%=stime[0]+":30" %></option>
								<%
								}
								  for(int i=h;i<22;i++)
								  {
								  hh=String.format(i+"","%2d");
								 %>
								<option value="<%=hh+":00" %>"><%=hh+":00" %></option>
								<option value="<%=hh+":30" %>"><%=hh+":30" %></option>
								<%} %>
								</select>
						</td>
					</tr>
					<tr>	
						<td>
							付款类型：
						</td>
						<td>
								<select id="spendtype" name="spendtype" onclick="checkramaining()">
								
								<option value="现金">现金</option>
								<option value="账户余额">账户余额</option>
								
								</select>
								
							<span style="color: red;">*</span>
						</td>
					</tr>
					<tr>	
						<td>
							付款金额：
						</td>
						<td>
								<input type="text" name="spend" id="spend">
								
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
