<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>����Ԥ��</title>
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
			alert("��ѡ���û�����");
			form.uname.focus();
			return false;
		}
		if(form.sid.value=="" || form.sid.value.length<=0){
			alert("��ѡ�񳡵أ�");
			form.sname.focus();
			return false;
		}
		
		if(form.otel.value=="" || form.otel.value.length<=0){
			alert("��������ϵ�绰��");
			form.otel.focus();
			return false;
		}
		if(form.odate.value=="" || form.odate.value.length<=0){
			alert("������Ԥ�����ڣ�");
			form.odate.focus();
			return false;
		}
		if(form.otime.value=="" || form.otime.value.length<=0){
			alert("������Ԥ�����ڣ�");
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
				����Ԥ��
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/SorderServlet?method=updateSorder" method="post"
				onsubmit="return checkForm()">
				<table border="1" class="table" align="center">
					
					<tr>
						
						<td>
							�û�����
						</td>
						<td >
							<input type="text" readonly="readonly" name="uname"  id="uname"  value="${sorder.uname }">
							<input type="hidden" name="uid" id="uid" value="${sorder.uid }">
							<input type="hidden" name="sid" id="sid" value="${sorder.sid }">
							<input type="hidden" name="oid" id="oid" value="${sorder.oid }">
							<input type="hidden" name="updatetype" id="updatetype" value="${updatetype }">
						</td>
						
					</tr>
					<tr>
						<td >
							������
						</td>
						<td >
								<input type="text" readonly="readonly" name="urealname"  id="urealname"  value="${sorder.urealname }">					
						</td>
					</tr>
					<tr>
						<td >
							�������ƣ�
						</td>
						<td>
								<input type="text" onclick="siteList()" name="sname" id="sname"  readonly="readonly"  value="${sorder.sname }">			
						</td>
					</tr>
					<tr>	
						<td>
							��ϵ�绰��
						</td>
						<td>
							<input type="text" id="otel" name="otel" value="${sorder.otel }" >	
							<span style="color: red;">*</span>
						</td>
					</tr>
					<tr>	
						<td>
							Ԥ�����ڣ�
						</td>
						<td>
								<input type="text" id="odate" size="10"
							name="odate" maxlength="20" onfocus="setday(this)"  value="${sorder.odate }" 
							readonly="readonly"  />
							<span style="color: red;">*</span>
						</td>
					</tr>
					<tr>	
						<td>
							Ԥ��ʱ�䣺
						</td>
						<td>
								<select id="otime" name="otime" >
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
								<script type="text/javascript">document.forms[0].otime='${sorder.otime}'</script>
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
