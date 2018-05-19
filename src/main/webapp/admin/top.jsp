<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title></title>
		<link href="style/top.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
		function logout(){
			if (confirm("您确定要退出吗？"))
			top.location = "<%=basePath%>";
			return false;
		}
		</script>
	</head>

	<body leftmargin="0" topmargin="0">
		<div class="admin_topbg">
			<div class="logo" style="font-size: 50px;width:300;font-family:微软雅黑">
				&nbsp;&nbsp;欢迎使用网站后台管理系统
			</div>
			<div class="top_text">
				欢迎： ${sessionScope.adminname }|
				<a href="<%=basePath%>admin/editpwd.jsp" target="main">修改密码</a>&nbsp;
				<a href="<%=basePath%>admin/exit.shtml" target="_top" onclick="logout();">安全退出</a>
			</div>
		</div>
	</body>
</html>
