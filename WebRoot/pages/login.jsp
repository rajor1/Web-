<%@ page language="java" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户登录</title>
		<script type="text/javascript" language="javascript">
	function checkForm(){
		var form = document.forms[0];
		if(form.userName.value==""){
			alert("请输入用户名");
			return false;
		}
		if(form.password.value==""){
			alert("请输入密码");
			return false;
		}
		return true;
	}
	
</script>

	</head>

	<body
		style="BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-BOTTOM-STYLE: none"
		style="overflow:hidden">
		<form action="servlet/LoginServlet?method=login" method="post"
			name="frm" onsubmit="return checkForm()">
			<c:if test="${message!=null&&message!=''}">
				<script type="text/javascript">
			alert('${message}');
		</script>
			</c:if>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgcolor="#e5f6cf">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="608" background="images/login_03.gif">
						<table width="862" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="266" background="images/login_04.gif">
									<table width="72%" height="51" border="0" align="center">
										<tr>
											<td>
												<div
													style="font-family: 宋体; color: #FFFFFF; filter: Glow(Color = #000000, Strength = 2); WIDTH: 100%; FONT-WEIGHT: bold; FONT-SIZE: 19pt; margin-top: 5pt">
													<div align="center" class="STYLE5">
														体育馆场地管理系统
													</div>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="95">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="424" height="95" style="background-image: url(${base }/images/login_06.gif)  ;background-repeat:no-repeat;">
												&nbsp;
											</td>
											<td width="183" background="images/login_07.gif">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">

													<tr>
														<td width="21%" height="30">
															<div align="center">
																<span class="STYLE3">用户</span>
															</div>
														</td>
														<td width="79%" height="30">
															<input name="userName" type="text" id="userName"
																value=""
																style="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;">

														</td>
													</tr>
													<tr>
														<td height="30">
															<div align="center">
																<span class="STYLE3">密码</span>
															</div>
														</td>
														<td height="30">
															<input name="password" type="password" id="password"
																value=""
																style="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;">
														</td>
													</tr>
													
													<tr>
														<td height="30">
															&nbsp;
														</td>
														<td height="30">
															<input type="submit" name="Submit" value="登陆">
															<input type="reset" name="Submit2" value="重置">
														</td>
													</tr>
												</table>
											</td>
											<td width="255" background="images/login_08.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="247" valign="top" background="images/login_09.gif">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="22%" height="30">
												&nbsp;
											</td>
											<td width="56%">
												&nbsp;
											</td>
											<td width="22%">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td height="30">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="35%" height="20">
															
														</td>
														<td width="56%">
															<input name="type" type="radio" value="1"
																checked="checked" />
															管理员
															<input name="type" type="radio" value="2" />
															会员
															<a href="${base }/servlet/UsersServlet?method=addUsers&addtype=reg" style="font-size: 12px;font-weight: bold;">会员注册</a>
														</td>
													</tr>
												</table>
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#a2d962">
						&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

