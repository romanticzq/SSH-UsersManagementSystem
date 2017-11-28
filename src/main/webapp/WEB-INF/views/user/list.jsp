<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<jsp:include page="../../../common/left.jsp"></jsp:include>
	<div id="right">
		<div id="right_head">
			<form action="userAction!queryPage1AndName">
				用户名称：<input type="text" name="user.name"> <input
					type="submit" value="查询">
			</form>
		</div>
		<div id="right_foot">
			<table border="1" cellspacing="0">
				<tr bgcolor="#EFF0EF">
					<td>编号</td>
					<td>用户名称</td>
					<td>邮箱</td>
					<td>性别</td>
					<td>创建时间</td>
					<td>更新时间</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${page.list }" var="temp">
					<tr>
						<td>${temp.no}</td>
						<td>${temp.name}</td>
						<td>${temp.email}</td>
						<td>${temp.sex}</td>
						<td>${temp.createDate}</td>
						<td>${temp.updateDate}</td>
						<td><a href="userAction!to_form?user.no=${temp.no }">编辑</a>&nbsp;<a
							href="userAction!deleteUser?user.no=${temp.no }">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<label>共:${page.pages }页，${page.total }条记录</label>
			<c:if test="${flag ==true}">
				<a href="userAction!queryPage1?pageNo=1">首页</a>
				<a href="userAction!queryPage1?pageNo=${page.prePage }">上一页</a>
				<c:forEach items="${page.navigatepageNums }" var="page">
					<a href="userAction!queryPage1?pageNo=${page }">${page }</a>
				</c:forEach>
				<a href="userAction!queryPage1?pageNo=${page.nextPage }">下一页</a>
				<a href="userAction!queryPage1?pageNo=${page.pages}">末页</a>
				<br>
			</c:if>
			<c:if test="${flag ==false}">
				<a
					href="userAction!queryPage1AndName?page.pageNum=1&user.name=${user.name }">首页</a>
				<a
					href="userAction!queryPage1AndName?page.pageNum=${page.prePage }&user.name=${user.name }">上一页</a>
				<c:forEach items="${page.navigatepageNums }" var="page">
					<a
						href="userAction!queryPage1AndName?page.pageNum=${page }&user.name=${user.name }">${page }</a>
				</c:forEach>
				<a
					href="userAction!queryPage1AndName?page.pageNum=${page.nextPage }&user.name=${user.name }">下一页</a>
				<a
					href="userAction!queryPage1AndName?page.pageNum=${page.pages}&user.name=${user.name }">末页</a>
				<br>
			</c:if>
		</div>
	</div>
</body>
</html>