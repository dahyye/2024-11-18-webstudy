package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.GoodsVO;

import java.io.*;
//2점대 버전에서는 iBatis, 3점대부터는 MyBatis
public class GoodsDAO {
	private static SqlSessionFactory ssf;
	//관리자 -> 연결 -> sql문장 처리
	static {
		
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		finally {
			
		}
	}
	
	//기능처리
	public static List<GoodsVO> goodsListData(Map map)
	{
		return ssf.openSession().selectList("goodsListData", map);
	}
	
	
	public static int goodsTotalPage()
	{
		
		return ssf.openSession().selectOne("goodsTotalPage");
		
	}
	
	
}
