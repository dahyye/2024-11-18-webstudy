package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static BoardDAO dao;
	
	public BoardDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static BoardDAO newInstance()
	{
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	
	
	//기능
	//1. 목록 

	public List<BoardVO> boardListData(int page)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		try { //sql.* -> 컴파일 예외처리 -> CheckException
			getConnection();
			//페이지나누기(인라인뷰)
			String sql="SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD'), hit, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+ "FROM (SELECT no,subject, name, regdate, hit FROM htmlBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				BoardVO vo= new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
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

	//1-1. 총페이지
	public int boardTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM htmlBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return total;
	}

	//2. 상세보기 select
	public BoardVO boardDetailData(int num)
	{
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql="";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	//3. 글쓰기 insert
	//4. 수정 update
	//5. 삭제 delete
}
