<!-- sss -->
<%@ page contentType="text/html; charset=gbk" language="java" errorPage="" %>
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*" %>
<html>
<head>
	<title>����Tomcat����Դ</title>
</head>
<body>
<%
//��ʼ��Context��ʹ��InitialContext��ʼ��Context
Context ctx=new InitialContext(); 
/*
ͨ��JNDI��������Դ����JNDIΪjava:comp/env/jdbc/catv_jn���ֳ���������
java:comp/env��Tomcat�̶��ģ�Tomcat�ṩ��JNDI�󶨶�����Ӹ�ǰ׺
jdbc/catv_jn�Ƕ�������Դʱ������Դ��
*/
DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/catv_jn");
//��ȡ���ݿ�����
Connection conn=ds.getConnection();
//��ȡStatement
Statement stmt=conn.createStatement();
//ִ�в�ѯ������ResulteSet����
ResultSet rs=stmt.executeQuery("select * from region_info");
while(rs.next())
{
	out.println(rs.getString(1) 
		+ "\t" + rs.getString(2) + "<br/>");
}
//ִ�в�ѯ
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