package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*; // jdbc/oracle을 찾기 위해서 사용
/*
 	  POOL
 	  => 메모리주소 : java://comp/env
 	----------
 	  conn(주소) ==> 오라클 연결
 	  => 사용중이면 true
 	  	 사용중이 아니면 false
 	----------
 	  conn(주소)
 	----------
 	  conn(주소)
 	----------
 	  conn(주소)
 	----------
 	..
 	..
 	
 	
 
 
 */

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//연결되어 있는 POOL안에 Connection 주소를 얻어온다
	public void getConnection()
	{
		//메모리 저장형식 -> 탐색기와 비슷
		//JNDI
		try {
			//1. 탐색기를 연다
			Context init=new InitialContext();
			//2. c드라이버에 접근
			Context c=(Context)init.lookup("java://comp/env");
			//3. 해당 Connection 주소를 찾아온다
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//POOL로 반환 => 재사용
	public void disConnection()
	{
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
			//자동으로 반환
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//드라이버등록이나 싱글톤을 사용안함 -> 미리 오라클을 연결시켰기 때문에 드라이버 등록 필요없음
	//싱글톤을 안쓰는 이유는 액션태그를 사용해서 그 기능을 대채하기 위해

	
	public List<EmpBean> empListData()
	{
		List<EmpBean> list = new  ArrayList<EmpBean>();
		try 
		{
			//미리 생성된 Connection 주소를 얻어온다
			getConnection();
			String sql="SELECT empno, ename, job, hiredate, sal FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				EmpBean vo = new EmpBean();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}

}





















