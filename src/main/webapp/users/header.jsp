<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="themes/dapu/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/index.js"></script>
<div class="top_nav">
	<div class="block">

		<div class="bookmail">
			<c:if test="${sessionScope.userid == null }">
				<font id="ECS_MEMBERZONE"> 欢迎光临本店，<a
					href="index/preLogin.shtml">[用户登录]</a>&nbsp;<a
					href="index/preReg.shtml">[用户注册]</a>
				</font>
			</c:if>
			<c:if test="${sessionScope.userid != null }">
				<font id="ECS_MEMBERZONE"> 欢迎光临本店${sessionScope.username }， <a
					href="index/usercenter.shtml">[用户中心]</a>&nbsp; <a
					href="index/cart.shtml">[购物车]</a>&nbsp;<a href="index/exit.shtml">[退出系统]</a>
				</font>
			</c:if>
		</div>
	</div>
</div>
<div class="clearfix">
	<div class="block header">
		<div class="top clearfix">
			<a href="index.jsp" class="logo"><img
				src="themes/dapu/images/logo.gif"> </a>
			<div class="head_r">
				<div class="top_search">
					<form id="searchForm" name="searchForm" method="post"
						action="index/query.shtml" style="_position: relative; top: 5px;">
						<div class="headSearch_input">
							<input name="name" type="text" id="keyword" class="keyword"
								value="" /> <input value="" id="seachbtn" type="submit" />
						</div>
					</form>
					<div style="clear: both"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="clear: both"></div>

<div class="menu_box clearfix">
	<div class="block main_menu">
		<div class="menu">
			<a href="index.jsp" class="cur">网站首页<span></span></a> 
			<a href="index/article.shtml" class="cur">网站新闻<span></span></a>
			<c:forEach items="${cateList}" var="cate">
			<a href="index/cate.shtml?id=${cate.cateid }" class="cur">${cate.catename }<span></span></a>
			</c:forEach>
			<a href="index/all.shtml" class="cur">全部商品<span></span></a>
			<a href="index/bbs.shtml" class="cur">留言交流<span></span></a>
		</div>
	</div>
</div>
<div style="clear: both"></div>











