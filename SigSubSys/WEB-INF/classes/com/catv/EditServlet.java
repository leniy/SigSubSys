package com.catv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

//��Servlet�Ĺ����ǰ�Edit.jsp��request�еĲ������µ����ݿ⣬ͬ���޸��û���״̬
public class EditServlet extends HttpServlet
{
	//��Ӧ�ͻ�������ķ���
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		RequestDispatcher rd;
		//����SignalModel����
		SignalModel sm = new SignalModel();
		//��ȡ sm ����Ŀ�ArrayList
		ArrayList<Signal> signalSet = sm.getSignalSet();
		//ͨ��session�����ȡuser����
		HttpSession session = request.getSession(true);
		UserModel user = (UserModel)session.getAttribute("user");
		//!!��ȡϵͳʱ��
		Calendar cal = Calendar.getInstance(); 
		long tm = cal.getTimeInMillis();
		//���������������
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
		//i > 0 ������ֹ�ύ������
		if (i > 0)
		{
			//���¶���UserModel��״̬
			user.setState(UserState.DONE);
			
			try
			{
				//�������ݿ�
				sm.delSignalSet(user.getId(), user.getYear(), user.getWeek());
				sm.putSignalSet(signalSet);
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}
		}
		
		rd = request.getRequestDispatcher("/editpage.jsp");	
		String msg = "�����ύ�ɹ���";
		request.setAttribute("msg" , msg);
		rd.forward(request,response);
	}
}
