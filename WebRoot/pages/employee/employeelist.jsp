<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>����Ա����</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/EmployeeServlet?method=findAllEmployee" method="post">
			<div align="center">
				<h1>
					����Ա�б�
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							�û�����
							<input name="ename" id="ename" value="${ employee.ename}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/EmployeeServlet?method=addEmployee'" value="��  ��" />
					<input class="box" type="button" value="��  ��"
						onClick="javascript:history.go(-1);" />
						</th>

					</tr>
					</table>
			</div>
			
			<div align="center" id="studentlist">
				<table border="1">
					<tr>
						<th align="center">
							���
						</th>
						
						<th>
							�û���
						</th>
						<th>
							����
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

							����
						</th>

					</tr>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td >
								${item.ename}
							</td>
							<td >
								${item.erealname}
							</td>
							<td >
								${item.epassword}
							</td>
							
							<td>
								${item.etel}
							</td>
							<td>
								${item.eaddress}
							</td>
							
							<td align="center" >
								<a href="${base }/servlet/EmployeeServlet?method=updatePage&eid=${item.eid}">�޸�</a>|
								<a
									href="${base }/servlet/EmployeeServlet?method=deleteEmployee&eid=${item.eid}">ɾ��</a>
							</td>

						</tr>
					</c:forEach>
					
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
