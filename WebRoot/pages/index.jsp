<%@ page language="java"  pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>体育馆场地管理系统</title>
    
  </head>
<frameset rows="61,*,24" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="<%=basePath%>pages/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frame src="<%=basePath%>pages/center.jsp" name="mainFrame" id="mainFrame" />
  <frame src="<%=basePath%>pages/down.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
