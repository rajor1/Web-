<%@ page language="java" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>�ޱ����ĵ�</title>
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
										foldersTree = gFldr('<DIV CLASS=fldrroot><b>ϵͳ�����б�</b></DIV>','');										
										Class1 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>����Ա�˺Ź���</DIV>", ''));										
										insDoc(Class1, gLnk(1, "<a href='../servlet/EmployeeServlet?method=findAllEmployee' target='hsgmain' alt='����Ա�˺Ź���'>����Ա�˺Ź���</a>", '', base+'/images/editinfo.gif'));
										Class2 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>��Ա�˺Ź���</DIV>", ''));
										insDoc(Class2, gLnk(1, "<a href='../servlet/UsersServlet?method=findAllUsers' target='hsgmain' alt='��Ա�ʺŹ���'>��Ա�ʺŹ���</a>", '', base+'/images/editinfo.gif'));
										insDoc(Class2, gLnk(1, "<a href='../servlet/RechargeServlet?method=findAllUsers' target='hsgmain' alt='��Ա��ֵ����'>��Ա��ֵ����</a>", '', base+'/images/editinfo.gif'));
										Class3 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>������Ϣ����</DIV>", ''));
										insDoc(Class3, gLnk(1, "<a href='../servlet/SiteServlet?method=findAllSite' target='hsgmain' alt='������Ϣ����'>������Ϣ����</a>", '', base+'/images/editinfo.gif'));
										Class4 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>������Ϣ����</DIV>", ''));
										insDoc(Class4, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorder' target='hsgmain' alt='����Ԥ������'>����Ԥ������</a>", '', base+'/images/editinfo.gif'));
										insDoc(Class4, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorderSign' target='hsgmain' alt='���ؿ�������'>���ؿ�������</a>", '', base+'/images/editinfo.gif'));
										Class5 = insFldr(foldersTree, gFldr("<DIV CLASS='fldrroot'>����ͳ�ƹ���</DIV>", ''));
										insDoc(Class5, gLnk(1, "<a href='../servlet/SorderServlet?method=statistic' target='hsgmain' alt='��������ͳ��'>�� ������ͳ��</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, '�˳�', '../servlet/LoginServlet?method=loginout', base+'/images/exit.gif'));
										initializeDocument(0);
									</SCRIPT>
									</c:if>
								<c:if test="${type==2 }">
									<c:set var="uname"
									value="<%=request.getSession().getAttribute("username")%>"></c:set>
									<c:set var="userid"
									value="<%=request.getSession().getAttribute("userid")%>"></c:set>
									<SCRIPT LANGUAGE='JavaScript'>
										foldersTree = gFldr('<DIV CLASS=fldrroot><b>ϵͳ�����б�</b></DIV>','');
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/SorderServlet?method=addSorder&addtype=myadd' target='hsgmain' alt='����Ԥ������'>����Ԥ������</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/SorderServlet?method=findAllSorder&uid=<%=request.getSession().getAttribute("userid")%>' target='hsgmain' alt='���ض����鿴'>�� �ض����鿴</a>", '', base+'/images/editinfo.gif'));
										insDoc(foldersTree, gLnk(1, "<a href='../servlet/UsersServlet?method=updatePage&uid=<%=request.getSession().getAttribute("userid")%>&updatetype=2' target='hsgmain'>�û���Ϣ�޸�</a>", '', base+'/images/pwd.gif'));
										insDoc(foldersTree, gLnk(1, '�˳�', '../servlet/LoginServlet?method=loginout', base+'/images/exit.gif'));
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
