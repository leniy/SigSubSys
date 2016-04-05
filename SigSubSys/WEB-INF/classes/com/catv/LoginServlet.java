package com.catv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
	//��Ӧ�ͻ�������ķ���
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		String tip = "";
		//Servlet�����������Ӧ���ͻ��ˣ���˱��뽫����ת��
		RequestDispatcher rd;
		//��ȡ�������
		String username = request.getParameter("username");
		String pass = request.getParameter("passwd");
		
		try
		{
			//Servlet��������ִ���κε�ҵ���߼�����������JavaBean�����û�����
			DbDao dd = new DbDao();
			//��ѯ�����
			ResultSet rs = dd.query("select passwd from emp_info " + "where emp_name = ?", username);
			if (rs.next())
			{
				//�û���������ƥ��
				if (rs.getString("passwd").equals(pass))
				{
					//��ȡsession����
					HttpSession session = request.getSession(true);
					//����session���ԣ������û��Ự״̬
					UserModel user = new UserModel(username);
					session.setAttribute("user" , user);
					//��ȡת������
					rd = request.getRequestDispatcher("/welcome.jsp");
					//ת������
					rd.forward(request,response);
				}
				else
				{
					//�û��������벻ƥ��ʱ
					tip += "�����û������벻����,����������";
				}
			}
			else
			{
				//�û���������ʱ
				tip += "�����û���������,����ϵ����Աע��";
			}
			dd.closeConn();			
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
		
		//�������ת�������µ�¼
		if (tip != null && !tip.equals(""))
		{
			rd = request.getRequestDispatcher("/login.jsp");		
			request.setAttribute("tip" , tip);
			rd.forward(request,response);
		}
	}
}
