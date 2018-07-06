<%@ page language="java" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<script>var base = '${base}';</script>
		<SCRIPT LANGUAGE="JavaScript" SRC="${base }/js/treemenu.js"></SCRIPT>
		<SCRIPT LANGUAGE="JavaScript" SRC="${base }/js/Common.js"></SCRIPT>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE2 {color: #43860c; font-size: 12px; }

a:link {font-size:12px; text-decoration:none; color:#43860c;}
a:visited {font-size:12px; text-decoration:none; color:#43860c;}
a:hover{font-size:12px; text-decoration:none; color:#FF0000;}
-->
</style>
		<script type="text/JavaScript">
<!--



function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>
	</head>

	<body>
		<table width="177" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="table-layout:fixed">
						<tr>
							<td height="26" background="${base }images/main_21.gif">
								&nbsp;
							</td>
						</tr>

						<tr>
							<td
								style="line-height:4px; background:url(${base }images/main_38.gif)">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>



								<c:set var="type"
									value="<%=request.getSession().getAttribute("type")%>"></c:set>
								<c:if test="${type==1 }">

									<SCRIPT LANGUAGE='JavaScript'>
										foldersTree = gFldr('<DIV CLASS=fldrroot><b>系统功能列表</b></DIV>','');										
										Class1 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>管理员账号管理</DIV>", ''));										
										insDoc(Class1, gLnk(1, "<a href='../servlet/EmployeeServlet?method=findAllEmployee' target='hsgmain' alt='管理员账号管理'>管理员账号管理</a>", '', base+'/images/editinfo.gif'));
										Class2 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>会员账号管理</DIV>", ''));
										insDoc(Class2, gLnk(1, "<a href='../servlet/UsersServlet?method=findAllUsers' target='hsgmain' alt='会员帐号管理'>会员帐号管理</a>", '', base+'/images/editinfo.gif'));
										insDoc(Class2, gLnk(1, "<a href='../servlet/RechargeServlet?method=findAllUsers' target='hsgmain' alt='会员充值管理'>会员充值管理</a>", '', base+'/images/editinfo.gif'));
										Class3 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>场地信息管理</DIV>", ''));
										insDoc(Class3, gLnk(1, "<a href='../servlet/SiteServlet?method=findAllSite' target='hsgmain' alt='场地信息管理'>场地信息管理</a>", '', base+'/images/editinfo.gif'));
										Class4 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>订单信息管理</DIV>", ''));
										insDoc(Class4, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorder' target='hsgmain' alt='场地预定管理'>场地预定管理</a>", '', base+'/images/editinfo.gif'));
										insDoc(Class4, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorderSign' target='hsgmain' alt='场地开单管理'>场地开单管理</a>", '', base+'/images/editinfo.gif'));
										Class5 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>收入统计管理</DIV>", ''));
										insDoc(Class5, gLnk(1, "<a href='../servlet/SorderServlet?method=statistic' target='hsgmain' alt='场地收入统计'>场 地收入统计</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, '退出', '../servlet/LoginServlet?method=loginout', base+'/images/exit.gif'));
										initializeDocument(0);
									</SCRIPT>
									</c:if>
								<c:if test="${type==2 }">
									<c:set var="uname"
									value="<%=request.getSession().getAttribute("username")%>"></c:set>
									<c:set var="userid"
									value="<%=request.getSession().getAttribute("userid")%>"></c:set>
									<SCRIPT LANGUAGE='JavaScript'>
										foldersTree = gFldr('<DIV CLASS=fldrroot><b>系统功能列表</b></DIV>','');
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/SorderServlet?method=addSorder&addtype=myadd' target='hsgmain' alt='场地预定管理'>场地预定管理</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorder&uid=<%=request.getSession().getAttribute("userid")%>' target='hsgmain' alt='场地订单查看'>场 地订单查看</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/UsersServlet?method=updatePage&uid=<%=request.getSession().getAttribute("userid")%>&updatetype=2' target='hsgmain'>用户信息修改</a>", '', base+'/images/pwd.gif'));
										insDoc(foldersTree, gLnk(1, '退出', '../servlet/LoginServlet?method=loginout', base+'/images/exit.gif'));
										initializeDocument(0);
									</SCRIPT>
								</c:if>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
