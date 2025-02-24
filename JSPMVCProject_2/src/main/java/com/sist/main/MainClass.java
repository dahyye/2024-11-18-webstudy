package com.sist.main;
import com.sist.model.*;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardModel bm= new BoardModel();
		Scanner scan = new Scanner(System.in);
		System.out.println("URL주소 입력: ");
		//list.do / detail.do ...
		String url=scan.next();
		
		if(url.equals("list.do"))
		{
			bm.boardListData();
		}
		else if(url.equals("detail.do"))
		{
			bm.boardDetailData();
		}
		else if(url.equals("insert.do"))
		{
			bm.boardDetailData();
		}
		else if(url.equals("delete.do"))
		{
			bm.boardDelete();
		}
		else if(url.equals("update.do"))
		{
			bm.boardUpdate();
		}
		
		
	}

}
