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

		<title>会员充值</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="${base }/servlet/UsersServlet?method=findAllUsers" method="post">
			<div align="center">
				<h1>
					会员列表
				</h1>


			</div>
			
			
				<div align="center" >
					<table border="1" >
					<tr >
						<th align="left" >
							会员名：
							<input name="uname" id="uname" value="${ recharge.uname}">
							<input type="submit" value="搜  索" />
						</th>
						
						
					</tr>
					</table>
					
				</div>
			<div align="center" id="studentlist">
				<table border="1"  >
					<tr>
						<th align="center">
							序号
						</th>
						<th>
							用户
						</th>
						<th>
							姓名
						</th>
						<th>
							性别
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
							余额
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
										href="${base }/servlet/RechargeServlet?method=addRecharge&uid=${item.uid}">充值</a>
									<a
										href="${base }/servlet/RechargeServlet?method=findAllRecharge&uid=${item.uid}">充值明细</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
