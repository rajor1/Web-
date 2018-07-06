<%@ page language="java"  pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    

  </head>

  <body >
  <table width="66%" height="68%" border="0" cellpadding="5" cellspacing="5">
  <tr>
    <td valign="top"><p style="font-weight: bolder;">欢迎进入〉〉〉 </p>
      <p style="font-weight: bolder;"><br>
        体育馆场地管理系统 </p>
     
  </tr>
</table>
<p class="welcome">&nbsp;</p>
  </body>
</html>

