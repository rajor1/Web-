<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">
		<script type="text/javascript">
		
			function search(){
			document.forms[0].submit();
		}
		function selectvalue(uid,uname,urealname){
		window.opener.document.getElementById('uid').value = uid;
		window.opener.document.getElementById('uname').value = uname;
		window.opener.document.getElementById('urealname').value = urealname;
		window.opener=null;
		window.open('','_self');
		window.close(); 
}
		</script>
		<title>��Ա����</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body >
		<form action="${base }/servlet/SorderServlet?method=findAllUsers" method="post">
			<div align="center">
				<h1>
					��Ա�б�
				</h1>


			</div>
			
			
				<div align="center" >
					<table border="1" >
					<tr >
						<th align="left" >
							��Ա����
							<input name="uname" id="uname" value="${ users.uname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						

					</tr>
					</table>
					
				</div>
			<div align="center" id="studentlist">
				<table border="1"  >
					<tr>
						<th align="center">
							���
						</th>
						<th>
							�û�
						</th>
						<th>
							����
						</th>
						<th>
							�Ա�
						</th>
						<th>
							����
						</th>
						<th>
							��ϵ�绰
						</th>
						<th>
							��ַ
						</th>
						<th>
							���
						</th>
						<th>
							����
						</th>

					</tr>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td width="100px;">
								${item.uname}
							</td>
							<td width="100px;">
								${item.urealname}
							</td>
							<td width="100px;">
								${item.usex}
							</td>
							<td>
								${item.upassword}
							</td>
							<td>
								${item.utel}
							</td>
							<td>
								${item.uaddress}
							</td>
							<td>
								���
							</td>
								<td align="center" width="100px;">
									<a href="javascript:selectvalue('${item.uid}','${item.uname}','${item.urealname}')">ѡ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
