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
		<form action="${base }/servlet/SorderServlet?method=findAllSorderSign" method="post">
			<div align="center">
				<h1>
					�����б�
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
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SorderServlet?method=addSorder&addtype=sign'" value="��  ��" />
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
							��ʼʱ��
						</th>
						<th>
							��������
						</th>
						<th>
							������
						</th>
						<th>
							��������
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
							<td>
								${item.ostart}
							</td>
							<td>
								${item.spendtype}
							</td>
							<td>
							<c:if test="${item.spend!=0}">	${item.spend}</c:if>
							</td>
							
							<td>
								${item.spendtime}
							</td>
							<td align="center" >
							<c:if test="${item.ostart==''}"><a href="${base }/servlet/SorderServlet?method=updatePage&oid=${item.oid}&updatetype=sign">����</a></c:if>
							<c:if test="${item.ostart!=''&&item.spend==0}"><a href="${base }/servlet/SorderServlet?method=updatePage&oid=${item.oid}&updatetype=pay">����</a></c:if>
								
							</td>

						</tr>
					</c:forEach>
					
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
