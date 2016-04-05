package com.catv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

//本Servlet的功能是把Edit.jsp的request中的参数更新到数据库，同事修改用户的状态
public class EditServlet extends HttpServlet
{
	//响应客户端请求的方法
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		RequestDispatcher rd;
		//创建SignalModel对象
		SignalModel sm = new SignalModel();
		//获取 sm 对象的空ArrayList
		ArrayList<Signal> signalSet = sm.getSignalSet();
		//通过session对象获取user对象
		HttpSession session = request.getSession(true);
		UserModel user = (UserModel)session.getAttribute("user");
		//!!获取系统时间
		Calendar cal = Calendar.getInstance(); 
		long tm = cal.getTimeInMillis();
		//遍历所有请求参数
		int i;
		for (i = 0; request.getParameter(i + "_Ch_no") != null; i ++)
		{
			Signal sig = new Signal(Integer.parseInt(request.getParameter(i + "_Ch_no")), 
									request.getParameter(i + "_Ch_freg"), 
									Float.parseFloat(request.getParameter(i + "_Signal_video")), 
									Float.parseFloat(request.getParameter(i + "_Signal_mer")), 
									Float.parseFloat(request.getParameter(i + "_Signal_ber")), 
									tm, 
									user.getId(), 
									user.getWeek(), 
									user.getYear());
			signalSet.add(sig);
		}
		System.out.println("i = :" + i);
		//i > 0 用来防止提交空数据
		if (i > 0)
		{
			//更新对象UserModel的状态
			user.setState(UserState.DONE);
			
			try
			{
				//更新数据库
				sm.delSignalSet(user.getId(), user.getYear(), user.getWeek());
				sm.putSignalSet(signalSet);
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}
		}
		
		rd = request.getRequestDispatcher("/editpage.jsp");	
		String msg = "数据提交成功！";
		request.setAttribute("msg" , msg);
		rd.forward(request,response);
	}
}
