package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class FoodDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  
  /*
   *       <select id="foodMainHouseData" resultType="FoodVO">
		    SELECT * FROM (SELECT * FROM project_food 
		      ORDER BY DBMS_RANDOM.RANDOM
		    )
		    WHERE rownum&lt;=1
		  </select>
		  <select id="foodMainHouseData8" resultType="FoodVO">
		    SELECT * FROM (SELECT * FROM project_food 
		      ORDER BY DBMS_RANDOM.RANDOM
		    )
		    WHERE rownum&lt;=8
		  </select>
   */
  public static FoodVO foodMainHouseData()
  {
	  System.out.println("푸드메인하우스데이터 DAO");
	  SqlSession session=ssf.openSession();
	  FoodVO vo=session.selectOne("foodMainHouseData");
	  session.close();
	  return vo;
  }
  
  public static List<FoodVO> foodMainHouseData8()
  {
	  SqlSession session=ssf.openSession();
	  List<FoodVO> list=session.selectList("foodMainHouseData8");
	  session.close();
	  return list;
  }
  
  /*
   *   <select id="foodListData" resultType="FoodVO" parameterType="hashmap">
	   SELECT fno,name,poster,score,type,content,theme,phone,address,num
	   FROM (SELECT fno,name,poster,score,type,content,theme,phone,address,rownum as num 
	   FROM (SELECT + INDEX_ASC(project_food pf_fno_pk)fno,name,poster,score,type,content,theme,phone,address
	   FROM project_food))
	   WHERE num BETWEEN #{start} AND #{end}
	  </select>
	  <select id="foodTotalPage" resultType="int">
	   SELECT CEIL(COUNT(*)/20.0) FROM project_food
	  </select>
   */
  public static List<FoodVO> foodListData(Map map)
  {
	  SqlSession session=ssf.openSession();
	  List<FoodVO> list=session.selectList("foodListData",map);
	  session.close();
	  return list;
  }
  public static int foodTotalPage()
  {
	  SqlSession session=ssf.openSession();
	  int total=session.selectOne("foodTotalPage");
	  session.close();
	  return total;
  }
  
  /*
    <select id="foodDetailData" resultType="FoodVO" parameterType="int">
   SELECT * FROM project_food
   WHERE fno=#{fno}
  </select>
   */
  public static FoodVO foodDetailData(int fno)
  {
	  SqlSession session=ssf.openSession(true);//true 있으면 자동 commit
	  session.update("foodHitIncrement",fno);
	  //session.commit();
	  FoodVO vo =session.selectOne("foodDetailData", fno);
	  session.close();
	  return vo;
  }
  
  public static FoodVO foodCookieData(int fno)
  {
	  SqlSession session=ssf.openSession();//true 있으면 자동 commit
	  FoodVO vo =session.selectOne("foodDetailData", fno);
	  session.close();
	  return vo;
  }
  
  /*
   <!-- 맛집 검색 -->
 <select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
 SELECT fno,name,poster,score,type,content,theme,phone,address,num
   FROM (SELECT fno,name,poster,score,type,content,theme,phone,address,rownum as num 
   FROM (SELECT /*+ INDEX_ASC(project_food pf_fno_pk)/fno,name,poster,score,type,content,theme,phone,address
   FROM project_food WHERE ${fd} LIKE '%'||#{ss}||'%'))
   WHERE num BETWEEN #{start} AND #{end}
 </select>
 <select id="foodFindTotalPage" resultMap="int" parameterType="hashmap">
  SELECT CEIL(COUNT(*)/12.0)
  FROM project_food
  WHERE ${fd} LIKE '%'||#{ss}||'%'
 </select>
   
   
   */
  /*
   ChefVO vo=null;
		  SqlSession session=null;
		  try
		  {
			  session=ssf.openSession();
			  vo=session.selectOne("recipeTodayChef");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
		  return vo;
	오류처리
   */
  public static List<FoodVO> foodFindData(Map map)
  {
	  List<FoodVO> list=null;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  list=session.selectList("foodFindData",map);
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
  
  public static int foodFindTotalPage(Map map)
  {
	  int total=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  total=session.selectOne("foodFindTotalPage",map);
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
  
  
  
  
  
}
