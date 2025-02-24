package com.sist.dao;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BoardVO;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	//초기화 => xml에 등록된 데이터를 전송 => ssf;
	static
	{
		try
		{
			//1.xml읽기
			Reader reader= Resources.getResourceAsReader("Config.xml"); //경로명 지정안하기 위해서 src/main/java안에 넣어둔 것 
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//목록 읽어오기
	/*
	<select id="boardListData" resultType="BoardVO" parameterType="hashmap">
   SELECT no, subject, name, regdate, hit, num
   FROM (SELECT no, subject, name, regdate, hit, rownum as num
   FROM (SELECT no, subject, name, regdate, hit 
   FROM board ORDER BY no ASC))
   WHERE num BETWEEN #{start} AND #{end}
  </select>
	 */
	public static List<BoardVO> boardListData(Map map)
	{
		//conn,ps
		SqlSession session=ssf.openSession();
		List<BoardVO> list = session.selectList("boardListData", map);
		//목록은 여러개 -> selectList
		//상세보기는 1개 -> selectOne
		session.close();
		return list;
	}
	//총페이지
	/*
	 <select id="boardTotalPage" resultType="int">
   SELECT CEIL(COUNT(*)/10.0) FROM board
  </select>
	 */
	public static int boardTotalPage()
	{
		SqlSession session=ssf.openSession();
		int total = session.selectOne("boardTotalPage");
		//목록은 여러개 -> selectList
		//상세보기는 1개 -> selectOne
		session.close();
		return total;
		
	}
	//글쓰기
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session=ssf.openSession();
		session.insert("boardInsert",vo);
		session.commit();
		session.close();
	}
	
	//상세보기
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session=ssf.openSession();
		//조회수증가
		session.update("hitIncrement", no);
		session.commit();
		//데이터가지고오기
		BoardVO vo = session.selectOne("boardDetailDate",no);
		//반환
		session.close();
		return vo;
	}
}










