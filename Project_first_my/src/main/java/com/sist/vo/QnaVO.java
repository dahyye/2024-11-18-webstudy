package com.sist.vo;
/*
 
 NO          NOT NULL NUMBER         
USER_ID              VARCHAR2(20)   
NAME        NOT NULL VARCHAR2(52)   
EMAIL       NOT NULL VARCHAR2(100)  
PHONE                VARCHAR2(20)   
TYPE        NOT NULL VARCHAR2(52)   
TYPE_DETAIL NOT NULL VARCHAR2(100)  
RESERVE_NO           VARCHAR2(52)   
SUBJECT     NOT NULL VARCHAR2(2000) 
CONTENT     NOT NULL CLOB           
FILTNAME             VARCHAR2(260)  
FILESIZE             NUMBER         
PWD         NOT NULL VARCHAR2(10)   
REGDATE              DATE           
HIT                  NUMBER         
GROUP_ID             NUMBER         
GROUP_STEP           NUMBER         
GROUP_TAB            NUMBER         
ANOK                 CHAR(1)        

 
 */
import java.util.*;

import lombok.Data;

@Data
public class QnaVO {
	private int no, hit, filesize, group_id, group_step, group_tab,count;
	private String user_id, name, email, phone, type, type_detail, reserve_no,subject,content;
	private String filename, pwd, dbday, anok;
	private Date regdate;
}
