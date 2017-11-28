<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<jsp:include page="../../../common/left.jsp"></jsp:include>
	<div id="right">
		<form action="userAction!addOrUpdateUser" onsubmit="return judge()" method="post">
			<h3>新增&编辑用户信息</h3>
			<input type="hidden" value="${user.no }"  name="user.no">
			<input type="hidden" value="${user.createDate }"  name="user.createDate">
			<p>用户名称：<input type="text" placeholder="请输入您的用户名！" class="input" required name="user.name" value="${user.name }"></p>
			<p>登录密码：<input type="password" placeholder="请输入您的密码！" class="input" required name="user.password" value="${user.password }"></p>
			<p>重复密码：<input type="password" placeholder="请输入您的确认密码！" class="input" required value="${user.password }"></p>
			<p>电子邮箱：<input type="email" placeholder="请输入您的邮箱！" class="input" required name="user.email" value="${user.email }"></p>
			<p>性别：
				<input type="radio" name="user.sex" value="男" checked>男
				&nbsp;&nbsp;&nbsp;
				<input type="radio" name="user.sex" value="女">女
			</p>
			<hr width="1100px">
			<div id="div_button">
				<input type="submit" value="保存">
				<input type="reset" value="重置">
				<input type="button" value="返回" onclick="javascript:history.go(-1)">
			</div>
		</form>	
	</div>
</body>
</html>