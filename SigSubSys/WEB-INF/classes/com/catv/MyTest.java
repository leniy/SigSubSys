package com.catv;

import java.sql.*;
import javax.sql.*;

public class MyTest
{
	public static void main(String[] args)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!test sql!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try
			{
			DbDao db = new DbDao();
			ResultSet rs = db.query("select * from emp_info " + "where emp_name = ?", "admin");
			while (rs.next())
			{
				System.out.println(rs.getString(1) + ", " + rs.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
}