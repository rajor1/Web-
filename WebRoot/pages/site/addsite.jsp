<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${base }">

		<title>场地添加</title>

		<script type="text/javascript" language="javascript">
	 
     	function add_upload()        
{
 window.open("${base }/pages/upload.jsp","addStu",
"resizable=false,top=250," +"left=450,width=400,height=450,toolbar=no,menubar=no,scrollbars=no");
}
      function checkForm(){
         var form = document.forms[0];
         
		
	     if(form.simg.value=="" || form.simg.value.length<=0){
			alert("请上传图片！");
			form.simg.focus();
			return false;
		}
		if(form.sprice.value=="" || form.sprice.value.length<=0){
			alert("请输入单价！");
			form.sprice.focus();
			return false;
		}
		 if(form.sremarks.value=="" || form.sremarks.value.length<=0){
			alert("请输入场地名称！");
			form.sremarks.focus();
			return false;
		}
		

		
		return true;
	}
	
</script>
	</head>

	<body>
		<div align="center">
			<h1>
				场地添加
			</h1>
		</div>
		<div id="user" align="center">
			<form action="${base }/servlet/SiteServlet?method=saveSite"
				method="post" onsubmit="return checkForm()">
				<table border="1" class="table" align="center">

					<tr>

						<td>
							图片：
						</td>
						<td>
							<input type="text" readonly="readonly" name="simg" id="simg"
								value="${picture.purl}" />
							<input type="button" value="上传图片" onclick="add_upload()">

							<span style="color: red;">*.jpg|.JPG|.gif|.GIF|.png|.PNG|.JPEG|.jpeg</span>
						</td>
						</tr>
					<tr>
						<td>
							单价：
						</td>
						<td>
							<input type="text" id="sprice" name="sprice">
							<span style="color: red;">*</span>
						</td>
					</tr>
					<tr>
						<td>
							名称：
						</td>
						<td>
							<input type="text" id="sremarks" name="sremarks">
							<span style="color: red;">*</span>
						</td>

					</tr>
					<tr>
						<td>
							详情：
						</td>
						<td>
							<input type="text" id="sinfo" name="sinfo">
							<span style="color: red;">*</span>
						</td>

					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="提  交" />

							<input class="box" type="button" value="返  回"
								onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
