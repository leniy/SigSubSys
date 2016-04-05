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
	//��ȡ���ݿ�����
	public Connection getConnection() throws Exception
	{
		if (conn == null)
		{
			Context ctx = new InitialContext(); 
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/catv_jn");
			//��ȡ���ݿ�����
		    conn = ds.getConnection();
		}
		return conn;
	}
	//�����¼
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
	//ִ�в�ѯ
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
	//ִ���޸�
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
	//�ر����ݿ����ӵķ���
	public void closeConn()
		throws Exception
	{
		if (conn != null && !conn.isClosed())
		{
			conn.close();
		}
	}
}