<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" /><base href="${base }">

		<title>场地管理</title>
	</head>

	<body>
		<form action="${base }/servlet/SiteServlet?method=findAllSite" method="post">
			<div align="center">
				<h1>
					场地列表
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" >
							场地：
							<input name="sremarks" id="sremarks" value="${ site.sremarks}">
						
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th >
							<input type="button" onclick="location.href='${base }/servlet/SiteServlet?method=addSite'" value="添  加" />
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
							名称
						</th>
						<th>
							图片
						</th>
						<th>
							单价
						</th>

						<th>
							详情
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
								<a href="${base }/servlet/SiteServlet?method=updatePage&sid=${item.sid}">修改</a>|
								<a
									href="${base }/servlet/SiteServlet?method=deleteSite&sid=${item.sid}">删除</a>
							</td>

						</tr>
					</c:forEach>
					
				</table>
   
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
