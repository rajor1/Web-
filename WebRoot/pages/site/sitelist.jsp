<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" /><base href="${base }">

		<title>���ع���</title>
	</head>

	<body>
		<form action="${base }/servlet/SiteServlet?method=findAllSite" method="post">
			<div align="center">
				<h1>
					�����б�
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							���أ�
							<input name="sremarks" id="sremarks" value="${ site.sremarks}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SiteServlet?method=addSite'" value="��  ��" />
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
							����
						</th>
						<th>
							ͼƬ
						</th>
						<th>
							����
						</th>

						<th>
							����
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
								${item.sremarks}
							</td>
							<td >
								<img src="${base }/${item.simg}" width="100px" height="100px"/>
							</td>
							<td >
								${item.sprice}
							</td>
							
							
							<td >
								${item.sinfo}
							</td>
							
							<td align="center" >
								<a href="${base }/servlet/SiteServlet?method=updatePage&sid=${item.sid}">�޸�</a>|
								<a
									href="${base }/servlet/SiteServlet?method=deleteSite&sid=${item.sid}">ɾ��</a>
							</td>

						</tr>
					</c:forEach>
					
				</table>
   
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
