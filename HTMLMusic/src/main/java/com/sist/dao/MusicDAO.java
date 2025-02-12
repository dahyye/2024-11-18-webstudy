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
	
	
	public List<MusicVO> musicTypeFind(int page, int cno)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, poster, num FROM (SELECT mno,title,poster,rownum as num FROM (SELECT mno,title,poster FROM genie_music WHERE cno="+cno+")) WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			
			int rowpage=12;
			int start=(rowpage*page)-(rowpage-1);
			int end=rowpage*page;
			
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
	
	
	public int foodTypeTotalPage(int cno)
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM genie_music WHERE cno="+cno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	
	public List<MusicVO> musicFind(int page, String col, String md)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, poster, num "
					+ "FROM (SELECT mno,title,poster, rownum as num "
					+ "FROM (SELECT mno,title,poster "
					+ "FROM genie_music WHERE "+col+" LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setString(1, md);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
	}
	
	
	public int musicFindTotalPage(String col, String md)
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM genie_music WHERE "+col+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, md);
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
	
	public MemberVO memberLogin(String id,String pwd)
	{
		MemberVO vo = new MemberVO();
		try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) FROM member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  // "no="+no
			  // "id='"+id+"'" => ps.setString(1,id)
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  int count=rs.getInt(1);
			  rs.close();
			  
			  if(count==0)//ID가 없는 상태
			  {
				  vo.setMsg("NOID");
			  }
			  else // ID가 있는 상태 
			  {
				  sql="SELECT id,name,sex,pwd "
				     +"FROM member "
					 +"WHERE id=?";
				  
				  ps=conn.prepareStatement(sql);
				  ps.setString(1, id);
				  
				  rs=ps.executeQuery();
				  rs.next();
				  vo.setId(rs.getString(1));
				  vo.setName(rs.getString(2));
				  vo.setSex(rs.getString(3));
				  String db_pwd=rs.getString(4);
				  if(db_pwd.equals(pwd))
				  {
					  vo.setMsg("OK");
				  }
				  else
				  {
					  vo.setMsg("NOPWD");
				  }
				  rs.close();
			  }
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		return vo;
	}
	
	public List<MusicVO> musicHitTop10()
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, poster, hit, rownum "
					+ "FROM (SELECT mno, title, poster, hit FROM genie_music ORDER BY hit DESC) "
					+ "WHERE rownum<=10";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
			    vo.setTitle(rs.getString(2));
			    vo.setPoster(rs.getString(3));
			    vo.setHit(rs.getInt(4));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	
	
	
	
}
