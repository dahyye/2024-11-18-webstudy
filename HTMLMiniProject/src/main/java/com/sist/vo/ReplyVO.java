package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
CREATE TABLE reply(
    rno NUMBER,
    fno NUMBER,
    id VARCHAR2(20),
    name VARCHAR2(51) CONSTRAINT reply_name_nn NOT NULL,
    msg CLOB CONSTRAINT reply_msg_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    CONSTRAINT reply_rno_pk PRIMARY KEY(rno),
    CONSTRAINT reply_fno_fk FOREIGN KEY(fno)
    REFERENCES food_menupan(fno),
    CONSTRAINT reply_id_fk FOREIGN KEY(id)
    REFERENCES member(id)
);

CREATE SEQUENCE reply_rno_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE




RNO     NOT NULL NUMBER       
FNO              NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(51) 
MSG     NOT NULL CLOB         
REGDATE          DATE         


로그인 == id, name,sex(서버에 저장)
 		----------------------
 		| 모든 파일에서 사용이 가능(로그아웃 / 브라우저 종료시까지 사용)
 		  ------------------- 전역변수
 		  => 1. 사용자 정보
 		  => 2. 장바구니
 		  => 3. 경매
 		  => 저장기간 : 1600초(30분)
 		  => session :프로그램이 실행되는 동안
 		  => 각 클라이언트당 1개 배정
 		  		--------------- request
 		  	------- 브라우저에 대해서
 		  	
 		vo => 컬럼외의 변수를 설정
 		  		=> JDBC / MyBatics
 		  		=> 사용자 정의로 SQL문장을 전송
 		Entitu => 컬럼외의 변수는 설정이 불가능
 		  		=> JPA => SQL을 자동화
 		  	
 		  	
 */ 
@Data
public class ReplyVO {
	private int rno,fno;
	private String id,name,msg,dbday;
	private Date regdate;
	//심플데이트포멧은 날짜는 있는데 시간이 항상 12시기 떄문에 오라클로 받아서와서 dbday로 변환
}
