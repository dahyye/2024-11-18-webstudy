package com.sist.vo;
/*



 */

import java.util.*;

import lombok.Data;

@Data
public class FesVO {
	private int content_id,contenttype_id, sigungucode, areacode, mapx, mapy,content_id_1,content_id_2,img_id,content_id_3,info_id;
	private String title,addr1,addr2,zipcode, first_image,first_image2, origin_img, sponsor1, sponsor2, playtime,charge,spendtime; 
	private String thumb_img,tel,cat1,cat2,cat3, event_startdate, event_enddate,agelimit, infotext,infoname;
	private String infotext1,infotext2; //행사소개 1, 행사내용 2
}
