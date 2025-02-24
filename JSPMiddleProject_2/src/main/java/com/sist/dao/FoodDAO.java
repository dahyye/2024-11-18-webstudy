package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	//SqlSessionFactoryBean => String
	//sax parse => 이런 오류가 뜨면 xml이 틀렸음
	/*
	 	XML파싱
	 	1. dom -> xml 트리형태로 저장 후 처리 => CRUD 가능 / 속도가 느리다
	 	2. sax -> 대부분
	 	 		  태그를 한개씩 읽어서 처리 => 읽기 전용 / 속도가 빠르다
	 	 ================ data.go.kr / 카페 / 뉴스.. 등
	 
	 */
	static
	{
		try {
			//xml파일 읽기 => config.xml => mapper포함
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * <select id="foodListData" resultType="FoodVO"> SELECT fno,name,poster,num
	 * FROM (SELECT fno,name,poster,rownum as num FROM (SELECT fno, name, poster
	 * FROM food_menupan ORDER BY fno ASC)) WHERE num BETWEEN #{start} AND #{end}
	 * 
	 * </select>
	 * 
	 * <select id="foodTotalPage" resultType="int"> SELECT CEIL(COUNT(*)/12.0) FROM
	 * food_menupan </select>
	 */
	
	public static List<FoodVO> foodListData(Map map)
	{
		return ssf.openSession().selectList("foodListData",map);
	}
	
	public static int foodTotalPage()
	{
		return ssf.openSession().selectOne("foodTotalPage");
	}
	
	/*
	  <update id="hitIncrement" parameterType="int">
 	  UPDATE food_menupan SET
 	  hit=hit+1
 	  WHERE fno=#{fno}
 	  <!-- 
 	  	<include refid="where-fno"/>
 	  	반복적이고 긴 코딩이 있을 때 include활용	
 	  -->
		 	  
 	 </update>
 	 
 	 <select id="foodDetailData" resultType="FoodVO" parameterType="int">
	   SELECT name,type,phone,address,theme,poster,images,time,parking,content,price,score,hit
	   FROM food_menupan 
	   WHERE fno=#{fno}
	 
	 
	 */
	public static FoodVO foodDetailDate(int fno)
	{
		ssf.openSession(true).update("hitIncrement", fno);
		//true => autocommit
		return ssf.openSession().selectOne("foodDetaiData", fno);
		
	}
}
