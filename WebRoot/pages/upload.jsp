<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/pages/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<title>�ļ��ϴ�</title>
</head>
<body>
<c:if test="${msg == 'type is diff!'}">�ļ��ϴ�ʧ�ܣ�</c:if>
<c:if test="${msg == 'is null'}">��ѡ���ļ���</c:if>
<c:if test="${msg == 'success'}">
<script>
window.opener.document.getElementById('simg').value = '${filepath}';
window.opener=null;
window.open('','_self');
window.close(); 
</script>
</c:if>
<form action="${base }/servlet/SiteServlet?method=uploadPicture" method="post"" method="post" enctype="multipart/form-data">
<div>
	�ļ�·����<input type="file" name="slave" />
	<input type="submit" value="�ϴ��ļ�" /></div>
</form>
</body>
</html>