package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class MusicDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static MusicDAO dao;
	
	public MusicDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static MusicDAO newInstance()
	{
		if(dao==null)
			dao=new MusicDAO();
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
	
	//리스트출력
	public List<MusicVO> musicListData(int page)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, poster, num FROM (SELECT mno,title,poster, rownum as num FROM (SELECT /*+ INDEX_ASC(genie_music gm_mno_pk)*/mno, title,poster FROM genie_music)) WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			int rowsize=12;
			int start = (rowsize*page)-(rowsize-1);
			int end=(rowsize*page);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	
	//전체페이지
	public int Musictotalpage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM genie_music";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
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
	//상세보기
	
	public MusicVO musicDetailData(int mno)
	{
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			//조회수 증가하는 부분
			String sql="UPDATE genie_music SET hit=hit+1 WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			//상세보기 데이터 가져오기
			sql="SELECT title, singer, album, poster, idcrement, state, key, hit FROM genie_music WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString("title"));
			vo.setSinger(rs.getString("singer"));
			vo.setAlbum(rs.getString("album"));
			vo.setPoster(rs.getString("poster"));
			vo.setIncrement(rs.getInt("idcrement"));
			vo.setState(rs.getString("state"));
			vo.setKey(rs.getString("key"));
			vo.setHit(rs.getInt("hit"));
			
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
	
	
	
	//cookie 데이터 
	public MusicVO musicCookieData(int mno)
	{
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql="SELECT mno, title, poster FROM genie_music WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
