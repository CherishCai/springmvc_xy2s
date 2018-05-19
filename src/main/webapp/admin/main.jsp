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
		<title>欢迎使用网站后台管理系统</title>
		<style type="text/css">
            <!--*{margin:0px;padding:0px;}html,body{height:100%;}* html, * html body
            {overflow: hidden; /*隐藏IE的默认滚动条的宽度*/}
			#bodyDiv{width:100%;height:100%;}
			#topDiv{height:18%;background:#ccc;border:0px solid #000;overflow:auto; /*溢出的时候显示滚动条为 auto */}
			#homeDiv{height:70%;background:#ccc;border:0px solid #000;overflow:auto; /*溢出的时候显示滚动条为 auto */}
			#bottomDiv{height:12%;background:#f4f4f4;border:0px solid #F00;margin-top:-4px;overflow:auto;}
			h3{font-size:14px;font-weight:bold;}-->
        </style>
	</head>

	<frameset rows="95,*,40" frameborder="no" border="0" framespacing="0">
	<frame src="admin/top.jsp" name="header" scrolling="No"
		noresize="noresize" id="topFrame" />
	<frame src="admin/center.jsp" name="center" id="mainFrag="0" />
	<frame src="admin/foot.jsp" name="footer" scrolling="No"
		noresize="noresize" id="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body></noframes>
</html>
