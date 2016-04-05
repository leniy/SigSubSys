package com.catv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
	//响应客户端请求的方法
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		String tip = "";
		//Servlet本身并不输出响应到客户端，因此必须将请求转发
		RequestDispatcher rd;
		//获取请求参数
		String username = request.getParameter("username");
		String pass = request.getParameter("passwd");
		
		try
		{
			//Servlet本身，并不执行任何的业务逻辑处理，它调用JavaBean处理用户请求
			DbDao dd = new DbDao();
			//查询结果集
			ResultSet rs = dd.query("select passwd from emp_info " + "where emp_name = ?", username);
			if (rs.next())
			{
				//用户名和密码匹配
				if (rs.getString("passwd").equals(pass))
				{
					//获取session对象
					HttpSession session = request.getSession(true);
					//设置session属性，跟踪用户会话状态
					UserModel user = new UserModel(username);
					session.setAttribute("user" , user);
					//获取转发对象
					rd = request.getRequestDispatcher("/welcome.jsp");
					//转发请求
					rd.forward(request,response);
				}
				else
				{
					//用户名和密码不匹配时
					tip += "您的用户名密码不符合,请重新输入";
				}
			}
			else
			{
				//用户名不存在时
				tip += "您的用户名不存在,请联系管理员注册";
			}
			dd.closeConn();			
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
		
		//如果出错，转发到重新登录
		if (tip != null && !tip.equals(""))
		{
			rd = request.getRequestDispatcher("/login.jsp");		
			request.setAttribute("tip" , tip);
			rd.forward(request,response);
		}
	}
}
