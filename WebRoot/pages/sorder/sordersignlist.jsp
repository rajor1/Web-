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
		<form action="${base }/servlet/SorderServlet?method=findAllSorderSign" method="post">
			<div align="center">
				<h1>
					开单列表
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
						
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SorderServlet?method=addSorder&addtype=sign'" value="添  加" />
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
							开始时间
						</th>
						<th>
							付款类型
						</th>
						<th>
							付款金额
						</th>
						<th>
							付款日期
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
							<c:if test="${item.ostart==''}"><a href="${base }/servlet/SorderServlet?method=updatePage&oid=${item.oid}&updatetype=sign">开单</a></c:if>
							<c:if test="${item.ostart!=''&&item.spend==0}"><a href="${base }/servlet/SorderServlet?method=updatePage&oid=${item.oid}&updatetype=pay">付款</a></c:if>
								
							</td>

						</tr>
					</c:forEach>
					
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
