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
    
    <title>�޸Ļ�Ա</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
	  var uname =[<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>{'uid':'${item.uid}','uname':'${item.uname}'}</c:forEach>];
       function checkForm(){
         var form = document.forms[0];
	     if(form.uname.value=="" || form.uname.value.length<=0){
			alert("�������û�����");
			form.uname.focus();
			return false;
		}
		checkname();
		if(flag==1)
			{
				alert("�û����Ѵ��ڣ�");
				form.uname.focus();
				return false;
			}
		if(form.urealname.value=="" || form.urealname.value.length<=0){
			alert("������������");
			form.urealname.focus();
			return false;
		}
		if(form.upassword.value=="" || form.upassword.value.length<=0){
			alert("���������룡");
			form.upassword.focus();
			return false;
		}
		if(form.usex.value=="" || form.usex.value.length<=0){
			alert("�������Ա�");
			form.usex.focus();
			return false;
		}
		if(form.utel.value=="" || form.utel.value.length<=0){
			alert("��������ϵ�绰��");
			form.utel.focus();
			return false;
		}
		if(form.uaddress.value=="" || form.uaddress.value.length<=0){
			alert("�������ַ��");
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
			if(uname[i].uname==form.uname.value&&form.uid.value!=uname[i].uid)
			{
				alert("�û����Ѵ��ڣ�");
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
				�޸Ļ�Ա
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/UsersServlet?method=updateUsers" method="post"
				onsubmit="return checkForm()">
				<table border="1">
				<tr>
						<td>
							�û�����
						</td>
						<td>
							<input type="text" id="uname" name="uname" value="${users.uname }" onblur="checkname()">	
							<input id="uid" name="uid" value="${users.uid }" type="hidden">
							<input id="updatetype" name="updatetype" value="<%=request.getParameter("updatetype") %>" type="hidden">
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							������
						</td>
						<td>
							<input type="text" id="urealname" name="urealname" value="${users.urealname }">
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							���룺
						</td>
						<td>
							<input type="password" id="upassword" name="upassword" value="${users.upassword }">	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							�Ա�
						</td>
						<td>
							<select id="usex" name="usex" >
							<option value="">--</option>
							<option value="��">��</option>
							<option value="Ů">Ů</option>
							</select>
							<script type="text/javascript">
							document.forms[0].usex.value='${users.usex}';
							</script>
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							��ϵ�绰��
						</td>
						<td>
							<input type="text" id="utel" name="utel"  value="${users.utel }">
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							��ַ��
						</td>
						<td>
							<input type="text" id="uaddress" name="uaddress"  value="${users.uaddress }">
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							�˻���
						</td>
						<td>
							${users.remaining }
							<span style="color: red;">*</span>						
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
