<!-- sss -->
<%@ page contentType="text/html; charset=gbk" language="java" errorPage="" %>
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*" %>
<html>
<head>
	<title>测试Tomcat数据源</title>
</head>
<body>
<%
//初始化Context，使用InitialContext初始化Context
Context ctx=new InitialContext(); 
/*
通过JNDI查找数据源，该JNDI为java:comp/env/jdbc/catv_jn，分成两个部分
java:comp/env是Tomcat固定的，Tomcat提供的JNDI绑定都必须加该前缀
jdbc/catv_jn是定义数据源时的数据源名
*/
DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/catv_jn");
//获取数据库连接
Connection conn=ds.getConnection();
//获取Statement
Statement stmt=conn.createStatement();
//执行查询，返回ResulteSet对象
ResultSet rs=stmt.executeQuery("select * from region_info");
while(rs.next())
{
	out.println(rs.getString(1) 
		+ "\t" + rs.getString(2) + "<br/>");
}
//执行查询
PreparedStatement pstmt = conn.prepareStatement("select * from emp_info");
rs = pstmt.executeQuery();
while(rs.next())
{
	out.println(rs.getString(1) 
		+ "\t" + rs.getString(2) + "<br/>");
}
%>
</body>
</html>