package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

@WebServlet(value={"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao=new UserDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		HttpSession session=request.getSession();
		
		switch(request.getServletPath()) {
		case "/user/login":
			request.setAttribute("pageName", "/user/login.jsp");
			dis.forward(request, response);
			break;
		case "/user/logout":
			session.invalidate();
			response.sendRedirect("/");
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		switch(request.getServletPath()) {
		case "/user/login":
			String uid=request.getParameter("uid");
			String upass=request.getParameter("upass");
			UserVO vo=dao.read(uid);
			int result=0; //아이디가 없는경우
			if(vo.getUid()!=null) {
				if(vo.getUpass().equals(upass)) {
					session.setAttribute("uid", uid);
					result=1; //로그인 성공
				}else {
					result=2; //비밀번호 불일치
				}
			}
			out.print(result);
		}
	}
}