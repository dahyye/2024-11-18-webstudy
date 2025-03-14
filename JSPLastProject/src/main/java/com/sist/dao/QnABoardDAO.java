package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class QnABoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 
	<select id="qnaListData" resultType="QnABoardVO" parameterType="hashmap">
	 SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num
	 FROM (SELECT no,subject,name,regdate as dbday,hit,group_tab,rownum as num 
	 FROM (SELECT no,subject,name,regdate as dbday,hit,group_tab
	 FROM qnaBoard ORDER BY group_id DESC,group_step ASC))
	 WHERE num BETWEEN #{start} AND #{end}
	</select>
	
	<select id="qnaRowCount" resultType="int">
	 SELECT COUNT(*) 
	 FROM qnaBoard
	</select>
	
	<insert id="qnaInsert" parameterType="QnABoardVO">
	 INSERT INTO qnaBoard(no,id,name,subject,content,pwd,group_id) 
	 VALUES((SELECT NVL(MAX(no)+1,1) FROM qnaBoard), #{id}, #{name}, #{subject}, #{content}, #{pwd}, (SELECT NVL(MAX(group_id)+1,1) FROM qnaBoard))
	</insert>	
	 
	 */
	public static List<QnABoardVO> qnaListData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<QnABoardVO> list = session.selectList("qnaListData",map);
		session.close();
		return list;
	}
	
	public static int qnaRowCount()
	{
		SqlSession session = ssf.openSession();
		int count = session.selectOne("qnaRowCount");
		session.close();
		return count;
	}
	
	public static void qnaInsert(QnABoardVO vo)
	{
		SqlSession session = ssf.openSession(true);
		session.insert("qnaInsert",vo);
		session.close();
	}
	
	public static void qnaDelete(int group_id)
	{
		SqlSession session = ssf.openSession(true);
		session.insert("qnaDelete",group_id);
		session.close();
	}
	
	/*
	<update id="qnaHitIncrement" parameterType="int">
	 UPDATE qnaBoard SET
	  hit=hit+1
	 WHERE no=#{no} 
	</update>
	<select id="qnaDetailData" resultType="QnABoardVO" parameterType="int">
	 SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday
	 FROM qnaBoard
	 WHERE no=#{no}
	</select>
	*/
	public static QnABoardVO qnaDetailData(int no)
	{
		SqlSession session = ssf.openSession();
		session.update("qnaHitIncrement",no);
		QnABoardVO vo = session.selectOne("qnaDetailData",no);
		session.close();
		return vo;
	}
	/*
	<update id="qnaUpdate" parameterType="QnABoardVO">
	 UPDATE qnaBoard SET
	 subject=#{subject}, content=#{content}
	 WHERE no=#{no}
	</update>
	
	*/
	public static void qnaUpdate(QnABoardVO vo)
	{
		//session.insert(update,delete) 어떤걸 넣어도 같은 결과를 나타내지만
		//가독성이 좋으려면 맞춰서 적어주는게 좋아
		SqlSession session = ssf.openSession(true);
		session.update("qnaUpdate",vo);
		session.close();
	}
	
	public static List<QnABoardVO> qnaAdminListData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<QnABoardVO> list = session.selectList("qnaAdminListData",map);
		session.close();
		return list;
	}
	
	public static int qnaAdminRowCount()
	{
		SqlSession session = ssf.openSession();
		int count = session.selectOne("qnaAdminRowCount");
		session.close();
		return count;
	}
	
	/*
	<update id="qnaAdminAnDeleteOK" parameterType="int">
	 UPDATE qnaBoard SET
	 anok='n'
	 WHERE group_id=#{group_id}
	</update>
	
	*/
	
	
	
	public static void qnaAdminDelete(int group_id)
	{
		SqlSession session=null;
		try {
			session = ssf.openSession();
			session.update("qnaAdminAnDeleteOK",group_id);
			session.insert("qnaAdminDelete",group_id);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback(); //동시에 취소
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
	

	
	/*
	 
	 <select id="qnaAdminDetailData" resultType="QnABoardVO" parameterType="int">
	 SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday, content
	 FROM qnaBoard
	 WHERE group_id=#{group_id}
	</select>
	<update id="qnaAdminAnOKChange" parameterType="int">
	 UPDATE qnaBoard SET
	 anok='y'
	 WHERE group_id=#{group_id}
	</update>
	<insert id="qnaAdminInsert" parameterType="QnABoardVO">
	 INSERT INTO qnaBoard(no,id,name,subject,content,pwd,group_id,group_step, group_tab,anok) 
	 VALUES((SELECT NVL(MAX(no)+1,1) FROM qnaBoard), #{id}, '관리자', #{subject}, #{content}, #{pwd},#{group_id},1,1,'y');
	</insert>
	 
	 */
	
	
	
	public static QnABoardVO qnaAdminDetailData(int group_id)
	{
		SqlSession session = ssf.openSession();
		QnABoardVO vo = session.selectOne("qnaAdminDetailData",group_id);
		session.close();
		return vo;
	}
	
	public static void qnaAdminInsert(QnABoardVO vo)
	{
		//트랜잭션 => 일괄처리 -> 금융권에서 많이 사용
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("qnaAdminAnOKChange",vo.getGroup_id());
			System.out.println("1번성공");
			session.insert("qnaAdminInsert",vo);
			session.commit();//동시에 저장 
			//여기서 true를 안하는 이유는 두개를 동시에 관리하기 위해서
			//true를 사용하면 update만 성공하고 insert는 실패할 수 있다
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback(); //동시에 취소
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
	
	// => 회원 수정도 비슷한 방식 ( 수정하면 안되는 부분 id는 비활성화 필수), pwd는 바꾸는 부분을 따로 해줘야해
	// 비밀번호는 보통 6개월 후에 바꾸라고 하는데 6개월 후를 계산하는 건 오라클의 addMonth()를 활용하면 쉽다
	public static QnABoardVO qnaUpdateData(int no)
	{
		SqlSession session = ssf.openSession();
		QnABoardVO vo = session.selectOne("qnaDetailData",no);
		session.close();
		
		
		return vo;
				
	}
	
}









