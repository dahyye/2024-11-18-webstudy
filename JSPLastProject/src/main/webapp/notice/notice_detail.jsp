<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table">
              <tr>
               <th class="text-center" width=20%>공지타입</th>
               <td class="text-center" width=30%>${vo.types }</td>
               <th class="text-center" width=20%>작성일</th>
               <td class="text-center" width=30%>${vo.dbday }</td>
              </tr>
              
              <tr>
               <th class="text-center" width=20%>이름</th>
               <td class="text-center" width=30%>${vo.name }</td>
               <th class="text-center" width=20%>조회수</th>
               <td class="text-center" width=30%>${vo.hit }</td>
              </tr>
              
              <tr>
               <th class="text-center" width=20%>제목</th>
               <td colspan="3">${vo.subject }</td>
              </tr>
              
              <tr>
               <td colspan="4" class="text-left" valign="top" height="200"><pre style="white-space: pre-wrap; border: none; background-color: white;">${vo.content }</pre></td>
              </tr>
              
              <tr>
               <td colspan="4" class="text-right">
               
                <a href="../board/board_update.do?no=${vo.no }&page=${page}" class="btn btn-outline-info btn-xs">수정</a>
                <!-- <a href="javascript:history.back()" class="btn btn-warning btn-xs">목록</a>  이렇게 주소값을 주면 조회수 증가된 값이 새로고침되지 않아-->
                <a href="../admin/notice_list.do?" class="btn btn-warning btn-xs">목록</a>
               </td>
              </tr>
             </table>
</body>
</html>