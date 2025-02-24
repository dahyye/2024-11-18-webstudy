package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.*;
import com.sist.vo.*;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	//xml의 데이터를 저장 -> SqlSessionFactory
	//insert/update/delete/select => 관리 => SqlSession
	//SqlSession은 SqlSessionFactory가 생성
	//SqlSession session=ssf.openSession()
	//connection / preparedstatement => close() 반환
	static 
	{
		try
		{
			Reader reader= Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public static List<BoardVO> boardListData(Map map)
	{
		SqlSession session=ssf.openSession();
		List<BoardVO> list = session.selectList("boardListData");
		session.close(); //POOL로 반환
		return list;
	}
	
	public static int boardTotalPage()
	{
		SqlSession session=ssf.openSession();
		int total = session.selectOne("boardTotalPage");
		session.close(); //POOL로 반환
		return total;
	}
	
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session = ssf.openSession();
		session.insert("boardInsert",vo);
		session.commit();
		//여기서 commit을 날리거나 ssf.openSessoin(true)로 commit을 날리거나
		//둘 중 하나의 방법으로 사용하기
		session.close();
	}
}
