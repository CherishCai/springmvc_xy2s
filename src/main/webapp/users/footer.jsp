<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="VjiaFooter" class="block">
	<div class="copyRight">
		<div class="f_nav">&nbsp;</div>
		<div style="line-height: 22px; color: #000;">
			<a href="index.jsp"
				style="font-family: Verdana; font-size: 11px;">Powered&nbsp;by&nbsp;<strong><span
					style="color: #3366FF">${title }</span></strong></a>&nbsp;<br />
		</div>
		<div align="center">
			<a href="<%=basePath%>admin/login.shtml" target="_blank">管理员入口</a>
		</div>
		<div class="blank"></div>
	</div>
</div>
