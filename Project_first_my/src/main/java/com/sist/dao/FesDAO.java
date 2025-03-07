package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class FesDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/*
		<select id="fesMainHouseData" resultType="FesVO">
		SELECT * FROM (SELECT * FROM content
		      ORDER BY DBMS_RANDOM.RANDOM
		    )
		    WHERE rownum&lt;=1
		</select>
		
		<select id="fesMainHouseData8" resultType="FesVO">
		    SELECT * FROM (SELECT * FROM content 
		      ORDER BY DBMS_RANDOM.RANDOM
		    )
		    WHERE rownum&lt;=8
		  </select>	 
	
	 */
	
	public static FesVO fesMainHouseData()
	{
		System.out.println("DAO진입");
		SqlSession session = ssf.openSession();
		System.out.println("vo값 받아오기");
		FesVO vo = session.selectOne("fesMainHouseData");
		System.out.println("vo값 받아오기완료");
		session.close();
		return vo;
	}
	
	public static List<FesVO> fesMainHouseData8()
	{
		SqlSession session=ssf.openSession();
		List<FesVO> list=session.selectList("fesMainHouseData8");
		session.close();
		return list;
	}
	
	/*
	 <select id="fesListData" resultType="FesVO" parameterType="hashmap">

   SELECT content_id, title, contenttype_id, addr1, tel, event_startdate, event_enddate,agelimit, num
	FROM (SELECT content_id, title, contenttype_id, addr1, tel, event_startdate, event_enddate,agelimit, rownum as num
	FROM (SELECT content.content_id, title, contenttype_id, addr1, tel, event_startdate, event_enddate,agelimit 
	FROM content, event
	WHERE content.content_id=event.content_id))
	WHERE num BETWEEN #{start} AND #{end}  
   
</select>

<select id="fesTotalPage" resultType="int">
  SELECT CEIL(COUNT(*)/12.0) 
  FROM content,event 
  WHERE content.content_id=event.content_id
  
</select>
		 
	 */
	
	public static List<FesVO> fesListData(Map map)
	{
		List<FesVO> list=null;
		SqlSession session=null;
		try
		{
		  session=ssf.openSession();
		  list=session.selectList("fesListData",map);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		  e.printStackTrace();
		}
		finally
		{
		  if(session!=null)
			  session.close();
		}
		return list;
		
	}
	
	public static int fesTotalPage()
	{
		int total=0;
		SqlSession session=null;
		try
		{
		 session=ssf.openSession();
		 total=session.selectOne("fesTotalPage");
		}
		catch (Exception e) 
		{
		// TODO: handle exception
		 e.printStackTrace();
		}
		finally
		{
		 if(session!=null)
		  session.close();
		}
		  
		return total;
	}
	
	/*
	 <select id="fesDetailData" resultType="FesVO" parameterType="int">
	  SELECT * 
	  FROM content,event,event_detail
	  WHERE content.content_id=event.content_id 
	  	AND content.content_id=event_detail.content_id 
	  	AND content.cat2='A0207'


	 */
	
	public static FesVO fesDetailData(int content_id)
	{
		FesVO vo=null;
		SqlSession session=null;
		try
		{
		  session=ssf.openSession();
		  vo=session.selectOne("fesListData",content_id);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		  e.printStackTrace();
		}
		finally
		{
		  if(session!=null)
			  session.close();
		}
		return vo;
	}
	


}










