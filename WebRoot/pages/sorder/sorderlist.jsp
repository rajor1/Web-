<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>预定管理</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/SorderServlet?method=findAllSorder" method="post">
			<div align="center">
				<h1>
					预定列表
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							用户名：
							<input name="uname" id="uname" value="${ sorder.uname}">
							场地
						<input name="sname" id="sname" value="${ sorder.sname}">
						<input type="hidden" name="uid" id="uid" value="${sorder.uid }">
						
							<input type="submit" value="搜  索" />
						</th>
						<c:if test="${sorder.uid==0}">
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SorderServlet?method=addSorder&addtype=add'" value="添  加" />
					<input class="box" type="button" value="返  回"
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
							序号
						</th>
						
						<th>
							会员名称
						</th>
						<th>
							会员姓名
						</th>

						<th>
							场地名称
						</th>

						<th>
							预使用日期
						</th>
						
						<th>
							预使用时间
						</th>
						
						
						<th>
							联系电话
						</th>
						
						


						<th>

							操作
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
							<c:if test="${item.ostart!=''}">已开单</c:if>
							<c:if test="${item.ostart==''}">
							<c:if test="${sorder.uid!=0}">
								<a href="${base }/servlet/SorderServlet?method=updatePage&updatetype=updatemy&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">修改</a>|
								</c:if>
								<c:if test="${sorder.uid==0}">
								<a href="${base }/servlet/SorderServlet?method=updatePage&updatetype=update&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">修改</a>|
								</c:if>
								<a
									href="${base }/servlet/SorderServlet?method=deleteSorder&sname=${sorder.sname }&uname=${sorder.uname }&uid=${sorder.uid }&oid=${item.oid}">删除</a>
									
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
