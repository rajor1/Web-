<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>Ԥ������</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/SorderServlet?method=findAllSorder" method="post">
			<div align="center">
				<h1>
					Ԥ���б�
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							�û�����
							<input name="uname" id="uname" value="${ sorder.uname}">
							����
						<input name="sname" id="sname" value="${ sorder.sname}">
						<input type="hidden" name="uid" id="uid" value="${sorder.uid }">
						
							<input type="submit" value="��  ��" />
						</th>
						<c:if test="${sorder.uid==0}">
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SorderServlet?method=addSorder&addtype=add'" value="��  ��" />
					<input class="box" type="button" value="��  ��"
						onClick="javascript:history.go(-1);" />
						</th>
						</c:if>
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
							��Ա����
						</th>
						<th>
							��Ա����
						</th>

						<th>
							��������
						</th>

						<th>
							Ԥʹ������
						</th>
						
						<th>
							Ԥʹ��ʱ��
						</th>
						
						
						<th>
							��ϵ�绰
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
								${item.uname}
							</td>
							<td >
								${item.urealname}
							</td>
							<td >
								${item.sname}
							</td>
							
							<td>
								${item.odate}
							</td>
							<td>
								${item.otime}
							</td>
							<td>
								${item.otel}
							</td>
							<td align="center" >
							<c:if test="${item.ostart!=''}">�ѿ���</c:if>
							<c:if test="${item.ostart==''}">
							<c:if test="${sorder.uid!=0}">
								<a href="${base }/servlet/SorderServlet?method=updatePage&updatetype=updatemy&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">�޸�</a>|
								</c:if>
								<c:if test="${sorder.uid==0}">
								<a href="${base }/servlet/SorderServlet?method=updatePage&updatetype=update&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">�޸�</a>|
								</c:if>
								<a
									href="${base }/servlet/SorderServlet?method=deleteSorder&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">ɾ��</a>
									
									</c:if>
							</td>

						</tr>
					</c:forEach>
					
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
