package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static DataBoardDAO dao;
	
	public DataBoardDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static DataBoardDAO newInstanse()
	{
		if(dao==null)
			dao=new DataBoardDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL, "hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void disConnection()
	{
		try {
			if(ps!=null)
				ps.close();	
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//기능
}
