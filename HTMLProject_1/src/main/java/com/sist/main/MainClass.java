package com.sist.main;
import java.util.*;

public class MainClass {
	
	
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		while(true)
		{
			System.out.println("==========menu=========");
			System.out.println("1. 목록");
			System.out.println("2. 상세");
			System.out.println("3. 등록");
			System.out.println("4. 수정");
			System.out.println("5. 삭제");
			System.out.println("6. 종료");
			System.out.println("=======================");
			System.out.print("메뉴선택 : ");
			int menu=scan.nextInt();
			if(menu==6)
			{
				System.out.println("프로그램종료");
				break;
			}
			else if(menu==1)
			{
				StudentDAO dao = StudentDAO.newInstance();
				List<StudentVO> list = dao.studentAllData();
				for(StudentVO vo : list)
				{
					System.out.println(vo.getHakbun()+" "+
									   vo.getName()+" "+
									   vo.getKor()+" "+
									   vo.getEng()+" "+
									   vo.getMath());
					
				}
			}
			else if(menu==2)
			{
				System.out.print("학번 입력 : ");
				int hak = scan.nextInt();
				
				StudentDAO dao = StudentDAO.newInstance();
				StudentVO vo = dao.studentDetailDate(hak);
				System.out.println("학번: "+vo.getHakbun()+" 이름: "+
						   vo.getName()+" 국어점수: "+
						   vo.getKor()+" 영어점수: "+
						   vo.getEng()+" 수학점수: "+
						   vo.getMath());
					
				
			}
			
			
			
		}
	}
		
	
	
	
	
	
	
}
