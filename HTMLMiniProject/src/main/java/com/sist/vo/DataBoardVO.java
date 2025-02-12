package com.sist.vo;
import java.util.Date;
import lombok.Data;

//자료실
//upload / download
/*
 
 	1. DAO => 데이터베이스 설계
 			  -------------
 			  데이터 수집
 			  
 	2. VO 설정
 		HTML 화면 UI => CSS(약간)
 		JavaScript => 브라우저처리
 		=> Bassic
 		=> Jquery => Vue / React
 	
 	3. 전송값 / 출력값
 		<a>, <form>
 			  
 	   
CREATE TABLE databoard(
    no NUMBER,
    name VARCHAR2(51) CONSTRAINT db_name_nn NOT NULL,
    subject VARCHAR2(2000) CONSTRAINT db_sub_nn NOT NULL,
    content CLOB CONSTRAINT db_cont_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT db_pwd_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    filename VARCHAR2(260),
    filesize NUMBER DEFAULT 0,
    CONSTRAINT db_no_pk PRIMARY KEY(no) 
);

CREATE SEQUENCE db_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE 	   
 
 
NO       NOT NULL NUMBER         
NAME     NOT NULL VARCHAR2(51)   
SUBJECT  NOT NULL VARCHAR2(2000) 
CONTENT  NOT NULL CLOB           
PWD      NOT NULL VARCHAR2(10)   
REGDATE           DATE           
HIT               NUMBER         
FILENAME          VARCHAR2(260)  
FILESIZE          NUMBER  
 
 */
@Data
public class DataBoardVO {
	
	private int no, hit,filesize;
	private String name, subject, content, pwd, db_date, filename;
	private Date regdate;
}
