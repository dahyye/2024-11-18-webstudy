package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import com.sist.vo.*;
public class DetailModel  implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		//전송
		request.setAttribute("vo", vo);
		//setAttribute가 나오면 아래의 코드 detail에 값을 보낸다
		return "board/detail.jsp";
	}

}
