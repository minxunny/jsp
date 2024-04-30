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

@WebServlet(value={"/user/login", "/user/logout", "/user/mypage", "/user/update", "/user/update/pass"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao=new UserDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		HttpSession session=request.getSession();
		
		switch(request.getServletPath()) {
		case "/user/mypage":
			String uid=(String)session.getAttribute("uid");
			request.setAttribute("user",dao.read(uid));
			request.setAttribute("pageName", "/user/mypage.jsp");
			dis.forward(request, response);
			break;
		case "/user/logout":
			session.invalidate();
			response.sendRedirect("/");
			break;
		case "/user/login":
			request.setAttribute("pageName", "/user/login.jsp");
			dis.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		switch(request.getServletPath()) {
		case "/user/update/pass":
			String uid1=request.getParameter("uid");
			String npass=request.getParameter("npass");
			dao.update(uid1, npass); //비밀번호변경
			break;
		case "/user/update":
			UserVO vo=new UserVO();
			String uid=request.getParameter("uid");
			vo.setUid(uid);
			vo.setUname(request.getParameter("uname"));
			vo.setPhone(request.getParameter("phone"));
			vo.setAddress1(request.getParameter("address1"));
			vo.setAddress2(request.getParameter("address2"));
			System.out.print(vo.toString());
			dao.update(vo); //데이터베이스에 업데이트
			break;
		case "/user/login":
			uid=request.getParameter("uid");
			String upass=request.getParameter("upass");
			System.out.println(uid + ".........." + upass);
			
			int result=0;
			vo=dao.read(uid);
			if(vo.getUid() != null) {
				if(vo.getUpass().equals(upass)) {
					HttpSession session=request.getSession();
					session.setAttribute("user", vo);
					session.setAttribute("uid", uid);
					result=1;
				}else {
					result=2;
				}
			}
			out.print(result);
			break;
		}
	}
}