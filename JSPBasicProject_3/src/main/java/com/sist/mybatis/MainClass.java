package com.sist.mybatis;
import java.util.*;

public class MainClass {
	public static void main(String[] args) {
		
		List<EmpVO> list=EmpDAO.empListData();
		for(EmpVO vo:list)
		{
			
		}
	}
}
