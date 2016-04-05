package com.catv;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class DbDao 
{
	private Connection conn;
	
	public DbDao()
	{
		
	}
	//获取数据库连接
	public Connection getConnection() throws Exception
	{
		if (conn == null)
		{
			Context ctx = new InitialContext(); 
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/catv_jn");
			//获取数据库连接
		    conn = ds.getConnection();
		}
		return conn;
	}
	//插入记录
	public boolean insert(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		if (pstmt.executeUpdate() != 1)
		{
			return false;
		}
		pstmt.close();
		return true;
	}
	//执行查询
	public ResultSet query(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		return pstmt.executeQuery();
	}
	//执行修改
	public void modify(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}
	//关闭数据库连接的方法
	public void closeConn()
		throws Exception
	{
		if (conn != null && !conn.isClosed())
		{
			conn.close();
		}
	}
}