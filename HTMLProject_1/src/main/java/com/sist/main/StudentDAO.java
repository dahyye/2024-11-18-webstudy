package com.sist.main;
import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class StudentDAO {
	
	private Connection conn;
	private CallableStatement cs;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static StudentDAO dao;
	
	public StudentDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static StudentDAO newInstance()
	{
		if(dao==null)
			dao= new StudentDAO();
		return dao;
		
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void disConnection()
	{
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//기능
	//1. 목록출력
	/*
	CREATE OR REPLACE PROCEDURE studentAllData(
	  pResult OUT SYS_REFCURSOR
	)
	IS
	BEGIN
	  OPEN pResult FOR 
	  SELECT * FROM student;
	END;
	/	 
	 */
	public List<StudentVO> studentAllData()
	{
		List<StudentVO> list = new ArrayList<StudentVO>();
		try {
			getConnection();
			String sql="{CALL studentAllData(?)}";
			cs=conn.prepareCall(sql); //전송
			cs.registerOutParameter(1, OracleTypes.CURSOR); //orcal.jdbc 에서 가져오기
			//INTEGER(정수), VARCHAR, CURSOR
			//실행요청
			cs.executeQuery(); //모든 실행 insert / delete / update
			//CURSOR-> ResultSet
			ResultSet rs = (ResultSet)cs.getObject(1);
			
			while(rs.next())
			{
				StudentVO vo = new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				list.add(vo);
				
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	//2. 등록
	
	//3. 수정
	//4. 삭제
	//5. 상세보기
	/*
	CREATE OR REPLACE PROCEDURE studentDetailDate(
    pHakbun student.hakbun%TYPE,
    pResult OUT SYS_REFCURSOR
	)
	IS
	 
	BEGIN
	  OPEN pResult FOR
	    SELECT *
	    FROM student
	    WHERE hakbun=pHakbun;
	END;
	/

	 
	*/
	public StudentVO studentDetailDate(int hakbun)
	{

		StudentVO vo = new StudentVO();
		try {
			getConnection();
			String sql="{CALL studentDetailDate(?,?)}";
			cs=conn.prepareCall(sql); //전송
			cs.setInt(1, hakbun); //in변수는 동일
			cs.registerOutParameter(2, OracleTypes.CURSOR); //orcal.jdbc 에서 가져오기
			
			//INTEGER(정수), VARCHAR, CURSOR
			//실행요청
			cs.executeQuery(); //모든 실행 insert / delete / update
			//CURSOR-> ResultSet
			ResultSet rs = (ResultSet)cs.getObject(2);//cursor위치랑 맞춰줘야해(저장된위치)
			rs.next();
			
			vo.setHakbun(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setKor(rs.getInt(3));
			vo.setEng(rs.getInt(4));
			vo.setMath(rs.getInt(5));
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	
	
	
}
