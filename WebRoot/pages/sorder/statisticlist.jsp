<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" /><base href="${base }">

		<title>场地收入统计</title>
		<script type='text/javascript' src='${base }/js/meizzDate.js'></script>
	</head>

	<body >
		<form action="${base }/servlet/SorderServlet?method=statistic" method="post">
			<div align="center">
				<h1>
					场地收入统计
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							期间：
							<input name="startdate" id="startdate" value="${ startdate}" onfocus="setday(this)"
							readonly="readonly"  >--
						<input name="enddate" id="enddate" value="${ enddate}" onfocus="setday(this)"
							readonly="readonly"  >
						
							<input type="submit" value="搜  索" />
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
							场地名称
						</th>

						<th>
							收入
						</th>
						
						

					</tr>
					<c:set value="0" var="total"></c:set>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${i.index+1}
							</td>
							<td >
								${item.sremarks}
							</td>
							<td >
								${item.sprice}
								<c:set value="${item.sprice+total}" var="total"></c:set>
							</td>
							
							
							
						
						</tr>
					</c:forEach>
					<tr>
							<td>
								
							</td>
							<td style="font-weight: 200;font-size: 20;">
								总计
							</td>
							<td  style="font-weight: 200;font-size: 20;">
								${total}
							</td>
							
							
							
						
						</tr>
					<tr>
							<td>
								
							</td>
							<td style="font-weight: 200;font-size: 20;">
								充值收入总计
							</td>
							<td  style="font-weight: 200;font-size: 20;">
								${rechargetotal}
							</td>
							
							
							
						
						</tr>
				</table>
   
			</div>
		</form>
	</body>
</html>
