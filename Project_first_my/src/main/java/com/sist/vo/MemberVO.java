package com.sist.vo;

import lombok.Data;

/*


이름       널?       유형            
-------- -------- ------------- 
ID       NOT NULL VARCHAR2(20)  
PWD      NOT NULL VARCHAR2(10)  
NAME     NOT NULL VARCHAR2(51)  
SEX               VARCHAR2(20)  
BIRTHDAY NOT NULL VARCHAR2(30)  
EMAIL             VARCHAR2(100) 
POST     NOT NULL VARCHAR2(20)  
ADDR1    NOT NULL VARCHAR2(200) 
ADDR2             VARCHAR2(200) 
PHONE             VARCHAR2(20)  
CONTENT           CLOB          
ADMIN             CHAR(1)       

프로젝트 user테이블
이름          널?       유형            
----------- -------- ------------- 
USER_ID     NOT NULL VARCHAR2(50)  
PWD         NOT NULL VARCHAR2(50)  
NAME        NOT NULL VARCHAR2(50)  
NICKNAME    NOT NULL VARCHAR2(50)  
PROFILE_IMG          VARCHAR2(300) 
SEX         NOT NULL VARCHAR2(10)  
BIRTHDAY    NOT NULL DATE          
EMAIL       NOT NULL VARCHAR2(100) 
POST        NOT NULL VARCHAR2(7)   
ADDR1       NOT NULL VARCHAR2(100) 
ADDR2                VARCHAR2(100) 
PHONE       NOT NULL VARCHAR2(14)  
ADMIN                CHAR(1)       

*/
@Data
public class MemberVO {
	private String user_id,pwd,name,nickname,profile_img,sex,birthday,email,post;
	private String addr1,addr2,phone,admin,msg;

}
