package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao;

	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	public FoodDAO()
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
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//기능
	//1. 목록출력
	public List<FoodVO> foodListData(int page)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql="SELECT fno, name, poster,num FROM (SELECT fno,name,poster,rownum as num FROM(SELECT /*+ INDEX_ASC(food_menupan fm_fno_pk)*/fno,name,poster FROM food_menupan)) WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			
			int rowsize=12; 
			int start= rowsize*page-(rowsize-1);
			int end= rowsize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
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
	//2. 총 페이지
	public int foodTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan";
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
	
	//3. 상세보기
	public FoodVO foodDetailData(int fno)
	{
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql="UPDATE food_menupan SET hit=hit+1 WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="SELECT name, type, phone, address, score, theme, poster, images, time, parking, content, hit, price FROM food_menupan WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setName(rs.getString("name"));
			vo.setType(rs.getString("type"));
			vo.setPhone(rs.getString("phone"));
			vo.setAddress(rs.getString("address"));
			vo.setScore(rs.getDouble("score"));
			vo.setTheme(rs.getString("theme"));
			vo.setPoster("https://www.menupan.com"+rs.getString("poster"));
			vo.setImages(rs.getString("images"));
			vo.setTime(rs.getString("time"));
			vo.setParking(rs.getString("parking"));
			vo.setContent(rs.getString("content"));
			vo.setHit(rs.getInt("hit"));
			vo.setPrice(rs.getString("price"));
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
	public FoodVO foodCookieData(int fno)
	{
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql="SELECT fno, name, poster FROM food_menupan WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPoster("https://www.menupan.com"+rs.getString(3));
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
	
	//음식종류별 검색
	public List<FoodVO> foodTypeFind(int page, String type)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			//페이지별 출력
			String sql="";
			int rowsize=12; 
			int start= rowsize*page-(rowsize-1);
			int end= rowsize*page;
			//종류가 기타인 경우
			if(!type.equals("기타"))
			{
				sql="SELECT fno, name, poster, num "
						+ "FROM (SELECT fno, name, poster, rownum as num "
						+ "FROM (SELECT fno, name, poster FROM food_menupan WHERE type LIKE '%'||?||'%')) "
						+ "WHERE num BETWEEN ? AND ?";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, type);
				ps.setInt(2, start);
				ps.setInt(3, end);
			}
			else 
			{
				sql="SELECT fno, name, poster, num "
						+ "FROM (SELECT fno, name, poster, rownum as num "
						+ "FROM (SELECT fno, name, poster FROM food_menupan WHERE NOT REGEXP_LIKE(type,'한식|양식|중식|일식|카페'))) "
						+ "WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, start);
				ps.setInt(2, end);
			}
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
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
	
	public int foodTypeTotalPage(String type) 
	{
		int total=0;
		try {
			getConnection();
			String sql="";
			if(!type.equals("기타"))
			{
				sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan WHERE type LIKE '%'||?||'%'";
				ps=conn.prepareStatement(sql);
				ps.setString(1, type);
			}
			else
			{
				sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan "
						+ "WHERE NOT REGEXP_LIKE(type,'한식|양식|중식|일식|카페')";
				ps=conn.prepareStatement(sql);
				
			}
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
	
	public List<FoodVO> foodFind(int page, String col, String fd)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql="SELECT fno,name,poster,address,type,num "
					+ "FROM (SELECT fno,name,poster,address,type,rownum as num "
					+ "FROM (SELECT fno,name,poster,address,type "
					+ "FROM food_menupan WHERE "+col+" LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setString(1, fd);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setType(rs.getString(5));
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
	
	public int foodFindTotalPage(String col, String fd)
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM food_menupan WHERE "+col+" LIKE '%'||?||'%'";
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
	
}

