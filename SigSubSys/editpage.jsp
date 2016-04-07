<%@ page contentType="text/html; charset=utf8" language="java" errorPage="" %>
<%@ page import="java.util.*" %>
<%@ page import="com.catv.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="css/editpage.css" />
	<title>编辑信息</title>
</head>
<body>
	<p>
	<%
		if(request.getAttribute("msg") != null)
		{
			out.println(request.getAttribute("msg"));
		}
	%>
	</p>
	<form id="edit" method="post" action="edit">
	<table border="1">
	<th>ch_no</th>
	<th>ch_freg</th>
	<th>video</th>
	<th>mer</th>
	<th>ber</th>
	<%
	SignalModel sm = new SignalModel();
	ArrayList<Signal> signalTempl;
	UserModel user = (UserModel)session.getAttribute("user");
	//判断用户是否已经提交
	//如果已经提交过数据，则读取提交过的数据
	if (user.getState() == UserState.DONE)
	{
		signalTempl = sm.getSignalSet(user.getId(), user.getYear(), user.getWeek());
	}
	else  //否则，生成一个空模板
	{
		signalTempl = sm.getSignalTempl();
	}
	
	for (int i = 0; i < signalTempl.size(); i++)
	{
		Signal sg = signalTempl.get(i);
	%>
		<tr>
		<td><input type="text" name="<%=i%>_Ch_no" value="<%=sg.getCh_no()%>" readonly="readonly"/></td>
		<td><input type="text" name="<%=i%>_Ch_freg" value="<%=sg.getCh_freg()%>" readonly="readonly"/></td>
		<td><input type="text" name="<%=i%>_Signal_video" value="<%=sg.getSignal_video()%>"/></td>
		<td><input type="text" name="<%=i%>_Signal_mer" value="<%=sg.getSignal_mer()%>"/></td>
		<td><input type="text" name="<%=i%>_Signal_ber" value="<%=sg.getSignal_ber()%>"/></td>
		</tr>
	<%
	}
	%>
	</table>
	<div>
	<a href="welcome.jsp"><input type="button" value="返回"/></input></a>
	<input type="submit" value="确认"/></input>
	</div>
	</form>
</body>
</html>