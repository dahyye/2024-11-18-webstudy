package com.sist.dao;
import java.util.*;
import java.sql.*;
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
	
	public static DataBoardDAO newInstance()
	{
		if(dao==null)
			dao=new DataBoardDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
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
	//JDBC -> DBCP -> MyBatis(XML(JSP), Annotation(String))
	/*
	 	과정
	 	JSP활영 => 18일
	 	--------------
	 	Spring : MVC => SpringFramework 직접 개발
	 
	 */
	//목록+총페이지
	public List<DataBoardVO> dataBoardListData(int page)
	{
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		try {
			getConnection();
			String sql="SELECT no, subject, name, regdate, hit, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+ "FROM (SELECT no, subject, name, regdate, hit "
					+ "FROM jspDataBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			int rowsize=10;
			int start = (rowsize*page)-(rowsize-1);
			int end=rowsize*page;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				DataBoardVO vo = new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	
	public int databoardTotalPage()
	{
		int totalpage=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM jspDataBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			totalpage=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return totalpage;
	}
 	
	//상세보기 -> 다운로드
	public DataBoardVO dateboardDetail(int no)
	{
		DataBoardVO vo = new DataBoardVO();
		try {
			getConnection();
			String sql="UPDATE jspDataBoard SET hit=hit+1 WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="SELECT no, name, subject, content, regdate, hit, filename,filesize FROM jspDataBoard WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	
	//추가 -> 업로드
	public void databoardInsert(DataBoardVO vo)
	{
		try {
			getConnection();
			String sql="INSERT INTO jspDataboard VALUES(jdb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		finally {
			disConnection();
		}
	}
	//수정
	//삭제 -> 댓글먼저 삭제 -> 게시물
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
