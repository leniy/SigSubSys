<%@ page contentType="text/html; charset=utf8" language="java" errorPage="" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="mystyle.css" />
	<title>登录</title>
</head>
<body>
	<div id="div1">
		<div id="div2">
		<%
		if(request.getAttribute("tip") != null)
		{
			out.println("<font color='red'>" + request.getAttribute("tip") + "</font>");
		}
		else
		{
			out.print("欢迎登录");
		}
		%>
		</div>
		
		<div id="div3">
		<br/>
			<form id="login" method="post" action="login">
			<div id="div4">
				工号：<input type="text" name="username"/>
				<br/><br/>
				密码：<input type="password" name="passwd"/>
				<br/><br/>
			</div>
			<div id="div5">				
				<input type="submit" value="登录"/>
				<input type="reset" value="重置"/>
			</div>
			</form>
		</div>
	</div>
</body>
</html>