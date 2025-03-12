package com.sist.vo;
import java.util.*;

import lombok.Data;
@Data
public class BoardVO {
	private int no,hit,filesize;
	private String name,email,phone,type,type_detail,reserve_no,subject,content,filename,pwd,dbday;
	private Date regdate;
}
