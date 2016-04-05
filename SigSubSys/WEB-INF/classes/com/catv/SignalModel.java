package com.catv;

import java.sql.*;
import java.util.ArrayList;

public class SignalModel
{
	private ArrayList<Signal> signalSet;
	
	public SignalModel()
	{
		signalSet = new ArrayList<>(80);
	}
	
	public ArrayList<Signal> getSignalSet()
	{
		return signalSet;
	}
	
	public ArrayList<Signal> getSignalSet(int emp_id, int year, int week)
	{
		
		try
		{
			//链接数据库，初始化用户信息
			DbDao dd = new DbDao();
			ResultSet rs = dd.query("select * from signal_record " + "where emp_id = ? and year = ? and week = ?", emp_id, year, week);
			
			while (rs.next())
			{
				ResultSet rs2 = dd.query("select * from ch_info " + "where ch_no = ?", rs.getInt(6));
				if (rs2.next())
				{
					Signal sig = new Signal(rs.getInt(6), rs2.getString(2), rs.getFloat(3), rs.getFloat(3), rs.getFloat(4), rs.getLong(5), rs.getInt(7), rs.getInt(8), rs.getInt(9));
					signalSet.add(sig);
				}
			}
			dd.closeConn();
			return signalSet;		
					
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		} 
	
		return null;
	}    
	
		
	public int putSignalSet(ArrayList<Signal> signalSet)
	{
		
		try
		{
			DbDao dd = new DbDao();
			int i;
			
			for (i = 0; i < signalSet.size(); i++)
			{
				Signal sig = signalSet.get(i);
				dd.insert("insert into signal_record(signal_video, signal_mer, signal_ber, submit_time, ch_no, emp_id, week, year) values(?, ?, ?, ?, ?, ?, ?, ?)", 
						sig.getSignal_video(), sig.getSignal_mer(), sig.getSignal_ber(), sig.getSubmit_time(), sig.getCh_no(), sig.getEmp_id(), sig.getWeek(), sig.getYear());
				
			}
			dd.closeConn();
			return i;
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int delSignalSet(int emp_id, int year, int week)
	{
		
		try
		{
			DbDao dd = new DbDao();
			dd.modify("delete from signal_record " + "where emp_id = ? and year = ? and week = ?", emp_id, year, week);
			dd.closeConn();
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Signal> getSignalTempl()
	{	
		
		try
		{
			//链接数据库，初始化用户信息
			DbDao dd = new DbDao();
			ResultSet rs = dd.query("select * from ch_info");
			ArrayList<Signal> signalTempl = new ArrayList<>(80);
			
			while (rs.next())
			{
				Signal sig = new Signal(rs.getInt(1), rs.getString(2), 0.0f, 0.0f, 0.0f, 0, 0, 0, 0);
				signalTempl.add(sig);
			}
			dd.closeConn();
			return signalTempl;		
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
		
		return null;
	} 
}
