<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>��Ա����</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/UsersServlet?method=findAllUsers" method="post">
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
							<input name="uname" id="uname" value="${ stu.uname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/UsersServlet?method=addUsers'" value="��  ��" />
					<input class="box" type="button" value="��  ��"
						onClick="javascript:history.go(-1);" />
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
								${item.remaining}
							</td>
								<td align="center" width="100px;">
									<a
										href="${base }/servlet/UsersServlet?method=updatePage&uid=${item.uid}">�޸�</a>|
									<a
										href="${base }/servlet/UsersServlet?method=deleteUsers&uid=${item.uid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
