package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;

public class FoodDAO {

	private static SqlSessionFactory ssf;
	static
	{
		//xml을 읽어서 필요한 데이터 추출 => 파싱
		/*
		 	HTML : jSoup
		 	XML : DocumentBuiler
		 	JSON : json-simple,jackson
		 	
		 	MyBatis / Spring / Spring-Boot => 자동으로 파싱	 
		 		 
		 */
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static List<FoodVO> foodFindData(Map map)
	{
		//연결
		SqlSession session=ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		//닫기
		session.close();
		return list;
	}
	public static int foodFindTotalPage(String address)
	{
		SqlSession session=ssf.openSession();
		int total=session.selectOne("foodFindTotalPage",address);
		session.close();
		return total;
	}
	public static FoodVO foodFindDetailData(int fno)
	{
		SqlSession session=ssf.openSession();
		FoodVO vo=session.selectOne("foodFindDetailData",fno);
		session.close();
		return vo;
	}
}
