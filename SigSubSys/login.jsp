<%@ page contentType="text/html; charset=utf8" language="java" errorPage="" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script src="js/prefixfree.min.js"></script>
<title>登录</title>
</head>
<body>
<div class="container-fluid">
	<section id="content">
		<% if(request.getAttribute("tip") != null) { out.println("<font color='red'>" + request.getAttribute("tip") + "</font>"); } else { out.print("欢迎登录"); } %>
		<form id="login" method="post" action="login">
			<h1>信号指标提交</h1>
			<input type="text" placeholder="工号" required="" id="username" name="username" />
			<input type="password" placeholder="密码" required="" id="passwd" name="passwd" />
			<div id="buttondiv">
				<input type="submit" value="登录" />
				<input type="reset" value="重置"/>
			</div>
		</form>
	</section>
</div>
</body>
</html>
