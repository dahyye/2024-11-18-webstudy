package com.sist.dao;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.EmpVO;
import com.sist.vo.FoodVO;
import com.sist.dao.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	static 
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static List<EmpVO> empListData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list = session.selectList("empListData");
		System.out.println(list.size());
		session.close();
		return list;
	}
	
	public static List<FoodVO> foodTypeListData(String type)
	{
		SqlSession session=ssf.openSession();
		List<FoodVO> list=session.selectList("foodTypeListData", type);
		session.close();
		return list;
	}
	
}
