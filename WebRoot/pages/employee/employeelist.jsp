<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>管理员管理</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/EmployeeServlet?method=findAllEmployee" method="post">
			<div align="center">
				<h1>
					管理员列表
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							用户名：
							<input name="ename" id="ename" value="${ employee.ename}">
						
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/EmployeeServlet?method=addEmployee'" value="添  加" />
					<input class="box" type="button" value="返  回"
						onClick="javascript:history.go(-1);" />
						</th>

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
							用户名
						</th>
						<th>
							姓名
						</th>

						<th>
							密码
						</th>

						<th>
							联系电话
						</th>

						<th>
							地址
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
								<a href="${base }/servlet/EmployeeServlet?method=updatePage&eid=${item.eid}">修改</a>|
								<a
									href="${base }/servlet/EmployeeServlet?method=deleteEmployee&eid=${item.eid}">删除</a>
							</td>

						</tr>
					</c:forEach>
					
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
