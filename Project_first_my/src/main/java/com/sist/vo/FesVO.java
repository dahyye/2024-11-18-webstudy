package com.sist.vo;
/*

이름              널?       유형             
--------------- -------- -------------- 
CONTENT_ID      NOT NULL NUMBER         
EVENT_STARTDATE NOT NULL DATE           
EVENT_ENDDATE   NOT NULL DATE           
SPONSOR1                 VARCHAR2(4000) 
SPONSOR2                 VARCHAR2(4000) 
PLAYTIME                 VARCHAR2(4000) 
CHARGE                   VARCHAR2(4000) 
SPENDTIME                VARCHAR2(4000) 
AGELIMIT                 VARCHAR2(4000) 



이름             널?       유형             
-------------- -------- -------------- 
CONTENT_ID     NOT NULL NUMBER         
TITLE                   VARCHAR2(150)  
CONTENTTYPE_ID          NUMBER         
ADDR1                   VARCHAR2(500)  
ADDR2                   VARCHAR2(500)  
ZIPCODE                 VARCHAR2(10)   
SIGUNGUCODE             NUMBER         
AREACODE                NUMBER         
FIRST_IMAGE             VARCHAR2(1000) 
FIRST_IMAGE2            VARCHAR2(1000) 
MAPX                    NUMBER(15,10)  
MAPY                    NUMBER(15,10)  
TEL                     VARCHAR2(400)  
CAT1                    VARCHAR2(50)   
CAT2                    VARCHAR2(50)   
CAT3                    VARCHAR2(50)   



 */

import java.util.*;

import lombok.Data;

@Data
public class FesVO {
	private int content_id,contenttype_id, sigungucode, areacode, mapx, mapy;
	private String title,addr1,addr2,zipcode,first_image,first_image2, origin_img, sponsor1, sponsor2, playtime,charge; 
	private String thumb_img,tel,cat1,cat2,cat3, event_startdate, event_enddate,agelimit, infotext;
}
