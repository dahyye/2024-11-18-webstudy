package com.sist.dao;
import java.util.*;
import java.util.Date;
/*
 
 -- 자료실 
-- 앞으로 할 예정
-- 맛집(지도), 결제모듈, 다음 우편번호, 아이디중복체크
-- 아이디 / 비밀번호 찾기 -> 메일전송
-- 장바구니, 예약하기, 추천(네이버카페이용)
--마지막에 하나로 합치면 사이트가 나올예정

CREATE TABLE jspDataBoard(
    no NUMBER,
    name VARCHAR2(51)CONSTRAINT jdb_name_nn NOT NULL,
    subject VARCHAR2(2000) CONSTRAINT jdb_sub_nn NOT NULL,
    content CLOB CONSTRAINT jdb_cont_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT jdb_pwd_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    filename VARCHAR2(260),
    filesize NUMBER  DEFAULT 0,
    CONSTRAINT jdb_no_pk PRIMARY KEY(no)
);

CREATE SEQUENCE jdb_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
 
 */
//JSP에서는 Bean이라고 함 => 데이터를 모아서 관리 / 전송
//Spring에서는 VO
//Mybatis에서는 DTO
/*
 
 자료실 한 개에 대한 정보만 가지고 있다
 	1. 목록
// 		DataBoardVO 여러개 -> List<DataBoardVO>
 		=> 검색
 	2. 상세보기
 		DataBoardVO
 
 */
public class DataBoardVO {
	private int no,hit,filesize;
	private String name,subject,content,pwd,filename,dbday;
	private Date regdate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	
	
}
