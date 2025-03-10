package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 <select id="memberIdcheck" resultType="int" parameterType="MemberVO">
	  SELECT COUNT(*) FROM project_member
	  WHERE id=#{id}
	 </select>
	
	 */
	public static int memberIdcheck(String id)
	{
		SqlSession session=ssf.openSession();
		int count=session.selectOne("memberIdcheck",id);
		session.close();
		return count;
	}
	
	/*
	 <insert id="memberInsert" parameterType="MemberVO">
	  INSERT INTO project_member VALUES(
	  	#{id},#{pwd},#{name},#{sex},#{birthday},
	  	#{email},#{post},#{addr1},#{addr2},
	  	#{phone},#{content},'n'
	  )  
	 </insert>
	 
	 */
	public static void memberInsert(MemberVO vo)
	{
		System.out.println("Insert진입");
		SqlSession session=ssf.openSession(true);
		session.insert("memberInsert",vo);
		System.out.println("Insert완료");
		session.close();
	}
	
	public static MemberVO memberLogin(String id,String pwd)
	{
		
		MemberVO vo = new MemberVO();
		SqlSession session=ssf.openSession();
		int count=session.selectOne("memberIdCheckCount",id);
		
		if(count==0)
		{
			//아이디가 없는 상태
			vo.setMsg("NOID");
			//System.out.println(vo.getMsg());
		}
		else
		{
			
			vo=session.selectOne("memberGetPassword",id);
			
			if(pwd.equals(vo.getPwd()))
			{
				vo.setMsg("OK");
				System.out.println(vo.getMsg());
			}
			else
			{
				vo.setMsg("NOPWD");
				System.out.println(vo.getMsg());
			}
		}
		session.close();
		return vo;
	}
	
}
