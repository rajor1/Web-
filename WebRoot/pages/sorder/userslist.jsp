<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">
		<script type="text/javascript">
		
			function search(){
			document.forms[0].submit();
		}
		function selectvalue(uid,uname,urealname){
		window.opener.document.getElementById('uid').value = uid;
		window.opener.document.getElementById('uname').value = uname;
		window.opener.document.getElementById('urealname').value = urealname;
		window.opener=null;
		window.open('','_self');
		window.close(); 
}
		</script>
		<title>会员管理</title>
		<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body >
		<form action="${base }/servlet/SorderServlet?method=findAllUsers" method="post">
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
							<input name="uname" id="uname" value="${ users.uname}">
						
						
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
								余额
							</td>
								<td align="center" width="100px;">
									<a href="javascript:selectvalue('${item.uid}','${item.uname}','${item.urealname}')">选择</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
