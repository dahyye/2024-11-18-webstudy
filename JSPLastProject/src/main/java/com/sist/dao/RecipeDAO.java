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
	  /*
	   
	   1. MyBatis
	   	DML => select update, delete, insert, sql
	   			|
	   		resultMap : join / SUBQUERY
	   		
	   	동적 쿼리 
	   		=> <trim> : 추가 / 제거
	   		   <bind>  : 변수형 = 문장이 긴 경우
	   		   	<bind name="likes" value="'%'||#{ss}||'%'">
	   		   		#{likes}
	   		   <foreach> : in연산자 데이터 여러개(checkbox)
	   		   	<foreach collection="arr" item="no">
	   		   		=> for(int no:arr)
	   		   		=> 배열이나 컬랙션이 넘어갈 때 반드시 Map에 채워서 설정해야한다
	   		   <where> 
	   		     <where>
	   		      <if test="조건">AND id=#{id}</if>
	   		      <if test="조건">AND pwd=#{pwd}</if>
	   		     </where>
	   		     => 시작할 떄 and가 들어가는게 문제가 된다 
	   		   <if> 
	   		    <if test="id!=null"> id==''
	   		    					 id==null
	   		   <choose> : 다중조건문
	   		    <choose>
	   		     <when test=""></when>
	   		     <when test=""></when>
	   		     <otherwise></otherwise>
	   		    </choose>
	   		    
	   			
	   
	   
	   */
	  
	  /*
	  SELECT no, title, poster, chef, hit, likecount, replycount, num
	   FROM (SELECT no, title, poster, chef, hit, likecount, replycount, rownum as num 
	   FROM (SELECT + INDEX_ASC(recipe recipe_no_pk)no, title, poster, chef, hit, likecount, replycount
	   FROM recipe
	   WHERE 
	   
	   바인드 사용법
	    <bind name="likes" value="'%'+ss+'%'">
	    <trim prefix="(" suffix=")" prefixOverrides="OR">
	   	<!-- <trim prefix="WHERE (" suffix=")" prefixOverrides="OR">  --><!-- 검색데이터가 없다면 WHERE문 다음에 문장이 없기 때문에 전체가 다 들어온다 -->
	   	 <foreach collection="findArr" item="type">http://localhost/Project_first_my/fes/fes_list.do
	   	  <trim prefix="OR">
	   	   <choose> <!-- 조건문이 여러개일 땐 choose 하나일 땐 if -> choose는 다중조건문 -->
	   	    <when test="type=='T'.toString()"> <!-- toString으로 변환 필요 -->
	   	     title LIKE #{likes}    
	   	    </when>
	   	    <when test="type=='C'.toString()">
	   	     chef LIKE #{likes}  	    
	   	    </when>
	   	   </choose>
	   	  </trim>
	   	 </foreach>
	   	</trim>
	   ))
	   WHERE num BETWEEN #{start} AND #{end}
	    
	    
	 </select>
	  
	  */
	  
	  public static List<RecipeVO> recipeFindData(Map map)
	  {
		  SqlSession session=ssf.openSession();
		  List<RecipeVO> list = session.selectList("recipeFindData", map);
		  session.close();
		  return list;
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	}