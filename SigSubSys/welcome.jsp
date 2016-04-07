<%@ page contentType="text/html; charset=utf8" language="java" errorPage="" %>
<%@ page import="com.catv.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="css/welcom.css" />
	<title>欢迎</title>
</head>
<body>
	<p>您好，${sessionScope.user.getName()}！</p>
	<%
		ArrayList<UserModel> userModelSet = new ArrayList<UserModel>();
		UserModel user = (UserModel)session.getAttribute("user");
		//根据员工权限，显示不同的内容
		if (user.getLevel() > 9)
		{
			userModelSet = user.getAllUser(user.getYear(), user.getWeek());
		}
		else
		{
			userModelSet.add(user);
		}
	%>
	<table border="1">
		<th>员工工号</th>
		<th>员工描述</th>
		<th>所属地区</th>
		<th>年度</th>
		<th>周数</th>
		<th>是否提交</th>
		<%
		for (int i = 0; i < userModelSet.size(); i++)
		{
			UserModel temp = userModelSet.get(i);
		%>
			<tr>
			<td><%=temp.getName()%></td>
			<td><%=temp.getDesc()%></td>
			<td><%=temp.getRegion()%></td>
			<td><%=temp.getYear()%></td>
			<td><%=temp.getWeek()%></td>
			<td><%=temp.getState().toString()%></td>
			</tr>
		<%
		}
		%>
	</table>
	<div>
	<a href="login.jsp"><input type="button" value="重新登录"/></input></a>
	<%
		if (((UserModel)session.getAttribute("user")).getState() == UserState.DONE)
		{
			out.println("<a href='editpage.jsp'><input type='button' value='查看/修改'/></input></a>");
		}
		else
		{
			out.println("<a href='editpage.jsp'><input type='button' value='创建'/></input></a>");
		}
	%>
	</div>
</body>
</html>