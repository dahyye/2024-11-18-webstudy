package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
		//공통모듈(AOP)로 클래스화시켜서 유지보수가 좋다
		//com.sist.commons
	}
	/*
	 <select id="recipeTodayChef" resultType="ChefVO">
	 SELECT * 
	 FROM (SELECT * FROM chef ORDER BY DBMS_RANDOM.RANDOM)
	 WHERE rownum&lt;=1
	 
	 */
	public static ChefVO recipeTodayChef()
	  {
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
	  }
	  public static List<RecipeVO> recipeListData(Map map)
	  {
		  List<RecipeVO> list=null;
		  SqlSession session=null;
		  try
		  {
			  session=ssf.openSession();
			  list=session.selectList("recipeListData",map);
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
		  return list;
	  }
	  
	  public static List<RecipeVO> recipeData7()
	  {
		  List<RecipeVO> list=null;
		  SqlSession session=null;
		  try
		  {
			  session=ssf.openSession();
			  list=session.selectList("recipeData7");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
		  return list;
	  }
	  
	  public static int recipeTotalPage()
	  {   
		  int total=0;
		  SqlSession session=null;
		  try
		  {
			  session=ssf.openSession();
			  total=session.selectOne("recipeTotalPage");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
		  return total;
	  }
	  
	  /*
	   
	    <select id="recipeChefListData" resultType="ChefVO" parameterType="hashpmap">
		  SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2, num
		  FROM (SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2, rownum as num
		  FROM (SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2
		  FROM chef ORDER BY no ASC))
		  WHERE num BETWEEN #{start} AND #{end}
		 </select>
		 <select id="recipeChefTotalPage" resultType="int">
		 SELECT CEIL(COUNT (*)/30.0) FROM chef
		 </select>
		 	   
	   */
	  
	  public static List<ChefVO> recipeChefListData(Map map)
	  {
		  SqlSession session=ssf.openSession();
		  List<ChefVO> list = session.selectList("recipeChefListData", map);
		  System.out.println(list.size());
		  session.close();
		  return list;
	  }
	  
	  public static int recipeChefTotalPage()
	  {
		  SqlSession session=ssf.openSession();
		  int total = session.selectOne("recipeChefTotalPage");
		  session.close();
		  return total;
	  }
	  
	  
	  
	}