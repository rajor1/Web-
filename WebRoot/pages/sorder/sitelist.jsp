<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base }/css/list.css" rel="stylesheet"
			type="text/css" /><base href="${base }">

		<title>���ع���</title>
		<script type="text/javascript">
		function search(){
			document.forms[0].submit();
		}
		function selectvalue(sid,sremarks){
		window.opener.document.getElementById('sid').value = sid;
		window.opener.document.getElementById('sname').value = sremarks;
		window.opener=null;
		window.open('','_self');
		window.close(); 
}
		</script>
	</head>

	<body >
		<form action="${base }/servlet/SorderServlet?method=findAllSite" method="post">
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
							<input name="sremarks" id="sremarks" value="${ sremarks}">
						
						
							<input type="submit" value="��  ��" />
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
								<a href="javascript:selectvalue('${item.sid}','${item.sremarks}')">ѡ��</a>
							</td>

						</tr>
					</c:forEach>
					
				</table>
   
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
