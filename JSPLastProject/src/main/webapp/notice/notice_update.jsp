<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width:800px; ">
            <form method="post" action="../board/board_update_ok.do">
             <table class="table">
              <tr>
               <th class="texet-center" width=15%>이름</th>
               <td width=85%>
                <input type=text name=name id=name size=20 class="form-control-sm"required value="${vo.name }">
                <input type="hidden" name="no" value="${vo.no }"> <!-- 사용자는 볼 수 없고 개발자만 확인가능하게 숨기기 -->
                <input type="hidden" name="page" value="${vo.page }">
               </td>
              </tr>
              
              <tr>
               <th class="texet-center" width=15%>제목</th>
               <td width=85%>
                <input type=text name=subject id=subject size=50 class="form-control-sm" required value="${vo.subject }">
               </td>
              </tr>
              
              <tr>
               <th class="texet-center" width=15%>내용</th>
               <td width=85%>
                <textarea rows="10" cols="52" name=content required>${vo.content }</textarea>
               </td>
              </tr>
              
              <tr>
               <th class="texet-center" width=15%>비밀번호</th>
               <td width=85%>
                <input type="password" name=pwd id=pwde size=20 class="form-control-sm" required>
               </td>
              </tr>
              
              <tr>
               <td colspan="2" class="text-center">
                <input type="submit" value="글쓰기" class="btn-outlint-primary btn-sm">
                <input type="button" value="취소" class="btn-outlint-danger btn-sm"
                 onclick="javascript:history.back()">
                >
               </td> 
              </tr>
              
             </table>
             </form>
        </div>
        
     </div>
     
    </section>
</body>
</html>