<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.dao.RechargeDao"%>
<%@page import="com.dao.SpendDao"%>
<%@page import="com.bean.Recharge"%>
<%@page import="com.bean.Spend"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>会员充值明细</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/UsersServlet?method=findAllRecharge&uid=<%=request.getParameter("uid") %>" method="post">
			<div align="center">
				<h1>
					充值明细
				</h1>


			</div>
			
			
				
			<div align="center" id="studentlist">
				<table border="1"  >
					<tr>
						<th align="center">
							序号
						</th>
						
						<th>
							充值时间
						</th>
						<th>
							充值金额
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
							<td width="100px;">
								${item.rdate}
							</td>
							<td width="100px;">
								${item.rmoney}
							</td>
							
								<td align="center" width="100px;">
									<a
										href="${base }/servlet/RechargeServlet?method=updatePage&rid=${item.rid}">修改</a>|
									<a
										href="${base }/servlet/RechargeServlet?method=deleteRecharge&rid=${item.rid}&uid=<%=request.getParameter("uid") %>">删除</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
