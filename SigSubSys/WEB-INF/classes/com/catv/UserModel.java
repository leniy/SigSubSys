package com.catv;

import java.util.Calendar;
import java.util.ArrayList;
import java.sql.*;

public class UserModel
{
	private int id;
	private String name;
	private String region;
	private String desc;
	private UserState state;
	private int level;
	private int week;
	private int year;
	
	public UserModel(String name)
	{
		this.name = name;
		this.state = UserState.NOT_DONE;
		
		try
		{
			//链接数据库，初始化用户信息
			DbDao dd = new DbDao();
			ResultSet rs1 = dd.query("select emp_id, region_id, emp_level, emp_desc from emp_info " + "where emp_name = ?", name);
			//根据查询的结果初始化用户信息
			if (rs1.next())
			{
				id = rs1.getInt(1);
				desc = rs1.getString(4);
				level = rs1.getInt(3);
				ResultSet rs2 = dd.query("select region_name from region_info " + "where region_id = ?", rs1.getInt(2));
				if (rs2.next())
				{
					region = rs2.getString(1);
					//!!获取系统时间
					Calendar cal = Calendar.getInstance(); 
					//cal.setTimeInMillis(long millis)
					week = cal.get(Calendar.WEEK_OF_YEAR);
					year = cal.get(Calendar.YEAR);
					ResultSet rs3 = dd.query("select count(*) from signal_record " + "where emp_id = ? and year = ? and week = ?", rs1.getInt(1), year, week);
					if (rs3.next())
					{
						if (rs3.getInt(1) > 0)
						{
							state = UserState.DONE;
						}
						System.out.println("数据库中本周当前用户提交的记录数量：" + rs3.getInt(1));
					}
				}
			}
			dd.closeConn();
			
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
	}
	
	public UserModel(int id)
	{
		this.id = id;
		this.state = UserState.NOT_DONE;
		
		try
		{
			//链接数据库，初始化用户信息
			DbDao dd = new DbDao();
			ResultSet rs1 = dd.query("select emp_name, region_id, emp_level, emp_desc from emp_info " + "where emp_id = ?", id);
			//根据查询的结果初始化用户信息
			if (rs1.next())
			{
				name = rs1.getString(1);
				desc = rs1.getString(4);
				level = rs1.getInt(3);
				ResultSet rs2 = dd.query("select region_name from region_info " + "where region_id = ?", rs1.getInt(2));
				if (rs2.next())
				{
					region = rs2.getString(1);
					//!!获取系统时间
					Calendar cal = Calendar.getInstance(); 
					//cal.setTimeInMillis(long millis)
					week = cal.get(Calendar.WEEK_OF_YEAR);
					year = cal.get(Calendar.YEAR);
					//查询指定id,year,week历史提交记录，用来判断该用户的状态。
					ResultSet rs3 = dd.query("select count(*) from signal_record " + "where emp_id = ? and year = ? and week = ?", id, year, week);
					if (rs3.next())
					{
						if (rs3.getInt(1) > 0)
						{
							state = UserState.DONE;
						}
						System.out.println("数据库中本周当前用户提交的记录数量：" + rs3.getInt(1));
					}
				}
			}
			dd.closeConn();
			
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
	}
	//指定条件userMoel的构造器
	public UserModel(int id, int year, int week)
	{
		this.id = id;
		this.state = UserState.NOT_DONE;
		this.year = year;
		this.week = week;
		
		try
		{
			//链接数据库，初始化用户信息
			DbDao dd = new DbDao();
			ResultSet rs1 = dd.query("select emp_name, region_id, emp_level, emp_desc from emp_info " + "where emp_id = ?", id);
			//根据查询的结果初始化用户信息
			if (rs1.next())
			{
				name = rs1.getString(1);
				desc = rs1.getString(4);
				level = rs1.getInt(3);
				ResultSet rs2 = dd.query("select region_name from region_info " + "where region_id = ?", rs1.getInt(2));
				if (rs2.next())
				{
					region = rs2.getString(1);
					//查询指定id,year,week历史提交记录，用来判断该用户的状态。
					ResultSet rs3 = dd.query("select count(*) from signal_record " + "where emp_id = ? and year = ? and week = ?", id, year, week);
					if (rs3.next())
					{
						if (rs3.getInt(1) > 0)
						{
							state = UserState.DONE;
						}
						System.out.println("数据库中本周当前用户提交的记录数量：" + rs3.getInt(1));
					}
				}
			}
			dd.closeConn();
			
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
	}
	
	//返回指定日期所有用户的usermodel
	public ArrayList<UserModel> getAllUser(int year, int week)
	{
		ArrayList<UserModel> userModelSet = new ArrayList<>(50);
		
		try
		{
			//链接数据库，查询用户信息
			DbDao dd = new DbDao();
			ResultSet rs = dd.query("select emp_id from emp_info");
			//查询所有用户id
			while (rs.next())
			{
				int id = rs.getInt(1);
				userModelSet.add(new UserModel(id, year, week));
			}
					
			dd.closeConn();
			
		}
		catch(Exception e)
		{
			System.out.println("SQL ERR!");
			e.printStackTrace();
		}
		
		return userModelSet;
	}
	
	public void setId()
	{
		
	}
	
	public int getId()
	{
			return id;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
			return name;
		
	}
	
	public void setRegion()
	{
	}
	
	public String getRegion()
	{
			return region;
	}
	
	public void setDesc()
	{
	}
	
	public String getDesc()
	{
			return desc;
	}
	
	public void setState(UserState state)
	{
		this.state = state;
	}
	
	public UserState getState()
	{
			return state;
	}
	
	public void setWeek()
	{
		
	}
	
	public int getWeek()
	{
			return week;
	}
	
	public void setYear()
	{
		
	}
	
	public int getYear()
	{
			return year;
	}
	
	public void setLevel()
	{
		
	}
	
	public int getLevel()
	{
			return level;
	}

}