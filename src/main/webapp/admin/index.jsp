<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>欢迎使用网站后台管理系统</title>
<link href="style/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	background-color: #FFFFFF;
	background-image: url(images/86304_top.jpg);
	background-repeat: repeat-x;
	background-position: top;
}
</style>
<script type="text/javascript">
	function checkLogin() {
		var username = document.myform.username.value;
		var password = document.myform.password.value;
		if (username == '') {
			alert('请输入用户名');
			document.myform.username.focus();
			return false;
		}
		if (password == '') {
			alert('请输入密码');
			document.myform.password.focus();
			return false;
		}
	}
</script>
</head>

<body id="exampleBody">
	<div id="main">
		<h1>网站后台管理系统</h1>
		<form action="<%=basePath%>admin/login.shtml" name="myform"
			method="post" onsubmit="return checkLogin()">
			<div class="row email">
				<input name="username" type="text" class="text-input"
					id="username" maxlength="20" />
			</div>
			<div class="row pass">
				<input type="password" id="password" name="password"
					class="pass-input" maxlength="20" />
			</div>
			<input type="submit" value="登录" class="submit-input" />
		</form>
	</div>
</body>
</html>
