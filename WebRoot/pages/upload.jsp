<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/pages/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<title>文件上传</title>
</head>
<body>
<c:if test="${msg == 'type is diff!'}">文件上传失败！</c:if>
<c:if test="${msg == 'is null'}">请选择文件！</c:if>
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
	文件路径：<input type="file" name="slave" />
	<input type="submit" value="上传文件" /></div>
</form>
</body>
</html>